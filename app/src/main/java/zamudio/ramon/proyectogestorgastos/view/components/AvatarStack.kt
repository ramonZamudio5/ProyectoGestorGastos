package zamudio.ramon.proyectogestorgastos.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex


@Composable
fun AvatarStack(
    modifier: Modifier = Modifier,
    includeCurrentUser: Boolean = true
){
    val placeholderColors = listOf(
        Color(0xFF90CAF9), // Azul pastel
        Color(0xFFA5D6A7), // Verde pastel
        Color(0xFFFFCC80)  // Naranja pastel
    )


    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        placeholderColors.forEachIndexed { index, color ->
            Box(
                modifier = Modifier
                    .offset(x = (-8).dp * index)
                    .zIndex(index.toFloat())
                    .size(26.dp)
                    .border(1.5.dp, Color.White, CircleShape)
                    .clip(CircleShape)
                    .background(color)
            )
        }

        // Burbuja final para "Tú" o un contador "+N"
        if (includeCurrentUser) {
            Box(
                modifier = Modifier
                    .offset(x = (-8).dp * placeholderColors.size)
                    .zIndex(placeholderColors.size.toFloat())
                    .size(26.dp)
                    .border(1.5.dp, Color.White, CircleShape)
                    .clip(CircleShape)
                    .background(Color(0xFF7E57C2)), // Morado como el acento de la app
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Tú",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

@Preview (showBackground = true)
@Composable
fun AvatarStackPreview(){
    AvatarStack()
}