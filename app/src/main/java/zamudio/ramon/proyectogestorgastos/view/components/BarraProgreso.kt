package zamudio.ramon.proyectogestorgastos.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import zamudio.ramon.proyectogestorgastos.ui.theme.BackGroundPurple
import zamudio.ramon.proyectogestorgastos.ui.theme.PurplePrimary
import zamudio.ramon.proyectogestorgastos.ui.theme.Typography

@Composable
fun BarraProgreso(pasoActual: Int, totalPasos: Int) {
    val progreso = pasoActual.toFloat() / totalPasos.toFloat()

    Row(
        Modifier.fillMaxWidth().padding(bottom = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Paso $pasoActual de $totalPasos",
            style = Typography.labelLarge,
            color = PurplePrimary
        )
        Spacer(Modifier.width(16.dp))
        Box(Modifier.weight(1f).height(8.dp).clip(RoundedCornerShape(4.dp)).background(
                BackGroundPurple)) {
            Box(
                modifier = Modifier.fillMaxWidth(progreso).fillMaxHeight().clip(RoundedCornerShape(4.dp)).background(PurplePrimary)
            )
        }
    }
}

@Preview
@Composable
fun PreviewBarraProgreso(){
    BarraProgreso(2,3)
}