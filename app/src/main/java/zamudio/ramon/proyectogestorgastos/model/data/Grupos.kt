package zamudio.ramon.proyectogestorgastos.model.data

import zamudio.ramon.proyectogestorgastos.R
import zamudio.ramon.proyectogestorgastos.model.dominio.Grupo

val grupo = Grupo(1, "Viajes", "ahorro para vacaciones", R.drawable.ic_car, 3, "Amigos", 15000.00)

val grupos = listOf<Grupo>(
    Grupo(1, "Viajes", "ahorro para vacaciones", R.drawable.ic_car, 3, "Amigos", 15000.00),
    Grupo(2,"Comida", "uber eat y restaurantes", R.drawable.ic_car, 4, "Alimento", 17000.00))
