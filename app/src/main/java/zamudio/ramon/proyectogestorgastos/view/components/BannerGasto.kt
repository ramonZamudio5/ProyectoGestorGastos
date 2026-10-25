package zamudio.ramon.proyectogestorgastos.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.DraggableState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import zamudio.ramon.proyectogestorgastos.model.dominio.Gasto
import zamudio.ramon.proyectogestorgastos.model.dominio.Tipo
import zamudio.ramon.proyectogestorgastos.model.data.gastosMock
import zamudio.ramon.proyectogestorgastos.model.data.getGastosByTipo
import zamudio.ramon.proyectogestorgastos.model.data.getGastosTotales
import zamudio.ramon.proyectogestorgastos.model.data.getPorcentajeTipo
import zamudio.ramon.proyectogestorgastos.model.data.getTiposInGastos
import zamudio.ramon.proyectogestorgastos.ui.theme.Purple40
import zamudio.ramon.proyectogestorgastos.ui.theme.Purple80
import zamudio.ramon.proyectogestorgastos.ui.theme.PurpleGrey40
import zamudio.ramon.proyectogestorgastos.ui.theme.Typography
import zamudio.ramon.proyectogestorgastos.ui.theme.White
import zamudio.ramon.proyectogestorgastos.ui.theme.fondo
import zamudio.ramon.proyectogestorgastos.ui.theme.lightGreen
import zamudio.ramon.proyectogestorgastos.ui.theme.lightRed

@Composable
fun ColumnaGastos(gastos: List<Gasto>){
    LazyColumn(Modifier.padding(5.dp).fillMaxHeight(.6f)){
        items(gastos){
                it ->BannerGasto(it)
        }
    }
}

@Composable
fun ColumnaGastosPorFiltro(filtro: String, gastos: List<Gasto>) {
    val filtroLower = filtro.lowercase()
    val gastosAgrupados = when (filtroLower) {
        "dia" -> gastos.groupBy { "${it.fecha.day}/${it.fecha.month}/${it.fecha.year}" }
        "mes" -> gastos.groupBy {"${it.fecha.month}/${it.fecha.year}"}
        else -> {
            val gastosFiltrados = gastos.filter { it.tipo.toString().equals(filtro, ignoreCase = true) }
            gastosFiltrados.groupBy { it.tipo.toString() }
        }
    }
    LazyColumn(modifier = Modifier.padding(5.dp)) {
        gastosAgrupados.forEach { (dato, gastosDelDia) ->
            item {
                Text(text = dato, style = Typography.titleMedium, color = PurpleGrey40)
            }
            items(gastosDelDia) { gasto ->
                BannerGasto(gasto)
            }
        }
    }
}

@Composable
fun RowDeFiltros(gastos: List<Gasto>,filtroSeleccionado: String, onFiltroSelected: (String) -> Unit) {
    val tipos = getTiposInGastos(gastos)

    LazyRow(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        item {
            FiltroChip(texto = "Día", seleccionado = filtroSeleccionado.equals("dia", ignoreCase = true), onClick = { onFiltroSelected("dia") })
        }

        item {
            FiltroChip(texto = "Mes", seleccionado = filtroSeleccionado.equals("mes", ignoreCase = true), onClick = { onFiltroSelected("mes") })
        }

        items(tipos) { tipo ->
            val esSeleccionado = filtroSeleccionado.equals(tipo.toString(), ignoreCase = true)
            FiltroChip(
                texto = tipo.toString(),
                seleccionado = esSeleccionado,
                colorActivo = tipo.color,
                onClick = { onFiltroSelected(tipo.toString()) }
            )
        }
    }
}

@Composable
fun FiltroChip(texto: String, seleccionado: Boolean, colorActivo: Color = Purple40, onClick: () -> Unit) {
    val colorFondo = if (seleccionado) colorActivo else White
    val colorTexto = if (seleccionado) White else Color.Black
    Box(modifier = Modifier.background(color = colorFondo, shape = RoundedCornerShape(10.dp)
            ).border(width = 1.dp, color = if (seleccionado) Color.Transparent else Color.LightGray,
                shape = RoundedCornerShape(10.dp)).clickable { onClick() }
        .padding(horizontal = 14.dp, vertical = 8.dp)) {
        Text(text = texto, color = colorTexto, style = Typography.bodyMedium)
    }
}

