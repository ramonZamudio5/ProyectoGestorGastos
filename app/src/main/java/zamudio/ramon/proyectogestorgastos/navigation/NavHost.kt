package zamudio.ramon.proyectogestorgastos.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import zamudio.ramon.proyectogestorgastos.view.screens.LoginScreen
import zamudio.ramon.proyectogestorgastos.view.screens.PerfilScreen

@Composable
fun Myapp(innerPadding: PaddingValues) {
    val navController = rememberNavController()
    val navigateToPerfil = {navController.navigate(route = Perfil)}
    val navigateToLogin = {navController.navigate(route = Login)}
    NavHost(navController = navController, startDestination = Perfil) {
        composable<Login> {
            LoginScreen(innerPadding)
        }
        composable<Perfil> {
            PerfilScreen(innerPadding,navigateToLogin)
        }
    }
}