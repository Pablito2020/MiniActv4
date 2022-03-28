package udl.eps.manejoserviciokotlininc

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.widget.Toast

class ElServicio : Service() {

    private lateinit var player: MediaPlayer

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onCreate() {
        super.onCreate()
        Toast.makeText(this, R.string.creaserv, Toast.LENGTH_LONG).show()
        player = MediaPlayer.create(this, R.raw.train)
        player.isLooping = true
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        Toast.makeText(this, R.string.iniserv, Toast.LENGTH_LONG).show()
        player.start()
        return startId
    }

    override fun onDestroy() {
        super.onDestroy()
        if (player.isPlaying) {
            player.stop()
            Toast.makeText(this, R.string.finaserv, Toast.LENGTH_LONG).show()
        }
    }

}