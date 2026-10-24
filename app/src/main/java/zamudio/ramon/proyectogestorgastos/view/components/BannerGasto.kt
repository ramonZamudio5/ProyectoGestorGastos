package zamudio.ramon.proyectogestorgastos.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import zamudio.ramon.proyectogestorgastos.model.Gasto
import zamudio.ramon.proyectogestorgastos.model.dominio.gastosMock
import zamudio.ramon.proyectogestorgastos.ui.theme.Purple80
import zamudio.ramon.proyectogestorgastos.ui.theme.PurpleGrey40
import zamudio.ramon.proyectogestorgastos.ui.theme.Typography
import zamudio.ramon.proyectogestorgastos.ui.theme.White
import zamudio.ramon.proyectogestorgastos.ui.theme.lightGreen
import zamudio.ramon.proyectogestorgastos.ui.theme.lightRed

@Composable
fun ColumnaGastos(gastos: List<Gasto>){
    LazyColumn(Modifier.padding(10.dp)){
        items(gastos){
                it ->BannerGasto(it)
        }
    }
}

@Composable
fun BannerGasto(gasto: Gasto){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
        .background(color = White, shape = RoundedCornerShape(10.dp)),
        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
        Box(modifier = Modifier.padding(10.dp), contentAlignment = Alignment.Center){
            Box(Modifier
                .background(color = Purple80, shape = CircleShape)
                .size(30.dp))
            Image(painterResource(gasto.imagen), contentDescription = "${gasto.nombre} imagen", Modifier.size(20.dp))
        }
        Column(Modifier.padding(5.dp)) {
            Text(gasto.nombre, style= Typography.titleMedium)
            Text(gasto.tipo.toString() + " • " + gasto.fecha, style= Typography.labelSmall)
        }
        Column() {
            Text("$" + gasto.cantidad.toString(), style= Typography.titleMedium)
        }
    }
}

@Composable
fun BannerBalance(gastos: Double, ingresos: Double){
    Column(Modifier.background(color = White, shape = RoundedCornerShape(10.dp)).fillMaxWidth().padding(15.dp)) {
        Text("BALANCE TOTAL", style= Typography.titleMedium, color= PurpleGrey40)
        Spacer(Modifier.size(15.dp))
        Row(verticalAlignment = Alignment.Bottom) {
            Text("$${ingresos - gastos}", style= Typography.headlineLarge)
            Text("MXN", style= Typography.bodySmall)
        }
        Row (Modifier.background(color = White, shape = RoundedCornerShape(10.dp)).fillMaxWidth().padding(15.dp)
            , verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
            Column(Modifier.background(color = lightGreen, shape = RoundedCornerShape(10.dp)).padding(10.dp)) {
               Text("Ingresos del mes", style= Typography.labelMedium)
               Text("+$${ingresos}", style= Typography.titleMedium)
           }
            Column(Modifier.background(color = lightRed, shape = RoundedCornerShape(10.dp)).padding(10.dp)) {
                Text("Gastos del mes", style= Typography.labelMedium)
                Text("-$${gastos}", style= Typography.titleMedium)
            }
        }
    }
}

@Composable
fun BannerDistribucion(gastos: List<Gasto>){
    Column(Modifier.background(color = White, shape = RoundedCornerShape(10.dp)).fillMaxWidth().padding(15.dp)) {
        Text("BALANCE TOTAL", style= Typography.titleMedium)
        LazyRow() { }
    }
}

@Composable
fun CeldaTipoGasto(gasto: Gasto){
    Row (Modifier.background(color = White, shape = RoundedCornerShape(10.dp)).fillMaxWidth().padding(15.dp)
        , verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
        Column(
            Modifier.background(color = lightGreen, shape = RoundedCornerShape(10.dp))
                .padding(10.dp)
        ) {
            Text("Ingresos del mes", style = Typography.labelMedium, color = PurpleGrey40)
            Text("+$${gasto.nombre}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BannerGastoPreview(){
    ColumnaGastos(gastosMock)
}