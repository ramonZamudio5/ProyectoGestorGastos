package zamudio.ramon.proyectogestorgastos.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupAccentBackground
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupSettlementAccent
import zamudio.ramon.proyectogestorgastos.R

@Composable
fun GroupChip(
    nombreGrupo: String,
    modifier: Modifier = Modifier
){
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        color = GroupAccentBackground
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_flight),
                contentDescription = null,
                tint = GroupSettlementAccent,
                modifier = Modifier.size(16.dp)
            )
            Text(
                text = nombreGrupo,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = GroupSettlementAccent
            )
        }
    }
}

@Preview
@Composable
fun GroupChipPreview(){
    GroupChip("Cancun 2025")
}