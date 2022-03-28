package udl.eps.manejoserviciokotlininc

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import udl.eps.manejoserviciokotlininc.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mediaIntent: Intent
    private val musicLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            mediaIntent.putExtra("type", "music")
            val uri = it.data?.data.toString()
            mediaIntent.putExtra("music_uri", uri)
            sendBroadcast(mediaIntent)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.finishButton.setOnClickListener(this)
        binding.musicButton.setOnClickListener(this)
        binding.trainButton.setOnClickListener(this)
        binding.chooseMusicButton.setOnClickListener(this)
        registerReceiver(AudioReceiver(), IntentFilter(Intent.ACTION_HEADSET_PLUG))
        mediaIntent = Intent(this, AudioReceiver::class.java)
    }

    override fun onClick(src: View) {
        when (src.id) {
            binding.trainButton.id -> {
                mediaIntent.putExtra("type", "train")
                sendBroadcast(mediaIntent)
            }
            binding.finishButton.id -> {
                mediaIntent.putExtra("type", "stop")
                sendBroadcast(mediaIntent)
            }
            binding.musicButton.id -> {
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
}