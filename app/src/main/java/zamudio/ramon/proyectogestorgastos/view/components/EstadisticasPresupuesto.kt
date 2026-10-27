package zamudio.ramon.proyectogestorgastos.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import zamudio.ramon.proyectogestorgastos.ui.theme.Typography
import zamudio.ramon.proyectogestorgastos.ui.theme.White
import zamudio.ramon.proyectogestorgastos.ui.theme.purpleButton
import zamudio.ramon.proyectogestorgastos.ui.theme.PurplePrimary
import zamudio.ramon.proyectogestorgastos.R

@Composable
fun EstadisticasPresupuesto(
    ingresos: Double,
    gastosObligados: Double,
    disponible: Double,
    onAjustarPresupuesto: () -> Unit,
    onEditarIngresos: () -> Unit
) {
    Column(
        Modifier.fillMaxWidth().background(White, RoundedCornerShape(18.dp)).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(painterResource(R.drawable.ic_graficas), contentDescription = null, modifier = Modifier.size(18.dp))
            Text("ESTADÍSTICAS", Modifier.padding(start = 6.dp), style = Typography.labelLarge, color = PurplePrimary, fontWeight = FontWeight.Bold)
        }
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            EstadisticaCelda("↓  Ingresos del mes", ingresos, Color(0xFFF0F2FF), Modifier.weight(1f), onEditarIngresos)
            EstadisticaCelda("↑  Gastos obligados", gastosObligados, Color(0xFFFFF0F0), Modifier.weight(1f))
        }
        Column(Modifier.fillMaxWidth().background(Color(0xFFF0EDFF), RoundedCornerShape(14.dp)).padding(12.dp)) {
            Text("RESTANTE DISPONIBLE MENSUAL", style = Typography.labelSmall, color = Color.DarkGray)
            Row(verticalAlignment = Alignment.Bottom) {
                Text("\$${disponible.formatoDinero()}", style = Typography.headlineLarge, color = purpleButton, fontWeight = FontWeight.ExtraBold)
                Text(" MXN", style = Typography.labelSmall, color = PurplePrimary, modifier = Modifier.padding(bottom = 5.dp))
            }
        }
        Button(
            onClick = onAjustarPresupuesto,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = purpleButton)
        ) {
            Text("＋  Agregar / Ajustar Presupuesto\nMensual", color = White, style = Typography.labelLarge)
        }
    }
}

@Composable
private fun EstadisticaCelda(
    titulo: String,
    cantidad: Double,
    color: Color,
    modifier: Modifier,
    onClick: (() -> Unit)? = null
) {
    Column(modifier
        .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier)
        .background(color, RoundedCornerShape(12.dp)).padding(10.dp)) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(titulo, modifier = Modifier.weight(1f), style = Typography.labelSmall, color = Color.DarkGray)
            if (onClick != null) {
                Image(painterResource(R.drawable.ic_edit), contentDescription = "Editar ingreso mensual", modifier = Modifier.size(14.dp))
            }
        }
        Spacer(Modifier.size(2.dp))
        Text("\$${cantidad.formatoDinero()}", style = Typography.titleMedium, fontWeight = FontWeight.Bold)
        Text("MXN", style = Typography.labelSmall, color = Color.Gray)
    }
}

fun Double.formatoDinero(): String = "%1$,.2f".format(this)
