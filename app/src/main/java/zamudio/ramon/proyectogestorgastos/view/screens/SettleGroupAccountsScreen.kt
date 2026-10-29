package zamudio.ramon.proyectogestorgastos.view.screens

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupSettlementBackground
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupTextPrimary
import zamudio.ramon.proyectogestorgastos.ui.theme.White
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupTextSecondary
import zamudio.ramon.proyectogestorgastos.R
import zamudio.ramon.proyectogestorgastos.model.data.getMiembros
import zamudio.ramon.proyectogestorgastos.model.dominio.MetodoPago
import zamudio.ramon.proyectogestorgastos.ui.theme.PurplePrimary
import zamudio.ramon.proyectogestorgastos.view.components.GroupChip
import zamudio.ramon.proyectogestorgastos.view.components.MemberDropdownSelector
import zamudio.ramon.proyectogestorgastos.view.components.PaymentMethodSelector
import zamudio.ramon.proyectogestorgastos.view.components.ReceiptAttachmentButton
import zamudio.ramon.proyectogestorgastos.view.components.TransferAmountInput
import java.math.BigDecimal
import java.util.Locale

@Composable
fun SettleGroupAccountsScreen(
    innerPadding: PaddingValues = PaddingValues(0.dp),
    modifier: Modifier = Modifier,
    nombreGrupo: String = "Cancún 2025",
    deudaTotal: Double = 1000.0,
    onConfirmarLiquidacion: (quienSalda: String, quienRecibe: String, monto: Double, metodo: MetodoPago) -> Unit = { _, _, _, _ -> }
) {
    var quienSalda by remember { mutableStateOf(getMiembros().firstOrNull() ?: "") }
    var quienRecibe by remember { mutableStateOf(getMiembros().getOrNull(1) ?: "") }
    var montoTexto by remember(deudaTotal) {
        mutableStateOf(String.format(Locale.ROOT, "%.2f", deudaTotal))
    }
    var metodoSeleccionado by remember { mutableStateOf(MetodoPago.SPEI) }
    val montoNormalizado = montoTexto.trim().replace(',', '.')
    val monto = if (montoNormalizado.matches(Regex("[0-9]+(?:\\.[0-9]{1,2})?"))) {
        montoNormalizado.toBigDecimalOrNull()
    } else {
        null
    }
    val errorMonto = when {
        montoTexto.isBlank() -> "Ingresa el monto transferido."
        monto == null -> "Ingresa un monto válido con hasta 2 decimales, sin separadores de miles."
        monto <= BigDecimal.ZERO -> "El monto debe ser mayor que cero."
        !deudaTotal.isFinite() || deudaTotal <= 0.0 -> "No hay una deuda válida para liquidar."
        monto > BigDecimal.valueOf(deudaTotal) -> "El monto no puede superar la deuda total."
        else -> null
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(GroupSettlementBackground)
            .padding(innerPadding)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Chip del Grupo
        GroupChip(nombreGrupo = nombreGrupo)

        // Tarjeta Principal
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = White),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Título con punto morado
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(PurplePrimary)
                    )
                    Text(
                        text = "Registrar Liquidación",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = GroupTextPrimary
                    )
                }

                // Componentes modulares
                MemberDropdownSelector(
                    label = "¿Quién saldó o transfirió?",
                    members = getMiembros(),
                    selectedMember = quienSalda,
                    onMemberSelected = { quienSalda = it }
                )

                MemberDropdownSelector(
                    label = "¿Quién recibió?",
                    members = getMiembros(),
                    selectedMember = quienRecibe,
                    onMemberSelected = { quienRecibe = it }
                )

                TransferAmountInput(
                    montoTexto = montoTexto,
                    onMontoChange = { montoTexto = it },
                    deudaTotal = deudaTotal,
                    errorMessage = errorMonto,
                    onLiquidarTotalClick = {
                        montoTexto = String.format(Locale.ROOT, "%.2f", deudaTotal)
                    }
                )

                PaymentMethodSelector(
                    metodoSeleccionado = metodoSeleccionado,
                    onMetodoSelected = { metodoSeleccionado = it }
                )

                ReceiptAttachmentButton(
                    onClick = { /* Lógica comprobante */ }
                )

                // Botón de acción principal
                Button (
                    onClick = {
                        if (errorMonto == null && monto != null) {
                            onConfirmarLiquidacion(quienSalda, quienRecibe, monto.toDouble(), metodoSeleccionado)
                        }
                    },
                    enabled = errorMonto == null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PurplePrimary)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_check),
                        contentDescription = null,
                        tint = White,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Confirmar y Actualizar Balances",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = White
                    )
                }

                Text(
                    text = "Al confirmar, las deudas de este grupo se reducirán automáticamente para todos los miembros.",
                    fontSize = 11.sp,
                    color = GroupTextSecondary,
                    textAlign = TextAlign.Center,
                    lineHeight = 15.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview
@Composable
fun SettleGroupAccountsScreenPreview(){
    SettleGroupAccountsScreen()
}
