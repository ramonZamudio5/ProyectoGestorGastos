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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import zamudio.ramon.proyectogestorgastos.ui.theme.Typography
import zamudio.ramon.proyectogestorgastos.ui.theme.screenBackground
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import zamudio.ramon.proyectogestorgastos.ui.theme.BluePrimary
import zamudio.ramon.proyectogestorgastos.ui.theme.LightBlue
import zamudio.ramon.proyectogestorgastos.ui.theme.PurplePrimary
import zamudio.ramon.proyectogestorgastos.view.components.BarraProgreso


@Composable
fun RegisterScreen(innerPadding: PaddingValues,navigateToLogin:()-> Unit,navigateToRegisterPerfil:()-> Unit){
    var nombre by remember { mutableStateOf("") }
    var primerApellido by remember { mutableStateOf("") }
    var segundoApellido by remember { mutableStateOf("") }

    Column(Modifier.fillMaxSize().background(screenBackground).padding(innerPadding)) {
        Spacer(Modifier.height(10.dp))
        BarraProgreso(1,3)
        Text(
            "Crear Cuenta",
            style = Typography.headlineLarge,
            modifier = Modifier.padding(top = 40.dp)
        )
        Text(
            "Ingresa tus Datos para comenzar a gestionar tus finanzas con claridad y control",
            style = Typography.bodyLarge
        )
        Spacer(Modifier.height(65.dp))
        Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
            Text("Nombre(s)", style = Typography.labelLarge)
            Text(
                "Obligatorio", style = Typography.bodySmall, modifier = Modifier
                    .background(
                        color = LightBlue,
                        shape = RoundedCornerShape(12.dp)
                    ), color = BluePrimary
            )
        }
        Spacer(Modifier.height(8.dp))
        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            placeholder = { Text("ej. Juan Carlos") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth(), colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedTextColor = Color.White,
                focusedContainerColor = Color.White
            )
        )
        Spacer(Modifier.height(12.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Column(Modifier.weight(1f)) {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Text("Primer Apellido",style = Typography.labelLarge)
                    Text("Oblig.",style = Typography.bodySmall,color = BluePrimary,
                        modifier = Modifier.background(LightBlue, CircleShape).padding(horizontal = 8.dp, vertical = 2.dp))
                }
                Spacer(Modifier.height(8.dp))
                TextField(
                    value = primerApellido,
                    onValueChange = { primerApellido = it },
                    placeholder = { Text("ej: Pérez") },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ), shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true)
            }

            Column(Modifier.weight(1f)) {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Text("Segundo Apellido", style = Typography.labelLarge)
                    Text("Opcional",style = Typography.bodySmall, fontSize = 9.sp, modifier = Modifier.background(LightBlue, CircleShape).padding(horizontal = 8.dp, vertical = 2.dp))
                }
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = segundoApellido,
                    onValueChange = { segundoApellido = it },
                    placeholder = { Text("ej: Gómez", color = Color(0xFFA1A1AA)) },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
            }
        }
        Spacer(Modifier.height(30.dp))
        Button(onClick = {navigateToRegisterPerfil()}, Modifier.fillMaxWidth().height(52.dp), colors = ButtonDefaults.buttonColors(containerColor = PurplePrimary),
            shape = RoundedCornerShape(40.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                Text(text = "Continuar ->", color = Color.White, style = Typography.labelLarge)
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
        Spacer(Modifier.height(25.dp))
        Row(Modifier.fillMaxWidth().clickable{navigateToLogin()},horizontalArrangement = Arrangement.Center) {
            Text("¿Ya tienes una cuenta?", style = Typography.labelLarge, color = Color(0xFF475467))
            Text("Inicia Sesion", color = PurplePrimary, style = Typography.labelLarge)
        }
        }

    }




@Preview
@Composable
fun PreviewRegisterScreen(){
    RegisterScreen(PaddingValues(10.dp),{},{})
}