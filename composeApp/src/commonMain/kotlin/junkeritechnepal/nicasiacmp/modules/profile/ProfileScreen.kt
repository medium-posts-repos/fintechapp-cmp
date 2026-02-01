package junkeritechnepal.nicasiacmp.modules.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import junkeritechnepal.nicasiacmp.modules.designSystem.AppTextStyle
import junkeritechnepal.nicasiacmp.modules.designSystem.AppTextStyle.large24
import junkeritechnepal.nicasiacmp.modules.designSystem.appColorPrimary
import junkeritechnepal.nicasiacmp.modules.designSystem.backgroundColor
import nicasia_cmp.composeapp.generated.resources.Res
import nicasia_cmp.composeapp.generated.resources.bank_logo
import org.jetbrains.compose.resources.painterResource

object ProfileScreenModule {
    @Composable
    fun ProfileContainerScreen() {
        Scaffold(
            containerColor = backgroundColor,
            topBar = {

            }
        ) { padding ->
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(18.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(vertical = 0.dp, horizontal = 24.dp),
            ) {
                item { ItemProfileImage() }
                item { ItemFonePayRewardView() }
                item { ItemAdaptiveLoader() }
            }
        }
    }

    @Composable
    private fun ItemProfileImage() {
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.CenterHorizontally)) {
            Image(
                painter = painterResource(Res.drawable.bank_logo), // put your image in resources
                contentDescription = "Sample Image",
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
                    .background(appColorPrimary),
                contentScale = ContentScale.Crop,
            )
            Column {
                Text("Swornim Bikram Shah", style = large24())
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    Icon(imageVector = Icons.Outlined.Phone, contentDescription = "",
                        modifier = Modifier.size(16.dp)
                    )
                    Text("9813334343", style = AppTextStyle.textNormal15Dim())
                }
            }
        }
    }

    @Composable
    private fun ItemFonePayRewardView() {
        Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color.White),) {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.CenterHorizontally), modifier = Modifier.padding(14.dp)) {
                Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    Image(
                        painter = painterResource(Res.drawable.bank_logo), // put your image in resources
                        contentDescription = "Sample Image",
                        modifier = Modifier
                            .size(42.dp)
                            .clip(CircleShape)
                            .background(Color.Yellow),
                        contentScale = ContentScale.Crop,
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text("Fonepay Reward Points", style = AppTextStyle.textNormalDim())
                    Text("1951.00 points", style = AppTextStyle.boldNormalDark(fontSize = 18.sp))
                }
                Spacer(modifier = Modifier.weight(1f))
                Icon(imageVector = Icons.Outlined.KeyboardArrowRight, contentDescription = "",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }

    @Composable
    private fun ItemAdaptiveLoader() {
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(14.dp).fillMaxWidth()) {
            ProgressLoader.Loader(Modifier.size(24.dp))
        }
    }
}