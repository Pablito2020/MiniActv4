package udl.eps.manejoserviciokotlininc

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import udl.eps.manejoserviciokotlininc.constants.ServicesExtras
import udl.eps.manejoserviciokotlininc.factory.ServiceFactory

class AudioReceiver : BroadcastReceiver() {

    object FactorySingleton {
        var factory: ServiceFactory? = null
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null && intent != null) {
            setUpFactorySingleton(context)
            execute(intent, context)
        }
    }

    private fun setUpFactorySingleton(context: Context) {
        if (FactorySingleton.factory == null)
            FactorySingleton.factory = ServiceFactory(context)
    }

    private fun execute(intent: Intent, context: Context) {
        if (intent.getStringExtra(ServicesExtras.TYPE) == ServicesExtras.SERVICE_TYPE.STOP_PLAYERS.name)
            FactorySingleton.factory!!.getServices().forEach { s -> context.stopService(s) }
        else
            executeNonStopService(intent, context)
    }

    private fun executeNonStopService(intent: Intent, context: Context) {
        val service = FactorySingleton.factory!!.getService(intent)
        if (headphonesAreOut(intent))
            context.stopService(service)
        else
            context.startService(service)
    }

    private fun headphonesAreOut(intent: Intent): Boolean =
        intent.action == "android.intent.action.HEADSET_PLUG" && intent.getIntExtra(
            "state",
            1
        ) != 1

}