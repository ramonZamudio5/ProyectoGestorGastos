package zamudio.ramon.proyectogestorgastos.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import zamudio.ramon.proyectogestorgastos.R

@Composable
fun NavBar(
    onInicioClick: () -> Unit = {},
    onPresupuestoClick: () -> Unit = {},
    onAgregarClick: () -> Unit = {},
    onGruposClick: () -> Unit = {},
    onGraficasClick: () -> Unit = {}
) {
    Row(
        Modifier.fillMaxWidth()
            .background(Color.White)
            .navigationBarsPadding()
            .padding(top = 4.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onInicioClick) {
            Icon(painterResource(R.drawable.ic_inicio), contentDescription = "Inicio")
        }
        IconButton(onClick = onPresupuestoClick) {
            Icon(painterResource(R.drawable.ic_presupuesto), contentDescription = "Presupuesto")
        }
        IconButton(onClick = onAgregarClick) {
            Icon(painterResource(R.drawable.ic_anadir), contentDescription = "Agregar gasto")
        }
        IconButton(onClick = onGruposClick) {
            Icon(painterResource(R.drawable.ic_grupos), contentDescription = "Grupos")
        }
        IconButton(onClick = onNavigateToGraficos) {
            Icon(painter = painterResource(R.drawable.ic_graficas), contentDescription = "grafica")
        }
    }
}

@Preview
@Composable
fun previewNavBar(){
    NavBar({}, {}, {}, {}, {})
}
