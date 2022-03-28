package udl.eps.manejoserviciokotlininc

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import udl.eps.manejoserviciokotlininc.actions.ButtonActionFactory
import udl.eps.manejoserviciokotlininc.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var audioReceiver: BroadcastReceiver
    private lateinit var actionFactory: ButtonActionFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.getButtons().forEach { button -> button.setOnClickListener(this) }
        setUp()
    }

    private fun setUp() {
        audioReceiver = AudioReceiver()
        actionFactory = ButtonActionFactory(binding, this)
        registerReceiver(audioReceiver, IntentFilter(Intent.ACTION_HEADSET_PLUG))
    }

    override fun onClick(src: View) = actionFactory.getAction(src.id).execute()

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(audioReceiver)
    }

}