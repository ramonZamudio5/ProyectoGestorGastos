package zamudio.ramon.proyectogestorgastos.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zamudio.ramon.proyectogestorgastos.R
import zamudio.ramon.proyectogestorgastos.view.components.BarraProgreso
import zamudio.ramon.proyectogestorgastos.view.components.GroupBudgetInputCard
import zamudio.ramon.proyectogestorgastos.view.components.GroupDescriptionCard
import zamudio.ramon.proyectogestorgastos.view.components.GroupSummaryHeaderCard
import zamudio.ramon.proyectogestorgastos.view.components.Header
import zamudio.ramon.proyectogestorgastos.view.components.NavBar
import zamudio.ramon.proyectogestorgastos.view.components.TipoGrupoItem

@Composable
fun CompleteGroupDataScreen(
    onNavigateNext: () -> Unit = {}
) {
    var budget by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Scaffold(
        topBar = { Header(PaddingValues(15.dp), {}) },
        bottomBar = { NavBar() },
        containerColor = Color(0xFFF3F1F9)
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(top = 16.dp, bottom = 28.dp)
        ) {
            // 1. Barra de progreso (Paso 2 de 2)
            item {
                BarraProgreso(2, 2)
            }

            // 2. Encabezado de la pantalla
            item {
                Column(modifier = Modifier.padding(vertical = 4.dp)) {
                    Text(
                        text = "Completa los datos del grupo",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E2022)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Configura el presupuesto estimado y añade notas antes de iniciar.",
                        fontSize = 13.sp,
                        color = Color.Gray,
                        lineHeight = 18.sp
                    )
                }
            }

            // 3. Resumen compacto del grupo (con botón para editar el paso 1)
            //modificar esto para dejar de se mock
            item {
                GroupSummaryHeaderCard(
                    TipoGrupoItem(
                        "amigos",
                        "Amigos / Viajes",
                        "Vacaciones y escapadas",
                        R.drawable.ic_flight,
                        Color(0xFF7E57C2)
                    )
                )
            }

            // 4. Presupuesto estimado en pesos mexicanos (fijo MXN)
            item {
                GroupBudgetInputCard(
                    budget = budget,
                    onBudgetChange = { budget = it }
                )
            }

            // 5. Nota o descripción
            item {
                GroupDescriptionCard(
                    description = description,
                    onDescriptionChange = { description = it }
                )
            }

            // 6. Botón de acción principal
            item {
                Button(
                    onClick = onNavigateNext,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .padding(top = 8.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7E57C2))
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_check),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Finalizar y Crear Grupo",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

        }
    }
}

@Preview
@Composable
fun CompleteGroupDataScreenPreview(){
    CompleteGroupDataScreen()
}