package zamudio.ramon.proyectogestorgastos.view.screens

import android.app.DatePickerDialog
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import zamudio.ramon.proyectogestorgastos.R
import zamudio.ramon.proyectogestorgastos.model.Gasto
import zamudio.ramon.proyectogestorgastos.model.dominio.Tipo
import zamudio.ramon.proyectogestorgastos.ui.theme.Typography
import zamudio.ramon.proyectogestorgastos.ui.theme.White
import zamudio.ramon.proyectogestorgastos.ui.theme.fondo
import zamudio.ramon.proyectogestorgastos.ui.theme.purpleButton
import zamudio.ramon.proyectogestorgastos.ui.theme.purplePrimary
import zamudio.ramon.proyectogestorgastos.view.components.Header
import zamudio.ramon.proyectogestorgastos.view.components.NavBar
import java.util.Calendar

@Composable
fun RegistroGastoScreen(
    innerPadding: PaddingValues = PaddingValues(0.dp),
    onNavigateToPerfil: () -> Unit = {},
    onGuardar: (Gasto) -> Unit = {}
) {
    var esGasto by remember { mutableStateOf(true) }
    var monto by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }
    var tipoPago by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var recibo by remember { mutableStateOf("") }
    val context = LocalContext.current
    val receiptPicker = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        recibo = uri?.lastPathSegment?.substringAfterLast('/') ?: ""
    }
    val calendar = Calendar.getInstance()

    Column(Modifier.fillMaxSize().background(fondo).padding(innerPadding)) {
        Header(PaddingValues(horizontal = 16.dp, vertical = 10.dp), onNavigateToPerfil)
        Column(
            Modifier.weight(1f).fillMaxWidth().verticalScroll(rememberScrollState()).padding(horizontal = 28.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(11.dp)
        ) {
            Row(Modifier.fillMaxWidth().clip(RoundedCornerShape(10.dp)).background(White).padding(4.dp)) {
                TipoMovimientoButton("Gasto", esGasto, Modifier.weight(1f)) {
                    if (!esGasto) {
                        esGasto = true
                        categoria = ""
                        tipoPago = ""
                    }
                }
                TipoMovimientoButton("Ingreso", !esGasto, Modifier.weight(1f)) {
                    if (esGasto) {
                        esGasto = false
                        categoria = ""
                        tipoPago = ""
                    }
                }
            }
            CampoRegistro(
                if (esGasto) "Monto del gasto" else "Monto del ingreso",
                monto, { monto = it }, "0.00", KeyboardType.Decimal
            )
            CampoRegistro(
                if (esGasto) "Descripción del gasto" else "Descripción del ingreso",
                descripcion, { descripcion = it },
                if (esGasto) "¿En qué consiste el gasto?" else "¿De dónde proviene el ingreso?"
            )
            SelectorRegistro(
                if (esGasto) "Categoría del gasto" else "Categoría del ingreso",
                categoria,
                if (esGasto) listOf("Alimentos", "Hogar y servicios", "Transporte", "Ocio y salidas", "Salud", "Otros")
                else listOf("Sueldo", "Ventas", "Inversiones", "Trabajo freelance", "Regalo", "Otros"),
            ) { categoria = it }
            SelectorRegistro(
                if (esGasto) "Tipo de pago" else "Método de recepción",
                tipoPago,
                if (esGasto) listOf("Efectivo", "Tarjeta de débito", "Tarjeta de crédito", "Transferencia", "Otro")
                else listOf("Efectivo", "Transferencia", "Depósito", "Otro")
            ) { tipoPago = it }
            Text(
                if (esGasto) "Foto del recibo (opcional)" else "Comprobante del ingreso (opcional)",
                style = Typography.labelLarge, fontWeight = FontWeight.SemiBold
            )
            Row(
                Modifier.fillMaxWidth().height(48.dp).clip(RoundedCornerShape(10.dp)).background(White)
                    .clickable { receiptPicker.launch("image/*") }.padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    if (recibo.isBlank()) {
                        if (esGasto) "Toca para subir el recibo" else "Toca para subir el comprobante"
                    } else recibo,
                    style = Typography.bodyMedium, color = Color.Gray
                )
                Icon(
                    painterResource(R.drawable.ic_camara),
                    contentDescription = if (esGasto) "Adjuntar recibo" else "Adjuntar comprobante",
                    tint = Color.Black, modifier = Modifier.size(24.dp)
                )
            }
            Text("Fecha", style = Typography.labelLarge, fontWeight = FontWeight.SemiBold)
            Row(
                Modifier.fillMaxWidth().height(48.dp).clip(RoundedCornerShape(10.dp)).background(White)
                    .clickable {
                        DatePickerDialog(context, { _, year, month, day -> fecha = "%02d/%02d/%04d".format(day, month + 1, year) },
                            calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
                    }.padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(painterResource(R.drawable.ic_calendar_outline), contentDescription = null, tint = Color.DarkGray, modifier = Modifier.size(18.dp))
                Text(if (fecha.isBlank()) "mm/dd/aaaa" else fecha, Modifier.padding(start = 10.dp), style = Typography.bodyMedium, color = if (fecha.isBlank()) Color.Gray else Color.Black)
            }
            Spacer(Modifier.height(8.dp))
            Button(
                onClick = {
                    val gasto = Gasto(
                        nombre = descripcion.ifBlank { categoria },
                        tipo = categoria.toTipoGasto(),
                        cantidad = monto.toDoubleOrNull() ?: 0.0,
                        fecha = fecha,
                        imagen = categoria.toIconoGasto()
                    )
                    onGuardar(gasto)
                },
                Modifier.fillMaxWidth().height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = purpleButton),
                enabled = monto.isNotBlank() && categoria.isNotBlank()
            ) {
                Icon(painterResource(R.drawable.ic_anadir), contentDescription = null, modifier = Modifier.size(18.dp))
                Text(if (esGasto) "Agregar Gasto" else "Agregar Ingreso", Modifier.padding(start = 8.dp), color = White)
            }
        }
        NavBar()
    }
}

