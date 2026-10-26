package zamudio.ramon.proyectogestorgastos.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import zamudio.ramon.proyectogestorgastos.ui.theme.Typography
import zamudio.ramon.proyectogestorgastos.ui.theme.White
import zamudio.ramon.proyectogestorgastos.ui.theme.purplePrimary
import zamudio.ramon.proyectogestorgastos.model.dominio.CategoriaPresupuesto

@Composable
fun TarjetaCategoriaPresupuesto(
    categoria: CategoriaPresupuesto,
    onClick: () -> Unit
) {
    val porcentaje = if (categoria.limite <= 0) 0 else (categoria.gastado / categoria.limite * 100).toInt()
    val estado = when {
        porcentaje >= 95 -> "Alerta crítica"
        porcentaje >= 80 -> "Cerca del límite"
        else -> null
    }
    Column(
        Modifier.fillMaxWidth().background(White, RoundedCornerShape(16.dp)).clickable(onClick = onClick).padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(categoria.icono),
                contentDescription = categoria.nombre,
                modifier = Modifier.background(categoria.colorClaro, RoundedCornerShape(9.dp)).padding(7.dp).height(24.dp)
            )
            Column(Modifier.weight(1f).padding(start = 9.dp)) {
                Text(categoria.nombre, style = Typography.titleSmall, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(categoria.descripcion, style = Typography.labelSmall, color = Color.DarkGray, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
            Column(horizontalAlignment = Alignment.End) {
                Text("\$${categoria.gastado.formatoDinero()}", style = Typography.labelLarge, fontWeight = FontWeight.Bold)
                Text("de \$${categoria.limite.formatoDinero()}", style = Typography.labelSmall, color = Color.DarkGray)
            }
        }
        Box(Modifier.fillMaxWidth().height(7.dp).background(Color(0xFFE8E8F2), RoundedCornerShape(8.dp))) {
            Box(Modifier.fillMaxWidth(categoria.progreso).height(7.dp).background(categoria.color, RoundedCornerShape(8.dp)))
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                "$porcentaje% consumido",
                modifier = Modifier.background(categoria.colorClaro, RoundedCornerShape(20.dp)).padding(horizontal = 7.dp, vertical = 2.dp),
                style = Typography.labelSmall,
                color = categoria.color
            )
            estado?.let {
                Text("  $it", style = Typography.labelSmall, color = if (porcentaje >= 95) Color(0xFFB91C1C) else Color(0xFF8B7200))
            }
            Column(Modifier.weight(1f), horizontalAlignment = Alignment.End) {
                Text("Restan \$${(categoria.limite - categoria.gastado).coerceAtLeast(0.0).formatoDinero()}", style = Typography.labelSmall, color = purplePrimary)
            }
        }
    }
}
