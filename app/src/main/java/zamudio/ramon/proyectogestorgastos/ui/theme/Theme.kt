package zamudio.ramon.proyectogestorgastos.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
// para usar los colores se debe llamar MaterialTheme.colorScheme.background o el color que quieren

private val DarkColorScheme = darkColorScheme(
    background = ScreenDarkBackground,
    primary = PurpleButton,
    onPrimary = OffWhite,
    secondary = OffWhite,
    onSecondary = OffBlack,
    onBackground = PurpleButton,
    surface = ScreenDarkBackground,
    onSurface = OffWhite
)

private val LightColorScheme = lightColorScheme(
    //fonde de la pantalla
    background = ScreenBackground,
    //color morado para los botones principales o cosas asi
    primary = PurpleButton,
    // el color que va dentro de los botones
    onPrimary = OffWhite,
    // color de las card o columns donde muestran los datos
    secondary = OffWhite,
    // el color de los elementos dentro de las cards tipo la info que imprimimos
    onSecondary = OffBlack,
    // color de los titulos grandes de cada pantalla, solo los titulos pq esos ocupan cambiar de un color distinto al texto
    onBackground = OffBlack,
    surface = ScreenBackground,
    //color para el texto de las pantallas que no esta dentro de las cards tipo en el login el texto debajo del titulo
    onSurface = OffBlack



    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun ProyectoGestorGastosTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}