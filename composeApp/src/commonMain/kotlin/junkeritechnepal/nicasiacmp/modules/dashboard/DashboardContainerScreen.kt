package junkeritechnepal.nicasiacmp.modules.dashboard

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import junkeritechnepal.nicasiacmp.modules.accounts.AccountPageScreen
import junkeritechnepal.nicasiacmp.modules.camera.CameraScreen
import junkeritechnepal.nicasiacmp.modules.designSystem.appColorPrimary
import junkeritechnepal.nicasiacmp.modules.profile.ProfileScreenModule.ProfileContainerScreen
import junkeritechnepal.nicasiacmp.modules.sendmoney.SendMoneyContainerScreen
import kotlinx.coroutines.launch

@Composable
fun DashboardContainerScreen() {

    val pagerState = rememberPagerState(
        initialPage = 1, // Start with middle screen
        pageCount = { 3 }
    )
    val scope = rememberCoroutineScope()
    val saveableStateHolder = rememberSaveableStateHolder()

    HorizontalPager(
        state = pagerState,
        pageSize = PageSize.Fill,
        modifier = Modifier.fillMaxSize(),
        userScrollEnabled = true
    ) { page ->
        saveableStateHolder.SaveableStateProvider(key = page) {
            when (page) {
                0 -> AccountPageScreen()
                1 -> DashboardScreen(onQRScanClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(2)
                    }
                })

                2 -> CameraScreen()
                else -> Text("Unknown page")
            }
        }
    }
}

@Composable
private fun DashboardScreen(onQRScanClick: () -> Unit) {
    val items = listOf("Home", "Payments", "", "Transfers", "Profile")
    var selectedIndex by rememberSaveable { mutableStateOf(0) }
    val saveableStateHolder = rememberSaveableStateHolder()
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Scaffold(
            containerColor = Color(0xfffafafa),
            bottomBar = {
                NavigationBar(containerColor = Color.White) {
                    items.forEachIndexed { index, label ->
                        val isSelected = selectedIndex == index
                        NavigationBarItem(
                            colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent),
                            selected = isSelected && index != 2,
                            onClick = {
                                if (index != 2) {
                                    selectedIndex = index
                                }
                            },
                            icon = {
                                when (index) {
                                    0 -> Icon(Icons.Outlined.Home, contentDescription = label)
                                    1 -> Icon(
                                        Icons.Outlined.ShoppingCart,
                                        contentDescription = label
                                    )

                                    2 -> QRScanNavigationBarItem(onClick = onQRScanClick)
                                    3 -> Icon(Icons.Outlined.Send, contentDescription = label)
                                    4 -> Icon(Icons.Outlined.Person, contentDescription = label)
                                }
                            },
                            label = { if (index != 2) Text(label, fontSize = 12.sp) }
                        )
                    }
                }
            }
        ) { innerPadding ->
            Crossfade(
                targetState = selectedIndex,
                animationSpec = tween(durationMillis = 500), // Customize animation duration (e.g., 1 second)
                modifier = Modifier.padding(innerPadding) // Fills remaining vertical space
            ) { targetIndex ->
                saveableStateHolder.SaveableStateProvider(key = targetIndex) {
                    when (targetIndex) {
                        0 -> HomeScreen1(scrollState)
                        1 -> Text("Current tab $targetIndex")
                        3 -> SendMoneyContainerScreen()
                        4 -> ProfileContainerScreen()
                        else -> Text("Current tab $targetIndex")
                    }
                }
            }
        }
    }
}

@Composable
private fun QRScanNavigationBarItem(onClick: () -> Unit) {
    FloatingActionButton(
        containerColor = appColorPrimary,
        contentColor = Color.White,
        onClick = onClick,
        shape = CircleShape,
        modifier = Modifier.size(56.dp) // Adjust size as needed
    ) {
        Icon(
            Icons.Filled.PlayArrow,
            contentDescription = "Scan QR"
        )
    }
}
