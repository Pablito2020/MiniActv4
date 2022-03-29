package udl.eps.manejoserviciokotlininc.services

import android.content.Context
import android.content.Intent
import udl.eps.manejoserviciokotlininc.constants.CustomMusicExtras.MUSIC_URI
import udl.eps.manejoserviciokotlininc.constants.ServicesExtras.SERVICE_TYPE
import udl.eps.manejoserviciokotlininc.constants.ServicesExtras.TYPE

class ServiceFactory(context: Context) {

    private val trainIntent = Intent(context, TrainPlayer::class.java)
    private val musicIntent = Intent(context, MusicPlayer::class.java)
    private val defaultIntent = Intent(context, MusicPlayer::class.java)
    private val customIntent = Intent(context, CustomMusicPlayer::class.java)

    fun getService(intent: Intent): Intent =
        when (intent.getStringExtra(TYPE)) {
            SERVICE_TYPE.TRAIN_PLAYER.name -> trainIntent
            SERVICE_TYPE.MUSIC_PLAYER.name -> musicIntent
            SERVICE_TYPE.CUSTOM_MUSIC_PLAYER.name -> {
                customIntent.putExtra(MUSIC_URI, intent.getStringExtra(MUSIC_URI))
                customIntent
            }
            else -> defaultIntent
        }

    fun getServices(): Collection<Intent> = listOf(trainIntent, musicIntent, defaultIntent, customIntent)

}