@Composable
fun BannerGasto(gasto: Gasto){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
        .background(color = White, shape = RoundedCornerShape(10.dp))
        .draggable(orientation = Orientation.Horizontal, enabled = true, state= DraggableState{}),
        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
        Box(modifier = Modifier.padding(10.dp), contentAlignment = Alignment.Center){
            Box(Modifier
                .background(color = Purple80, shape = CircleShape)
                .size(30.dp))
            Image(painterResource(gasto.imagen), contentDescription = "${gasto.nombre} imagen", Modifier.size(20.dp))
        }
        Column(Modifier.padding(5.dp)) {
            Text(gasto.nombre, style= Typography.titleMedium)
            Text(gasto.tipo.toString() + " • " + gasto.fecha.day.toString() + "/" + gasto.fecha.month.toString(), style= Typography.labelSmall)
        }
        Column() {
            Text("$" + gasto.cantidad.toString(), style= Typography.titleMedium)
            Text(gasto.tipoPago.toString(), style= Typography.labelSmall)
        }
    }
}

@Composable
fun BannerBalance(gastos: Double, ingresos: Double){
    Column(Modifier.background(color = White, shape = RoundedCornerShape(5.dp)).fillMaxWidth().padding(10.dp)) {
        Text("BALANCE TOTAL", style= Typography.titleMedium, color= PurpleGrey40)
        Spacer(Modifier.size(5.dp))
        Row(verticalAlignment = Alignment.Bottom) {
            val balance = ingresos - gastos
            Text("$${"%.2f".format(balance).toDouble()}", style= Typography.headlineLarge)
            Text("MXN", style= Typography.bodySmall)
        }
        Row (Modifier.background(color = White, shape = RoundedCornerShape(10.dp)).padding(10.dp).fillMaxWidth()
                , verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly){
            Column(Modifier.background(color = lightGreen, shape = RoundedCornerShape(10.dp)).padding(10.dp)) {
                Text("Ingresos del mes", style= Typography.labelMedium)
                Text("+$${ingresos}", style= Typography.titleMedium)
            }
            Column(Modifier.background(color = lightRed, shape = RoundedCornerShape(10.dp)).padding(10.dp)) {
                Text("Gastos del mes", style= Typography.labelMedium)
                Text("-$${gastos}", style= Typography.titleMedium)
            }
        }
    }
}

@Composable
fun BannerDistribucion(gastos: List<Gasto>){
    Column(Modifier.background(color = White, shape = RoundedCornerShape(10.dp)).fillMaxWidth().padding(10.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        val tipos = getTiposInGastos(gastos)
        Text("Distribución de Gastos", style= Typography.titleMedium)
        Row(modifier = Modifier.fillMaxWidth().height(10.dp).clip(RoundedCornerShape(10.dp))) {
            tipos.forEach { tipo ->
                val fraccion = getPorcentajeTipo(
                    getGastosTotales(getGastosByTipo(tipo)),
                    getGastosTotales(gastos)).toFloat()
                Box(modifier = Modifier.weight(fraccion).fillMaxHeight().background(tipo.color)
                )
            }
        }
        LazyVerticalGrid(GridCells.Fixed(2),
            contentPadding = PaddingValues(5.dp, 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp)){
            items(tipos) {
                it -> CeldaTipoGasto(it, gastos)
            }
        }
    }
}

@Composable
fun CeldaTipoGasto(tipo: Tipo, gastos: List<Gasto>){
    Column(
        Modifier.background(color = fondo, shape = RoundedCornerShape(10.dp))
            .padding(10.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(5.dp)) {
            Box(Modifier
                .background(color = tipo.color, shape = CircleShape)
                .size(15.dp))
            Text(tipo.toString(), style = Typography.titleMedium)
        }
        Row() {
            Text(getPorcentajeTipo(getGastosTotales(getGastosByTipo(tipo)),
                getGastosTotales(gastos)).toString() + "%")
            Text(" • ")
            Text("$" + getGastosTotales(getGastosByTipo(tipo)).toString() + "MXN")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BannerGastoPreview(){
    BannerBalance(getGastosTotales(gastosMock), 3000.0)
}