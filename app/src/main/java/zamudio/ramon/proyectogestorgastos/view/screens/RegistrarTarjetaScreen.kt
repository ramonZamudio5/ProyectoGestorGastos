package zamudio.ramon.proyectogestorgastos.view.screens

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import zamudio.ramon.proyectogestorgastos.R
import zamudio.ramon.proyectogestorgastos.model.dominio.TarjetaPago
import zamudio.ramon.proyectogestorgastos.model.dominio.TipoTarjeta
import zamudio.ramon.proyectogestorgastos.ui.theme.Typography
import zamudio.ramon.proyectogestorgastos.ui.theme.White
import zamudio.ramon.proyectogestorgastos.ui.theme.BackGroundPurple
import zamudio.ramon.proyectogestorgastos.ui.theme.purpleButton
import zamudio.ramon.proyectogestorgastos.ui.theme.PurplePrimary
import zamudio.ramon.proyectogestorgastos.view.components.Header
import zamudio.ramon.proyectogestorgastos.view.components.NavBar

private val bancosDisponibles = listOf(
    "BBVA México", "Nu México", "Santander", "Banorte", "HSBC", "Otro"
)

@Composable
fun RegistrarTarjetaScreen(
    innerPadding: PaddingValues = PaddingValues(0.dp),
    onGuardar: (TarjetaPago) -> Unit = {},
    onCancelar: () -> Unit = {},
    onNavigateToPerfil: () -> Unit = {},
    onNavigateToPrincipal: () -> Unit = {},
    onNavigateToPresupuesto: () -> Unit = {},
    onNavigateToRegistroGasto: () -> Unit = {}
) {
    var titular by remember { mutableStateOf("") }
    var banco by remember { mutableStateOf("") }
    var alias by remember { mutableStateOf("") }
    var ultimosDigitos by remember { mutableStateOf("") }
    var tipoTarjeta by remember { mutableStateOf(TipoTarjeta.DEBITO) }

    Column(Modifier
        .fillMaxSize()
        .background(Color(0xFFFAF9FF))
        .padding(innerPadding)) {
        Column(
            Modifier
                .weight(1f)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 22.dp, vertical = 26.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Spacer(Modifier.height(12.dp))
            Text("Registrar Tarjeta", style = Typography.headlineSmall, fontWeight = FontWeight.Bold, color = Color(0xFF101828))
            Spacer(Modifier.height(6.dp))
            CampoTarjeta("Nombre del Titular *", titular, { titular = it }, "Nombre como aparece en la tarjeta", R.drawable.ic_id_badge)
            SelectorBanco(banco = banco, onSeleccionar = { banco = it })
            CampoTarjeta("Alias o Nombre de la Tarjeta *", alias, { alias = it }, "Ej. Nómina Personal", R.drawable.ic_edit)
            CampoTarjeta(
                "Últimos 4 Dígitos *", ultimosDigitos,
                { nuevo -> if (nuevo.length <= 4 && nuevo.all(Char::isDigit)) ultimosDigitos = nuevo },
                "0000", null, KeyboardType.Number
            )
            Text("Tipo de Tarjeta *", style = Typography.labelLarge, fontWeight = FontWeight.SemiBold, color = Color(0xFF172033))
            Row(Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(BackGroundPurple)
                .padding(4.dp)) {
                TipoTarjetaButton("Débito", tipoTarjeta == TipoTarjeta.DEBITO, Modifier.weight(1f)) { tipoTarjeta = TipoTarjeta.DEBITO }
                TipoTarjetaButton("Crédito", tipoTarjeta == TipoTarjeta.CREDITO, Modifier.weight(1f)) { tipoTarjeta = TipoTarjeta.CREDITO }
            }
            Spacer(Modifier.height(4.dp))
            Button(
                onClick = {
                    onGuardar(TarjetaPago(banco, alias, tipoTarjeta, titular.trim(), ultimosDigitos))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = purpleButton),
                enabled = titular.isNotBlank() && banco.isNotBlank() && alias.isNotBlank() && ultimosDigitos.length == 4
            ) {
                Text("Guardar Tarjeta", style = Typography.titleSmall, color = White, fontWeight = FontWeight.Bold)
                Icon(painterResource(R.drawable.ic_arrow_right), contentDescription = null, tint = White, modifier = Modifier
                    .padding(start = 8.dp)
                    .size(19.dp))
            }
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text("Cancelar", Modifier
                    .clickable(onClick = onCancelar)
                    .padding(12.dp), style = Typography.labelLarge, color = Color(0xFF101828), fontWeight = FontWeight.Medium)
            }
        }
    }
}

@Composable
private fun CampoTarjeta(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    icono: Int?,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(label, style = Typography.labelLarge, fontWeight = FontWeight.SemiBold, color = Color(0xFF172033))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            placeholder = { Text(placeholder, style = Typography.bodyMedium, color = Color(0xFF98A2B3)) },
            trailingIcon = {
                if (icono != null) Icon(painterResource(icono), contentDescription = null, tint = Color(0xFF475467), modifier = Modifier.size(19.dp))
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            shape = RoundedCornerShape(13.dp),
            colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PurplePrimary,
                unfocusedBorderColor = Color.Transparent,
                focusedContainerColor = White,
                unfocusedContainerColor = White
            )
        )
    }
}

@Composable
private fun SelectorBanco(banco: String, onSeleccionar: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text("Banco Emisor *", style = Typography.labelLarge, fontWeight = FontWeight.SemiBold, color = Color(0xFF172033))
        Box {
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .clip(RoundedCornerShape(13.dp))
                    .background(White)
                    .clickable { expanded = true }
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(banco.ifBlank { "Selecciona tu banco" }, Modifier.weight(1f), style = Typography.bodyMedium, color = if (banco.isBlank()) Color(0xFF98A2B3) else Color(0xFF172033))
                Icon(painterResource(R.drawable.ic_chevron_up), contentDescription = "Seleccionar banco", tint = Color(0xFF475467), modifier = Modifier.size(17.dp))
            }
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                bancosDisponibles.forEach { opcion ->
                    DropdownMenuItem(text = { Text(opcion) }, onClick = { onSeleccionar(opcion); expanded = false })
                }
            }
        }
    }
}

@Composable
private fun TipoTarjetaButton(texto: String, seleccionado: Boolean, modifier: Modifier, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = modifier.height(40.dp),
        shape = RoundedCornerShape(9.dp),
        colors = ButtonDefaults.buttonColors(
        containerColor = if (seleccionado) White else Color.Transparent,
        contentColor = if (seleccionado) PurplePrimary else Color(0xFF667085)
        ),
        contentPadding = PaddingValues(horizontal = 6.dp)
    ) {
        Icon(painterResource(R.drawable.ic_presupuesto), contentDescription = null, tint = if (seleccionado) PurplePrimary else Color(0xFF667085), modifier = Modifier.size(17.dp))
        Text(texto, Modifier.padding(start = 6.dp), style = Typography.labelLarge, fontWeight = FontWeight.SemiBold)
    }
}

@Preview(showBackground = true)
@Composable
private fun RegistrarTarjetaPreview() {
    RegistrarTarjetaScreen(PaddingValues(), {}, {}, {})
}
