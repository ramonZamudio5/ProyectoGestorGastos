package zamudio.ramon.proyectogestorgastos.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import zamudio.ramon.proyectogestorgastos.ui.theme.ScreenBackground
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import zamudio.ramon.proyectogestorgastos.view.components.BarraProgreso
import zamudio.ramon.proyectogestorgastos.view.components.GroupFormActions
import zamudio.ramon.proyectogestorgastos.view.components.GroupImagePickerCard
import zamudio.ramon.proyectogestorgastos.view.components.GroupNameImputField
import zamudio.ramon.proyectogestorgastos.view.components.GroupTypeSelectorCard

@Composable
fun CreateEditGroupScreen(
    onNavigateNext: () -> Unit = {},
    onCancel: () -> Unit = {}
) {
    var groupName by remember { mutableStateOf("Viaje Cancún 2025") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize().background(color= ScreenBackground)
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
            GroupFormActions(
                onNextClick = onNavigateNext,
                onCancelClick = onCancel
            )
        }

    }
}

@Preview
@Composable
fun CreateEditGroupScreenPreview() {
    CreateEditGroupScreen()
}