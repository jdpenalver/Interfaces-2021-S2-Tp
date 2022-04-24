package view

import SpotifyService
import examples.extensions.widgets.control.TableExtensionsWindow
import org.github.unqui.PlaylistException
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import viewmodel.AddPlayListViewModel
import viewmodel.SongViewModel


class AddPlayListView (owner: WindowOwner, model : AddPlayListViewModel, var service : SpotifyService): SimpleWindow<AddPlayListViewModel>(owner, model){
    override fun createFormPanel(p0: Panel?) {
        title = "Spotify - Add PlayList"
        setMinWidth(600)
        setMinHeight(400)

        Panel(p0) with {
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
        Panel(p0) with {
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



        Panel(p0) with {
            asHorizontal()
            Button(it) with {
                width = 100
                caption = "Accept"
                onClick {
                    try {
                        if (!thisWindow.modelObject.songsDraft.isEmpty() &&
                            thisWindow.modelObject.name != ""
                        ) {
                            thisWindow.modelObject.acceptAdd(service)
                            showInfo("List Added")
                            close()
                        } else showInfo("Playlist without name or empty")
                    } catch (e: PlaylistException){
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