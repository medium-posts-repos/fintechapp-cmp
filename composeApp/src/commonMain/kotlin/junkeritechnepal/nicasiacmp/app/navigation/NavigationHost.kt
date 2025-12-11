package junkeritechnepal.nicasiacmp.app.navigation

import FormViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.referentialEqualityPolicy
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import junkeritechnepal.nicasiacmp.app.router.Router
import junkeritechnepal.nicasiacmp.modules.designSystem.DesignSystemScreen
import junkeritechnepal.nicasiacmp.modules.forms.DynamicFormScreen
import junkeritechnepal.nicasiacmp.modules.login.LoginSecondaryScreen
import junkeritechnepal.nicasiacmp.modules.menus.MenuViews
import junkeritechnepal.nicasiacmp.modules.sms.SmsScreen

val LocalNavController = compositionLocalOf<NavHostController>(referentialEqualityPolicy()) {
    error("NavController not provided")
}

val LocalRouter = compositionLocalOf<Router>(policy = referentialEqualityPolicy()) {
    error("Router not provided")
}

@Composable
fun AppNavigationHost() {
    val navController = rememberNavController()
    val router = remember { Router(navController) }

    CompositionLocalProvider(
        LocalNavController provides navController,
        LocalRouter provides router
    ) {
        NavHost(
            navController = navController,
            startDestination = PrivateRouteIntent(code = NavigationRoutes.LOGIN_ROUTE.name),
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize(),
//            enterTransition = {
//                slideIntoContainer(
//                    AnimatedContentTransitionScope.SlideDirection.Left,
//                    animationSpec = tween(300)
//                )
//            },
//            exitTransition = {
//                slideOutOfContainer(
//                    AnimatedContentTransitionScope.SlideDirection.Left,
//                    animationSpec = tween(300)
//                )
//            },
//            popEnterTransition = {
//                slideIntoContainer(
//                    AnimatedContentTransitionScope.SlideDirection.Right,
//                    animationSpec = tween(300)
//                )
//            },
//            popExitTransition = {
//                slideOutOfContainer(
//                    AnimatedContentTransitionScope.SlideDirection.Right,
//                    animationSpec = tween(300)
//                )
//            }
        ) {
            setupNonProtectedRoutes(router)
        }
    }
}

fun NavGraphBuilder.setupNonProtectedRoutes(router: Router) {
    composable<PrivateRouteIntent> {
        val intent = it.toRoute<PrivateRouteIntent>()
        when(intent.code) {
            NavigationRoutes.LOGIN_ROUTE.name -> {
//                LoginScreen()
            }
            NavigationRoutes.LOGIN_SECONDARY_ROUTE.name -> {
                LoginSecondaryScreen()
            }
            NavigationRoutes.DASHBOARD_ROUTE.name -> {
//                DashboardContainerScreen(router)
            }
            NavigationRoutes.DESIGN_SYSTEM.name -> {
                DesignSystemScreen()
            }
            NavigationRoutes.SMS.name -> {
                SmsScreen()
            }
            NavigationRoutes.SUBMENUS_ROUTE.name -> {
                MenuViews.MenuCardListScreen(intent)
            }
            NavigationRoutes.MENU_ROUTE.name -> {
                DynamicFormScreen(FormViewModel(), intent)
            }
            else -> {
                Text("No routes configured")
            }
        }
    }
}


@Composable
fun SplashScreen() {
    Text("Splash Screen")
}