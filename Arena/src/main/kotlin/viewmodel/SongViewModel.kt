package viewmodel


import org.uqbar.commons.model.annotations.Observable

@Observable
class SongViewModel(val id: String,
                    val name: String,
                    val band: String,
                    val url: String,
                    val duration: Int){
    var nameandband : String = name + " - " + band
}
