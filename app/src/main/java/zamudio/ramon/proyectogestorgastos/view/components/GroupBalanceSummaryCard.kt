package zamudio.ramon.proyectogestorgastos.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupBalanceText
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupBlueAccent
import androidx.compose.ui.res.stringResource
import zamudio.ramon.proyectogestorgastos.R
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupTextStyles
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun GroupBalanceSummaryCard(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Encabezado del Balance
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(GroupBlueAccent)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        style = GroupTextStyles.CaptionBold,
                        text = stringResource(R.string.group_balance_title),
                        color = GroupBalanceText
                    )
                }
            }

            // Las dos tarjetas usando BalanceStatCard
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                BalanceStatCard(
                    type = BalanceType.TE_DEBEN,
                    modifier = Modifier.weight(1f)
                )
                BalanceStatCard(
                    type = BalanceType.DEBES,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Preview
@Composable
fun GroupBalanceSummaryCardPreview(){
    GroupBalanceSummaryCard()
}