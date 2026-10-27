package zamudio.ramon.proyectogestorgastos.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zamudio.ramon.proyectogestorgastos.R
import zamudio.ramon.proyectogestorgastos.model.data.grupos
import zamudio.ramon.proyectogestorgastos.ui.theme.BlueNavy
import zamudio.ramon.proyectogestorgastos.ui.theme.Fondo
import zamudio.ramon.proyectogestorgastos.view.components.GroupBalanceSummaryCard
import zamudio.ramon.proyectogestorgastos.view.components.GroupCardItem
import zamudio.ramon.proyectogestorgastos.view.components.Header
import zamudio.ramon.proyectogestorgastos.view.components.NavBar

@Composable
fun MyGroupScreen(
    onNavigateToDetail: (Int) -> Unit = {},
    onCreateGroupClick: () -> Unit = {},
    onJoinGroupClick: () -> Unit = {}
) {
    Scaffold(
        topBar = { Header(PaddingValues(15.dp), {}) },
        bottomBar = { NavBar() },
        containerColor = Fondo
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(top = 16.dp, bottom = 24.dp)
        ) {
            // 1. TÍTULO Y BADGE DE GRUPOS ACTIVOS
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Mis Grupos",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = BlueNavy
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Surface(
                            shape = RoundedCornerShape(50),
                            color = Color(0xFFE0D7F5)
                        ) {
                            Text(
                                text = "${grupos.size} activos",
                                color = Color(0xFF5E35B1),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                            )
                        }
                    }
                }
            }
            // 2. BOTONES DE ACCIÓN
            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // Botón Principal: Crear un Grupo
                    Button(
                        onClick = onCreateGroupClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF2962FF)
                        )
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_add_person),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Crear un Grupo",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    // Botón Secundario: Unirse a un Grupo con Código
                    Button(
                        onClick = onJoinGroupClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White
                        ),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 1.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_key),
                            contentDescription = null,
                            tint = Color(0xFF673AB7),
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Unirse a un Grupo con Código",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF673AB7)
                        )
                    }
                }
            }
            // 3. TARJETA DE BALANCE TOTAL GRUPAL
            item {
                GroupBalanceSummaryCard()
            }
            // 4. HEADER DE LA SECCIÓN: GRUPOS EN CURSO
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "GRUPOS EN CURSO",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF37474F)
                    )
                }
            }
            // 5. LISTA DE GRUPOS
            items(grupos) { grupo ->
                GroupCardItem(
                    grupo = grupo,
                    onClick = { onNavigateToDetail(grupo.id) }
                )
            }
        }
    }
}


@Preview
@Composable
fun MyGroupScreenPreview() {
    MyGroupScreen()
}