package udl.eps.manejoserviciokotlininc

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity
import udl.eps.manejoserviciokotlininc.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var audioReceiver: BroadcastReceiver
    private val musicLauncher = registerForActivityResult(StartActivityForResult()) {
        val mediaIntent = Intent(this, AudioReceiver::class.java)
        mediaIntent.putExtra("type", "music-custom")
        val uri = it.data?.data.toString()
        mediaIntent.putExtra("music_uri", uri)
        sendBroadcast(mediaIntent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.getButtons().forEach { button -> button.setOnClickListener(this) }
        setUp()
    }

    private fun setUp() {
        audioReceiver = AudioReceiver()
        registerReceiver(audioReceiver, IntentFilter(Intent.ACTION_HEADSET_PLUG))
    }

    override fun onClick(src: View) {
        when (src.id) {
            binding.trainButton.id -> {
                val mediaIntent = Intent(this, AudioReceiver::class.java)
                mediaIntent.putExtra("type", "train")
                sendBroadcast(mediaIntent)
            }
            binding.finishButton.id -> {
                val mediaIntent = Intent(this, AudioReceiver::class.java)
                mediaIntent.putExtra("type", "stop")
                sendBroadcast(mediaIntent)
            }
            binding.musicButton.id -> {
                val mediaIntent = Intent(this, AudioReceiver::class.java)
                mediaIntent.putExtra("type", "music")
                sendBroadcast(mediaIntent)
            }
            binding.chooseMusicButton.id -> {
                musicLauncher.launch(
                    Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                    )
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(audioReceiver)
    }

}