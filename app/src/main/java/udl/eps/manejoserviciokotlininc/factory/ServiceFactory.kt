package udl.eps.manejoserviciokotlininc.factory

import android.content.Context
import android.content.Intent
import udl.eps.manejoserviciokotlininc.services.CustomMusicPlayer
import udl.eps.manejoserviciokotlininc.services.MusicPlayer
import udl.eps.manejoserviciokotlininc.services.TrainPlayer

class ServiceFactory(context: Context) {

    private val map = mutableMapOf(
        "train" to Intent(context, TrainPlayer::class.java),
        "music" to Intent(context, MusicPlayer::class.java),
        "music-custom" to Intent(context, CustomMusicPlayer::class.java),
        "default-intent" to Intent(context, MusicPlayer::class.java)
    )

    fun getService(intent: Intent): Intent =
        when (intent.getStringExtra("type")) {
            "train" -> map["train"]!!
            "music" -> map["music"]!!
            "music-custom" -> {
                map["music-custom"] =
                    map["music-custom"]!!.putExtra("music_uri", intent.getStringExtra("music_uri"))
                map["music-custom"]!!
            }
            else -> map["default-intent"]!!
        }

    fun getServices(): Collection<Intent> = map.map { (_, value) -> value }

}