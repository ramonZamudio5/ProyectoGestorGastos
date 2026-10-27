package zamudio.ramon.proyectogestorgastos.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupFieldBackground
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupTextPrimary
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupTextSecondary
import zamudio.ramon.proyectogestorgastos.ui.theme.White
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zamudio.ramon.proyectogestorgastos.ui.theme.PurplePrimary

@Composable
fun GroupDescriptionCard(
    description: String,
    modifier: Modifier = Modifier,
    onDescriptionChange: (String) -> Unit = {},
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Cabecera: Título y etiqueta "Visible para todos"
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Nota o descripción",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = GroupTextPrimary
                )
                Text(
                    text = "Visible para todos",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = GroupTextSecondary
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 90.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(GroupFieldBackground)
                    .padding(14.dp)
            ) {
                BasicTextField(
                    value = description,
                    onValueChange = onDescriptionChange,
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = GroupTextPrimary,
                        lineHeight = 20.sp
                    ),
                    cursorBrush = SolidColor(PurplePrimary),
                    decorationBox = { innerTextField ->
                        if (description.isEmpty()) {
                            Text(
                                text = "Ej. Gastos compartidos de hotel, gasolina y comidas del viaje a la playa...",
                                fontSize = 14.sp,
                                color = GroupTextSecondary,
                                lineHeight = 20.sp
                            )
                        }
                        innerTextField()
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun GroupDescriptionCardPreview() {
    GroupDescriptionCard("")
}