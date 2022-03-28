package udl.eps.manejoserviciokotlininc

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import udl.eps.manejoserviciokotlininc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mediaIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.finishButton.setOnClickListener(this)
        binding.musicButton.setOnClickListener(this)
        binding.trainButton.setOnClickListener(this)
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
        }
    }
}