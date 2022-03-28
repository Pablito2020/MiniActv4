package udl.eps.manejoserviciokotlininc

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mediaIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnInicio: Button = findViewById(R.id.btnIn)
        val btnFin: Button = findViewById(R.id.btnFin)
        btnInicio.setOnClickListener(this)
        btnFin.setOnClickListener(this)
        mediaIntent = Intent(this, ElServicio::class.java)
    }

    override fun onClick(src: View) {
        when (src.id) {
            R.id.btnIn -> startService(mediaIntent)
            R.id.btnFin -> stopService(mediaIntent)
        }
    }
}