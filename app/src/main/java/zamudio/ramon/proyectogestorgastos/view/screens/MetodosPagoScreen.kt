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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import zamudio.ramon.proyectogestorgastos.R
import zamudio.ramon.proyectogestorgastos.model.data.tarjetasPagoMock
import zamudio.ramon.proyectogestorgastos.model.dominio.TarjetaPago
import zamudio.ramon.proyectogestorgastos.ui.theme.PurpleButton
import zamudio.ramon.proyectogestorgastos.ui.theme.Typography
import zamudio.ramon.proyectogestorgastos.ui.theme.BackGroundPurple
import zamudio.ramon.proyectogestorgastos.view.components.BotonPrincipal
import zamudio.ramon.proyectogestorgastos.view.components.TarjetaMetodoPago

@Composable
fun MetodosPagoScreen(
    tarjetas: List<TarjetaPago> = tarjetasPagoMock,
    onRegistrarTarjeta: () -> Unit = {}
) {
    Column(Modifier.fillMaxSize().padding(20.dp).background(MaterialTheme.colorScheme.background)) {
        Column(
            Modifier.weight(1f).fillMaxWidth().verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            BotonPrincipal("Registrar Nueva Tarjeta", onRegistrarTarjeta, R.drawable.ic_anadir)
            Spacer(Modifier.height(4.dp))
            Text("Otras Formas de Pago", Modifier.padding(start = 3.dp), style = Typography.titleSmall, fontWeight = FontWeight.SemiBold)
            tarjetas.forEach { tarjeta ->
                val esNu = tarjeta.banco.startsWith("Nu", ignoreCase = true)
                TarjetaMetodoPago(
                    tarjeta = tarjeta,
                    logoTexto = if (esNu) "nu" else null,
                    iconoBanco = if (esNu) null else R.drawable.ic_presupuesto,
                    colorMarca = PurpleButton,
                    colorFondoMarca = BackGroundPurple
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MetodosPagoPreview() {
    MetodosPagoScreen(tarjetasPagoMock)
}



