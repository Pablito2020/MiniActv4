package udl.eps.manejoserviciokotlininc.services

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.IBinder
import android.widget.Toast
import udl.eps.manejoserviciokotlininc.R

class CustomMusicPlayer : Service() {

    private var choosePlayer: MediaPlayer? = null

    override fun onBind(p0: Intent?): IBinder = throw NotImplementedError("Unsupported")

    override fun onCreate() {
        super.onCreate()
        Toast.makeText(this, R.string.creaserv, Toast.LENGTH_LONG).show()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        Toast.makeText(this, R.string.iniserv, Toast.LENGTH_LONG).show()
        choosePlayer = MediaPlayer.create(
            this,
            Uri.parse(intent?.getStringExtra("music_uri").toString())
        )
        choosePlayer?.isLooping = true
        choosePlayer?.start()
        return startId
    }

    override fun onDestroy() {
        super.onDestroy()
        if (choosePlayer != null && choosePlayer?.isPlaying == true)
            choosePlayer?.stop()
    }

}