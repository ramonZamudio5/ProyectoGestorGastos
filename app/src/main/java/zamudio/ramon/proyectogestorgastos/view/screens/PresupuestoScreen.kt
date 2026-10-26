package zamudio.ramon.proyectogestorgastos.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import zamudio.ramon.proyectogestorgastos.ui.theme.Typography
import zamudio.ramon.proyectogestorgastos.ui.theme.White
import zamudio.ramon.proyectogestorgastos.ui.theme.fondo
import zamudio.ramon.proyectogestorgastos.ui.theme.purpleButton
import zamudio.ramon.proyectogestorgastos.ui.theme.purplePrimary
import zamudio.ramon.proyectogestorgastos.R
import zamudio.ramon.proyectogestorgastos.model.dominio.CategoriaPresupuesto
import zamudio.ramon.proyectogestorgastos.view.components.AlertaPresupuesto
import zamudio.ramon.proyectogestorgastos.view.components.TarjetaCategoriaPresupuesto
import zamudio.ramon.proyectogestorgastos.view.components.EstadisticasPresupuesto
import zamudio.ramon.proyectogestorgastos.view.components.Header
import zamudio.ramon.proyectogestorgastos.view.components.NavBar
import zamudio.ramon.proyectogestorgastos.view.components.formatoDinero

@Composable
fun PresupuestoScreen(
    innerPadding: PaddingValues = PaddingValues(0.dp),
    onNavigateToPerfil: () -> Unit = {}
) {
    val categorias = remember {
        mutableStateListOf(
            CategoriaPresupuesto("Alimentos", "Supermercado y despensa", R.drawable.ic_presupuesto, 7000.0, 4850.0, Color(0xFF6852E8), Color(0xFFE7E1FF)),
            CategoriaPresupuesto("Hogar y Servicios", "Luz, agua, internet, gas", R.drawable.ic_energy, 5000.0, 3900.0, Color(0xFFD55B25), Color(0xFFFFE6D8)),
            CategoriaPresupuesto("Transporte", "Gasolina y viajes diarios", R.drawable.ic_car, 3500.0, 3100.0, Color(0xFFC5A800), Color(0xFFFFF4A8)),
            CategoriaPresupuesto("Ocio y Salidas", "Restaurantes, cine y cafés", R.drawable.ic_grupos, 3000.0, 2950.0, Color(0xFFC3222B), Color(0xFFFFE1E1))
        )
    }
    var categoriaEditando by remember { mutableStateOf<CategoriaPresupuesto?>(null) }
    var alertaActiva by remember { mutableStateOf(true) }
    var porcentajeAlerta by remember { mutableStateOf(80) }
    var cantidadFija by remember { mutableStateOf(true) }
    var ingresoMensual by remember { mutableStateOf(35000.0) }
    var mostrarEditorIngreso by remember { mutableStateOf(false) }
    val gastosObligados = 14200.0
    val disponibleMensual = (ingresoMensual - gastosObligados).coerceAtLeast(0.0)

    Column(Modifier.fillMaxSize().background(fondo).padding(innerPadding)) {
        Header(PaddingValues(horizontal = 16.dp, vertical = 8.dp), onNavigateToPerfil)
        Column(
            Modifier.weight(1f).fillMaxWidth().verticalScroll(rememberScrollState()).padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            EstadisticasPresupuesto(
                ingresos = ingresoMensual,
                gastosObligados = gastosObligados,
                disponible = disponibleMensual,
                onAjustarPresupuesto = { categoriaEditando = categorias.firstOrNull() },
                onEditarIngresos = { mostrarEditorIngreso = true }
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Row(Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                    Image(painterResource(R.drawable.ic_presupuesto), contentDescription = null, modifier = Modifier.size(16.dp))
                    Text("Asignación por categorías", style = Typography.labelLarge, color = purplePrimary, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 5.dp))
                }
                Text("${categorias.size} de 4 activas", style = Typography.labelSmall, color = Color.DarkGray)
            }
            Row(Modifier.fillMaxWidth().background(Color(0xFFE8E7FA), RoundedCornerShape(10.dp)).padding(3.dp)) {
                SelectorAsignacion("＄ Por Cantidad", cantidadFija, Modifier.weight(1f)) { cantidadFija = true }
                SelectorAsignacion("% Por Porcentaje", !cantidadFija, Modifier.weight(1f)) { cantidadFija = false }
            }
            categorias.forEach { categoria ->
                TarjetaCategoriaPresupuesto(categoria) { categoriaEditando = categoria }
            }
            AlertaPresupuesto(
                activa = alertaActiva,
                porcentaje = porcentajeAlerta,
                onActivaChange = { alertaActiva = it },
                onPorcentajeChange = { porcentajeAlerta = it }
            )
            Spacer(Modifier.height(4.dp))
        }
        NavBar()
    }

    categoriaEditando?.let { categoria ->
        EditorCategoriaBottomSheet(
            categoria = categoria,
            modoCantidadFija = cantidadFija,
            presupuestoDisponible = disponibleMensual,
            onDismiss = { categoriaEditando = null },
            onConfirmar = { nuevoLimite ->
                val index = categorias.indexOfFirst { it.nombre == categoria.nombre }
                if (index >= 0) categorias[index] = categorias[index].copy(limite = nuevoLimite)
                categoriaEditando = null
            }
        )
    }

    if (mostrarEditorIngreso) {
        EditorIngresoMensualDialog(
            ingresoActual = ingresoMensual,
            onDismiss = { mostrarEditorIngreso = false },
            onGuardar = { nuevoIngreso ->
                ingresoMensual = nuevoIngreso
                mostrarEditorIngreso = false
            }
        )
    }
}

