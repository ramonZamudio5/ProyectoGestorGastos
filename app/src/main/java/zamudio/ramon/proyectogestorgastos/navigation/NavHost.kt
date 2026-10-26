package zamudio.ramon.proyectogestorgastos.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.fragment.app.FragmentManager
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import zamudio.ramon.proyectogestorgastos.model.data.gastosMock
import zamudio.ramon.proyectogestorgastos.view.screens.GraficosScreen
import zamudio.ramon.proyectogestorgastos.view.screens.HistorialScreen
import zamudio.ramon.proyectogestorgastos.view.screens.LoginScreen
import zamudio.ramon.proyectogestorgastos.view.screens.PerfilScreen
import zamudio.ramon.proyectogestorgastos.view.screens.PrincipalScreen
import zamudio.ramon.proyectogestorgastos.view.screens.RegisterPasswordScreen
import zamudio.ramon.proyectogestorgastos.view.screens.RegisterPerfilScreen
import zamudio.ramon.proyectogestorgastos.view.screens.RegisterScreen

@Composable
fun Myapp(innerPadding: PaddingValues) {
    val navController = rememberNavController()
    val navigateToPerfil = {navController.navigate(route = Perfil)}
    val navigateToLogin = {navController.navigate(route = Login)}
    val navigateToRegister = {navController.navigate(route = Register)}
    val navigateToRegisterPerfil = {navController.navigate(route = RegisterPerfil)}
    val navigateToRegisterPassword = {navController.navigate(route = RegisterPassword)}
    val navigateToHistorial = {navController.navigate(route = Historial)}
    val navigateToPrincipal = {navController.navigate(route = Principal)}
    val navigateToGraficos = {navController.navigate(route = Graficos)}

    NavHost(navController = navController, startDestination = Login) {
        composable<Login> {
            LoginScreen(innerPadding,navigateToRegister, navigateToPrincipal)
        }
        composable<Perfil> {
            PerfilScreen(innerPadding,navigateToLogin, navigateToGraficos)
        }
        composable<Register> {
            RegisterScreen(innerPadding,navigateToLogin,navigateToRegisterPerfil)
        }
        composable<RegisterPerfil> {
            RegisterPerfilScreen(innerPadding,navigateToRegister,navigateToRegisterPassword)
        }
        composable<RegisterPassword>{
            RegisterPasswordScreen(innerPadding,navigateToRegisterPerfil)
        }
        composable<Historial> {
            HistorialScreen(innerPadding,gastosMock, navigateToPerfil, navigateToGraficos)
        }
        composable<Principal> {
            PrincipalScreen(innerPadding,navigateToPerfil, gastosMock,
                navigateToHistorial, navigateToGraficos)
        }
        composable<Graficos> {
            GraficosScreen(innerPadding, gastosMock,navigateToPerfil, navigateToGraficos)
        }
    }
}