package zamudio.ramon.proyectogestorgastos.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zamudio.ramon.proyectogestorgastos.R
import zamudio.ramon.proyectogestorgastos.ui.theme.BackgroundRed
import zamudio.ramon.proyectogestorgastos.ui.theme.BluePrimary
import zamudio.ramon.proyectogestorgastos.ui.theme.GrayForm
import zamudio.ramon.proyectogestorgastos.ui.theme.GrayText
import zamudio.ramon.proyectogestorgastos.ui.theme.RedlogOut
import zamudio.ramon.proyectogestorgastos.ui.theme.Typography
import zamudio.ramon.proyectogestorgastos.ui.theme.purplePrimary
import zamudio.ramon.proyectogestorgastos.ui.theme.screenBackground
import zamudio.ramon.proyectogestorgastos.view.components.NavBar

@Composable
fun PerfilScreen(
    innerPadding: PaddingValues,
    onNavigateToLogin: () -> Unit,
    navigateToPrincipal: () -> Unit,
    navigateToMetodosPago: () -> Unit = {},
    navigateToPresupuesto: () -> Unit = {},
    navigateToRegistroGasto: () -> Unit = {}
) {
    Column(Modifier.fillMaxSize().background(screenBackground).padding(innerPadding)
        .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Column(Modifier.weight(1f).fillMaxWidth().verticalScroll(rememberScrollState()), horizontalAlignment = Alignment.CenterHorizontally) {
            Column(Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)).background(Color.White)
                .padding(vertical = 32.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Box(contentAlignment = Alignment.BottomEnd) {
                    Image(painter = painterResource(R.drawable.profile_picture), contentDescription = "Foto de perfil",
                        contentScale = ContentScale.Crop, modifier = Modifier.size(100.dp).padding(4.dp).clip(CircleShape))
                    IconButton(onClick = {}, Modifier.size(36.dp).background(BluePrimary, CircleShape)) {
                        Icon(painter = painterResource(R.drawable.ic_camara), contentDescription = "Cambiar Foto", tint = Color.White, modifier = Modifier.size(18.dp))
                    }
                }
                Spacer(Modifier.height(16.dp))
                Text("Sofía Morales", style = Typography.titleLarge)
            }
            Spacer(Modifier.height(16.dp))

            TarjetaDatosPersonales()
            Spacer(Modifier.height(16.dp))
            Row(
                Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)).background(Color.White)
                    .clickable(onClick = navigateToMetodosPago).padding(18.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(painterResource(R.drawable.ic_presupuesto), contentDescription = null, tint = purplePrimary)
                Spacer(Modifier.width(12.dp))
                Text("Métodos de pago", Modifier.weight(1f), style = Typography.bodyLarge, fontWeight = FontWeight.SemiBold)
                Icon(painterResource(R.drawable.ic_arrow_right), contentDescription = "Abrir métodos de pago", tint = GrayText)
            }
            Spacer(Modifier.height(16.dp))
            TarjetaSeguridad()
            Spacer(modifier = Modifier.height(24.dp))
            BotonCerrarSesion(onNavigateToLogin)
            Spacer(modifier = Modifier.height(16.dp))
        }
        NavBar(
            onInicioClick = navigateToPrincipal,
            onPresupuestoClick = navigateToPresupuesto,
            onAgregarClick = navigateToRegistroGasto
        )
    }
}

@Composable
fun TarjetaDatosPersonales() {
    Column(Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)).background(Color.White).padding(24.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp)) {
            Box(Modifier.size(44.dp).clip(RoundedCornerShape(12.dp)).background(Color(0xFFF3E8FF)),contentAlignment = Alignment.Center) {
                Icon(painter = painterResource(R.drawable.ic_id_badge), contentDescription = "Icono Datos Personales", tint = Color(0xFF6B21A8))
            }
            Spacer(Modifier.width(12.dp))
            Text("Datos Personales",style = Typography.bodyLarge
            )
            Spacer(Modifier.weight(1f))
            Row(Modifier.clickable { }, verticalAlignment = Alignment.CenterVertically) {
                Icon(painter = painterResource(R.drawable.ic_edit), contentDescription = "Icono Datos Personales", tint = BluePrimary)
                Spacer(Modifier.width(4.dp))
                Text("Editar", color = BluePrimary,style = Typography.bodyMedium)
            }
        }

        CampoDatoPersonal("Nombre completo", "Sofía Morales")
        CampoDatoPersonal("Teléfono móvil", "+52 55 4192 8830")
        CampoDatoPersonal("Fecha de nacimiento", "08/14/1995")
        CampoDatoPersonal("Correo electrónico","sofia.morales@gastoclaro.com",
            true,"El correo electrónico no puede ser modificado por seguridad.")
    }
}

