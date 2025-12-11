package junkeritechnepal.nicasiacmp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import junkeritechnepal.nicasiacmp.app.di.viewModelModule
import junkeritechnepal.nicasiacmp.app.navigation.Navigation3Host
import junkeritechnepal.nicasiacmp.modules.designSystem.LightColorScheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.compose.KoinContext

@Composable
@Preview
fun App() {
    KoinApplication(
       application = {
           modules(viewModelModule)
       }
    ) {
        MaterialTheme(
            colorScheme = LightColorScheme
        ) {
            KoinContext {
                Navigation3Host()
            }
        }
    }
}