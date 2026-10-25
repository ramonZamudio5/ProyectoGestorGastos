package zamudio.ramon.proyectogestorgastos.view.screens

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.Typography
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zamudio.ramon.proyectogestorgastos.R
import zamudio.ramon.proyectogestorgastos.ui.theme.Typography
import zamudio.ramon.proyectogestorgastos.ui.theme.purplePrimary
import zamudio.ramon.proyectogestorgastos.ui.theme.screenBackground
import zamudio.ramon.proyectogestorgastos.view.components.Header
import zamudio.ramon.proyectogestorgastos.view.components.NavBar

@Composable
fun LoginScreen(innerPadding: PaddingValues,navigateToRegister : ()->Unit, onNavigateToPrincipal:()-> Unit) {
    var user by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(Modifier.fillMaxSize().background(screenBackground).padding(innerPadding), verticalArrangement = Arrangement.Center) {
        Text("¡Bienvenido!", style = Typography.headlineLarge)
        Text("Administra tus finanzas y gastos de forma clara, segura y sin complicaciones.", style = Typography.bodyMedium)
        Column(Modifier.padding(24.dp).clip(RoundedCornerShape(24.dp)).background(Color.White).padding(24.dp)) {
            Text("Correo Electrónico", style = Typography.labelLarge)
            Spacer(Modifier.height(8.dp))
            TextField(value = user, onValueChange = { user = it }, placeholder = { Text("ejemplo@gastoclaro.com") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)), singleLine = true)
            Spacer(Modifier.height(20.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text("Contraseña", style = Typography.labelLarge)
                Text(text = "¿Olvidaste tu contraseña?",Modifier.clickable { }, style = Typography.bodySmall,color = purplePrimary)
            }
            Spacer(Modifier.height(8.dp))
            TextField(value = password, onValueChange = { password = it }, trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(painter = painterResource(id = if (passwordVisible) R.drawable.ic_ojo_abierto else R.drawable.ic_ojo_cerrado),
                            contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña", tint = Color(0xFF667085))
                    }
                }, visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ), singleLine = true
            )
            HorizontalDivider(color = purplePrimary.copy(alpha = 0.3f), thickness = 1.dp)
            Box(Modifier.padding(24.dp)) {
                Button(onClick = onNavigateToPrincipal, Modifier.fillMaxWidth().height(52.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = purplePrimary), shape = RoundedCornerShape(12.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                        Text(text = "Iniciar Sesión", color = Color.White, style = Typography.titleLarge)
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("¿Aún no tienes una cuenta en GastoClaro?", style = Typography.labelLarge, color = Color(0xFF475467))
            Spacer(Modifier.height(8.dp))
            Row(Modifier.clickable { navigateToRegister()}, verticalAlignment = Alignment.CenterVertically) {
                Text("Regístrate", color = purplePrimary, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Spacer(Modifier.width(4.dp))
                Icon(painter = painterResource(id = R.drawable.ic_person_add), contentDescription = "Icono Registrar", tint = purplePrimary, modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}
@Preview()
@Composable
fun PreviewLogin(){
    LoginScreen(PaddingValues(15.dp),{}, {})
}