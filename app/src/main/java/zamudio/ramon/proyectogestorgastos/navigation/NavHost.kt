package zamudio.ramon.proyectogestorgastos.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateListOf
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import zamudio.ramon.proyectogestorgastos.model.dominio.gastosMock
import zamudio.ramon.proyectogestorgastos.model.dominio.TarjetaPago
import zamudio.ramon.proyectogestorgastos.model.Gasto
import zamudio.ramon.proyectogestorgastos.model.data.tarjetasPagoMock

import zamudio.ramon.proyectogestorgastos.view.screens.LoginScreen
import zamudio.ramon.proyectogestorgastos.view.screens.PerfilScreen
import zamudio.ramon.proyectogestorgastos.view.screens.PrincipalScreen
import zamudio.ramon.proyectogestorgastos.view.screens.RegisterPasswordScreen
import zamudio.ramon.proyectogestorgastos.view.screens.RegisterPerfilScreen
import zamudio.ramon.proyectogestorgastos.view.screens.RegisterScreen
import zamudio.ramon.proyectogestorgastos.view.screens.PresupuestoScreen
import zamudio.ramon.proyectogestorgastos.view.screens.MetodosPagoScreen
import zamudio.ramon.proyectogestorgastos.view.screens.RegistrarTarjetaScreen
import zamudio.ramon.proyectogestorgastos.view.screens.RegistroGastoScreen

@Composable
fun Myapp(innerPadding: PaddingValues) {
    val navController = rememberNavController()
    val tarjetasPago = remember { mutableStateListOf<TarjetaPago>().apply { addAll(tarjetasPagoMock) } }
    val gastos = remember { mutableStateListOf<Gasto>().apply { addAll(gastosMock) } }
    val navigateToPerfil = {navController.navigate(route = Perfil)}
    val navigateToLogin = {navController.navigate(route = Login)}
    val navigateToRegister = {navController.navigate(route = Register)}
    val navigateToRegisterPerfil = {navController.navigate(route = RegisterPerfil)}
    val navigateToRegisterPassword = {navController.navigate(route = RegisterPassword)}
    val navigateToPrincipal = {navController.navigate(route = Principal)}

    val navigateToMetodosPago = {navController.navigate(route = MetodosPago)}
    val navigateToRegistroMovimiento = { navController.navigate(route = RegistrarMovimiento) }


    NavHost(navController = navController, startDestination = Login) {
        composable<Login> {
            LoginScreen(innerPadding,navigateToRegister,navigateToPrincipal)
        }
        composable<Perfil> {

            PerfilScreen(innerPadding,navigateToLogin,navigateToPrincipal,navigateToMetodosPago)

            PerfilScreen(innerPadding,navigateToLogin,navigateToPrincipal)
        }
        composable<Register> {
            RegisterScreen(innerPadding,navigateToLogin,navigateToRegisterPerfil)
        }
        composable<RegisterPerfil> {
            RegisterPerfilScreen(innerPadding,navigateToRegister,navigateToRegisterPassword)
        }
        composable<RegisterPassword>{
            RegisterPasswordScreen(innerPadding,navigateToRegisterPerfil,navigateToPrincipal)
        }
        composable<Principal> {

            PrincipalScreen(innerPadding, navigateToPerfil, gastos, navigateToRegistroMovimiento)
        }
        composable<Presupuesto> {
            PresupuestoScreen(innerPadding, navigateToPerfil)
        }
        composable<MetodosPago> {
            MetodosPagoScreen(
                innerPadding = innerPadding,
                onNavigateToPerfil = navigateToPerfil,
                onNavigateToPrincipal = navigateToPrincipal,
                onRegistrarTarjeta = { navController.navigate(RegistrarTarjeta) },
                tarjetas = tarjetasPago
            )
        }
        composable<RegistrarTarjeta> {
            RegistrarTarjetaScreen(
                innerPadding = innerPadding,
                onGuardar = { nuevaTarjeta ->
                    tarjetasPago.add(nuevaTarjeta)
                    navController.popBackStack()
                },
                onCancelar = { navController.popBackStack() },
                onNavigateToPerfil = navigateToPerfil,
                onNavigateToPrincipal = navigateToPrincipal
            )
        }
        composable<RegistrarMovimiento> {
            RegistroGastoScreen(
                innerPadding = innerPadding,
                onNavigateToPerfil = navigateToPerfil,
                tarjetasDisponibles = tarjetasPago,
                onGuardar = { gasto ->
                    gastos.add(gasto)
                    navController.popBackStack()
                }
            )

            PrincipalScreen(innerPadding,navigateToPerfil, gastosMock)
        }
    }
}
