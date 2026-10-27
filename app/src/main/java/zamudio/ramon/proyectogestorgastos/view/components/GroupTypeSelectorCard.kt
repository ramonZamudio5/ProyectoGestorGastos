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
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupAccentBackground
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupBorder
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupIconSecondary
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupOptionsBackground
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupTextPrimary
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupTextSecondary
import zamudio.ramon.proyectogestorgastos.ui.theme.PurplePrimary
import zamudio.ramon.proyectogestorgastos.ui.theme.White
import androidx.compose.ui.res.stringResource
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupTextStyles
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import zamudio.ramon.proyectogestorgastos.R
import zamudio.ramon.proyectogestorgastos.model.data.tiposDisponibles
import zamudio.ramon.proyectogestorgastos.model.dominio.TipoGrupo

@Composable
fun GroupTypeSelectorCard(
    modifier: Modifier = Modifier,
    initialSelectedId: String = "amigos",
    onTipoSelected: (TipoGrupo) -> Unit = {}
) {


    var selectedTipo by remember {
        mutableStateOf(tiposDisponibles.firstOrNull { it.id == initialSelectedId }
            ?: tiposDisponibles.first())
    }
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = White),
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
                    style = GroupTextStyles.CardTitleBold,
                    text = stringResource(R.string.group_type_label),
                    color = GroupTextPrimary
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    style = GroupTextStyles.LabelDefaultLine16,
                    text = stringResource(R.string.group_type_description),
                    color = GroupTextSecondary
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .border(1.dp, GroupBorder, RoundedCornerShape(16.dp))
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
                        style = GroupTextStyles.BodyBold,
                        text = selectedTipo.title,
                        color = GroupTextPrimary
                    )
                    Text(
                        style = GroupTextStyles.CaptionDefault,
                        text = selectedTipo.subtitle,
                        color = GroupTextSecondary
                    )
                }
                Icon(
                    painter = if (isExpanded) painterResource(R.drawable.ic_keyboard_up)
                    else painterResource(R.drawable.ic_keyboard_down),
                    contentDescription = null,
                    tint = GroupIconSecondary
                )
            }
            AnimatedVisibility(visible = isExpanded) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(GroupOptionsBackground)
                        .padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    tiposDisponibles.forEach { tipoItem ->
                        val isSelected = tipoItem.id == selectedTipo.id

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(if (isSelected) GroupAccentBackground else Color.Transparent)
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
                                    style = GroupTextStyles.SupportingBold,
                                    text = tipoItem.title,
                                    color = GroupTextPrimary
                                )
                                Text(
                                    style = GroupTextStyles.CaptionDefault,
                                    text = tipoItem.subtitle,
                                    color = GroupTextSecondary
                                )
                            }

                            if (isSelected) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_check),
                                    contentDescription = stringResource(R.string.group_selected),
                                    tint = PurplePrimary,
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