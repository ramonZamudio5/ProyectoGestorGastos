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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zamudio.ramon.proyectogestorgastos.R
import zamudio.ramon.proyectogestorgastos.ui.theme.BackGroundPurple
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupFieldBackground
import zamudio.ramon.proyectogestorgastos.ui.theme.ProyectoGestorGastosTheme
import zamudio.ramon.proyectogestorgastos.ui.theme.Typography
import zamudio.ramon.proyectogestorgastos.view.components.BarraProgreso
import zamudio.ramon.proyectogestorgastos.view.components.BotonPrincipal

@Composable
fun RegisterPerfilScreen(
    innerPadding: PaddingValues,
    navigateToRegister: () -> Unit,
    navigateToRegisterPassword: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var day by remember { mutableStateOf("") }
    var month by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }
    var menuExpandido by remember { mutableStateOf(false) }
    var generoSeleccionado by remember { mutableStateOf("Selecciona una opción") }
    val opcionesGenero = listOf("Femenino", "Masculino", "No binario", "Prefiero no decirlo")

    // Colores reutilizables para los TextFields dentro de la tarjeta
    val cardTextFieldColors = TextFieldDefaults.colors(
        focusedContainerColor = GroupFieldBackground,
        unfocusedContainerColor = GroupFieldBackground,
        focusedTextColor = MaterialTheme.colorScheme.onSecondary,
        unfocusedTextColor = MaterialTheme.colorScheme.onSecondary,
        cursorColor = MaterialTheme.colorScheme.primary,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(innerPadding).padding(20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.height(10.dp))
        BarraProgreso(2, 3)

        Column(Modifier.padding(24.dp)) {
            Spacer(Modifier.height(18.dp))

            // Píldora "Identidad Segura" (Blanca con texto morado en Dark, morado claro en Light)
            Row(
                modifier = Modifier
                    .background(
                        color = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.secondary else BackGroundPurple,
                        shape = CircleShape
                    )
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_shield_outline),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(Modifier.width(6.dp))
                Text(
                    text = "Identidad Segura",
                    color = MaterialTheme.colorScheme.primary,
                    style = Typography.labelLarge.copy(fontSize = 12.sp)
                )
            }

            Spacer(Modifier.height(16.dp))

            // Título Principal (PurpleButton en Dark / OffBlack en Light)
            Text(
                text = "Datos de Contacto y\nPerfil",
                style = Typography.headlineLarge.copy(fontWeight = FontWeight.ExtraBold),
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(Modifier.height(8.dp))

            // Subtítulo sobre el fondo de pantalla (OffWhite en Dark / OffBlack en Light)
            Text(
                text = "Esta información nos ayuda a personalizar tus\nreportes y mantener tu cuenta conectada.",
                style = Typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.85f)
            )

            Spacer(Modifier.height(24.dp))

            // Tarjeta Blanca (secondary = OffWhite)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(24.dp))
                    .background(MaterialTheme.colorScheme.secondary)
                    .padding(20.dp)
            ) {
                // --- CORREO ELECTRÓNICO ---
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Correo Electrónico",
                        style = Typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                    Text(
                        text = "Obligatorio",
                        color = MaterialTheme.colorScheme.primary,
                        style = Typography.labelLarge.copy(fontSize = 10.sp),
                        modifier = Modifier
                            .background(Color(0xFFF4F1FB), CircleShape)
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
                Spacer(Modifier.height(8.dp))
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = {
                        Text(
                            text = "ejemplo@correo.com",
                            style = Typography.bodyMedium,
                            color = Color(0xFF9CA3AF)
                        )
                    },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_mail_outline),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    },
                    trailingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_shield_check),
                            contentDescription = null,
                            tint = Color(0xFF4B5563)
                        )
                    },keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    ),
                    colors = cardTextFieldColors,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    textStyle = Typography.bodyMedium
                )
                Spacer(Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(R.drawable.ic_lock_outline),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(Modifier.width(6.dp))
                    Text(
                        text = "Te enviaremos un código seguro de validación.",
                        style = Typography.bodyMedium.copy(fontSize = 12.sp),
                        color = Color(0xFF4B5563)
                    )
                }

                Spacer(Modifier.height(24.dp))

                // --- FECHA DE NACIMIENTO ---
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Fecha de Nacimiento",
                        style = Typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                    Text(
                        text = "Obligatorio",
                        color = MaterialTheme.colorScheme.primary,
                        style = Typography.labelLarge.copy(fontSize = 10.sp),
                        modifier = Modifier
                            .background(Color(0xFFF4F1FB), CircleShape)
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
                Spacer(Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    TextField(
                        value = day,
                        onValueChange = { day = it },
                        placeholder = {
                            Text(
                                text = "DD",
                                style = Typography.bodyMedium,
                                color = Color(0xFF9CA3AF),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        },keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        colors = cardTextFieldColors,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.weight(1f),
                        singleLine = true,
                        textStyle = Typography.bodyMedium.copy(textAlign = TextAlign.Center)
                    )
                    TextField(
                        value = month,
                        onValueChange = { month = it },
                        placeholder = {
                            Text(
                                text = "MM",
                                style = Typography.bodyMedium,
                                color = Color(0xFF9CA3AF),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        },keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        colors = cardTextFieldColors,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.weight(1f),
                        singleLine = true,
                        textStyle = Typography.bodyMedium.copy(textAlign = TextAlign.Center)
                    )
                    TextField(
                        value = year,
                        onValueChange = { year = it },
                        placeholder = {
                            Text(
                                text = "AAAA",
                                style = Typography.bodyMedium,
                                color = Color(0xFF9CA3AF),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        },keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        colors = cardTextFieldColors,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.weight(1.2f),
                        singleLine = true,
                        textStyle = Typography.bodyMedium.copy(textAlign = TextAlign.Center)
                    )
                }
                Spacer(Modifier.height(8.dp))
                Row {
                    Icon(
                        painter = painterResource(R.drawable.ic_calendar_outline),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .size(14.dp)
                            .padding(top = 2.dp)
                    )
                    Spacer(Modifier.width(6.dp))
                    Text(
                        text = "Requerido por regulaciones bancarias y de\nedad.",
                        style = Typography.bodyMedium.copy(fontSize = 12.sp),
                        color = Color(0xFF4B5563)
                    )
                }

                Spacer(Modifier.height(24.dp))

                // --- GÉNERO ---
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Género",
                        style = Typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                    Text(
                        text = "Obligatorio",
                        color = MaterialTheme.colorScheme.primary,
                        style = Typography.labelLarge.copy(fontSize = 10.sp),
                        modifier = Modifier
                            .background(Color(0xFFF4F1FB), CircleShape)
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
                Spacer(Modifier.height(8.dp))

                // Selector de género
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(if (menuExpandido) 4.dp else 0.dp, RoundedCornerShape(12.dp))
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.secondary)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(GroupFieldBackground)
                            .clickable { menuExpandido = !menuExpandido }
                            .padding(horizontal = 16.dp, vertical = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(R.drawable.ic_id_badge),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(Modifier.width(12.dp))
                            Text(
                                text = generoSeleccionado,
                                style = Typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSecondary
                            )
                        }
                        Icon(
                            painter = painterResource(R.drawable.ic_chevron_up),
                            contentDescription = null,
                            tint = Color(0xFF4B5563),
                            modifier = Modifier.rotate(if (menuExpandido) 0f else 180f)
                        )
                    }
                    if (menuExpandido) {
                        Column(Modifier.padding(vertical = 8.dp)) {
                            opcionesGenero.forEach { opcion ->
                                Text(
                                    text = opcion,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            generoSeleccionado = opcion
                                            menuExpandido = false
                                        }
                                        .padding(horizontal = 16.dp, vertical = 12.dp),
                                    style = Typography.bodyMedium,
                                    color = if (opcion == "Prefiero no decirlo") Color(0xFF4B5563) else MaterialTheme.colorScheme.onSecondary
                                )
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.height(32.dp))
            BotonPrincipal("Continuar", navigateToRegisterPassword, R.drawable.ic_arrow_right)
            Spacer(Modifier.height(16.dp))

            // Botón "Volver al paso anterior" sobre fondo oscuro (OffWhite en Dark / OffBlack en Light)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navigateToRegister() },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_chevron_left),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75f),
                    modifier = Modifier.size(16.dp)
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    text = "Volver al paso anterior",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold,
                    style = Typography.labelLarge
                )
            }
            Spacer(Modifier.height(32.dp))
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Composable
fun PreviewRegistroPerfilLight() {
    ProyectoGestorGastosTheme(darkTheme = false) {
        RegisterPerfilScreen(PaddingValues(10.dp), {}, {})
    }
}

@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewRegistroPerfilDark() {
    ProyectoGestorGastosTheme(darkTheme = true) {
        RegisterPerfilScreen(PaddingValues(10.dp), {}, {})
    }
}