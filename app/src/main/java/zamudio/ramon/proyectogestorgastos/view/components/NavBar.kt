package zamudio.ramon.proyectogestorgastos.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import zamudio.ramon.proyectogestorgastos.R
import zamudio.ramon.proyectogestorgastos.navigation.Graficos
import zamudio.ramon.proyectogestorgastos.navigation.Inicio
import zamudio.ramon.proyectogestorgastos.navigation.Presupuesto
import zamudio.ramon.proyectogestorgastos.navigation.RegistrarMovimiento

@Composable
fun NavBar(navControl: NavController) {

    NavigationBar(Modifier
        .fillMaxWidth(), containerColor = Color.White) {

        NavigationBarItem(onClick = { navControl.navigate(Inicio) }, selected = false, icon = {
            Icon(painterResource(R.drawable.ic_inicio), contentDescription = "Inicio")
        })
        NavigationBarItem(onClick = { navControl.navigate(Presupuesto) }, selected = false, icon = {
            Icon(painterResource(R.drawable.ic_presupuesto), contentDescription = "Presupuesto")
        })
        NavigationBarItem(onClick = { navControl.navigate(RegistrarMovimiento) }, selected = false, icon = {
            Icon(painterResource(R.drawable.ic_anadir), contentDescription = "Agregar gasto")
        })
        NavigationBarItem(onClick = {  }, selected = false, icon = {
            Icon(painterResource(R.drawable.ic_grupos), contentDescription = "Grupos")
        })
        NavigationBarItem(onClick = { navControl.navigate(Graficos) }, selected = false, icon = {
            Icon(painter = painterResource(R.drawable.ic_graficas), contentDescription = "grafica")
        })
    }
}

