package zamudio.ramon.proyectogestorgastos.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import zamudio.ramon.proyectogestorgastos.R
import zamudio.ramon.proyectogestorgastos.model.data.grupo
import zamudio.ramon.proyectogestorgastos.model.data.repartirDeuda
import zamudio.ramon.proyectogestorgastos.model.data.usuarios
import zamudio.ramon.proyectogestorgastos.model.dominio.Grupo
import zamudio.ramon.proyectogestorgastos.model.dominio.Usuario
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupAccentBackground
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupFieldBackground
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupTextPrimary
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupTextStyles
import zamudio.ramon.proyectogestorgastos.ui.theme.PurpleButton
import zamudio.ramon.proyectogestorgastos.ui.theme.PurplePrimary
import zamudio.ramon.proyectogestorgastos.ui.theme.SecondaryBackgroundColor
import zamudio.ramon.proyectogestorgastos.ui.theme.White
import kotlin.collections.forEach

@Composable
fun RemindGroupMembers(grupo: Grupo,
                       modifier: Modifier = Modifier
) {
    repartirDeuda(grupo)
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
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            grupo.miembros.forEach { usuario ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .background(White)
                        .padding(horizontal = 12.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = usuario.imgRes),
                        contentDescription = usuario.nombre,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column() {
                        Text(
                            style = GroupTextStyles.ActionBold,
                            text = usuario.nombre,
                            color = GroupTextPrimary
                        )
                        Text(

                            style = GroupTextStyles.ActionBold,
                            text = "Debe: $" + grupo.deuda?.find { it.usuario.id == usuario.id }?.monto.toString(),
                            color = GroupTextPrimary
                        )
                    }
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                        Button(onClick = { }, colors = ButtonDefaults.buttonColors(
                            containerColor = SecondaryBackgroundColor,
                            contentColor = PurpleButton
                        )) {
                            Text("Recordar")
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun RemindGroupMembersPreview() {
    RemindGroupMembers(grupo)
}