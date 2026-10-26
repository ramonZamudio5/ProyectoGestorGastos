package zamudio.ramon.proyectogestorgastos.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import zamudio.ramon.proyectogestorgastos.R

@Composable
fun NavBar(onNavigateToGraficos: ()-> Unit){
    Row(Modifier.fillMaxWidth().background(Color.White), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Bottom) {
        IconButton(onClick = { }) {
            Icon(painter = painterResource(R.drawable.ic_inicio), contentDescription = "inicio")
        }
        IconButton(onClick = { }) {
            Icon(painter = painterResource(R.drawable.ic_presupuesto), contentDescription = "presupuesto")
        }
        IconButton(onClick = { }) {
            Icon(painter = painterResource(R.drawable.ic_anadir), contentDescription = "añadir")
        }
        IconButton(onClick = { }) {
            Icon(painter = painterResource(R.drawable.ic_grupos), contentDescription = "grupos")
        }
        IconButton(onClick = onNavigateToGraficos) {
            Icon(painter = painterResource(R.drawable.ic_graficas), contentDescription = "grafica")
        }
    }
}


@Preview
@Composable
fun previewNavBar(){
    NavBar({})
}