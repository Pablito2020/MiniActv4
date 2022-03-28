package udl.eps.manejoserviciokotlininc.services

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.widget.Toast
import udl.eps.manejoserviciokotlininc.R

class TrainPlayer : Service() {

    private lateinit var trainPlayer: MediaPlayer

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onCreate() {
        super.onCreate()
        Toast.makeText(this, R.string.creaserv, Toast.LENGTH_LONG).show()
        trainPlayer = MediaPlayer.create(this, R.raw.train)
        trainPlayer.isLooping = true
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        Toast.makeText(this, R.string.iniserv, Toast.LENGTH_LONG).show()
        trainPlayer.start()
        return startId
    }

    override fun onDestroy() {
        super.onDestroy()
        if (trainPlayer.isPlaying) {
            trainPlayer.stop()
            Toast.makeText(this, R.string.finaserv, Toast.LENGTH_LONG).show()
        }
    }
}