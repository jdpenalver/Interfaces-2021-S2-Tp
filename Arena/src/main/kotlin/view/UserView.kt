package view

import SpotifyService
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.*
import viewmodel.*


class UserView(owner : WindowOwner, model :UserViewModel, var service: SpotifyService ) : SimpleWindow<UserViewModel>(owner, model){

    override fun createFormPanel(panelUsuario: Panel?) {
        title = "Spotify Premium"
        setMinWidth(600)
        setMinHeight(100)

        Panel(panelUsuario) with {
            asVertical()

            Panel(it) with {
                asHorizontal()
                Label(it) with {
                    alignLeft()
                    text = "Id: "
                }
                Label(it) with {
                    alignLeft()
                    bindTo("id")
                }
            }

            Panel(it) with {
                asHorizontal()

                Label(it) with {
                    alignLeft()
                    text = "Name: "
                }
                Label(it) with {
                    alignLeft()
                    bindTo("username")
                }
            }

            Panel(it) with {
                asHorizontal()

                Label(it) with {
                    alignLeft()
                    text = "Email: "
                }
                Label(it) with {
                    alignLeft()
                    bindTo("email")
                }
            }
        }

        Button(panelUsuario) with {
            width = 600
            caption = "Edit User"
            onClick { showEditUser()}
        }

        GroupPanel(panelUsuario) with {
            title = "Play Lists"
            asVertical()

            table<PlaylistViewModel>(it) {
                bindItemsToProperty("lists")
                bindSelectionTo("idListSeleccionada")
                visibleRows = 5
                column {
                    title = "Id"
                    fixedSize = 120
                    align("left")
                    bindContentsTo("id")
                }
                column {
                    title = "Name"
                    fixedSize = 120
                    align("left")
                    bindContentsTo("name")
                }
                column {
                    title = "Duration (min)"
                    fixedSize = 120
                    align("left")
                    bindContentsTo("duracion")
                }
                column {
                    title = "Ammount of Songs"
                    fixedSize = 120
                    align("left")
                    bindContentsTo("ammountOfSongs")
                }
                column {
                    title = "Image"
                    fixedSize = 120
                    align("left")
                    bindContentsTo("image")
                }
            }
        }

        Panel(panelUsuario) with {
            asHorizontal()
            Button(it) with {
                width = 100
                caption = "Add new Playlist"
                onClick { showAddPlaylist()}
            }
            Button(it) with {
                width = 100
                caption = "Edit Playlist"
                onClick {

                    showEditPlaylist()

                }
            }

            Button(it) with {
                width = 100
                caption = "Exit"
                onClick {

                    close()

                }
            }
        }
    }

    override fun addActions(p0: Panel?) {


    }

    fun showEditUser(){
        EditUserView(this, EditUserViewModel(modelObject, modelObject.usuario, service)).open()
    }
    fun showAddPlaylist(){

        AddPlayListView(this, AddPlayListViewModel(thisWindow.modelObject ,service), service).open()
    }
    fun showEditPlaylist(){

            if ( thisWindow.modelObject.idListSeleccionada != null) {
                PlaylistEditView(this, PlaylistViewModel(thisWindow.modelObject.idListSeleccionada!!.id,service), thisWindow.modelObject,service).open()
            }else
            {
                thisWindow.showInfo("Please select a playlist")
            }

    }

}



