package zamudio.ramon.proyectogestorgastos.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zamudio.ramon.proyectogestorgastos.R
import zamudio.ramon.proyectogestorgastos.ui.theme.BackGroundPurple
import zamudio.ramon.proyectogestorgastos.ui.theme.Typography
import zamudio.ramon.proyectogestorgastos.ui.theme.screenBackground

@Composable
fun RegisterPerfilScreen(innerPadding: PaddingValues,navigateToRegister:()-> Unit,navigateToRegisterPassword:()-> Unit) {
    var email by remember { mutableStateOf("") }
    var day by remember { mutableStateOf("") }
    var month by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }
    var menuExpandido by remember { mutableStateOf(false) }
    var generoSeleccionado by remember { mutableStateOf("Selecciona una opción") }
    val opcionesGenero = listOf("Femenino", "Masculino", "No binario", "Prefiero no decirlo")

    Column(Modifier.fillMaxSize().background(screenBackground).padding(innerPadding).verticalScroll(rememberScrollState())) {
        Column(Modifier.padding(24.dp)) {
            Spacer(Modifier.height(24.dp))
            Row(Modifier.background(BackGroundPurple, CircleShape).padding(horizontal = 12.dp, vertical = 6.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(painterResource(R.drawable.ic_shield_outline), contentDescription = null, tint = Color(0xFF6B21A8), modifier = Modifier.size(14.dp))
                Spacer(Modifier.width(6.dp))
                Text("Identidad Segura", color = Color(0xFF6B21A8), style = Typography.labelLarge.copy(fontSize = 12.sp))
            }
            Spacer(Modifier.height(16.dp))
            Text("Datos de Contacto y\nPerfil", style = Typography.headlineLarge.copy(fontWeight = FontWeight.ExtraBold), color = Color(0xFF111827))
            Spacer(Modifier.height(8.dp))
            Text("Esta información nos ayuda a personalizar tus\nreportes y mantener tu cuenta conectada.", style = Typography.bodyMedium, color = Color(0xFF4B5563))

            Spacer(Modifier.height(24.dp))

            Column(Modifier.fillMaxWidth().clip(RoundedCornerShape(24.dp)).background(Color.White).padding(20.dp)) {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Text("Correo Electrónico", style = Typography.labelLarge, color = Color(0xFF111827))
                    Text("Obligatorio", color = Color(0xFF8B5CF6), style = Typography.labelLarge.copy(fontSize = 10.sp), modifier = Modifier.background(Color(0xFFF4F1FB), CircleShape).padding(horizontal = 8.dp, vertical = 4.dp))
                }
                Spacer(Modifier.height(8.dp))
                TextField(value = email, onValueChange = { email = it }, placeholder = { Text("ejemplo@correo.com", style = Typography.bodyMedium, color = Color(0xFF9CA3AF)) },
                    leadingIcon = { Icon(painterResource(R.drawable.ic_mail_outline), null, tint = Color(0xFF8B5CF6)) },
                    trailingIcon = { Icon(painterResource(R.drawable.ic_shield_check), null, tint = Color(0xFF4B5563)) },
                    colors = TextFieldDefaults.colors(focusedContainerColor = Color(0xFFF8F9FA), unfocusedContainerColor = Color(0xFFF8F9FA), focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent),
                    shape = RoundedCornerShape(12.dp), modifier = Modifier.fillMaxWidth(), singleLine = true, textStyle = Typography.bodyMedium)
                Spacer(Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(painterResource(R.drawable.ic_lock_outline), null, tint = Color(0xFF8B5CF6), modifier = Modifier.size(14.dp))
                    Spacer(Modifier.width(6.dp))
                    Text("Te enviaremos un código seguro de validación.", style = Typography.bodyMedium.copy(fontSize = 12.sp), color = Color(0xFF4B5563))
                }

                Spacer(Modifier.height(24.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Text("Fecha de Nacimiento", style = Typography.labelLarge, color = Color(0xFF111827))
                    Text("Obligatorio", color = Color(0xFF8B5CF6), style = Typography.labelLarge.copy(fontSize = 10.sp), modifier = Modifier.background(Color(0xFFF4F1FB), CircleShape).padding(horizontal = 8.dp, vertical = 4.dp))
                }
                Spacer(Modifier.height(8.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    TextField(value = day, onValueChange = { day = it }, placeholder = { Text("DD", style = Typography.bodyMedium, color = Color(0xFF9CA3AF), textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth()) },
                        colors = TextFieldDefaults.colors(focusedContainerColor = Color(0xFFF8F9FA), unfocusedContainerColor = Color(0xFFF8F9FA), focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent),
                        shape = RoundedCornerShape(12.dp), modifier = Modifier.weight(1f), singleLine = true, textStyle = Typography.bodyMedium.copy(textAlign = TextAlign.Center))
                    TextField(value = month, onValueChange = { month = it }, placeholder = { Text("MM", style = Typography.bodyMedium, color = Color(0xFF9CA3AF), textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth()) },
                        colors = TextFieldDefaults.colors(focusedContainerColor = Color(0xFFF8F9FA), unfocusedContainerColor = Color(0xFFF8F9FA), focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent),
                        shape = RoundedCornerShape(12.dp), modifier = Modifier.weight(1f), singleLine = true, textStyle = Typography.bodyMedium.copy(textAlign = TextAlign.Center))
                    TextField(value = year, onValueChange = { year = it }, placeholder = { Text("AAAA", style = Typography.bodyMedium, color = Color(0xFF9CA3AF), textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth()) },
                        colors = TextFieldDefaults.colors(focusedContainerColor = Color(0xFFF8F9FA), unfocusedContainerColor = Color(0xFFF8F9FA), focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent),
                        shape = RoundedCornerShape(12.dp), modifier = Modifier.weight(1.2f), singleLine = true, textStyle = Typography.bodyMedium.copy(textAlign = TextAlign.Center))
                }
                Spacer(Modifier.height(8.dp))
                Row {
                    Icon(painterResource(R.drawable.ic_calendar_outline), null, tint = Color(0xFF8B5CF6), modifier = Modifier.size(14.dp).padding(top = 2.dp))
                    Spacer(Modifier.width(6.dp))
                    Text("Requerido por regulaciones bancarias y de\nedad.", style = Typography.bodyMedium.copy(fontSize = 12.sp), color = Color(0xFF4B5563))
                }

                Spacer(Modifier.height(24.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Text("Género", style = Typography.labelLarge, color = Color(0xFF111827))
                    Text("Obligatorio", color = Color(0xFF8B5CF6), style = Typography.labelLarge.copy(fontSize = 10.sp), modifier = Modifier.background(Color(0xFFF4F1FB), CircleShape).padding(horizontal = 8.dp, vertical = 4.dp))
                }
                Spacer(Modifier.height(8.dp))
                Column(Modifier.fillMaxWidth().shadow(if (menuExpandido) 4.dp else 0.dp, RoundedCornerShape(12.dp)).clip(RoundedCornerShape(12.dp)).background(if (menuExpandido) Color.White else Color(0xFFF8F9FA))) {
                    Row(Modifier.fillMaxWidth().clickable { menuExpandido = !menuExpandido }.padding(horizontal = 16.dp, vertical = 16.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(painterResource(R.drawable.ic_id_badge), null, tint = Color(0xFF8B5CF6), modifier = Modifier.size(20.dp))
                            Spacer(Modifier.width(12.dp))
                            Text(generoSeleccionado, style = Typography.bodyMedium, color = Color(0xFF111827))
                        }
                        Icon(painterResource(R.drawable.ic_chevron_up), null, tint = Color(0xFF4B5563), modifier = Modifier.rotate(if (menuExpandido) 0f else 180f))
                    }
                    if (menuExpandido) {
                        Column(Modifier.padding(vertical = 8.dp)) {
                            opcionesGenero.forEach { opcion ->
                                Text(opcion, modifier = Modifier.fillMaxWidth().clickable {
                                    generoSeleccionado = opcion
                                    menuExpandido = false
                                }.padding(horizontal = 16.dp, vertical = 12.dp), style = Typography.bodyMedium, color = if (opcion == "Prefiero no decirlo") Color(0xFF4B5563) else Color(0xFF111827))
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.height(32.dp))
            Button(onClick = { navigateToRegisterPassword()}, modifier = Modifier.fillMaxWidth().height(56.dp), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8B5CF6)), shape = RoundedCornerShape(16.dp)) {
                Text("Continuar", color = Color.White, style = Typography.titleMedium)
                Spacer(Modifier.width(8.dp))
                Icon(painterResource(R.drawable.ic_arrow_right), null, tint = Color.White)
            }
            Spacer(Modifier.height(16.dp))
            Row(Modifier.fillMaxWidth().clickable { navigateToRegister()}, horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                Icon(painterResource(R.drawable.ic_chevron_left), null, tint = Color(0xFF4B5563), modifier = Modifier.size(16.dp))
                Spacer(Modifier.width(4.dp))
                Text("Volver al paso anterior", color = Color(0xFF4B5563), style = Typography.labelLarge)
            }
            Spacer(Modifier.height(32.dp))
        }
    }
}

@Preview
@Composable
fun PreviewRegistroPerfil() {
    RegisterPerfilScreen(PaddingValues(10.dp),{},{})
}