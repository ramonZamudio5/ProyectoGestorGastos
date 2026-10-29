package zamudio.ramon.proyectogestorgastos.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import zamudio.ramon.proyectogestorgastos.model.data.gastosMock
import zamudio.ramon.proyectogestorgastos.model.data.grupo
import zamudio.ramon.proyectogestorgastos.model.dominio.Grupo
import zamudio.ramon.proyectogestorgastos.ui.theme.ScreenBackground
import zamudio.ramon.proyectogestorgastos.ui.theme.Typography
import zamudio.ramon.proyectogestorgastos.view.components.ColumnaGastosPorFiltro
import zamudio.ramon.proyectogestorgastos.view.components.GroupCardItemInfo
import zamudio.ramon.proyectogestorgastos.view.components.PosicionDeudaGrupo
import zamudio.ramon.proyectogestorgastos.view.components.RemindGroupMembers
import zamudio.ramon.proyectogestorgastos.view.components.RowDeFiltros
import zamudio.ramon.proyectogestorgastos.view.components.TarjetaCategoriaPresupuesto

@Composable
fun InfoGrupoScreen(grupo: Grupo, modifier: Modifier){
    var filtroActual by remember { mutableStateOf("todo") }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize().background(color= ScreenBackground)
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 24.dp)
    ) {
        item{
            GroupCardItemInfo(grupo, modifier)
        }
        item {
            PosicionDeudaGrupo()
        }
        item {
            TarjetaCategoriaPresupuesto(categoria = grupo.presupuesto!!, onClick = {})
        }
        item{
            RemindGroupMembers(grupo)
        }
        item {
            Row(Modifier.fillMaxWidth().padding(5.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Gastos Recientes", style = Typography.titleMedium)
                Row(Modifier.clickable(onClick = {})) {Text("Ver todos", style = Typography.labelMedium) }
            }
            RowDeFiltros(gastosMock, filtroActual, onFiltroSelected = { nuevoFiltro -> filtroActual = nuevoFiltro})
            ColumnaGastosPorFiltro(filtroActual, gastosMock)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InfoGrupoScreenPreview(){
    InfoGrupoScreen(grupo, Modifier)
}
