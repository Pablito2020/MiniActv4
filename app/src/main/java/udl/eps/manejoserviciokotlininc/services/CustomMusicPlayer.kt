package udl.eps.manejoserviciokotlininc.services

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.IBinder
import android.widget.Toast
import udl.eps.manejoserviciokotlininc.R
import udl.eps.manejoserviciokotlininc.constants.CustomMusicExtras.MUSIC_URI

class CustomMusicPlayer : Service() {

    private var choosePlayer: MediaPlayer? = null

    override fun onBind(p0: Intent?): IBinder = throw NotImplementedError("Unsupported")

    override fun onCreate() {
        super.onCreate()
        Toast.makeText(this, R.string.created_custom_music_service, Toast.LENGTH_LONG).show()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        Toast.makeText(this, R.string.init_custom_music_service, Toast.LENGTH_LONG).show()
        choosePlayer = MediaPlayer.create(
            this,
            Uri.parse(intent?.getStringExtra(MUSIC_URI).toString())
        )
        choosePlayer?.isLooping = true
        choosePlayer?.start()
        return startId
    }

    override fun onDestroy() {
        super.onDestroy()
        if (choosePlayer != null && choosePlayer?.isPlaying == true) {
            Toast.makeText(this, R.string.destroying_custom_player, Toast.LENGTH_SHORT).show()
            choosePlayer?.stop()
        }
    }

}