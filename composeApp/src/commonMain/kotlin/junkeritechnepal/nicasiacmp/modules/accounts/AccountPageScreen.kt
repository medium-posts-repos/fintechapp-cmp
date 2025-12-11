package junkeritechnepal.nicasiacmp.modules.accounts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import junkeritechnepal.nicasiacmp.modules.accounts.views.AccountView
import junkeritechnepal.nicasiacmp.modules.cards.AppCardModule
import junkeritechnepal.nicasiacmp.modules.menus.CardNormalIconDescArrowView
import junkeritechnepal.nicasiacmp.modules.menus.MenuSingleGridView
import junkeritechnepal.nicasiacmp.modules.menus.MenuViews
import junkeritechnepal.nicasiacmp.modules.menus.menuMockData

@Composable
fun AccountPageScreen() {
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier.padding(
            WindowInsets.systemBars.only(
                WindowInsetsSides.Top + WindowInsetsSides.Horizontal
            ).asPaddingValues()
        ).fillMaxHeight().background(Color(0xfffafafa)),
        contentAlignment = Alignment.TopStart // 👈 important
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .fillMaxWidth()
                .padding(14.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            AccountView.AccountImageDescView()
            Spacer(modifier = Modifier.height(12.dp))
            AppCardModule.DashboardCardView()
            MenuSingleGridView(title = "", data = menuMockData)
            MenuViews.CardNormalIconDescArrowView()
            MenuViews.CardNormalIconDescArrowView()
        }
    }
}