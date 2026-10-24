package zamudio.ramon.proyectogestorgastos.model

import zamudio.ramon.proyectogestorgastos.model.dominio.Tipo

data class Gasto(val nombre: String, val tipo: Tipo, val cantidad: Double, val fecha: String, val imagen: Int)
