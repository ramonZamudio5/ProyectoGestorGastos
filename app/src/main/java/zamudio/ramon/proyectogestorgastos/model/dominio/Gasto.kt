package zamudio.ramon.proyectogestorgastos.model.dominio

import java.time.LocalDate

data class Gasto(
    val nombre: String,
    val tipo: Tipo,
    val cantidad: Double,
    val fecha: LocalDate,
    val imagen: Int,
    val tipoPago: TipoPago,
    val tarjetaPago: TarjetaPago? = null
)
