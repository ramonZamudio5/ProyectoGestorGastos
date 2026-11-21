package zamudio.ramon.proyectogestorgastos.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
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
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import zamudio.ramon.proyectogestorgastos.model.dominio.TarjetaPago
import zamudio.ramon.proyectogestorgastos.model.data.grupo
import zamudio.ramon.proyectogestorgastos.model.data.tarjetasPagoMock
import zamudio.ramon.proyectogestorgastos.model.data.usuarios
import zamudio.ramon.proyectogestorgastos.model.dominio.Gasto
import zamudio.ramon.proyectogestorgastos.view.components.Header
import zamudio.ramon.proyectogestorgastos.view.components.NavBar
import zamudio.ramon.proyectogestorgastos.view.screens.AñadirGastoScreen
import zamudio.ramon.proyectogestorgastos.view.screens.CompleteGroupDataScreen
import zamudio.ramon.proyectogestorgastos.view.screens.CreateEditGroupScreen
import zamudio.ramon.proyectogestorgastos.view.screens.GroupCreatedSuccessScreen
import zamudio.ramon.proyectogestorgastos.view.screens.InfoGrupoScreen
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
import zamudio.ramon.proyectogestorgastos.view.screens.SettleGroupAccountsScreen

val topLevelRoutes = setOf(
    Inicio,
    Presupuesto,
    RegistrarMovimiento,
    Graficos,
    Perfil,
    Historial,
    MetodosPago,
    RegistrarTarjeta,
    MyGroups,
    CreateEditGroup,
    CompleteGroupData,
    GroupCreatedSuccess,
    AñadirGasto,
    SettleGroupAccounts,
    InfoGrupo
)

@Composable
fun Myapp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val shouldShow = topLevelRoutes.any { route ->
        currentDestination?.hasRoute(route::class) == true
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            if (shouldShow) {
                NavBar(navController)
            }
        }, topBar = {if (shouldShow) {
              Header(navController)
        }}
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
        val navigateToMetodosPago = {navController.navigate(route = MetodosPago)}
        val navigateToRegistroMovimiento = { navController.navigate(route = RegistrarMovimiento) }
        val navigateToPresupuesto = { navController.navigate(route = Presupuesto) }
        val navigateToGraficos = { navController.navigate(route = Graficos) }



    NavHost(
        navController = navController,
        startDestination = Login,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable<Login> {
            LoginScreen(PaddingValues(), navigateToRegister, navigateToPrincipal)
        }
        composable<Perfil> {
            PerfilScreen(
                onNavigateToLogin = navigateToLogin,
                navigateToMetodosPago = navigateToMetodosPago
            )
        }
        composable<Register> {
            RegisterScreen(PaddingValues(), navigateToLogin, navigateToRegisterPerfil)
        }
        composable<RegisterPerfil> {
            RegisterPerfilScreen(PaddingValues(), navigateToRegister, navigateToRegisterPassword)
        }
        composable<RegisterPassword>{
            RegisterPasswordScreen(PaddingValues(), navigateToRegisterPerfil, navigateToPrincipal)
        }
        composable<Inicio> {
            PrincipalScreen(
                gastos = gastos,
                onNavigateToRegistroGasto = navigateToRegistroMovimiento,
                onNavigateToHistorial = navigateToHistorial
            )
        }
        composable<Presupuesto> {
            PresupuestoScreen(
                innerPadding = PaddingValues()
            )
        }
        composable<MetodosPago> {
            MetodosPagoScreen(
                onRegistrarTarjeta = { navController.navigate(RegistrarTarjeta) },
                tarjetas = tarjetasPago
            )
        }
        composable<RegistrarTarjeta> {
            RegistrarTarjetaScreen(
                innerPadding = PaddingValues(),
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
                innerPadding = PaddingValues(),
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
        composable<MyGroups> {navBackStackEntry ->
            val grupo = 0
            MyGroupScreen (
                onCreateGroupClick = {
                    navController.navigate(CreateEditGroup)
                },
                onNavigateToDetail = {
                    navController.navigate(InfoGrupo)
                }
            )
        }

        composable<InfoGrupo> {
            InfoGrupoScreen(
                grupo = grupo,
                modifier = Modifier,
                onAñadirGasto = { navController.navigate(AñadirGasto) },
                onLiquidarCuentas = { navController.navigate(SettleGroupAccounts) }
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

        composable<AñadirGasto> {
            AñadirGastoScreen(
                onAñadirGasto = { navController.popBackStack() },
                onCancelar = { navController.popBackStack() }
            )
        }

        composable<SettleGroupAccounts> {
            SettleGroupAccountsScreen(
                innerPadding = PaddingValues(),
                onConfirmarLiquidacion = { _, _, _, _ -> navController.popBackStack() }
            )
        }
    }
}}

