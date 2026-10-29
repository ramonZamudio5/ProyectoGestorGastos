package zamudio.ramon.proyectogestorgastos.model.data

import zamudio.ramon.proyectogestorgastos.R
import zamudio.ramon.proyectogestorgastos.model.dominio.CategoriaPresupuesto
import zamudio.ramon.proyectogestorgastos.model.dominio.DeudaGrupo
import zamudio.ramon.proyectogestorgastos.model.dominio.Grupo
import zamudio.ramon.proyectogestorgastos.model.dominio.Usuario
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupCategoryText
import zamudio.ramon.proyectogestorgastos.ui.theme.SecondaryBackgroundColor
import zamudio.ramon.proyectogestorgastos.ui.theme.White
import kotlin.math.roundToLong

val grupo: Grupo
    get() = Grupo(1, "Viajes", "ahorro para vacaciones", R.drawable.ic_car, 3, "Amigos", 15000.00, "Saul Apodaca", "ASDFG", usuarios, presupuesto = presupuesto)

val grupos = listOf<Grupo>(
    Grupo(1, "Viajes", "ahorro para vacaciones", R.drawable.ic_car, 3, "Amigos", 15000.00, "Saul", "ASDFG", usuarios),
    Grupo(2,"Comida", "uber eat y restaurantes", R.drawable.ic_car, 4, "Alimento", 17000.00, "SaulApodaca", "ASDFG", usuarios))

val presupuesto = CategoriaPresupuesto(
    "vacaciones",
    "ahorro para vacaciones",
    R.drawable.ic_car,
    30000.0,
    15000.0,
    GroupCategoryText,
    White
)
fun repartirDeuda(grupo: Grupo): List<DeudaGrupo> {
    if (grupo.miembros.isEmpty()) {
        grupo.deuda = emptyList()
        return emptyList()
    }

    val totalCentavos = (grupo.totalGasto * 100).roundToLong().coerceAtLeast(0)
    val centavosBase = totalCentavos / grupo.miembros.size
    val centavosRestantes = (totalCentavos % grupo.miembros.size).toInt()

    return grupo.miembros.mapIndexed { index, usuario ->
        val centavos = centavosBase + if (index < centavosRestantes) 1 else 0
        DeudaGrupo(usuario, centavos / 100.0)
    }.also { grupo.deuda = it }
}

fun pagarDeuda(pago: Double, usuarioActual: Usuario, grupo: Grupo): List<DeudaGrupo> {
    val deudas = grupo.deuda ?: repartirDeuda(grupo)
    val pagoCentavos = (pago * 100).roundToLong().coerceAtLeast(0)
    val deudaUsuario = deudas.firstOrNull { it.usuario.id == usuarioActual.id } ?: return deudas
    val deudaCentavos = (deudaUsuario.monto * 100).roundToLong().coerceAtLeast(0)
    val nuevoSaldo = (deudaCentavos - pagoCentavos.coerceAtMost(deudaCentavos)) / 100.0

    return deudas.map { deuda ->
        if (deuda.usuario.id == usuarioActual.id) deuda.copy(monto = nuevoSaldo) else deuda
    }.also { grupo.deuda = it }
}

fun getMiembros(): List<String>{
    return listOf("Saul Apodaca", "Camila Zubia", "Ramon Zamudio", "Adel Mendez")
}