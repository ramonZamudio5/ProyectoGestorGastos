package zamudio.ramon.proyectogestorgastos.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import zamudio.ramon.proyectogestorgastos.model.data.gastosMock
import zamudio.ramon.proyectogestorgastos.model.dominio.Gasto
import zamudio.ramon.proyectogestorgastos.ui.theme.White
import zamudio.ramon.proyectogestorgastos.ui.theme.Fondo
import zamudio.ramon.proyectogestorgastos.view.components.ColumnaGastosPorFiltro
import zamudio.ramon.proyectogestorgastos.view.components.Header
import zamudio.ramon.proyectogestorgastos.view.components.NavBar
import zamudio.ramon.proyectogestorgastos.view.components.RowDeFiltros

@Composable
fun HistorialScreen(innerPadding: PaddingValues, gastos:List<Gasto>, onNavigateToPerfil:()-> Unit, onNavigateToGraficos: ()-> Unit){
    var filtroActual by remember { mutableStateOf("todo") }
    Column(Modifier.background(color= Fondo).fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween) {
        Column(Modifier.padding(15.dp).verticalScroll(rememberScrollState())
            , verticalArrangement = Arrangement.SpaceBetween) {
            OutlinedTextField(state= TextFieldState("Buscar movimiento, tienda o concepto..."),
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(focusedContainerColor = White,
                unfocusedContainerColor = White, disabledContainerColor = White,
                errorContainerColor = White, focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent, disabledBorderColor = Color.Transparent,
                errorBorderColor = Color.Transparent), shape = RoundedCornerShape(10.dp))
            Spacer(Modifier.size(10.dp))
            RowDeFiltros(gastos, filtroActual, onFiltroSelected = { nuevoFiltro -> filtroActual = nuevoFiltro})
            Spacer(Modifier.size(10.dp))
            Column(modifier = Modifier.background(color= White, shape= RoundedCornerShape(10.dp)).padding(10.dp)) {
                ColumnaGastosPorFiltro(filtroActual, gastos)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHistorialScreen(){
    HistorialScreen(PaddingValues(), gastosMock, {},{})
}