private fun String.toTipoGasto(): Tipo = when (this) {
    "Alimentos" -> Tipo.COMIDA
    "Hogar y servicios" -> Tipo.LUZ
    "Transporte" -> Tipo.GASOLINA
    "Sueldo", "Ventas", "Inversiones", "Trabajo freelance", "Regalo" -> Tipo.OTROS
    else -> Tipo.OTROS
}

private fun String.toIconoGasto(): Int = when (this) {
    "Alimentos" -> R.drawable.ic_car
    "Hogar y servicios" -> R.drawable.ic_energy
    "Transporte" -> R.drawable.ic_car
    else -> R.drawable.ic_circle_empty
}

@Composable
private fun TipoMovimientoButton(text: String, seleccionado: Boolean, modifier: Modifier, onClick: () -> Unit) {
    Button(
        onClick = onClick, modifier = modifier.height(38.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = if (seleccionado) purplePrimary else White, contentColor = if (seleccionado) White else Color.Black),
        contentPadding = PaddingValues(0.dp)
    ) { Text(text, style = Typography.labelLarge) }
}

@Composable
private fun CampoRegistro(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
        Text(label, style = Typography.labelLarge, fontWeight = FontWeight.SemiBold)
        OutlinedTextField(
            value, onValueChange, modifier = Modifier.fillMaxWidth().height(52.dp),
            placeholder = { Text(placeholder, style = Typography.bodyMedium, color = Color.Gray) },
            singleLine = true, keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            shape = RoundedCornerShape(10.dp),
            colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
                focusedBorderColor = purplePrimary, unfocusedBorderColor = Color.Transparent,
                focusedContainerColor = White, unfocusedContainerColor = White
            )
        )
    }
}

@Composable
private fun SelectorRegistro(label: String, seleccionado: String, opciones: List<String>, onSelect: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
        Text(label, style = Typography.labelLarge, fontWeight = FontWeight.SemiBold)
        androidx.compose.foundation.layout.Box {
            Surface(
                modifier = Modifier.fillMaxWidth().height(48.dp).clickable { expanded = true },
                shape = RoundedCornerShape(10.dp), color = White
            ) {
                Row(
                    Modifier.padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        seleccionado.ifBlank { "Selecciona una opción" },
                        style = Typography.bodyMedium,
                        color = if (seleccionado.isBlank()) Color.Gray else Color.Black
                    )
                    Icon(
                        painterResource(R.drawable.ic_chevron_up),
                        contentDescription = if (expanded) "Cerrar opciones" else "Mostrar opciones",
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }, modifier = Modifier.width(280.dp)) {
                opciones.forEach { opcion ->
                    DropdownMenuItem(
                        text = { Text(opcion) },
                        onClick = { onSelect(opcion); expanded = false }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RegistroGastoPreview() {
    RegistroGastoScreen()
}
