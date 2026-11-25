package zamudio.ramon.proyectogestorgastos.view.screens

import android.content.res.Configuration
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zamudio.ramon.proyectogestorgastos.R
import zamudio.ramon.proyectogestorgastos.ui.theme.ProyectoGestorGastosTheme
import zamudio.ramon.proyectogestorgastos.ui.theme.Typography
import zamudio.ramon.proyectogestorgastos.view.components.BarraProgreso
import zamudio.ramon.proyectogestorgastos.view.components.BotonPrincipal
import zamudio.ramon.proyectogestorgastos.view.components.PasswordHeader
import zamudio.ramon.proyectogestorgastos.view.components.PasswordStrengthCard
import zamudio.ramon.proyectogestorgastos.view.components.PasswordInputField
import zamudio.ramon.proyectogestorgastos.view.components.PasswordRequirementsCard
import zamudio.ramon.proyectogestorgastos.view.components.TermsAndConditionsCard
import zamudio.ramon.proyectogestorgastos.view.components.SecurityBadgeFooter

@Composable
fun RegisterPasswordScreen(
    innerPadding: PaddingValues,
    navigateToRegisterPerfil: () -> Unit,
    navigateToPrincipal: () -> Unit
) {
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    var termsAccepted by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(innerPadding).padding(horizontal = 20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.height(10.dp))
        BarraProgreso(3, 3)

        Column(Modifier.padding(24.dp)) {
            Spacer(Modifier.height(16.dp))

            PasswordHeader()

            Spacer(Modifier.height(24.dp))

            PasswordStrengthCard(
                statusText = "Esperando contraseña...",
                strengthLevel = 0
            )

            Spacer(Modifier.height(24.dp))

            PasswordInputField(
                label = "Contraseña",
                value = password,
                onValueChange = { password = it },
                isVisible = passwordVisible,
                onToggleVisibility = { passwordVisible = !passwordVisible },
                leadingIconRes = R.drawable.ic_lock_outline
            )

            Spacer(Modifier.height(16.dp))

            PasswordRequirementsCard()

            Spacer(Modifier.height(24.dp))

            PasswordInputField(
                label = "Confirmar Contraseña",
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                isVisible = confirmPasswordVisible,
                onToggleVisibility = { confirmPasswordVisible = !confirmPasswordVisible },
                leadingIconRes = R.drawable.ic_shield_check_purple
            )

            Spacer(Modifier.height(24.dp))

            TermsAndConditionsCard(
                accepted = termsAccepted,
                onToggleAccepted = { termsAccepted = !termsAccepted }
            )

            Spacer(Modifier.height(16.dp))

            SecurityBadgeFooter()

            Spacer(Modifier.height(24.dp))
            BotonPrincipal("Crear Cuenta", navigateToPrincipal, R.drawable.ic_arrow_right)
            Spacer(Modifier.height(24.dp))

            BackStepButton(onClick = navigateToRegisterPerfil)

            Spacer(Modifier.height(32.dp))
        }
    }
}

@Composable
private fun BackStepButton(onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
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
}

@Preview(showBackground = true)
@Composable
fun PreviewRegistroPasswordLight() {
    RegisterPasswordScreen(PaddingValues(10.dp), {}, {})
}
