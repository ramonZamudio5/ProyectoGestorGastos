package zamudio.ramon.proyectogestorgastos.view.components

import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import zamudio.ramon.proyectogestorgastos.model.dominio.Gasto
import zamudio.ramon.proyectogestorgastos.model.dominio.Tipo
import zamudio.ramon.proyectogestorgastos.model.data.gastosMock
import zamudio.ramon.proyectogestorgastos.model.data.getGastosByTipo
import zamudio.ramon.proyectogestorgastos.model.data.getGastosTotales
import zamudio.ramon.proyectogestorgastos.model.data.getPorcentajeTipo
import zamudio.ramon.proyectogestorgastos.model.data.getPresupuestoTotal
import zamudio.ramon.proyectogestorgastos.model.data.getTiposInGastos
import zamudio.ramon.proyectogestorgastos.ui.theme.Purple40
import zamudio.ramon.proyectogestorgastos.ui.theme.Purple80
import zamudio.ramon.proyectogestorgastos.ui.theme.PurpleGrey40
import zamudio.ramon.proyectogestorgastos.ui.theme.Typography
import zamudio.ramon.proyectogestorgastos.ui.theme.White
import zamudio.ramon.proyectogestorgastos.ui.theme.fondo
import zamudio.ramon.proyectogestorgastos.ui.theme.lightGreen
import zamudio.ramon.proyectogestorgastos.ui.theme.lightRed
import java.text.DateFormatSymbols
import java.time.LocalDate
import java.util.Locale

@Composable
fun ColumnaGastos(gastos: List<Gasto>) {
    LazyColumn(Modifier.padding(5.dp).fillMaxHeight(0.6f)) {
        items(gastos) { gasto ->
            BannerGasto(gasto)
        }
    }
}

