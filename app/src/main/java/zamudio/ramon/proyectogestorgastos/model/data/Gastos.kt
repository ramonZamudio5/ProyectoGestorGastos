package zamudio.ramon.proyectogestorgastos.model.data
import androidx.compose.runtime.Composable
import zamudio.ramon.proyectogestorgastos.R
import zamudio.ramon.proyectogestorgastos.model.dominio.Gasto
import zamudio.ramon.proyectogestorgastos.model.dominio.Tipo
import zamudio.ramon.proyectogestorgastos.model.dominio.TipoPago
import java.time.LocalDate

val gasto = Gasto(
    "gasolina",
    Tipo.GASOLINA,
    850.0,
    LocalDate.of(2026,10,23),
    R.drawable.ic_car,
    TipoPago.DEBITO
)

val gastosMock = listOf<Gasto>(
    Gasto(
        "gasolina",
        Tipo.GASOLINA,
        850.0,
        LocalDate.of(2026,10,23),
        R.drawable.ic_car,
        TipoPago.DEBITO
    ),
    Gasto("comida", Tipo.COMIDA, 342.3, LocalDate.of(2026,10,23), R.drawable.ic_car, TipoPago.DEBITO),
    Gasto("luz", Tipo.LUZ, 1000.0, LocalDate.of(2025,8,26), R.drawable.ic_energy, TipoPago.CREDITO),
    Gasto("agua", Tipo.AGUA, 465.8, LocalDate.of(2026,10,30), R.drawable.ic_car, TipoPago.DEBITO))

@Composable
fun getGastosByTipo(tipo: Tipo): List<Gasto>{
    return gastosMock.filter { it.tipo == tipo }
}

@Composable
fun getTiposInGastos(gastos: List<Gasto>): List<Tipo>{
    return gastos.map { it.tipo }.distinct()
}

@Composable
fun getPorcentajeTipo(gastosTipo: Double, gastosTotales: Double): Double {
    if (gastosTotales == 0.0) return 0.0
    val porcentaje = gastosTipo / gastosTotales * 100
    return "%.2f".format(porcentaje).toDouble()
}
@Composable
fun getGastosTotales(gastos: List<Gasto>): Double{
    val total = gastos.sumOf { it.cantidad }
    return "%.2f".format(total).toDouble()
}

@Composable
fun getPresupuestoTotal(gastos: List<Gasto>): Double{
    return "%.2f".format(getTiposInGastos(gastos).sumOf { it.estimado }).toDouble()
}