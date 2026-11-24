package zamudio.ramon.proyectogestorgastos.view.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zamudio.ramon.proyectogestorgastos.R
import zamudio.ramon.proyectogestorgastos.ui.theme.BadgeBackground
import zamudio.ramon.proyectogestorgastos.ui.theme.GrayText
import zamudio.ramon.proyectogestorgastos.ui.theme.Typography
import zamudio.ramon.proyectogestorgastos.view.components.BarraProgreso
import zamudio.ramon.proyectogestorgastos.view.components.BotonPrincipal

@Composable
fun RegisterScreen(
    innerPadding: PaddingValues,
    navigateToLogin: () -> Unit,
    navigateToRegisterPerfil: () -> Unit
) {
    var nombre by remember { mutableStateOf("") }
    var primerApellido by remember { mutableStateOf("") }
    var segundoApellido by remember { mutableStateOf("") }
    val badgeTextColor = MaterialTheme.colorScheme.primary
    val badgeBackgroundColor = BadgeBackground

    val customTextFieldColors = TextFieldDefaults.colors(
        focusedContainerColor = MaterialTheme.colorScheme.secondary,
        unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
        focusedTextColor = MaterialTheme.colorScheme.onSecondary,
        unfocusedTextColor = MaterialTheme.colorScheme.onSecondary,
        cursorColor = MaterialTheme.colorScheme.primary,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(innerPadding)
            .padding(20.dp)
    ) {
        Spacer(Modifier.height(10.dp))
        BarraProgreso(1, 3)

        Text(
            text = "Crear Cuenta",
            style = Typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(top = 40.dp)
        )
        Text(
            text = "Ingresa tus Datos para comenzar a gestionar tus finanzas con claridad y control",
            style = Typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(Modifier.height(65.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Nombre(s)",
                style = Typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Obligatorio",
                style = Typography.bodySmall,
                color = badgeTextColor,
                modifier = Modifier
                    .background(color = badgeBackgroundColor, shape = RoundedCornerShape(12.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
        Spacer(Modifier.height(8.dp))
        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            placeholder = { Text("ej. Juan Carlos", color = GrayText) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            shape = RoundedCornerShape(12.dp),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = customTextFieldColors
        )

        Spacer(Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Column(Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Primer Apellido",
                        style = Typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Oblig.",
                        style = Typography.bodySmall,
                        fontSize = 10.sp,
                        color = badgeTextColor,
                        modifier = Modifier
                            .background(badgeBackgroundColor, CircleShape)
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    )
                }
                Spacer(Modifier.height(8.dp))
                TextField(
                    value = primerApellido,
                    onValueChange = { primerApellido = it },
                    placeholder = { Text("ej: Pérez", color = GrayText) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    colors = customTextFieldColors,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
            }

            Column(Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Segundo Apellido",
                        style = Typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Opcional",
                        style = Typography.bodySmall,
                        fontSize = 10.sp,
                        color = badgeTextColor,
                        modifier = Modifier
                            .background(badgeBackgroundColor, CircleShape)
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = segundoApellido,
                    onValueChange = { segundoApellido = it },
                    placeholder = { Text("ej: Gómez", color = GrayText) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    colors = customTextFieldColors,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
            }
        }

        Spacer(Modifier.height(30.dp))
        BotonPrincipal("Continuar", navigateToRegisterPerfil, R.drawable.ic_arrow_right)
        Spacer(Modifier.height(25.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { navigateToLogin() },
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "¿Ya tienes una cuenta? ",
                style = Typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Inicia Sesión",
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                style = Typography.labelLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRegisterScreenLight() {
    RegisterScreen(PaddingValues(10.dp), {}, {})
}