@Composable
private fun EditorIngresoMensualDialog(
    ingresoActual: Double,
    onDismiss: () -> Unit,
    onGuardar: (Double) -> Unit
) {
    var montoTexto by remember(ingresoActual) { mutableStateOf(ingresoActual.toLong().toString()) }
    val monto = montoTexto.toDoubleOrNull()
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Editar ingreso mensual", style = Typography.titleMedium, fontWeight = FontWeight.Bold) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Actualiza el ingreso mensual para recalcular el disponible del presupuesto.", style = Typography.bodyMedium)
                OutlinedTextField(
                    value = montoTexto,
                    onValueChange = { nuevo -> if (nuevo.all { it.isDigit() || it == '.' }) montoTexto = nuevo },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Ingreso mensual") },
                    suffix = { Text("MXN") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    singleLine = true
                )
            }
        },
        confirmButton = {
            TextButton(onClick = { monto?.let(onGuardar) }, enabled = monto != null && monto > 0) {
                Text("Guardar", color = purplePrimary)
            }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancelar") } }
    )
}

@Composable
private fun SelectorAsignacion(texto: String, seleccionado: Boolean, modifier: Modifier, onClick: () -> Unit) {
    Button(
        onClick = onClick, modifier = modifier.height(34.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = if (seleccionado) White else Color.Transparent, contentColor = Color(0xFF293044)),
        contentPadding = PaddingValues(horizontal = 4.dp)
    ) { Text(texto, style = Typography.labelSmall) }
}

