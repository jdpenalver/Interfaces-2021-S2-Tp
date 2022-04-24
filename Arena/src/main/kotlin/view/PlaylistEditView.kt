package view

import SpotifyService
import errors.PlayListError
import examples.extensions.widgets.control.TableExtensionsWindow
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import viewmodel.PlaylistViewModel
import viewmodel.SongViewModel
import viewmodel.UserViewModel


class PlaylistEditView(owner: WindowOwner, model: PlaylistViewModel,var userModel:UserViewModel,  var service: SpotifyService ): SimpleWindow<PlaylistViewModel>(owner, model){


    override fun createFormPanel(panelPlaylist: Panel?) {
        title = "Spotify - Playlist edit"
        setMinWidth(600)
        setMinHeight(400)

        Panel(panelPlaylist) with {
            asVertical()

            Panel(it) with {
                asHorizontal()
                Label(it) with {
                    width = 80
                    alignLeft()
                    text = "Name: "
                }
                TextBox(it) with {
                    width = 400
                    alignLeft()
                    bindTo("name")
                }
            }

            Panel(it) with {
                asHorizontal()
                Label(it) with {
                    width = 80
                    alignLeft()
                    text = "Description: "
                }
                TextBox(it) with {
                    width = 400
                    height = 100
                    alignLeft()
                    bindTo("description")
                }
            }

            Panel(it) with {
                asHorizontal()
                Label(it) with {
                    width = 80
                    alignLeft()
                    text = "Image: "
                }
                TextBox(it) with {
                    width = 400
                    alignLeft()
                    bindTo("image")
                }
            }


        }
        Panel(panelPlaylist) with {
            asHorizontal()

            GroupPanel(it) with {
                title = "All songs"
                List<TableExtensionsWindow.Item>(it) with {
                    width = 250
                    height = 300
                    bindItemsTo("todasLasCancionesFiltradas").adaptWithProp<SongViewModel>("nameandband")
                    bindSelectedTo("songToAdd")
                }
            }



            GroupPanel(it) with {
                title = ""
                Button(it) with {
                    width = 20
                    caption = ">"
                    onClick { thisWindow.modelObject.validarYAgregar()}
                }
                Button(it) with {
                    width = 20
                    caption = "<"
                    onClick { thisWindow.modelObject.validarYQuitar()}
                }
            }


            GroupPanel(it) with  {
                title = "Added songs"
                List<TableExtensionsWindow.Item>(it) with {
                    width = 250
                    height =300
                    bindItemsTo("songsDraft").adaptWithProp<SongViewModel>("nameandband")
                    bindSelectedTo("songToQuit")
                }
            }
        }



        Panel(panelPlaylist) with {
            asHorizontal()
            Button(it) with {
                width = 100
                caption = "Accept"
                onClick {
                    try {
                        if (thisWindow.modelObject.name != ""){
                            thisWindow.modelObject.aceptEditPlaylist(userModel, service)
                            showInfo("List Updated")
                            close()
                        } else
                            showWarning("Playlist name could not be empty.")
                    } catch (e: PlayListError) {
                        showError(e.message)
                    }

                }
            }
            Button(it) with {
                width = 100
                caption = "Cancel"
                onClick { close()}
            }
        }
    }

    override fun addActions(p0: Panel?) {

    }

}

