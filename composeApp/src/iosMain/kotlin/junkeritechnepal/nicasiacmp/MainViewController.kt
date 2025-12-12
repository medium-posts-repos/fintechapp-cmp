package junkeritechnepal.nicasiacmp

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeUIViewController
import junkeritechnepal.nicasiacmp.app.di.KoinInitializer
import junkeritechnepal.nicasiacmp.app.di.factory.NativeViewFactory
import junkeritechnepal.nicasiacmp.app.navigation.Navigation3Host

val LocalNativeViewProvider = staticCompositionLocalOf<NativeViewFactory> {
    error("NativeViewFactory not provided")
}

@OptIn(ExperimentalComposeUiApi::class)
fun MainViewController(nativeViewFactory: NativeViewFactory) = ComposeUIViewController(configure = {
    parallelRendering = true
    KoinInitializer().init()
}) {
    Navigation3Host()
}