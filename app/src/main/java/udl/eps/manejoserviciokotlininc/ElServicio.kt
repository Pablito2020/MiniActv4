package udl.eps.manejoserviciokotlininc

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.IBinder
import android.widget.Toast

class ElServicio : Service() {

    private lateinit var trainPlayer: MediaPlayer
    private lateinit var musicPlayer: MediaPlayer
    private var choosePlayer: MediaPlayer? = null

    override fun onBind(p0: Intent?): IBinder = throw NotImplementedError("Unsupported")

    override fun onCreate() {
        super.onCreate()
        Toast.makeText(this, R.string.creaserv, Toast.LENGTH_LONG).show()
        trainPlayer = MediaPlayer.create(this, R.raw.train)
        trainPlayer.isLooping = true
        musicPlayer = MediaPlayer.create(this, R.raw.asereje)
        musicPlayer.isLooping = true
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        Toast.makeText(this, R.string.iniserv, Toast.LENGTH_LONG).show()
        when (intent!!.getStringExtra("type")) {
            "train" -> trainPlayer.start()
            "music" -> musicPlayer.start()
            "music-custom" -> {
                choosePlayer = MediaPlayer.create(
                    this,
                    Uri.parse(intent.getStringExtra("music_uri").toString())
                )
                choosePlayer?.isLooping = true
                choosePlayer?.start()
            }
        }
        return startId
    }

    override fun onDestroy() {
        super.onDestroy()
        if (trainPlayer.isPlaying) {
            trainPlayer.stop()
            Toast.makeText(this, R.string.finaserv, Toast.LENGTH_LONG).show()
        }
        if (musicPlayer.isPlaying) {
            musicPlayer.stop()
            Toast.makeText(this, R.string.finaserv, Toast.LENGTH_LONG).show()
        }
        if (choosePlayer != null && choosePlayer?.isPlaying == true) {
            choosePlayer?.stop()
        }
    }

}