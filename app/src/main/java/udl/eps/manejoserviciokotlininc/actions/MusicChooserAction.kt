package udl.eps.manejoserviciokotlininc.actions

import android.content.Intent
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import udl.eps.manejoserviciokotlininc.AudioReceiver
import udl.eps.manejoserviciokotlininc.constants.CustomMusicExtras
import udl.eps.manejoserviciokotlininc.constants.ServicesExtras.SERVICE_TYPE
import udl.eps.manejoserviciokotlininc.constants.ServicesExtras.TYPE

class MusicChooserAction : Command {

    object Initializer {
        internal var musicLauncher: ActivityResultLauncher<Intent>? = null

        fun setUp(activity: ComponentActivity) {
            musicLauncher =
                activity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                    if (it != null && it.data != null) {
                        val mediaIntent = Intent(activity, AudioReceiver::class.java)
                        mediaIntent.putExtra(TYPE, SERVICE_TYPE.CUSTOM_MUSIC_PLAYER.name)
                        val uri = it.data?.data.toString()
                        mediaIntent.putExtra(CustomMusicExtras.MUSIC_URI, uri)
                        activity.sendBroadcast(mediaIntent)
                    }
                }
        }
    }

    override fun execute() {
        if (Initializer.musicLauncher != null) {
            Initializer.musicLauncher!!.launch(
                Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                )
            )
        } else {
            throw UnsupportedOperationException("Please, setup the action first!")
        }
    }
}