package zamudio.ramon.proyectogestorgastos.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import zamudio.ramon.proyectogestorgastos.R
import zamudio.ramon.proyectogestorgastos.model.data.tarjetasPagoMock
import zamudio.ramon.proyectogestorgastos.model.dominio.TarjetaPago
import zamudio.ramon.proyectogestorgastos.ui.theme.purpleButton
import zamudio.ramon.proyectogestorgastos.ui.theme.Typography
import zamudio.ramon.proyectogestorgastos.ui.theme.Fondo
import zamudio.ramon.proyectogestorgastos.ui.theme.BackGroundPurple
import zamudio.ramon.proyectogestorgastos.ui.theme.PurplePrimary
import zamudio.ramon.proyectogestorgastos.view.components.Header
import zamudio.ramon.proyectogestorgastos.view.components.NavBar
import zamudio.ramon.proyectogestorgastos.view.components.TarjetaMetodoPago

@Composable
fun MetodosPagoScreen(
    innerPadding: PaddingValues = PaddingValues(0.dp),
    tarjetas: List<TarjetaPago> = tarjetasPagoMock,
    onNavigateToPerfil: () -> Unit = {},
    onNavigateToPrincipal: () -> Unit = {},
    onNavigateToPresupuesto: () -> Unit = {},
    onNavigateToRegistroGasto: () -> Unit = {},
    onRegistrarTarjeta: () -> Unit = {}
) {
    Column(Modifier.fillMaxSize().background(Fondo).padding(innerPadding)) {
        Header(PaddingValues(horizontal = 16.dp, vertical = 8.dp), onNavigateToPerfil)
        Column(
            Modifier.weight(1f).fillMaxWidth().verticalScroll(rememberScrollState()).padding(horizontal = 16.dp, vertical = 30.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = onRegistrarTarjeta,
                modifier = Modifier.fillMaxWidth().height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = purpleButton)
            ) {
                Icon(painterResource(R.drawable.ic_anadir), contentDescription = null, tint = Color.White)
                Text("Registrar Nueva Tarjeta", Modifier.padding(start = 8.dp), style = Typography.labelLarge, fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.height(4.dp))
            Text("Otras Formas de Pago", Modifier.padding(start = 3.dp), style = Typography.titleSmall, fontWeight = FontWeight.SemiBold)
            tarjetas.forEach { tarjeta ->
                val esNu = tarjeta.banco.startsWith("Nu", ignoreCase = true)
                TarjetaMetodoPago(
                    tarjeta = tarjeta,
                    logoTexto = if (esNu) "nu" else null,
                    iconoBanco = if (esNu) null else R.drawable.ic_presupuesto,
                    colorMarca = PurplePrimary,
                    colorFondoMarca = BackGroundPurple
                )
            }
        }
        NavBar(
            onInicioClick = onNavigateToPrincipal,
            onPresupuestoClick = onNavigateToPresupuesto,
            onAgregarClick = onNavigateToRegistroGasto
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MetodosPagoPreview() {
    MetodosPagoScreen(PaddingValues(),tarjetasPagoMock)
}
