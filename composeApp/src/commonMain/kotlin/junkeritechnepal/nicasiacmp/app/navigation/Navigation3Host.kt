package junkeritechnepal.nicasiacmp.app.navigation

import FormViewModel
import LoginScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.referentialEqualityPolicy
import androidx.compose.runtime.saveable.rememberSerializable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.compose.serialization.serializers.SnapshotStateListSerializer
import junkeritechnepal.nicasiacmp.modules.dashboard.DashboardContainerScreen
import junkeritechnepal.nicasiacmp.modules.forms.DynamicFormScreen

val AppStackNavigatorProvider = compositionLocalOf<AppStackNavigator>(referentialEqualityPolicy()) {
    error("NavController not provided")
}

@Composable
fun Navigation3Host() {
    val backStack: MutableList<Route> =
        rememberSerializable(serializer = SnapshotStateListSerializer()) {
            mutableStateListOf(LoginRoute)
        }

    CompositionLocalProvider(
        AppStackNavigatorProvider provides backStack
    ) {
        NavDisplay(
            backStack = backStack,
            onBack = { backStack.removeLastOrNull() },
            entryProvider = entryProvider {
                entry<LoginRoute> {
                    LoginScreen()
                }
                entry<DashboardRoute> {
                    DashboardContainerScreen()
                }
                entry<MenuRoute> {
                    DynamicFormScreen(FormViewModel(), intent = null)
                }
            }
        )
    }
}