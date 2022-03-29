package udl.eps.manejoserviciokotlininc.actions

import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_VIEW

class CustomMusicServiceOpenerAction(private val context: Context): Command {

    override fun execute() {
        val intent = Intent(ACTION_VIEW)
        context.startActivity(intent)
    }

}