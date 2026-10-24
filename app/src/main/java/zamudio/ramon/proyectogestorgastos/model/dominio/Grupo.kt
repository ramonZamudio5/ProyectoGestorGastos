package zamudio.ramon.proyectogestorgastos.model.dominio

data class Grupo(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val imagen: Int,
    val numMiemnros: Int,
    val tipo: String,
    val totalGasto: Double)
