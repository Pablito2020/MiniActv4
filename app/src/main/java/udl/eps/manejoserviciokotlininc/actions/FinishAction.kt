package udl.eps.manejoserviciokotlininc.actions

import android.content.Context
import android.content.Intent
import udl.eps.manejoserviciokotlininc.AudioReceiver

class FinishAction(private val context: Context): Command {

    private val mediaIntent = Intent(context, AudioReceiver::class.java)

    init {
        mediaIntent.putExtra("type", "stop")
    }

    override fun execute() = context.sendBroadcast(mediaIntent)

}