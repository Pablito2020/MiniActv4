package udl.eps.manejoserviciokotlininc.actions

import android.content.Context
import android.content.Intent
import udl.eps.manejoserviciokotlininc.services.AudioReceiver
import udl.eps.manejoserviciokotlininc.constants.ServicesExtras

class FinishAction(private val context: Context): Command {

    private val mediaIntent = Intent(context, AudioReceiver::class.java)

    init {
        mediaIntent.putExtra(ServicesExtras.TYPE, ServicesExtras.SERVICE_TYPE.STOP_PLAYERS.name)
    }

    override fun execute() = context.sendBroadcast(mediaIntent)

}