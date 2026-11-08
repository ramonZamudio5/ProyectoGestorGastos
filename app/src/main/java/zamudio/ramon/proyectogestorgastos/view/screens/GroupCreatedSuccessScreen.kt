package zamudio.ramon.proyectogestorgastos.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import zamudio.ramon.proyectogestorgastos.ui.theme.ScreenBackground
import zamudio.ramon.proyectogestorgastos.ui.theme.White
import androidx.compose.ui.res.stringResource
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupTextStyles
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import zamudio.ramon.proyectogestorgastos.R
import zamudio.ramon.proyectogestorgastos.model.data.grupo
import zamudio.ramon.proyectogestorgastos.model.data.usuarios
import zamudio.ramon.proyectogestorgastos.model.dominio.Grupo
import zamudio.ramon.proyectogestorgastos.model.dominio.Usuario
import zamudio.ramon.proyectogestorgastos.ui.theme.PurplePrimary
import zamudio.ramon.proyectogestorgastos.view.components.BotonPrincipal
import zamudio.ramon.proyectogestorgastos.view.components.GroupCreatedTopBar
import zamudio.ramon.proyectogestorgastos.view.components.GroupCreatedHeaderCard
import zamudio.ramon.proyectogestorgastos.view.components.GroupInviteCodeCard
import zamudio.ramon.proyectogestorgastos.view.components.GroupMemberListCard

@Composable
fun GroupCreatedSuccessScreen(
    group: Grupo,
    miembros: List<Usuario>,
    onClose: () -> Unit = {},
    onNavigateToDashboard: () -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize().background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 28.dp)
    ) {
        // 1. Barra superior: ¡Grupo Creado! con botón cerrar (X)
        item {
            GroupCreatedTopBar(onClose = onClose)
        }
        // 2. Tarjeta con resumen del grupo creado
        item {
            GroupCreatedHeaderCard(
                groupName = group.nombre,
                categoryTag = group.descripcion,
                creatorName = group.nombreCreador
            )
        }

        // 3. Tarjeta con el código de invitación único
        item {
            GroupInviteCodeCard(
                code = group.inviteCode
            )
        }

        // 4. Lista de miembros actuales y estado de espera
        item {
            GroupMemberListCard(
                members = miembros,
                inviteCode = group.inviteCode
            )
        }
        // 5. Botón principal: Ir al panel del grupo
        item {
            BotonPrincipal("Ir al Panel del Grupo", onNavigateToDashboard, R.drawable.ic_arrow_right)
        }
    }
}


@Preview
@Composable
fun GroupCreatedSuccessScreenPreview() {
    GroupCreatedSuccessScreen(grupo, usuarios)
}
