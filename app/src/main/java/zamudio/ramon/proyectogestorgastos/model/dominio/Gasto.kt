package zamudio.ramon.proyectogestorgastos.model.dominio

import java.util.Date
import zamudio.ramon.proyectogestorgastos.model.dominio.Tipo
import zamudio.ramon.proyectogestorgastos.model.dominio.TarjetaPago

data class Gasto(
    val nombre: String,
    val tipo: Tipo,
    val cantidad: Double,
    val fecha: Date,
    val imagen: Int,
    val tipoPago: TipoPago = TipoPago.DEBITO,
    val tarjetaPago: TarjetaPago? = null
)
