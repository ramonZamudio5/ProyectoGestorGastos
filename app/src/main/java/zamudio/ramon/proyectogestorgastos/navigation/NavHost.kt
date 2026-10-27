package zamudio.ramon.proyectogestorgastos.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import zamudio.ramon.proyectogestorgastos.model.data.gastosMock
import zamudio.ramon.proyectogestorgastos.view.screens.GraficosScreen
import zamudio.ramon.proyectogestorgastos.view.screens.HistorialScreen
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateListOf
import zamudio.ramon.proyectogestorgastos.model.data.grupo
import zamudio.ramon.proyectogestorgastos.model.dominio.TarjetaPago
import zamudio.ramon.proyectogestorgastos.model.data.tarjetasPagoMock
import zamudio.ramon.proyectogestorgastos.model.data.usuarios
import zamudio.ramon.proyectogestorgastos.model.dominio.Gasto
import zamudio.ramon.proyectogestorgastos.view.screens.CompleteGroupDataScreen
import zamudio.ramon.proyectogestorgastos.view.screens.CreateEditGroupScreen
import zamudio.ramon.proyectogestorgastos.view.screens.GroupCreatedSuccessScreen
import zamudio.ramon.proyectogestorgastos.view.screens.LoginScreen
import zamudio.ramon.proyectogestorgastos.view.screens.PerfilScreen
import zamudio.ramon.proyectogestorgastos.view.screens.PrincipalScreen
import zamudio.ramon.proyectogestorgastos.view.screens.RegisterPasswordScreen
import zamudio.ramon.proyectogestorgastos.view.screens.RegisterPerfilScreen
import zamudio.ramon.proyectogestorgastos.view.screens.RegisterScreen
import zamudio.ramon.proyectogestorgastos.view.screens.PresupuestoScreen
import zamudio.ramon.proyectogestorgastos.view.screens.MetodosPagoScreen
import zamudio.ramon.proyectogestorgastos.view.screens.MyGroupScreen
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
    val navigateToHistorial = {navController.navigate(route = Historial)}
    val navigateToPrincipal = {navController.navigate(route = Principal)}
    val navigateToGraficos = {navController.navigate(route = Graficos)}
    val navigateToMetodosPago = {navController.navigate(route = MetodosPago)}
    val navigateToRegistroMovimiento = { navController.navigate(route = RegistrarMovimiento) }
    val navigateToPresupuesto = { navController.navigate(route = Presupuesto) }


    NavHost(navController = navController, startDestination = Login) {
        composable<Login> {
            LoginScreen(innerPadding,navigateToRegister,navigateToPrincipal)
        }
        composable<Perfil> {
            PerfilScreen(
                innerPadding, navigateToLogin, navigateToPrincipal,
                navigateToMetodosPago, navigateToPresupuesto, navigateToRegistroMovimiento,
                navigateToGraficos
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
        composable<Principal> {
            PrincipalScreen(
                innerPadding = innerPadding,
                onNavigateToPerfil = navigateToPerfil,
                gastos = gastos,
                onNavigateToRegistroGasto = navigateToRegistroMovimiento,
                onNavigateToPresupuesto = navigateToPresupuesto,
                onNavigateToHistorial = navigateToHistorial,
                onNavigateToGraficos = navigateToGraficos,
                onNavigateToGrupos = {
                    navController.navigate(MyGroups) {
                        launchSingleTop = true
                    }
                }
            )
        }
        composable<Presupuesto> {
            PresupuestoScreen(
                innerPadding = innerPadding,
                onNavigateToPerfil = navigateToPerfil,
                onNavigateToPrincipal = navigateToPrincipal,
                onNavigateToRegistroGasto = navigateToRegistroMovimiento
            )
        }
        composable<MetodosPago> {
            MetodosPagoScreen(
                innerPadding = innerPadding,
                onNavigateToPerfil = navigateToPerfil,
                onNavigateToPrincipal = navigateToPrincipal,
                onNavigateToPresupuesto = navigateToPresupuesto,
                onNavigateToRegistroGasto = navigateToRegistroMovimiento,
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
                onNavigateToPrincipal = navigateToPrincipal,
                onNavigateToPresupuesto = navigateToPresupuesto,
                onNavigateToRegistroGasto = navigateToRegistroMovimiento
            )
        }
        composable<RegistrarMovimiento> {
            RegistroGastoScreen(
                innerPadding = innerPadding,
                onNavigateToPerfil = navigateToPerfil,
                onNavigateToPrincipal = navigateToPrincipal,
                onNavigateToPresupuesto = navigateToPresupuesto,
                tarjetasDisponibles = tarjetasPago,
                onGuardar = { gasto ->
                    gastos.add(gasto)
                    navController.popBackStack()
                }
            )
        }
        composable<Historial> {
            HistorialScreen(innerPadding,gastosMock, navigateToPerfil, navigateToGraficos)
        }

        composable<Graficos> {
            GraficosScreen(innerPadding, gastosMock,navigateToPerfil, navigateToGraficos)
        }
        composable<MyGroups> {
            MyGroupScreen (
                onCreateGroupClick = {
                    navController.navigate(CreateEditGroup)
                }
            )
        }

        composable<CreateEditGroup> {
            CreateEditGroupScreen (
                onNavigateNext = {
                    navController.navigate(CompleteGroupData)
                },
                onCancel = {
                    navController.popBackStack()
                }
            )
        }

        composable<CompleteGroupData> {
            CompleteGroupDataScreen(
                onNavigateNext = {
                    navController.navigate(GroupCreatedSuccess) {
                        popUpTo<CreateEditGroup> {
                            inclusive = true
                        }
                    }
                },
                onEditGroupClick = {
                    navController.popBackStack()
                }
            )
        }

        composable<GroupCreatedSuccess> {
            GroupCreatedSuccessScreen(
                group = grupo,
                miembros = usuarios,
                onClose = {
                    navController.popBackStack()
                }
            )
        }
    }
}
