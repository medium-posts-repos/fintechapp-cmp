package junkeritechnepal.nicasiacmp.modules.profile

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

actual object ProgressLoader {
    @Composable
    actual fun Loader(modifier: Modifier) {
        CircularProgressIndicator(modifier = modifier)
    }
}
