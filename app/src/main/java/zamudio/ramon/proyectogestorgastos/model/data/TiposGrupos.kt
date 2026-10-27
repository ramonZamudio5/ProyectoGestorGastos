package zamudio.ramon.proyectogestorgastos.model.data

import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import zamudio.ramon.proyectogestorgastos.R
import zamudio.ramon.proyectogestorgastos.model.dominio.TipoGrupo

val tiposDisponibles = listOf(
    TipoGrupo(
        "amigos",
        "Amigos / Viajes",
        "Vacaciones y escapadas",
        R.drawable.ic_flight,
        Color(0xFF7E57C2)
    ),
    TipoGrupo(
        "pareja",
        "Pareja / Cuentas compartidas",
        "Metas y gastos mutuos",
        R.drawable.ic_heart,
        Color(0xFFBA68C8)
    ),
    TipoGrupo(
        "hogar",
        "Hogar / Familia",
        "Servicios, despensa y renta",
        R.drawable.ic_home,
        Color(0xFFFFB300)
    ),
    TipoGrupo(
        "roomies",
        "Roomies / Compañeros de piso",
        "Alquiler y servicios comunes",
        R.drawable.ic_meeting_room,
        Color(0xFF5C6BC0)
    ),
    TipoGrupo(
        "proyecto",
        "Proyecto / Trabajo",
        "Eventos y viáticos de equipo",
        R.drawable.ic_work,
        Color(0xFF42A5F5)
    ),
    TipoGrupo(
        "otro",
        "Otro / Personalizado",
        "Reglas y categorías libres",
        R.drawable.ic_tune,
        Color(0xFF78909C)
    )
)
