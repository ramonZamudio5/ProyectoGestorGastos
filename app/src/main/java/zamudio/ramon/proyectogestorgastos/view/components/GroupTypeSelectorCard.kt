package zamudio.ramon.proyectogestorgastos.view.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

// Modelo local para las opciones del selector de tipo
//No sé si puede ir aquí o se tiene que sacar a otra parte
data class TipoGrupoItem(
    val id: String,
    val title: String,
    val subtitle: String,
    val iconRes: Int,
    val color: Color
)

@Composable
fun GroupTypeSelectorCard(
    modifier: Modifier = Modifier,
    initialSelectedId: String = "amigos",
    onTipoSelected: (TipoGrupoItem) -> Unit = {}
) {
    val tiposDisponibles = remember {
        listOf(
            TipoGrupoItem(
                "amigos",
                "Amigos / Viajes",
                "Vacaciones y escapadas",
                R.drawable.ic_flight,
                Color(0xFF7E57C2)
            ),
            TipoGrupoItem(
                "pareja",
                "Pareja / Cuentas compartidas",
                "Metas y gastos mutuos",
                R.drawable.ic_heart,
                Color(0xFFBA68C8)
            ),
            TipoGrupoItem(
                "hogar",
                "Hogar / Familia",
                "Servicios, despensa y renta",
                R.drawable.ic_home,
                Color(0xFFFFB300)
            ),
            TipoGrupoItem(
                "roomies",
                "Roomies / Compañeros de piso",
                "Alquiler y servicios comunes",
                R.drawable.ic_meeting_room,
                Color(0xFF5C6BC0)
            ),
            TipoGrupoItem(
                "proyecto",
                "Proyecto / Trabajo",
                "Eventos y viáticos de equipo",
                R.drawable.ic_work,
                Color(0xFF42A5F5)
            ),
            TipoGrupoItem(
                "otro",
                "Otro / Personalizado",
                "Reglas y categorías libres",
                R.drawable.ic_tune,
                Color(0xFF78909C)
            )
        )
    }

    var selectedTipo by remember {
        mutableStateOf(tiposDisponibles.firstOrNull { it.id == initialSelectedId }
            ?: tiposDisponibles.first())
    }
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Título de la tarjeta y descripción
            Column {
                Text(
                    text = "Tipo de Grupo",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E2022)
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Define cómo se calculan las balanzas y sugerencias de gastos.",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    lineHeight = 16.sp
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .border(1.dp, Color(0xFFE8E5F2), RoundedCornerShape(16.dp))
                    .clickable { isExpanded = !isExpanded }
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(selectedTipo.color.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = selectedTipo.iconRes),
                        contentDescription = null,
                        tint = selectedTipo.color,
                        modifier = Modifier.size(22.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = selectedTipo.title,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E2022)
                    )
                    Text(
                        text = selectedTipo.subtitle,
                        fontSize = 11.sp,
                        color = Color.Gray
                    )
                }
                Icon(
                    painter = if (isExpanded) painterResource(R.drawable.ic_keyboard_up)
                    else painterResource(R.drawable.ic_keyboard_down),
                    contentDescription = null,
                    tint = Color.DarkGray
                )
            }
            AnimatedVisibility(visible = isExpanded) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFFFAFAFD))
                        .padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    tiposDisponibles.forEach { tipoItem ->
                        val isSelected = tipoItem.id == selectedTipo.id

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(if (isSelected) Color(0xFFEDE7F6) else Color.Transparent)
                                .clickable {
                                    selectedTipo = tipoItem
                                    onTipoSelected(tipoItem)
                                    isExpanded = false
                                }
                                .padding(horizontal = 10.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(tipoItem.color.copy(alpha = 0.2f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(id = tipoItem.iconRes),
                                    contentDescription = null,
                                    tint = tipoItem.color,
                                    modifier = Modifier.size(18.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = tipoItem.title,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF1E2022)
                                )
                                Text(
                                    text = tipoItem.subtitle,
                                    fontSize = 11.sp,
                                    color = Color.Gray
                                )
                            }

                            if (isSelected) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_check),
                                    contentDescription = "Seleccionado",
                                    tint = Color(0xFF673AB7),
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun GroupTypeSelectorCardPreview() {
    GroupTypeSelectorCard()
}