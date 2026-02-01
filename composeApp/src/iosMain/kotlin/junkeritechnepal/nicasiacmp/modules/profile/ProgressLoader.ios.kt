package junkeritechnepal.nicasiacmp.modules.profile

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitInteropProperties
import androidx.compose.ui.viewinterop.UIKitView
import platform.UIKit.UIActivityIndicatorView
import platform.UIKit.UIColor

actual object ProgressLoader {
    @Composable
    @OptIn(ExperimentalComposeUiApi::class)
    actual fun Loader(modifier: Modifier) {
        UIKitView(
            factory = {
                UIActivityIndicatorView().apply {
                    backgroundColor = UIColor.clearColor()
                    startAnimating()
                }
            },
            update = {},
            onRelease = {
                it.stopAnimating()
                it.removeFromSuperview()
            },
            modifier = modifier,
            properties = UIKitInteropProperties(placedAsOverlay = true)
        )
    }
}