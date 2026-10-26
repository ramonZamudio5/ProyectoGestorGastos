package zamudio.ramon.proyectogestorgastos.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import zamudio.ramon.proyectogestorgastos.model.Gasto
import zamudio.ramon.proyectogestorgastos.model.dominio.gastosMock
import zamudio.ramon.proyectogestorgastos.ui.theme.Purple40
import zamudio.ramon.proyectogestorgastos.ui.theme.PurpleGrey40
import zamudio.ramon.proyectogestorgastos.ui.theme.Typography
import zamudio.ramon.proyectogestorgastos.ui.theme.White
import zamudio.ramon.proyectogestorgastos.ui.theme.fondo
import zamudio.ramon.proyectogestorgastos.ui.theme.purpleButton
import zamudio.ramon.proyectogestorgastos.view.components.BannerBalance
import zamudio.ramon.proyectogestorgastos.view.components.ColumnaGastos
import zamudio.ramon.proyectogestorgastos.view.components.Header
import zamudio.ramon.proyectogestorgastos.view.components.NavBar

@Composable
fun PrincipalScreen(
    innerPadding: PaddingValues,
    onNavigateToPerfil: () -> Unit,
    gastos: List<Gasto>,
    onNavigateToRegistroGasto: () -> Unit = {}
) {
    Column(Modifier
        .background(color = fondo)
        .fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween) {
        Header(innerPadding, onNavigateToPerfil)
        Column(Modifier.padding(15.dp), verticalArrangement = Arrangement.SpaceBetween) {
            BannerBalance(30.0, 40.0)
            Spacer(Modifier.size(15.dp))
            Button(onClick = onNavigateToRegistroGasto, Modifier.fillMaxWidth(),
                colors = ButtonColors(
                    containerColor = purpleButton,
                    contentColor = White, disabledContainerColor = Purple40,
                    disabledContentColor = PurpleGrey40)
            ) {
                Text("Agregar gasto/ingreso")
            }
            Spacer(Modifier.size(15.dp))
            Column(modifier = Modifier
                .background(color = White, shape = RoundedCornerShape(10.dp))
                .padding(10.dp)) {
                Text("Últimos gastos", style = Typography.titleMedium)
                ColumnaGastos(gastos)
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .background(color = White, shape = RoundedCornerShape(10.dp)),
                    verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
                    Text("Ver historial completo", style= Typography.labelMedium)
                }
            }
        }
        NavBar({})
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPrincipalScreen(){
    PrincipalScreen(PaddingValues(15.dp),{}, gastosMock)
}
