package zamudio.ramon.proyectogestorgastos.model.dominio

data class Grupo(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val imagen: Int,
    val numMiembros: Int,
    val tipo: String,
    val totalGasto: Double,
    val nombreCreador: String,
    val inviteCode: String)
