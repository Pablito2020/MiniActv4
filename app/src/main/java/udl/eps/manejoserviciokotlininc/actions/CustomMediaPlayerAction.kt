package udl.eps.manejoserviciokotlininc.actions

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import udl.eps.manejoserviciokotlininc.AudioReceiver
import udl.eps.manejoserviciokotlininc.constants.CustomMusicExtras
import udl.eps.manejoserviciokotlininc.constants.ServicesExtras

class CustomMediaPlayerAction : Command {

    object Initializer {
        internal var musicLauncher: ActivityResultLauncher<Intent>? = null

        fun setUp(activity: ComponentActivity) {
            musicLauncher =
                activity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                    val mediaIntent = Intent(activity, AudioReceiver::class.java)
                    mediaIntent.putExtra(
                        ServicesExtras.TYPE,
                        ServicesExtras.SERVICE_TYPE.CUSTOM_MUSIC_PLAYER.name
                    )
                    val uri = it.data?.getStringExtra("music_uri").toString()
                    println(uri)
                    mediaIntent.putExtra(CustomMusicExtras.MUSIC_URI, uri)
                    activity.sendBroadcast(mediaIntent)
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