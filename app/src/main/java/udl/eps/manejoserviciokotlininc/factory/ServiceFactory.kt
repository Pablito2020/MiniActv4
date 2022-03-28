package udl.eps.manejoserviciokotlininc.factory

import android.content.Context
import android.content.Intent
import udl.eps.manejoserviciokotlininc.services.CustomMusicPlayer
import udl.eps.manejoserviciokotlininc.services.MusicPlayer
import udl.eps.manejoserviciokotlininc.services.TrainPlayer

class ServiceFactory(context: Context) {

    private val trainIntent = Intent(context, TrainPlayer::class.java)
    private val musicIntent = Intent(context, MusicPlayer::class.java)
    private var musicCustom = Intent(context, CustomMusicPlayer::class.java)
    private val defaultIntent = Intent(context, MusicPlayer::class.java)

    fun getService(intent: Intent): Intent =
        when (intent.getStringExtra("type")) {
            "train" -> trainIntent
            "music" -> musicIntent
            "music-custom" -> {
                musicCustom = musicCustom.putExtra("music_uri", intent.getStringExtra("music_uri"))
                musicCustom
            }
            else -> defaultIntent
        }

    fun getServices(): Collection<Intent> =
        listOf(trainIntent, musicIntent, musicCustom, defaultIntent)

}