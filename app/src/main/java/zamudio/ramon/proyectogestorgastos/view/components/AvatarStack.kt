package zamudio.ramon.proyectogestorgastos.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupAvatarBlue
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupAvatarGreen
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupAvatarOrange
import zamudio.ramon.proyectogestorgastos.ui.theme.PurplePrimary
import zamudio.ramon.proyectogestorgastos.ui.theme.White
import androidx.compose.ui.res.stringResource
import zamudio.ramon.proyectogestorgastos.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
        GroupAvatarBlue, // Azul pastel
        GroupAvatarGreen, // Verde pastel
        GroupAvatarOrange  // Naranja pastel
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
                    .border(1.5.dp, White, CircleShape)
                    .clip(CircleShape)
                    .background(color)
            )
        }

        // Burbuja final para stringResource(R.string.group_you) o un contador "+N"
        if (includeCurrentUser) {
            Box(
                modifier = Modifier
                    .offset(x = (-8).dp * placeholderColors.size)
                    .zIndex(placeholderColors.size.toFloat())
                    .size(26.dp)
                    .border(1.5.dp, White, CircleShape)
                    .clip(CircleShape)
                    .background(PurplePrimary), // Morado como el acento de la app
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.group_you),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = White
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