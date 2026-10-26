package zamudio.ramon.proyectogestorgastos.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import zamudio.ramon.proyectogestorgastos.R

@Composable
fun AlertaPresupuesto(
    activa: Boolean,
    porcentaje: Int,
    onActivaChange: (Boolean) -> Unit,
    onPorcentajeChange: (Int) -> Unit
) {
    Column(
        Modifier.fillMaxWidth().background(White, RoundedCornerShape(16.dp)).padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.ic_notificaciones),
                contentDescription = null,
                modifier = Modifier.background(Color(0xFFFFF2B8), RoundedCornerShape(9.dp)).padding(7.dp)
            )
            Column(Modifier.weight(1f).padding(start = 8.dp)) {
                Text("Alertas de Presupuesto", style = Typography.titleSmall, fontWeight = FontWeight.Bold)
                Text("Previene sobregiros antes de fin de mes", style = Typography.labelSmall, color = Color.DarkGray)
            }
            Switch(
                checked = activa,
                onCheckedChange = onActivaChange,
                colors = SwitchDefaults.colors(checkedThumbColor = White, checkedTrackColor = purpleButton)
            )
        }
        if (activa) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text("Avisarme cuando supere el:", style = Typography.labelSmall)
                Text("$porcentaje%", Modifier.weight(1f), style = Typography.titleSmall, color = purpleButton, fontWeight = FontWeight.Bold, textAlign = androidx.compose.ui.text.style.TextAlign.End)
            }
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                listOf(70, 80, 90).forEach { value ->
                    Button(
                        onClick = { onPorcentajeChange(value) },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (value == porcentaje) purpleButton else Color(0xFFF0F0FF),
                            contentColor = if (value == porcentaje) White else Color.DarkGray
                        ),
                        contentPadding = androidx.compose.foundation.layout.PaddingValues(vertical = 6.dp)
                    ) { Text("$value%", style = Typography.labelSmall) }
                }
            }
            Row(
                Modifier.fillMaxWidth().background(Color(0xFFFFF2B8), RoundedCornerShape(12.dp)).padding(10.dp),
                verticalAlignment = Alignment.Top
            ) {
                Text("●", color = Color(0xFF675500), modifier = Modifier.padding(end = 6.dp))
                Column {
                    Text("ALERTA ACTIVA", style = Typography.labelSmall, color = Color(0xFF675500), fontWeight = FontWeight.Bold)
                    Text("Recibirás notificación push y por correo al llegar al $porcentaje% de cualquier categoría asignada.", style = Typography.labelSmall, color = Color(0xFF3F3A20))
                }
            }
        }
    }
}
