package zamudio.ramon.proyectogestorgastos.model.dominio

import androidx.compose.ui.graphics.Color
import zamudio.ramon.proyectogestorgastos.ui.theme.LightBlue
import zamudio.ramon.proyectogestorgastos.ui.theme.Purple80
import zamudio.ramon.proyectogestorgastos.ui.theme.lightOrange
import zamudio.ramon.proyectogestorgastos.ui.theme.lightYellow

enum class Tipo(val color: Color) {
    LUZ(lightYellow),
    AGUA(LightBlue),
    GASOLINA(Color.LightGray),
    COMIDA(lightOrange),

    VIAJE(Purple80),

    OTROS(Purple80)
}