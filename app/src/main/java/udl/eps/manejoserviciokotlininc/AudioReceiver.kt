package udl.eps.manejoserviciokotlininc

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import udl.eps.manejoserviciokotlininc.factory.ServiceFactory

class AudioReceiver : BroadcastReceiver() {

    object FactorySingleton {
        var factory: ServiceFactory? = null
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        requireNotNull(context)
        requireNotNull(intent)
        setUpFactory(context)
        execute(intent, context)
    }

    private fun setUpFactory(context: Context) {
        if (FactorySingleton.factory == null)
            FactorySingleton.factory = ServiceFactory(context)
    }

    private fun execute(intent: Intent, context: Context) {
        if (intent.getStringExtra("type") == "stop") {
            FactorySingleton.factory!!.getServices().forEach { s -> context.stopService(s) }
        } else {
            other(intent, context)
        }
    }

    private fun other(intent: Intent, context: Context) {
        val service = FactorySingleton.factory!!.getService(intent)
        print(service)
        if (headphonesAreOut(intent))
            context.stopService(service)
        else {
            Toast.makeText(context, "Currently doing shit", Toast.LENGTH_LONG).show()
            context.startService(service)
        }
    }

    private fun headphonesAreOut(intent: Intent): Boolean =
        intent.action == "android.intent.action.HEADSET_PLUG" && intent.getIntExtra(
            "state",
            1
        ) != 1

}