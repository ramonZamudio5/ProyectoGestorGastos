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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zamudio.ramon.proyectogestorgastos.R
import zamudio.ramon.proyectogestorgastos.ui.theme.BackGroundPurple
import zamudio.ramon.proyectogestorgastos.ui.theme.Typography
import zamudio.ramon.proyectogestorgastos.ui.theme.purplePrimary
import zamudio.ramon.proyectogestorgastos.ui.theme.screenBackground
import zamudio.ramon.proyectogestorgastos.view.components.BarraProgreso

@Composable
fun RegisterPasswordScreen(innerPadding: PaddingValues,navigateToRegisterPerfil:()-> Unit,navigateToPrincipal : ()-> Unit) {
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    var termsAccepted by remember { mutableStateOf(false) }

    Column(Modifier.fillMaxSize().background(screenBackground).padding(innerPadding).verticalScroll(rememberScrollState())) {
        Spacer(Modifier.height(10.dp))
        BarraProgreso(3,3)
        Column(Modifier.padding(24.dp)) {
            Spacer(Modifier.height(16.dp))
            Box(Modifier.size(48.dp).background(BackGroundPurple, CircleShape), contentAlignment = Alignment.Center) {
                Icon(painterResource(R.drawable.ic_lock_outline_large), contentDescription = "candado", tint = purplePrimary, modifier = Modifier.size(24.dp))
            }
            Spacer(Modifier.height(16.dp))
            Text("Protege tu Cuenta", style = Typography.headlineLarge.copy(fontWeight = FontWeight.ExtraBold))
            Spacer(Modifier.height(8.dp))
            Text("Crea una contraseña segura para acceder y autorizar tus movimientos financieros con total tranquilidad.", style = Typography.bodyMedium)

            Spacer(Modifier.height(24.dp))
            Row(Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)).background(Color.White).padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.size(40.dp).background(BackGroundPurple, CircleShape), contentAlignment = Alignment.Center) {
                    Icon(painterResource(R.drawable.ic_shield_outline), "escudo", tint = purplePrimary, modifier = Modifier.size(20.dp))
                }
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Text("SEGURIDAD DE LA CLAVE", style = Typography.labelLarge.copy(fontSize = 10.sp))
                    Text("Esperando contraseña...", style = Typography.labelLarge)
                }
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    repeat(4) { Box(Modifier.width(16.dp).height(6.dp).clip(RoundedCornerShape(4.dp)).background(BackGroundPurple)) }
                }
            }

            Spacer(Modifier.height(24.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Row {
                    Text("Contraseña ", style = Typography.labelLarge)
                    Text("*", style = Typography.labelLarge)
                }
                Text("Obligatorio", color = purplePrimary, style = Typography.labelLarge.copy(fontSize = 10.sp), modifier = Modifier.background(BackGroundPurple, CircleShape).padding(horizontal = 8.dp, vertical = 4.dp))
            }
            Spacer(Modifier.height(8.dp))
            TextField(value = password, onValueChange = { password = it },
                leadingIcon = { Icon(painterResource(R.drawable.ic_lock_outline), null, tint = purplePrimary) },
                trailingIcon = { Icon(painterResource(R.drawable.ic_ojo_abierto), null, modifier = Modifier.clickable { passwordVisible = !passwordVisible }) },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                colors = TextFieldDefaults.colors(focusedContainerColor = Color.White, unfocusedContainerColor = Color.White),
                shape = RoundedCornerShape(12.dp), modifier = Modifier.fillMaxWidth(), singleLine = true, textStyle = Typography.bodyMedium)

            Spacer(Modifier.height(16.dp))
            Column(Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)).background(Color.White).padding(16.dp)) {
                Text("REQUISITOS DE SEGURIDAD", style = Typography.labelLarge.copy(fontSize =10.sp))
                Spacer(Modifier.height(12.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(Modifier.weight(1f).clip(RoundedCornerShape(8.dp)).padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(painterResource(R.drawable.ic_circle_empty), null, modifier = Modifier.size(16.dp))
                        Spacer(Modifier.width(6.dp))
                        Text("Mín. 8 caracteres", style = Typography.bodyMedium.copy(fontSize = 12.sp))
                    }
                    Row(Modifier.weight(1f).clip(RoundedCornerShape(8.dp)).background(Color(0xFFF8F9FA)).padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(painterResource(R.drawable.ic_circle_empty), null, modifier = Modifier.size(16.dp))
                        Spacer(Modifier.width(6.dp))
                        Text("Al menos 1 número", style = Typography.bodyMedium.copy(fontSize = 12.sp))
                    }
                }
                Spacer(Modifier.height(8.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(Modifier.weight(1f).clip(RoundedCornerShape(8.dp)).background(Color(0xFFF8F9FA)).padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(painterResource(R.drawable.ic_circle_empty), null, modifier = Modifier.size(16.dp))
                        Spacer(Modifier.width(6.dp))
                        Text("Una mayúscula", style = Typography.bodyMedium.copy(fontSize = 12.sp))
                    }
                    Row(Modifier.weight(1f).clip(RoundedCornerShape(8.dp)).padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(painterResource(R.drawable.ic_circle_empty), null, tint = Color(0xFF6B7280), modifier = Modifier.size(16.dp))
                        Spacer(Modifier.width(6.dp))
                        Text("Símbolo especial", style = Typography.bodyMedium.copy(fontSize = 12.sp), color = Color(0xFF4B5563))
                    }
                }
            }

            Spacer(Modifier.height(24.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Row {
                    Text("Confirmar Contraseña ", style = Typography.labelLarge, color = Color(0xFF111827))
                    Text("*", style = Typography.labelLarge, color = Color(0xFF8B5CF6))
                }
                Text("Obligatorio", color = Color(0xFF8B5CF6), style = Typography.labelLarge.copy(fontSize = 10.sp), modifier = Modifier.background(Color(0xFFEBE4FF), CircleShape).padding(horizontal = 8.dp, vertical = 4.dp))
            }
            Spacer(Modifier.height(8.dp))
            TextField(value = confirmPassword, onValueChange = { confirmPassword = it },
                leadingIcon = { Icon(painterResource(R.drawable.ic_shield_check_purple), null, tint = Color(0xFF8B5CF6)) },
                trailingIcon = { Icon(painterResource(R.drawable.ic_ojo_abierto), null, tint = Color(0xFF4B5563), modifier = Modifier.clickable { confirmPasswordVisible = !confirmPasswordVisible }) },
                visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                colors = TextFieldDefaults.colors(focusedContainerColor = Color.White, unfocusedContainerColor = Color.White),
                shape = RoundedCornerShape(12.dp), modifier = Modifier.fillMaxWidth(), singleLine = true, textStyle = Typography.bodyMedium)

            Spacer(Modifier.height(24.dp))
            Row(Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)).background(Color.White).padding(16.dp), verticalAlignment = Alignment.Top) {
                Box(Modifier.size(20.dp).clip(RoundedCornerShape(6.dp)).background(if (termsAccepted) Color(0xFF8B5CF6) else Color(0xFFEBE4FF)).clickable { termsAccepted = !termsAccepted }, contentAlignment = Alignment.Center) {
                    if (termsAccepted) Icon(painterResource(R.drawable.ic_arrow_right), null, tint = Color.White, modifier = Modifier.size(14.dp))
                }
                Spacer(Modifier.width(12.dp))
                Text(
                    text = buildAnnotatedString {
                        append("Acepto los ")
                        withStyle(style = SpanStyle(color =purplePrimary, textDecoration = TextDecoration.Underline)) { append("Términos y Condiciones") }
                        append(" y la ")
                        withStyle(style = SpanStyle(color = purplePrimary, textDecoration = TextDecoration.Underline)) { append("Política de Privacidad") }
                        append(" de GastoClaro para el resguardo de mi información.")
                    },
                    style = Typography.bodyMedium.copy(fontSize = 12.sp), lineHeight = 18.sp
                )
            }

            Spacer(Modifier.height(16.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                Icon(painterResource(R.drawable.ic_lock_outline), null, tint = purplePrimary, modifier = Modifier.size(16.dp))
                Spacer(Modifier.width(6.dp))
                Text("Cifrado de grado bancario AES de 256 bits", style = Typography.labelLarge.copy(fontSize = 12.sp))
            }

            Spacer(Modifier.height(24.dp))
            Button(onClick = {navigateToPrincipal() }, modifier = Modifier.fillMaxWidth().height(56.dp), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6B21A8)), shape = RoundedCornerShape(16.dp)) {
                Text("Crear Cuenta", color = Color.White, style = Typography.titleMedium)
                Spacer(Modifier.width(8.dp))
                Icon(painterResource(R.drawable.ic_arrow_right), null, tint = Color.White)
            }
            Spacer(Modifier.height(24.dp))
            Row(Modifier.fillMaxWidth().clickable { navigateToRegisterPerfil()}, horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
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
fun PreviewRegistroPassword() {
    RegisterPasswordScreen(PaddingValues(10.dp),{},{})
}