package udl.eps.manejoserviciokotlininc

import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.children
import udl.eps.manejoserviciokotlininc.databinding.ActivityMainBinding

/**
 * Gets all the buttons recursively from a ViewGroup
 */
fun ActivityMainBinding.getButtons(): List<Button> {
    return getButtonsRecursive(this.root)
}

internal fun getButtonsRecursive(viewGroup: ViewGroup): List<Button> {
    val result = ArrayList<Button>()
    for (view in viewGroup.children) {
        if (view is ViewGroup)
            result.addAll(getButtonsRecursive(view))
        else if (view is Button)
            result.add(view)
    }
    return result
}
