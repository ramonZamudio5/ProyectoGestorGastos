package zamudio.ramon.proyectogestorgastos.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import zamudio.ramon.proyectogestorgastos.R

@Composable
fun GroupFormActions(
    onNextClick: () -> Unit,
    onCancelClick: () -> Unit,
    modifier: Modifier = Modifier,
    nextEnabled: Boolean = true
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        // Botón Principal: Guardar Cambios / Siguiente
        BotonPrincipal("Guardar Cambios", onNextClick, R.drawable.ic_check)
        // Botón Secundario: Cancelar / Descartar
        BotonLink("Cancelar", onCancelClick)
    }
}

@Preview(showBackground = true)
@Composable
fun GroupFormActionsPreview(){
    GroupFormActions({}, {})
}
