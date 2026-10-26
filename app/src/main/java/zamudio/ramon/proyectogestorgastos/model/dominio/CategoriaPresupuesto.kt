package zamudio.ramon.proyectogestorgastos.model.dominio

import androidx.compose.ui.graphics.Color

data class CategoriaPresupuesto(
    val nombre: String,
    val descripcion: String,
    val icono: Int,
    val limite: Double,
    val gastado: Double,
    val color: Color,
    val colorClaro: Color
) {
    val progreso: Float
        get() = if (limite <= 0.0) 0f else (gastado / limite).toFloat().coerceIn(0f, 1f)
}
