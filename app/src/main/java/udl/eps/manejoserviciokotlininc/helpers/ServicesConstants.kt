package udl.eps.manejoserviciokotlininc.constants

/**
 * This file contains all the Intent Extra constants for the different
 * types of services
 */
object ServicesExtras {
    val TYPE = "type"

    enum class SERVICE_TYPE {
        MUSIC_PLAYER,
        TRAIN_PLAYER,
        CUSTOM_MUSIC_PLAYER,
        STOP_PLAYERS
    }
}

object CustomMusicExtras {
    val MUSIC_URI = "music_uri"
}
