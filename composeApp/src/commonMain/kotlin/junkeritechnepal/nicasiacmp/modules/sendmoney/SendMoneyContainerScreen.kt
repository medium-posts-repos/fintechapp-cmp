package junkeritechnepal.nicasiacmp.modules.sendmoney

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import junkeritechnepal.nicasiacmp.app.navigation.AppStackNavigatorProvider
import junkeritechnepal.nicasiacmp.app.navigation.MenuRoute
import junkeritechnepal.nicasiacmp.modules.designSystem.backgroundColor
import junkeritechnepal.nicasiacmp.modules.menus.MenuViews

@Composable
fun SendMoneyContainerScreen() {
    val sendMoneyViewModel = remember { SendMoneyViewModel() }
    val menuApiRes by sendMoneyViewModel.menuApiResState.collectAsState()
    val navigator = AppStackNavigatorProvider.current

    LaunchedEffect(Unit) {
        sendMoneyViewModel.fetchSendMoneyMenus()
    }

    Scaffold(containerColor = backgroundColor) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 20.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    menuApiRes.forEach { item ->
                        MenuViews.NormalIconTitleDescCardView(item, modifier = Modifier.clickable {
//                            router.route(NavigationRoutes.MENU_ROUTE.toRoute(item.toJsonString()))
                            navigator.add(MenuRoute)
                        })
                    }
                }
            }
        }
    }
}