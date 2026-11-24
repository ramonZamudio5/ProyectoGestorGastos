package zamudio.ramon.proyectogestorgastos.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zamudio.ramon.proyectogestorgastos.R
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupFieldBackground
import zamudio.ramon.proyectogestorgastos.ui.theme.Typography


@Composable
fun PasswordRequirementsCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.secondary)
            .padding(16.dp)
    ) {
        Text(
            text = "REQUISITOS DE SEGURIDAD",
            style = Typography.labelLarge.copy(fontSize = 10.sp),
            color = Color(0xFF4B5563)
        )
        Spacer(Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            RequisitoItem(text = "Mín. 8 caracteres", modifier = Modifier.weight(1f))
            RequisitoItem(text = "Al menos 1 número", modifier = Modifier.weight(1f))
        }
        Spacer(Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            RequisitoItem(text = "Una mayúscula", modifier = Modifier.weight(1f))
            RequisitoItem(text = "Símbolo especial", modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun RequisitoItem(
    text: String,
    modifier: Modifier = Modifier,
    isMet: Boolean = false
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.tertiary)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_circle_empty),
            contentDescription = null,
            tint = if (isMet) MaterialTheme.colorScheme.primary else Color(0xFF9CA3AF),
            modifier = Modifier.size(16.dp)
        )
        Spacer(Modifier.width(6.dp))
        Text(
            text = text,
            style = Typography.bodyMedium.copy(fontSize = 12.sp),
            color = if (isMet) MaterialTheme.colorScheme.onTertiary else Color(0xFF4B5563)
        )
    }
}
