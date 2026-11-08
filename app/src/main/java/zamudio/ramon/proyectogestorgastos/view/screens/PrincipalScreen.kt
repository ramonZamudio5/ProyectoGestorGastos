package zamudio.ramon.proyectogestorgastos.view.screens

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import zamudio.ramon.proyectogestorgastos.R
import zamudio.ramon.proyectogestorgastos.model.data.gastosMock
import zamudio.ramon.proyectogestorgastos.model.data.getGastosTotales
import zamudio.ramon.proyectogestorgastos.model.dominio.Gasto
import zamudio.ramon.proyectogestorgastos.ui.theme.ScreenBackground
import zamudio.ramon.proyectogestorgastos.ui.theme.Typography
import zamudio.ramon.proyectogestorgastos.ui.theme.OffWhite
import zamudio.ramon.proyectogestorgastos.view.components.BannerBalance
import zamudio.ramon.proyectogestorgastos.view.components.BannerDistribucion
import zamudio.ramon.proyectogestorgastos.view.components.BotonPrincipal
import zamudio.ramon.proyectogestorgastos.view.components.ColumnaGastos

@Composable
fun PrincipalScreen(
    gastos: List<Gasto>,
    onNavigateToRegistroGasto: () -> Unit,
    onNavigateToHistorial: () -> Unit
) {
    Column(Modifier
        .background(MaterialTheme.colorScheme.surface)
        .fillMaxSize(), verticalArrangement = Arrangement.spacedBy(5.dp)) {
        Column(Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp).verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.SpaceBetween) {
            BannerBalance(getGastosTotales(gastos), 3000.0)
            Spacer(Modifier.size(15.dp))
            BotonPrincipal("Agregar Gasto/Ingreso", onNavigateToRegistroGasto, R.drawable.ic_anadir)
            Spacer(Modifier.size(10.dp))
            BannerDistribucion(gastos)
            Spacer(Modifier.size(10.dp))
            Column(modifier = Modifier.background(color= OffWhite, shape= RoundedCornerShape(10.dp)).padding(10.dp)) {
                Text("Últimos gastos", style = Typography.titleMedium)
                ColumnaGastos(gastos)
                Row(modifier = Modifier.fillMaxWidth().padding(5.dp)
                    .background(color= OffWhite, shape = RoundedCornerShape(10.dp)).clickable(onClick = onNavigateToHistorial),
                    verticalAlignment = Alignment.CenterVertically){
                    Text("Ver historial completo", style= Typography.labelLarge)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPrincipalScreen(){
    PrincipalScreen(gastosMock, {}, {})
}

