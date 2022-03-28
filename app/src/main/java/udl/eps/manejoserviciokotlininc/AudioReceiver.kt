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
        if (intent.action == "android.intent.action.HEADSET_PLUG" && intent.getIntExtra(
                "state",
                1
            ) == 1
        ) {
            service.putExtra("type", "music")
            context.startService(service)
        } else if (intent.action == "android.intent.action.HEADSET_PLUG" && intent.getIntExtra(
                "state",
                1
            ) != 1
        ) {
            context.stopService(service)
        } else {
            when (intent.getStringExtra("type")) {
                "train", "music" -> context.startService(service)
                "music-custom" -> {
                    service.putExtra("music_uri", intent.getStringExtra("music_uri"))
                    context.startService(service)
                }
                "stop" -> context.stopService(service)
                else -> throw Error("Unsupported type")
            }
        }
    }

}