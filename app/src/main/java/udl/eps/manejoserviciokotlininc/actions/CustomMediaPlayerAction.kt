package udl.eps.manejoserviciokotlininc.actions

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import udl.eps.manejoserviciokotlininc.AudioReceiver
import udl.eps.manejoserviciokotlininc.constants.CustomMusicExtras.MUSIC_URI
import udl.eps.manejoserviciokotlininc.constants.ServicesExtras.SERVICE_TYPE
import udl.eps.manejoserviciokotlininc.constants.ServicesExtras.TYPE

class CustomMediaPlayerAction : Command {

    object Initializer {
        internal var musicLauncher: ActivityResultLauncher<Intent>? = null

        fun setUp(activity: ComponentActivity) {
            musicLauncher =
                activity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                    if (it != null && it.data != null) {
                        val mediaIntent = Intent(activity, AudioReceiver::class.java)
                        mediaIntent.putExtra(TYPE, SERVICE_TYPE.CUSTOM_MUSIC_PLAYER.name)
                        val uri = it.data?.getStringExtra(MUSIC_URI).toString()
                        mediaIntent.putExtra(MUSIC_URI, uri)
                        activity.sendBroadcast(mediaIntent)
                    }
                }
        }
    }

    override fun execute() {
        if (Initializer.musicLauncher != null) {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "audio/*"
            Initializer.musicLauncher!!.launch(intent)
        } else {
            throw UnsupportedOperationException("Please, setup the action first!")
        }
    }

}