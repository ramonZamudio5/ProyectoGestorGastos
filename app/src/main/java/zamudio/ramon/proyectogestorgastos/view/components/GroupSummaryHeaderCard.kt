package zamudio.ramon.proyectogestorgastos.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zamudio.ramon.proyectogestorgastos.R
import zamudio.ramon.proyectogestorgastos.model.dominio.TipoGrupo
import zamudio.ramon.proyectogestorgastos.ui.theme.PurplePrimary

@Composable
fun GroupSummaryHeaderCard(
    typeGroup: TipoGrupo,
    modifier: Modifier = Modifier,
    groupName: String = "Viaje Cancún 2025",
    onEditClick: () -> Unit = {}
){
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icono fijo con su fondo morado suave
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color(0xFFEDE7F6)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = typeGroup.iconRes),
                    contentDescription = null,
                    tint = PurplePrimary,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = groupName,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E2022)
                )
                Text(
                    text = typeGroup.title,
                    fontSize = 12.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium
                )
            }
            // Botón de lápiz
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF3F1FA))
                    .clickable { onEditClick() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_edit),
                    contentDescription = "Editar",
                    tint = PurplePrimary,
                    modifier = Modifier.size(16.dp)
                )
            }

        }
    }
}

@Preview
@Composable
fun GroupSummaryHeaderCardPreview(){
    GroupSummaryHeaderCard(TipoGrupo(
        "amigos",
        "Amigos / Viajes",
        "Vacaciones y escapadas",
        R.drawable.ic_flight,
        Color(0xFF7E57C2)
    ))
}