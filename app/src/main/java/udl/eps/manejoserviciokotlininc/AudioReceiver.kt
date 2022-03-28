package udl.eps.manejoserviciokotlininc

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AudioReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        requireNotNull(context)
        requireNotNull(intent)
        val service = Intent(context, ElServicio::class.java)
        service.putExtra("type", intent.getStringExtra("type"))
        when (intent.getStringExtra("type")) {
            "train", "music" -> context.startService(service)
            "stop" -> context.stopService(service)
            else -> throw Error("Unsupported type")
        }
    }

}