@Composable
fun CampoDatoPersonal(titulo: String, valor: String, esProtegido: Boolean = false, textoAyuda: String? = null) {
    Column(Modifier.fillMaxWidth().padding(bottom = 16.dp)) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(titulo,style = Typography.bodyLarge)
            if (esProtegido) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Spacer(Modifier.width(4.dp))
                    Text("Protegido", style = Typography.bodySmall)
                }
            }
        }
        Spacer(Modifier.height(8.dp))
        Row(Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(GrayForm).padding(horizontal = 16.dp, vertical = 14.dp),horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Text(valor, fontSize = 16.sp, color = GrayText)
        }
        if (textoAyuda != null) {
            Spacer(Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Spacer(Modifier.width(6.dp))
                Text(textoAyuda, color = GrayText, fontSize = 13.sp)
            }
        }
    }
}

@Composable
fun TarjetaSeguridad() {
    var biometriaActivada by remember { mutableStateOf(true) }

    Column(Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)).background(Color.White).padding(24.dp)) {
        Row(Modifier.fillMaxWidth().padding(bottom = 24.dp),verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(36.dp).clip(CircleShape).background(Color(0xFFF3E8FF)), contentAlignment = Alignment.Center) {
                Icon(painter = painterResource(id = R.drawable.ic_shield), contentDescription = "Icono Seguridad",
                    tint = purplePrimary, modifier = Modifier.size(20.dp))
            }
            Spacer(Modifier.width(12.dp))
            Text("Seguridad y Acceso", style = Typography.titleLarge)
        }
        Row(Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(Color(0xFFF4F5F9)).padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(40.dp).clip(RoundedCornerShape(10.dp)).background(Color(0xFFEBF3FF)),contentAlignment = Alignment.Center) {
                Icon(painter = painterResource(id = R.drawable.ic_fingerprint), contentDescription = "Huella", modifier = Modifier.size(24.dp), tint = BluePrimary)
            }
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text("Inicio con Biometría (Huella / Face ID)", style = Typography.bodySmall, fontWeight = FontWeight.Bold)
                Text("Permite acceder a la app usando tu sensor biométrico sin escribir la contraseña.",style = Typography.bodySmall)
            }
            Spacer(Modifier.width(8.dp))
            Switch(checked = biometriaActivada, onCheckedChange = { biometriaActivada = it },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = BluePrimary
                )
            )
        }

        Spacer(Modifier.height(12.dp))
        Row(Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(Color(0xFFF4F5F9))
            .clickable {  }.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(40.dp).clip(RoundedCornerShape(10.dp)).background(Color(0xFFF3E8FF)),
                contentAlignment = Alignment.Center) {
                Icon(painter = painterResource(id = R.drawable.ic_key), contentDescription = "Llave",
                    tint = purplePrimary, modifier = Modifier.size(20.dp))
            }
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text("Cambio de contraseña de acceso", fontWeight = FontWeight.Bold,style = Typography.bodySmall)
                Text("Actualizada hace 2 meses", style = Typography.bodySmall)
            }
        }
    }
}

@Composable
fun BotonCerrarSesion(onNavigateToLogin: () -> Unit) {
    Row(Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(BackgroundRed)
        .clickable {onNavigateToLogin()}.padding(vertical = 16.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
        Icon(painter = painterResource(id = R.drawable.ic_logout), contentDescription = "Cerrar Sesión",
            tint = RedlogOut, modifier = Modifier.size(24.dp))
        Spacer(Modifier.width(12.dp))
        Text("Cerrar Sesión", style = Typography.labelLarge)
    }
}

@Preview
@Composable
fun PreviewPerfil(){
    PerfilScreen(PaddingValues(10.dp),{},{})
}
