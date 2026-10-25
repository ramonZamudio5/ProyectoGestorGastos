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
import androidx.compose.material3.OutlinedButton
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
import zamudio.ramon.proyectogestorgastos.view.components.GroupImagePickerCard
import zamudio.ramon.proyectogestorgastos.view.components.GroupNameImputField
import zamudio.ramon.proyectogestorgastos.view.components.GroupTypeSelectorCard
import zamudio.ramon.proyectogestorgastos.view.components.Header
import zamudio.ramon.proyectogestorgastos.view.components.NavBar

@Composable
fun CreateEditGroupScreen(
    onNavigateNext: () -> Unit = {},
    onCancel: () -> Unit = {}
) {
    var groupName by remember { mutableStateOf("Viaje Cancún 2025") }

    Scaffold(
        topBar = { Header(PaddingValues(15.dp), {}) },
        bottomBar = { NavBar() },
        containerColor = Color(0xFFF3F1F9) // Tinte lila de fondo
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(top = 16.dp, bottom = 28.dp)
        ) {
            // Barra de progreso (Paso 1 de 2)
            item {
                BarraProgreso(1, 2)
            }

            // 1. Selector de Foto Real / Ícono
            item {
                GroupImagePickerCard(
                    onTakePhotoClick = { },
                    onGalleryClick = { },
                    onRemovePhotoClick = { }
                )
            }

            // 2. Input del Nombre del Grupo con contador
            item {
                GroupNameImputField(
                    value = groupName,
                    onValueChange = { groupName = it },
                    maxLength = 30
                )
            }
            // 3. Tarjeta de selección de tipo de grupo encapsulada
            item {
                GroupTypeSelectorCard()
            }

            // 4. Botones de acción inferiores
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // Botón Principal: Guardar Cambios / Siguiente
                    Button(
                        onClick = onNavigateNext,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7E57C2))
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_check),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Guardar Cambios",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    // Botón Secundario: Cancelar / Descartar
                    OutlinedButton(
                        onClick = onCancel,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White)
                    ) {
                        Text(
                            text = "Cancelar / Descartar",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF37474F)
                        )
                    }
                }
            }

        }
    }
}

@Preview
@Composable
fun CreateEditGroupScreenPreview() {
    CreateEditGroupScreen()
}