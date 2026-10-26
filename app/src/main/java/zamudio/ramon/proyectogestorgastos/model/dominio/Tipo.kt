package zamudio.ramon.proyectogestorgastos.model.dominio

import androidx.compose.ui.graphics.Color
import zamudio.ramon.proyectogestorgastos.ui.theme.LightBlue
import zamudio.ramon.proyectogestorgastos.ui.theme.Purple80
import zamudio.ramon.proyectogestorgastos.ui.theme.lightOrange
import zamudio.ramon.proyectogestorgastos.ui.theme.lightYellow

enum class Tipo(val color: Color, val estimado: Double) {
    LUZ(lightYellow, 2000.0),
    AGUA(LightBlue, 1000.0),
    GASOLINA(Color.LightGray, 1000.0),
    COMIDA(lightOrange, 1000.0),

    VIAJE(Purple80, 1000.0),

    OTROS(Purple80, 1000.0)
}