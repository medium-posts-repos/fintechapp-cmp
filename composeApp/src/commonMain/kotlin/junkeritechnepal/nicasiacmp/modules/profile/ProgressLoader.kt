package junkeritechnepal.nicasiacmp.modules.profile

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/** Platform-adaptive progress loader API (expect). */
expect object ProgressLoader {
    @Composable
    fun Loader(modifier: Modifier)
}
