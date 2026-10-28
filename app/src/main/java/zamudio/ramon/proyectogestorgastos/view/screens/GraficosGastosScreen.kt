package zamudio.ramon.proyectogestorgastos.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import zamudio.ramon.proyectogestorgastos.model.data.gastosMock
import zamudio.ramon.proyectogestorgastos.model.dominio.Gasto
import zamudio.ramon.proyectogestorgastos.ui.theme.ScreenBackground
import zamudio.ramon.proyectogestorgastos.view.components.BannerPromedioDiario
import zamudio.ramon.proyectogestorgastos.view.components.ColumnaGastosVSEstimado
import zamudio.ramon.proyectogestorgastos.view.components.GraficaGastos
import zamudio.ramon.proyectogestorgastos.view.components.Header
import zamudio.ramon.proyectogestorgastos.view.components.NavBar
import zamudio.ramon.proyectogestorgastos.view.components.RowDeFiltros

@Composable
fun GraficosScreen(innerPadding: PaddingValues, gastos:List<Gasto>, onNavigateToPerfil:()-> Unit, onNavigateToGraficos: ()-> Unit){
    var filtroActual by remember { mutableStateOf("todo") }
    Column(Modifier.background(color= ScreenBackground).fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween) {
        Column(Modifier.padding(15.dp).verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.SpaceBetween, horizontalAlignment = Alignment.CenterHorizontally) {
            RowDeFiltros(gastos, filtroActual, onFiltroSelected = { nuevoFiltro -> filtroActual = nuevoFiltro})
            Spacer(Modifier.size(10.dp))
            GraficaGastos(gastos)
            Spacer(Modifier.size(10.dp))
            BannerPromedioDiario(gastos)
            Spacer(Modifier.size(10.dp))
            ColumnaGastosVSEstimado(gastos)
        }
    }
}

@Preview
@Composable
fun PreviewGraficosScreen(){
    GraficosScreen(PaddingValues(), gastosMock, {},{})
}