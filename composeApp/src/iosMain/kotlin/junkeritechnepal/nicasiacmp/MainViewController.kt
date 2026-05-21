package junkeritechnepal.nicasiacmp

import FormViewModel
import LoginScreen
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeUIViewController
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import junkeritechnepal.nicasiacmp.app.di.KoinInitializer
import junkeritechnepal.nicasiacmp.app.di.factory.NativeViewFactory
import junkeritechnepal.nicasiacmp.app.navigation.AppStackNavigator
import junkeritechnepal.nicasiacmp.app.navigation.AppStackNavigatorProvider
import junkeritechnepal.nicasiacmp.app.navigation.CameraRoute
import junkeritechnepal.nicasiacmp.app.navigation.DashboardRoute
import junkeritechnepal.nicasiacmp.app.navigation.LoginRoute
import junkeritechnepal.nicasiacmp.app.navigation.MenuRoute
import junkeritechnepal.nicasiacmp.app.navigation.Navigation3Host
import junkeritechnepal.nicasiacmp.app.navigation.PaymentsRoute
import junkeritechnepal.nicasiacmp.app.navigation.ProfileRoute
import junkeritechnepal.nicasiacmp.app.navigation.Route
import junkeritechnepal.nicasiacmp.app.navigation.TransfersRoute
import junkeritechnepal.nicasiacmp.modules.dashboard.HomeScreen1
import junkeritechnepal.nicasiacmp.modules.camera.CameraScreen
import junkeritechnepal.nicasiacmp.modules.sendmoney.SendMoneyContainerScreen
import junkeritechnepal.nicasiacmp.modules.profile.ProfileScreenModule.ProfileContainerScreen
import junkeritechnepal.nicasiacmp.modules.designSystem.LightColorScheme
import junkeritechnepal.nicasiacmp.modules.forms.DynamicFormScreen
import platform.UIKit.UIColor
import platform.UIKit.UIViewController

val LocalNativeViewProvider = staticCompositionLocalOf<NativeViewFactory> {
    error("NativeViewFactory not provided")
}

@Composable
private fun IOSAppContainer(initialRoute: Route) {
    val backStack: AppStackNavigator = remember { mutableStateListOf(initialRoute) }
    MaterialTheme(colorScheme = LightColorScheme) {
        CompositionLocalProvider(
            AppStackNavigatorProvider provides backStack
        ) {
            Scaffold(
                contentWindowInsets = WindowInsets(0, 0, 0, 0)
            ) { padding ->
                NavDisplay(
                    modifier = Modifier.padding(padding),
                    backStack = backStack,
                    onBack = { if (backStack.size > 1) backStack.removeLast() },
                    entryProvider = entryProvider {
                        entry<LoginRoute> {
                            LoginScreen()
                        }
                        entry<DashboardRoute> {
                            HomeScreen1(rememberScrollState())
                        }
                        entry<PaymentsRoute> {
                            Text("Payments Screen")
                        }
                        entry<CameraRoute> {
                            CameraScreen()
                        }
                        entry<TransfersRoute> {
                            SendMoneyContainerScreen()
                        }
                        entry<ProfileRoute> {
                            ProfileContainerScreen()
                        }
                        entry<MenuRoute> {
                            DynamicFormScreen(FormViewModel(), intent = null)
                        }
                    }
                )
            }
        }
    }
}

fun MainViewController(nativeViewFactory: NativeViewFactory): UIViewController = ComposeUIViewController {
    CompositionLocalProvider(
        LocalNativeViewProvider provides nativeViewFactory
    ) {
        Navigation3Host()
    }
}

object ViewControllerFactory {
    // Red color matching appColorPrimary (Color.Red)
    val primaryColor = UIColor.redColor

    fun initKoin() {
        KoinInitializer().init()
    }

    private fun createComposeViewController(initialRoute: Route): UIViewController {
        return ComposeUIViewController {
            IOSAppContainer(initialRoute)
        }.apply {
            view.backgroundColor = primaryColor
        }
    }

    fun HomeViewController(): UIViewController = createComposeViewController(DashboardRoute)

    fun PaymentsViewController(): UIViewController = createComposeViewController(PaymentsRoute)

    fun CameraViewController(): UIViewController = createComposeViewController(CameraRoute)

    fun TransfersViewController(): UIViewController = createComposeViewController(TransfersRoute)

    fun ProfileViewController(): UIViewController = createComposeViewController(ProfileRoute)
}
