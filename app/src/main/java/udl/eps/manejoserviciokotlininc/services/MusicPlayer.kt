package udl.eps.manejoserviciokotlininc.services

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.widget.Toast
import udl.eps.manejoserviciokotlininc.R

class MusicPlayer : Service() {

    private lateinit var musicPlayer: MediaPlayer

    override fun onBind(p0: Intent?): IBinder = throw NotImplementedError("Unsupported")

    override fun onCreate() {
        super.onCreate()
        Toast.makeText(this, R.string.creaserv, Toast.LENGTH_LONG).show()
        musicPlayer = MediaPlayer.create(this, R.raw.asereje)
        musicPlayer.isLooping = true
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        Toast.makeText(this, R.string.iniserv, Toast.LENGTH_LONG).show()
        musicPlayer.start()
        return startId
    }

    override fun onDestroy() {
        super.onDestroy()
        if (musicPlayer.isPlaying) {
            musicPlayer.stop()
            Toast.makeText(this, R.string.finaserv, Toast.LENGTH_LONG).show()
        }
    }

}