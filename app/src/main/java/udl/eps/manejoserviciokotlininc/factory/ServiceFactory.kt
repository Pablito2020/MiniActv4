package udl.eps.manejoserviciokotlininc.factory

import android.content.Context
import android.content.Intent
import udl.eps.manejoserviciokotlininc.constants.CustomMusicExtras.MUSIC_URI
import udl.eps.manejoserviciokotlininc.constants.ServicesExtras.SERVICE_TYPE
import udl.eps.manejoserviciokotlininc.constants.ServicesExtras.TYPE
import udl.eps.manejoserviciokotlininc.services.CustomMusicPlayer
import udl.eps.manejoserviciokotlininc.services.MusicPlayer
import udl.eps.manejoserviciokotlininc.services.TrainPlayer

class ServiceFactory(context: Context) {

    private val trainIntent = Intent(context, TrainPlayer::class.java)
    private val musicIntent = Intent(context, MusicPlayer::class.java)
    private var musicCustom = Intent(context, CustomMusicPlayer::class.java)
    private val defaultIntent = Intent(context, MusicPlayer::class.java)

    fun getService(intent: Intent): Intent =
        when (intent.getStringExtra(TYPE)) {
            SERVICE_TYPE.TRAIN_PLAYER.name -> trainIntent
            SERVICE_TYPE.MUSIC_PLAYER.name -> musicIntent
            SERVICE_TYPE.CUSTOM_MUSIC_PLAYER.name -> {
                musicCustom = musicCustom.putExtra(MUSIC_URI, intent.getStringExtra(MUSIC_URI))
                musicCustom
            }
            else -> defaultIntent
        }

    fun getServices(): Collection<Intent> =
        listOf(trainIntent, musicIntent, musicCustom, defaultIntent)

}