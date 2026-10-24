package zamudio.ramon.proyectogestorgastos.model.dominio
import zamudio.ramon.proyectogestorgastos.R
import zamudio.ramon.proyectogestorgastos.model.Gasto

val gasto = Gasto("gasolina", Tipo.GASOLINA, 850.0, "23/10/2026", R.drawable.ic_car)

val gastosMock = listOf<Gasto>(
    Gasto("gasolina", Tipo.GASOLINA, 850.0, "23/10/2026", R.drawable.ic_car),
    Gasto("comida", Tipo.COMIDA, 342.3, "16/03/2025", R.drawable.ic_car),
    Gasto("luz", Tipo.LUZ, 1000.0, "26/08/2025", R.drawable.ic_energy),
    Gasto("agua", Tipo.AGUA, 465.8, "30/10/2026", R.drawable.ic_car))