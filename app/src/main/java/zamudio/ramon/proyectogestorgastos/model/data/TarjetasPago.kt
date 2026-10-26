package zamudio.ramon.proyectogestorgastos.model.data

import zamudio.ramon.proyectogestorgastos.model.dominio.TarjetaPago
import zamudio.ramon.proyectogestorgastos.model.dominio.TipoTarjeta

val tarjetasPagoMock = listOf(
    TarjetaPago(
        banco = "Nu México",
        alias = "Compras Personales & Viajes",
        tipoTarjeta = TipoTarjeta.CREDITO,
        titular = "ADEL F. MENDEZ",
        ultimosCuatroDigitos = "1645"
    ),
    TarjetaPago(
        banco = "Santander LikeU",
        alias = "Gastos Compartidos y Salidas",
        tipoTarjeta = TipoTarjeta.DEBITO,
        titular = "ADEL F. MENDEZ",
        ultimosCuatroDigitos = "7731"
    )
)
