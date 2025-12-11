@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package junkeritechnepal.nicasiacmp.modules.designSystem.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import junkeritechnepal.nicasiacmp.app.navigation.AppStackNavigatorProvider
import junkeritechnepal.nicasiacmp.infrastructure.utils.isIos
import junkeritechnepal.nicasiacmp.modules.designSystem.appColorPrimary
import junkeritechnepal.nicasiacmp.modules.designSystem.backgroundColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NormalScaffold(
    title: String,
    onBack: (() -> Unit)? = null,
    scrollBehavior: TopAppBarScrollBehavior,
    content: @Composable (PaddingValues) -> Unit
) {
    val navigator = AppStackNavigatorProvider.current

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(title = { Text(title, color = MaterialTheme.colorScheme.onPrimary) }, navigationIcon = {
                    IconButton(onClick = {
                        onBack?.invoke() ?: navigator.removeLastOrNull()
                    }) {
                        Icon(if(isIos()) Icons.AutoMirrored.Outlined.KeyboardArrowLeft else Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.width(if(isIos()) 32.dp else 24.dp).height(26.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = appColorPrimary, scrolledContainerColor = appColorPrimary),
                scrollBehavior = scrollBehavior
            )
        },
        containerColor = backgroundColor
    ) { paddings ->
        content(paddings)
    }
}