package zamudio.ramon.proyectogestorgastos.model.dominio

import androidx.compose.ui.graphics.Color

data class TipoGrupo(
    val id: String,
    val title: String,
    val subtitle: String,
    val iconRes: Int,
    val color: Color
)