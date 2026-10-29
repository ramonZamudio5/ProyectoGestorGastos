package zamudio.ramon.proyectogestorgastos.view.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupFieldBackground
import zamudio.ramon.proyectogestorgastos.ui.theme.White
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupIconSecondary
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupTextSecondary
import zamudio.ramon.proyectogestorgastos.R
import zamudio.ramon.proyectogestorgastos.model.dominio.MetodoPago
import zamudio.ramon.proyectogestorgastos.ui.theme.PurplePrimary

@Composable
fun PaymentMethodSelector(
    metodoSeleccionado: MetodoPago,
    modifier: Modifier = Modifier,
    onMetodoSelected: (MetodoPago) -> Unit = {}
){
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            text = "Método de pago",
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = GroupTextSecondary
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            MetodoPago.values().forEach { metodo ->
                val seleccionado = metodo == metodoSeleccionado
                val iconRes = when (metodo) {
                    MetodoPago.SPEI -> R.drawable.ic_spei
                    MetodoPago.EFECTIVO -> R.drawable.ic_efectivo
                    MetodoPago.MERCADO_PAGO -> R.drawable.ic_mercado_pago
                }
                Surface(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(12.dp))
                        .clickable { onMetodoSelected(metodo) },
                    color = if (seleccionado) PurplePrimary else GroupFieldBackground,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(vertical = 10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            painter = painterResource(iconRes),
                            contentDescription = "${metodo.name} icon",
                            tint = if (seleccionado) White else GroupIconSecondary,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = metodo.name,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = if (seleccionado) White else GroupIconSecondary
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PaymentMethodSelectorScreen(){
    PaymentMethodSelector(MetodoPago.MERCADO_PAGO)
    PaymentMethodSelector(MetodoPago.EFECTIVO)
    PaymentMethodSelector(MetodoPago.SPEI)

}
