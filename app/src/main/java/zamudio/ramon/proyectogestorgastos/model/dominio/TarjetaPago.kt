package zamudio.ramon.proyectogestorgastos.model.dominio

data class TarjetaPago(
    val banco: String,
    val alias: String,
    val tipoTarjeta: TipoTarjeta,
    val titular: String,
    val ultimosCuatroDigitos: String
)
