package junkeritechnepal.nicasiacmp.modules.menus

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import junkeritechnepal.nicasiacmp.app.navigation.AppStackNavigator
import junkeritechnepal.nicasiacmp.app.navigation.AppStackNavigatorProvider
import junkeritechnepal.nicasiacmp.app.navigation.MenuRoute
import junkeritechnepal.nicasiacmp.infrastructure.utils.deviceWidth
import junkeritechnepal.nicasiacmp.modules.designSystem.AppTextStyle
import junkeritechnepal.nicasiacmp.modules.designSystem.AppTypography

@Composable
fun MenuSingleGridView(title: String = "Top Services", data: MenuResDto) {
    val navigator = AppStackNavigatorProvider.current

    data.data?.let { HorizontalGridBox(navigator, title, items = data.data) }
}

@Composable
fun HorizontalGridBox(
    navigator: AppStackNavigator,
    title: String,
    items: List<MenuItemDto>,
    rowsPerColumn: Int = 4
) {
    val columns: List<List<MenuItemDto>> = remember(items, rowsPerColumn) {
        items.chunked(rowsPerColumn)
    }

    val eachRowWidth = ((deviceWidth().dp - 32.dp)/4.dp).dp

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        if(title.isNotBlank()) Text(title, style = AppTextStyle.boldDark())
        columns.forEach { rowItems ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                rowItems.forEach { item ->
                    Box(
                        modifier = Modifier
                            .height(eachRowWidth)
                            .weight(1f)
                            .background(Color.White, shape = RoundedCornerShape(8.dp))
                            .clickable {
                                navigator.add(MenuRoute)
//                                navigator?.route(NavigationRoutes.MENU_ROUTE.toRoute(item.toJsonString()))
                            },
                        contentAlignment = Alignment.Center
                    ) {
                       Column(
                           modifier = Modifier.padding(vertical = 2.dp),
                           horizontalAlignment = Alignment.CenterHorizontally,
                           verticalArrangement = Arrangement.spacedBy(2.dp, alignment = Alignment.Top)
                       ) {
                           KamelImage(
                               { asyncPainterResource(data = item.icon ?: "") },
                               modifier = Modifier.height(28.dp).width(28.dp),
                               contentDescription = "Profile"
                           )

                           Text(
                               text = item.name ?: "N/A",
                               color = Color.DarkGray,
                               textAlign = TextAlign.Center,
                               style = AppTypography.labelSmall
                           )
                       }
                    }
                }
            }
        }
    }
}