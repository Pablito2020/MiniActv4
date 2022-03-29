package udl.eps.manejoserviciokotlininc.actions

import android.content.Context
import androidx.activity.ComponentActivity
import udl.eps.manejoserviciokotlininc.databinding.ActivityMainBinding

class ActionFactory(private val binding: ActivityMainBinding, activity: ComponentActivity) {

    private val context: Context = activity

    init {
        MusicChooserAction.Initializer.setUp(activity)
        CustomMusicChooserAction.Initializer.setUp(activity)
    }

    fun getAction(buttonId: Int) = when (buttonId) {
        binding.trainButton.id -> TrainAction(context)
        binding.finishButton.id -> FinishAction(context)
        binding.musicButton.id -> MusicAction(context)
        binding.chooseMusicButton.id -> MusicChooserAction()
        binding.ownMusicButton.id -> CustomMusicChooserAction()
        else -> throw Error("Unsupported button")
    }

}