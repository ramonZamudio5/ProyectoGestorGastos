package zamudio.ramon.proyectogestorgastos.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import zamudio.ramon.proyectogestorgastos.R
import zamudio.ramon.proyectogestorgastos.model.Gasto
import zamudio.ramon.proyectogestorgastos.model.dominio.gasto
import zamudio.ramon.proyectogestorgastos.ui.theme.Purple80
import zamudio.ramon.proyectogestorgastos.ui.theme.Typography

@Composable
fun BannerGasto(gasto: Gasto){
    Row(modifier = Modifier.fillMaxWidth().padding(10.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
        Box(modifier = Modifier.padding(10.dp), contentAlignment = Alignment.Center){
            Box(Modifier.background(color= Purple80, shape = CircleShape).size(60.dp))
            Image(painterResource(R.drawable.ic_car), contentDescription = "car", Modifier.size(50.dp))
        }
        Column(Modifier.padding(5.dp)) {
            Text(gasto.nombre, style= Typography.titleLarge)
            Text(gasto.tipo.toString() + " • " + gasto.fecha, style= Typography.labelMedium)
        }
        Column() {
            Text("$" + gasto.cantidad.toString(), style= Typography.titleLarge)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BannerGastoPreview(){
    BannerGasto(gasto)
}