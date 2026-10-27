package zamudio.ramon.proyectogestorgastos.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import zamudio.ramon.proyectogestorgastos.model.data.gastosMock
import zamudio.ramon.proyectogestorgastos.view.screens.GraficosScreen
import zamudio.ramon.proyectogestorgastos.view.screens.HistorialScreen
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import zamudio.ramon.proyectogestorgastos.model.dominio.TarjetaPago
import zamudio.ramon.proyectogestorgastos.model.data.tarjetasPagoMock
import zamudio.ramon.proyectogestorgastos.model.dominio.Gasto
import zamudio.ramon.proyectogestorgastos.view.components.NavBar
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

val topLevelRoutes = setOf(
    Inicio,
    Presupuesto,
    RegistrarMovimiento,
    Graficos,
    Perfil,
    Historial,
    MetodosPago,
    RegistrarTarjeta
)

@Composable
fun Myapp() {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val shouldShowBottomBar = topLevelRoutes.any { route ->
        currentDestination?.hasRoute(route::class) == true
    }

    Scaffold(
        bottomBar = {
            if (shouldShowBottomBar) {
                NavBar(navController)
            }
        }
    ){ innerPadding ->

        val tarjetasPago = remember { mutableStateListOf<TarjetaPago>().apply { addAll(tarjetasPagoMock) } }
        val gastos = remember { mutableStateListOf<Gasto>().apply { addAll(gastosMock) } }
        val navigateToPerfil = {navController.navigate(route = Perfil)}
        val navigateToLogin = {navController.navigate(route = Login)}
        val navigateToRegister = {navController.navigate(route = Register)}
        val navigateToRegisterPerfil = {navController.navigate(route = RegisterPerfil)}
        val navigateToRegisterPassword = {navController.navigate(route = RegisterPassword)}
        val navigateToHistorial = {navController.navigate(route = Historial)}
        val navigateToPrincipal = {navController.navigate(route = Inicio)}
        val navigateToGraficos = {navController.navigate(route = Graficos)}
        val navigateToMetodosPago = {navController.navigate(route = MetodosPago)}
        val navigateToRegistroMovimiento = { navController.navigate(route = RegistrarMovimiento) }
        val navigateToPresupuesto = { navController.navigate(route = Presupuesto) }


        NavHost(navController = navController, startDestination = Login, modifier = Modifier.padding(innerPadding)) {
            composable<Login> {
                LoginScreen(innerPadding,navigateToRegister,navigateToPrincipal)
            }
            composable<Perfil> {
                PerfilScreen(
                    innerPadding, navigateToLogin, navigateToMetodosPago
                )
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
            composable<Inicio> {
                PrincipalScreen(
                    innerPadding, navigateToPerfil, gastos, onNavigateToRegistroGasto = navigateToRegistroMovimiento, onNavigateToHistorial = navigateToHistorial
                )
            }
            composable<Presupuesto> {
                PresupuestoScreen(
                    innerPadding = innerPadding,
                    onNavigateToPerfil = navigateToPerfil
                )
            }
            composable<MetodosPago> {
                MetodosPagoScreen(
                    innerPadding = innerPadding,
                    onNavigateToPerfil = navigateToPerfil,
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
                    onNavigateToPerfil = navigateToPerfil
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
            }
            composable<Historial> {
                HistorialScreen(innerPadding, gastosMock,navigateToPerfil)
            }
            composable<Graficos> {
                GraficosScreen(innerPadding, gastosMock,navigateToPerfil)
            }
        }
    }
}
