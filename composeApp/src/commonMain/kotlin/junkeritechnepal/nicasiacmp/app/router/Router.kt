package junkeritechnepal.nicasiacmp.app.router

import androidx.compose.runtime.Immutable
import androidx.navigation.NavHostController
import junkeritechnepal.nicasiacmp.app.navigation.PrivateRouteIntent

@Immutable
class Router(private val navigator: NavHostController? = null) {

    fun route(intent: PrivateRouteIntent) {
        navigator?.navigate(intent)
    }
}