@Composable
fun ColumnaGastosPorFiltro(filtro: String, gastos: List<Gasto>) {
    val filtroLower = filtro.lowercase()
    val mesesNombres = remember { DateFormatSymbols(Locale("es", "ES")).months }

    val gastosAgrupados = when (filtroLower) {
        "todo" -> gastos.groupBy { "Todos tus gastos" }
        "dia" -> gastos.groupBy { "Día ${it.fecha.day}/${it.fecha.month}/${it.fecha.year}" }
        "mes" -> gastos.groupBy {
            val mesNum = it.fecha.month
            val nombreMes = if (mesNum in 1..12) mesesNombres[mesNum - 1].replaceFirstChar { c -> c.uppercase() } else "$mesNum"
            "$nombreMes ${it.fecha.year}"
        }
        else -> {
            val gastosFiltrados = gastos.filter { it.tipo.toString().equals(filtro, ignoreCase = true) }
            gastosFiltrados.groupBy { it.tipo.toString() }
        }
    }

    LazyColumn(modifier = Modifier.padding(5.dp)) {
        gastosAgrupados.forEach { (encabezado, gastosDelGrupo) ->
            item {
                Text(
                    text = encabezado,
                    style = Typography.titleMedium,
                    color = PurpleGrey40,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
            items(gastosDelGrupo) { gasto ->
                BannerGasto(gasto)
            }
        }
    }
}

@Composable
fun RowDeFiltros(
    gastos: List<Gasto>,
    filtroSeleccionado: String,
    onFiltroSelected: (String) -> Unit
) {
    val tipos = getTiposInGastos(gastos)

    LazyRow(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            FiltroChip(
                texto = "Todo",
                seleccionado = filtroSeleccionado.equals("todo", ignoreCase = true),
                onClick = { onFiltroSelected("todo") }
            )
        }
        item {
            FiltroChip(
                texto = "Día",
                seleccionado = filtroSeleccionado.equals("dia", ignoreCase = true),
                onClick = { onFiltroSelected("dia") }
            )
        }
        item {
            FiltroChip(
                texto = "Mes",
                seleccionado = filtroSeleccionado.equals("mes", ignoreCase = true),
                onClick = { onFiltroSelected("mes") }
            )
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

    Box(
        modifier = Modifier
            .background(color = colorFondo, shape = RoundedCornerShape(10.dp))
            .border(
                width = 1.dp,
                color = if (seleccionado) Color.Transparent else Color.LightGray,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 14.dp, vertical = 8.dp)
    ) {
        Text(text = texto, color = colorTexto, style = Typography.bodyMedium)
    }
}

@Composable
fun BannerGasto(gasto: Gasto) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 2.dp)
            .background(color = White, shape = RoundedCornerShape(10.dp))
            .draggable(
                orientation = Orientation.Horizontal,
                enabled = true,
                state = DraggableState {}
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(modifier = Modifier.padding(10.dp), contentAlignment = Alignment.Center) {
            Box(
                Modifier
                    .background(color = Purple80, shape = CircleShape)
                    .size(30.dp)
            )
            Image(
                painter = painterResource(gasto.imagen),
                contentDescription = "${gasto.nombre} imagen",
                modifier = Modifier.size(20.dp)
            )
        }
        Column(Modifier.padding(5.dp).weight(1f)) {
            Text(gasto.nombre, style = Typography.titleMedium)
            Text(
                "${gasto.tipo} • ${gasto.fecha.day}/${gasto.fecha.month}",
                style = Typography.labelSmall
            )
        }
        Column(Modifier.padding(end = 10.dp), horizontalAlignment = Alignment.End) {
            Text("$${gasto.cantidad}", style = Typography.titleMedium)
            Text(gasto.tipoPago.toString(), style = Typography.labelSmall)
        }
    }
}

@Composable
fun BannerBalance(gastos: Double, ingresos: Double) {
    Column(
        Modifier
            .background(color = White, shape = RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text("BALANCE TOTAL", style = Typography.titleMedium, color = PurpleGrey40)
        Spacer(Modifier.size(5.dp))
        Row(verticalAlignment = Alignment.Bottom) {
            val balance = ingresos - gastos
            Text("$${"%.2f".format(balance)}", style = Typography.headlineLarge)
            Spacer(Modifier.width(4.dp))
            Text("MXN", style = Typography.bodySmall)
        }
        Spacer(Modifier.height(10.dp))
        Row(
            Modifier
                .background(color = White, shape = RoundedCornerShape(10.dp))
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                Modifier
                    .background(color = lightGreen, shape = RoundedCornerShape(10.dp))
                    .padding(10.dp)
            ) {
                Text("Ingresos del mes", style = Typography.labelMedium)
                Text("+$${ingresos}", style = Typography.titleMedium)
            }
            Column(
                Modifier
                    .background(color = lightRed, shape = RoundedCornerShape(10.dp))
                    .padding(10.dp)
            ) {
                Text("Gastos del mes", style = Typography.labelMedium)
                Text("-$${gastos}", style = Typography.titleMedium)
            }
        }
    }
}

@Composable
fun BannerDistribucion(gastos: List<Gasto>) {
    Column(
        Modifier
            .background(color = White, shape = RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        val tipos = getTiposInGastos(gastos)
        val gastosTotales = getGastosTotales(gastos)

        Text("Distribución de Gastos", style = Typography.titleMedium)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .clip(RoundedCornerShape(10.dp))
        ) {
            tipos.forEach { tipo ->
                val fraccion = if (gastosTotales > 0) {
                    (getGastosTotales(getGastosByTipo(tipo)) / gastosTotales).toFloat()
                } else 0f
                if (fraccion > 0f) {
                    Box(
                        modifier = Modifier
                            .weight(fraccion)
                            .fillMaxHeight()
                            .background(tipo.color)
                    )
                }
            }
        }

        val celdasPorFila = tipos.chunked(2)
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            celdasPorFila.forEach { fila ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    fila.forEach { tipo ->
                        Box(modifier = Modifier.weight(1f)) {
                            CeldaTipoGasto(tipo, gastos)
                        }
                    }
                    if (fila.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
fun CeldaTipoGasto(tipo: Tipo, gastos: List<Gasto>) {
    Column(
        Modifier
            .background(color = fondo, shape = RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Box(
                Modifier
                    .background(color = tipo.color, shape = CircleShape)
                    .size(15.dp)
            )
            Text(tipo.toString(), style = Typography.titleMedium)
        }
        Spacer(Modifier.height(4.dp))
        Row {
            val porcentaje = getPorcentajeTipo(
                getGastosTotales(getGastosByTipo(tipo)),
                getGastosTotales(gastos)
            )
            Text("$porcentaje%")
            Text(" • ")
            Text("$${getGastosTotales(getGastosByTipo(tipo))} MXN")
        }
    }
}

@Composable
fun GraficaGastos(gastos: List<Gasto>) {
    var posicion = -90f
    val tipos = getTiposInGastos(gastos)
    val gastosTotales = getGastosTotales(gastos)
    val strokeWidthPx = with(LocalDensity.current) { 15.dp.toPx() }
    Column(Modifier
        .background(color = White, shape = RoundedCornerShape(10.dp))
        .fillMaxWidth()
        .padding(15.dp)) {
        Text("Gastos Totales", style = Typography.titleSmall)
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("$$gastosTotales", style = Typography.titleLarge)
            Text("Presupuesto asignado: $" + getPresupuestoTotal(gastos).toString() + "MXN", color= PurpleGrey40)
            Box(modifier = Modifier.fillMaxWidth().padding(10.dp), contentAlignment = Alignment.Center) {
                Canvas(Modifier.size(50.dp * 2f)){
                    drawArc(color= fondo, posicion, 360f, false,
                        style = Stroke(strokeWidthPx, cap=StrokeCap.Round))
                }
                tipos.forEach { tipo->
                    val fraccion = if (gastosTotales > 0) {
                        (getGastosTotales(getGastosByTipo(tipo)) / gastosTotales).toFloat()
                    } else 0f
                    Canvas(Modifier.size(50.dp * 2f)){
                        drawArc(color= tipo.color, posicion, 360f * fraccion, false,
                            style = Stroke(strokeWidthPx, cap=StrokeCap.Round))
                        posicion += 360f * fraccion
                    }
                }
            }
            val celdasPorFila = tipos.chunked(2)
            Column(Modifier.background(color = fondo, shape = RoundedCornerShape(10.dp))
                .fillMaxWidth().padding(10.dp), verticalArrangement = Arrangement.spacedBy(5.dp)) {
                celdasPorFila.forEach { fila ->
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                        fila.forEach { tipo ->
                            Row(verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(5.dp)
                            ) {
                                Box(
                                    Modifier.background(color = tipo.color, shape = CircleShape)
                                        .size(15.dp)
                                )
                                Text(tipo.toString() + ": " +
                                        getPorcentajeTipo(getGastosTotales(getGastosByTipo(tipo)), gastosTotales)
                                    + "%", style = Typography.labelMedium)
                            }
                        }
                        if (fila.size == 1) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BannerPromedioDiario(gastos: List<Gasto>) {
    val fechaActual = LocalDate.now()
    val gastosMesActual = gastos.filter {
        it.fecha.month == fechaActual.monthValue && it.fecha.year == fechaActual.year
    }

    val totalGastadoMes = getGastosTotales(gastosMesActual)
    val diasDelMes = fechaActual.lengthOfMonth()
    val promedioDiario = if (diasDelMes > 0) totalGastadoMes / diasDelMes else 0.0

    Column(
        modifier = Modifier
            .background(color = White, shape = RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Text("Promedio Diario", style = Typography.titleSmall)
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "$${"%.2f".format(promedioDiario)} MXN",
            style = Typography.titleLarge
        )
    }
}

@Composable
fun ColumnaGastosVSEstimado(gastos: List<Gasto>) {
    val tipos = getTiposInGastos(gastos)

    Column(modifier = Modifier.background(color = White, shape = RoundedCornerShape(10.dp))
        .fillMaxWidth().padding(15.dp)) {
        Text(text = "Gastado VS Estimado", style = Typography.titleSmall)
        Spacer(modifier = Modifier.height(10.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            tipos.forEach { tipo ->
                val gastado = getGastosTotales(getGastosByTipo(tipo))
                val estimado = tipo.estimado
                val porcentajeBarra = if (estimado > 0) {
                    (gastado / estimado).coerceIn(0.0, 1.0).toFloat()
                } else 0f

                val excedePresupuesto = gastado > estimado && estimado > 0

                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = tipo.toString(),
                            style = Typography.labelMedium
                        )
                        Text(
                            text = "$${"%.2f".format(gastado)} / $${"%.2f".format(estimado)}",
                            style = Typography.labelMedium,
                            color = if (excedePresupuesto) Color.Red else Color.Unspecified
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(10.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(color = fondo)
                    ) {
                        if (porcentajeBarra > 0f) {
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth(fraction = porcentajeBarra)
                                    .background(color = if (excedePresupuesto) Color.Red else tipo.color)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BannerGastoPreview() {
    BannerBalance(getGastosTotales(gastosMock), 3000.0)
}