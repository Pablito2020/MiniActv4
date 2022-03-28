package udl.eps.manejoserviciokotlininc.actions

import android.content.Context
import android.content.Intent
import udl.eps.manejoserviciokotlininc.AudioReceiver
import udl.eps.manejoserviciokotlininc.constants.ServicesExtras

class TrainAction(private val context: Context) : Command {

    override fun execute() {
        val mediaIntent = Intent(context, AudioReceiver::class.java)
        mediaIntent.putExtra(ServicesExtras.TYPE, ServicesExtras.SERVICE_TYPE.TRAIN_PLAYER.name)
        context.sendBroadcast(mediaIntent)
    }

}