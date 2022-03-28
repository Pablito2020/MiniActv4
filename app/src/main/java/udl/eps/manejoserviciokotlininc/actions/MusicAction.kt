package udl.eps.manejoserviciokotlininc.actions

import android.content.Context
import android.content.Intent
import udl.eps.manejoserviciokotlininc.AudioReceiver

class MusicAction(private val context: Context): Command {

    override fun execute() {
        val mediaIntent = Intent(context, AudioReceiver::class.java)
        mediaIntent.putExtra("type", "music")
        context.sendBroadcast(mediaIntent)
    }
}