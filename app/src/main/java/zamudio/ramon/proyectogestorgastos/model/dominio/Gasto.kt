package zamudio.ramon.proyectogestorgastos.model.dominio

import java.util.Date

data class Gasto(val nombre: String,
                 val tipo: Tipo,
                 val cantidad: Double,
                 val fecha: Date,
                 val imagen: Int,
                 val tipoPago: TipoPago)