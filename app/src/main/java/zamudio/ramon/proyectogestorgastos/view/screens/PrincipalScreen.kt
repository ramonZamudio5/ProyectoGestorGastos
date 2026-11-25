package zamudio.ramon.proyectogestorgastos.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import zamudio.ramon.proyectogestorgastos.R
import zamudio.ramon.proyectogestorgastos.model.data.gastosMock
import zamudio.ramon.proyectogestorgastos.model.data.getGastosTotales
import zamudio.ramon.proyectogestorgastos.model.dominio.Gasto
import zamudio.ramon.proyectogestorgastos.ui.theme.Typography
import zamudio.ramon.proyectogestorgastos.view.components.BannerBalance
import zamudio.ramon.proyectogestorgastos.view.components.BannerDistribucion
import zamudio.ramon.proyectogestorgastos.view.components.BotonPrincipal
import zamudio.ramon.proyectogestorgastos.view.components.BotonLink
import zamudio.ramon.proyectogestorgastos.view.components.BotonSecundario
import zamudio.ramon.proyectogestorgastos.view.components.ColumnaGastos

@Composable
fun PrincipalScreen(
    gastos: List<Gasto>,
    onNavigateToRegistroGasto: () -> Unit,
    onNavigateToHistorial: () -> Unit
) {
    Column(Modifier
        .background(MaterialTheme.colorScheme.background)
        .fillMaxSize(), verticalArrangement = Arrangement.spacedBy(5.dp)) {
        Column(Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp).verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.spacedBy(15.dp)) {
            BannerBalance(getGastosTotales(gastos), 3000.0)
            BotonPrincipal("Agregar Gasto/Ingreso", onNavigateToRegistroGasto, R.drawable.ic_anadir)
            BannerDistribucion(gastos)
            Column(modifier = Modifier.background(color= MaterialTheme.colorScheme.secondary, shape= RoundedCornerShape(10.dp)).padding(10.dp)) {
                Text("Últimos gastos", style = Typography.titleMedium, color = MaterialTheme.colorScheme.onSecondary)
                ColumnaGastos(gastos)
                BotonSecundario("Ver Historial Completo", onNavigateToHistorial, R.drawable.ic_arrow_right)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPrincipalScreen(){
    PrincipalScreen(gastosMock, {}, {})
}



