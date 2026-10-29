package zamudio.ramon.proyectogestorgastos.view.screens

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupBadgeBackground
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupBadgeText
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupBlueAccent
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupJoinAccent
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupSectionText
import zamudio.ramon.proyectogestorgastos.ui.theme.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.pluralStringResource
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupTextStyles
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import zamudio.ramon.proyectogestorgastos.R
import zamudio.ramon.proyectogestorgastos.model.data.grupos
import zamudio.ramon.proyectogestorgastos.ui.theme.BlueNavy
import zamudio.ramon.proyectogestorgastos.ui.theme.ScreenBackground
import zamudio.ramon.proyectogestorgastos.view.components.GroupBalanceSummaryCard
import zamudio.ramon.proyectogestorgastos.view.components.GroupCardItem

@Composable
fun MyGroupScreen(
    onNavigateToDetail: (Int) -> Unit = {},
    onCreateGroupClick: () -> Unit = {},
    onJoinGroupClick: () -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize().background(color= ScreenBackground)
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 24.dp)
    ) {
        // 1. TÍTULO Y BADGE DE GRUPOS ACTIVOS
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        style = GroupTextStyles.ScreenTitleBold,
                        text = stringResource(R.string.groups_title),
                        color = BlueNavy
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Surface(
                        shape = RoundedCornerShape(50),
                        color = GroupBadgeBackground
                    ) {
                        Text(
                            style = GroupTextStyles.LabelSemiBold,
                            text = pluralStringResource(R.plurals.groups_active_count, grupos.size, grupos.size),
                            color = GroupBadgeText,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                        )
                    }
                }
            }
        }
        // 2. BOTONES DE ACCIÓN
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Botón Principal: Crear un Grupo
                Button(
                    onClick = onCreateGroupClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GroupBlueAccent
                    )
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_add_person),
                        contentDescription = null,
                        tint = White,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        style = GroupTextStyles.ActionBold,
                        text = stringResource(R.string.groups_create),
                        color = White
                    )
                }

                // Botón Secundario: Unirse a un Grupo con Código
                Button(
                    onClick = onJoinGroupClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = White
                    ),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 1.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_key),
                        contentDescription = null,
                        tint = GroupJoinAccent,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        style = GroupTextStyles.ActionBold,
                        text = stringResource(R.string.groups_join_with_code),
                        color = GroupJoinAccent
                    )
                }
            }
        }
        // 3. TARJETA DE BALANCE TOTAL GRUPAL
        item {
            GroupBalanceSummaryCard()
        }
        // 4. HEADER DE LA SECCIÓN: GRUPOS EN CURSO
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    style = GroupTextStyles.LabelBold,
                    text = stringResource(R.string.groups_in_progress),
                    color = GroupSectionText
                )
            }
        }
        // 5. LISTA DE GRUPOS
        items(grupos) { grupo ->
            GroupCardItem(
                grupo = grupo,
                onClick = { onNavigateToDetail(grupo.id) }
            )
        }
    }
}


@Preview
@Composable
fun MyGroupScreenPreview() {
    MyGroupScreen()
}