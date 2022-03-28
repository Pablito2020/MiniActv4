package udl.eps.manejoserviciokotlininc.actions

import android.content.Context
import androidx.activity.ComponentActivity
import udl.eps.manejoserviciokotlininc.databinding.ActivityMainBinding

class ButtonActionFactory(private val binding: ActivityMainBinding, activity: ComponentActivity) {

    private val context: Context = activity

    init {
        MusicChooserAction.Initializer.setUp(activity)
        CustomMediaPlayerAction.Initializer.setUp(activity)
    }

    fun getAction(buttonId: Int) = when (buttonId) {
        binding.trainButton.id -> TrainAction(context)
        binding.finishButton.id -> FinishAction(context)
        binding.musicButton.id -> MusicAction(context)
        binding.chooseMusicButton.id -> MusicChooserAction()
        binding.ownMusicButton.id -> CustomMediaPlayerAction()
        else -> throw Error("Unsupported button")
    }

}