package zamudio.ramon.proyectogestorgastos.view.screens

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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zamudio.ramon.proyectogestorgastos.R
import zamudio.ramon.proyectogestorgastos.model.data.grupo
import zamudio.ramon.proyectogestorgastos.model.data.usuarios
import zamudio.ramon.proyectogestorgastos.model.dominio.Grupo
import zamudio.ramon.proyectogestorgastos.model.dominio.Usuario
import zamudio.ramon.proyectogestorgastos.ui.theme.PurplePrimary
import zamudio.ramon.proyectogestorgastos.view.components.GroupCreatedTopBar
import zamudio.ramon.proyectogestorgastos.view.components.GroupCreatedHeaderCard
import zamudio.ramon.proyectogestorgastos.view.components.GroupInviteCodeCard
import zamudio.ramon.proyectogestorgastos.view.components.GroupMemberListCard
import zamudio.ramon.proyectogestorgastos.view.components.Header
import zamudio.ramon.proyectogestorgastos.view.components.NavBar

@Composable
fun GroupCreatedSuccessScreen(
    group: Grupo,
    miembros: List<Usuario>,
    onClose: () -> Unit = {},
    onNavigateToDashboard: () -> Unit = {}
) {
    Scaffold(
        topBar = { Header(PaddingValues(15.dp), {}) },
        bottomBar = { NavBar() },
        containerColor = Color(0xFFF3F1F9)
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp),
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
                Button(
                    onClick = onNavigateToDashboard,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .padding(top = 4.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PurplePrimary)
                ) {
                    Text(
                        text = "Ir al Panel del Grupo",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        painter = painterResource(R.drawable.ic_arrow_right),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun GroupCreatedSuccessScreenPreview() {
    GroupCreatedSuccessScreen(grupo, usuarios)
}