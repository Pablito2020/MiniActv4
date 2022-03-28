package udl.eps.manejoserviciokotlininc.actions

import android.content.Context
import android.content.Intent
import udl.eps.manejoserviciokotlininc.AudioReceiver

class TrainAction(private val context: Context): Command {

    override fun execute() {
        val mediaIntent = Intent(context, AudioReceiver::class.java)
        mediaIntent.putExtra("type", "train")
        context.sendBroadcast(mediaIntent)
    }

}