@Composable
private fun EditorCategoriaBottomSheet(
    categoria: CategoriaPresupuesto,
    modoCantidadFija: Boolean,
    presupuestoDisponible: Double,
    onDismiss: () -> Unit,
    onConfirmar: (Double) -> Unit
) {
    var modoFijo by remember(categoria.nombre, modoCantidadFija) { mutableStateOf(modoCantidadFija) }
    var cantidadTexto by remember(categoria.nombre, modoCantidadFija) {
        mutableStateOf(
            if (modoCantidadFija) categoria.limite.toLong().toString()
            else (categoria.limite / presupuestoDisponible * 100.0).toInt().toString()
        )
    }
    val valor by remember(cantidadTexto) { mutableStateOf(cantidadTexto.toDoubleOrNull() ?: 0.0) }
    val porcentaje = if (presupuestoDisponible == 0.0) 0.0 else valor / presupuestoDisponible * 100.0
    val montoAsignado = if (modoFijo) valor else presupuestoDisponible * (valor / 100.0)
    val quedaLibre = (presupuestoDisponible - montoAsignado).coerceAtLeast(0.0)

    Dialog(onDismissRequest = onDismiss, properties = DialogProperties(usePlatformDefaultWidth = false)) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            Card(
                Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(topStart = 22.dp, topEnd = 22.dp),
                colors = CardDefaults.cardColors(containerColor = White)
            ) {
                Column(
                    Modifier.fillMaxWidth().navigationBarsPadding().padding(horizontal = 18.dp, vertical = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Box(Modifier.width(38.dp).height(4.dp).background(Color(0xFFD9D9E2), RoundedCornerShape(5.dp)).align(Alignment.CenterHorizontally))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(categoria.icono),
                            contentDescription = null,
                            modifier = Modifier.background(categoria.colorClaro, RoundedCornerShape(50)).padding(9.dp).size(22.dp)
                        )
                        Column(Modifier.weight(1f).padding(start = 9.dp)) {
                            Text("Modificar Asignación", style = Typography.titleSmall, fontWeight = FontWeight.Bold)
                            Text("Categoría: ${categoria.nombre}", style = Typography.labelSmall, color = Color.DarkGray)
                        }
                        Text("×", Modifier.clickable(onClick = onDismiss).padding(8.dp), color = Color.Gray)
                    }
                    Row(Modifier.fillMaxWidth().background(Color(0xFFE9E9FA), RoundedCornerShape(10.dp)).padding(3.dp)) {
                        SelectorAsignacion("＄ Monto Fijo", modoFijo, Modifier.weight(1f)) {
                            if (!modoFijo) cantidadTexto = (presupuestoDisponible * valor / 100.0).toLong().toString()
                            modoFijo = true
                        }
                        SelectorAsignacion("% Porcentaje", !modoFijo, Modifier.weight(1f)) {
                            if (modoFijo) cantidadTexto = (valor / presupuestoDisponible * 100.0).toInt().toString()
                            modoFijo = false
                        }
                    }
                    OutlinedTextField(
                        value = cantidadTexto,
                        onValueChange = { value -> if (value.all { it.isDigit() || it == '.' }) cantidadTexto = value },
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text(if (modoFijo) "Monto fijo (MXN)" else "Porcentaje del presupuesto") },
                        suffix = { Text(if (modoFijo) "MXN" else "%") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp)
                    )
                    Text(
                        if (modoFijo) "\$${valor.formatoDinero()} MXN" else "\$${montoAsignado.formatoDinero()} MXN (${valor.formatoDinero()}%)",
                        modifier = Modifier.fillMaxWidth(), style = Typography.headlineSmall,
                        color = purplePrimary, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center
                    )
                    Text("De un fondo disponible de \$${presupuestoDisponible.formatoDinero()} MXN", modifier = Modifier.fillMaxWidth(), style = Typography.labelSmall, textAlign = TextAlign.Center, color = Color.DarkGray)
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        listOf(-500, 500, 1000, 2000).forEach { ajuste ->
                            Button(
                                onClick = {
                                    val delta = if (modoFijo) ajuste.toDouble() else ajuste / 100.0
                                    cantidadTexto = (valor + delta).coerceAtLeast(0.0).toLong().toString()
                                },
                                modifier = Modifier.weight(1f), shape = RoundedCornerShape(20.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF0F0FF), contentColor = Color(0xFF293044)),
                                contentPadding = PaddingValues(horizontal = 2.dp, vertical = 4.dp)
                            ) { Text(if (modoFijo) if (ajuste < 0) "-\$${-ajuste}" else "+\$${ajuste}" else "${if (ajuste < 0) "-" else "+"}${kotlin.math.abs(ajuste) / 100}%", style = Typography.labelSmall) }
                        }
                    }
                    Column(Modifier.fillMaxWidth().background(Color(0xFFF1F0FF), RoundedCornerShape(12.dp)).padding(11.dp)) {
                        Text("◉  Impacto en saldo restante: te quedarán", style = Typography.labelSmall)
                        Text("\$${quedaLibre.formatoDinero()} MXN libres para tus otras categorías.", style = Typography.labelSmall, color = purplePrimary)
                    }
                    Button(
                        onClick = { onConfirmar(montoAsignado) }, modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(11.dp), colors = ButtonDefaults.buttonColors(containerColor = purpleButton),
                        enabled = montoAsignado > 0
                    ) { Text("✓  Confirmar y Aplicar (\$${montoAsignado.formatoDinero()} MXN)", color = White, style = Typography.labelLarge) }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PresupuestoPreview() {
    PresupuestoScreen()
}
