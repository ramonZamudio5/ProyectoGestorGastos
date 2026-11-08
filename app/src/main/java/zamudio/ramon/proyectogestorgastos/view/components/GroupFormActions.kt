package zamudio.ramon.proyectogestorgastos.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupSectionText
import zamudio.ramon.proyectogestorgastos.ui.theme.OffWhite
import androidx.compose.ui.res.stringResource
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupTextStyles
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import zamudio.ramon.proyectogestorgastos.R
import zamudio.ramon.proyectogestorgastos.ui.theme.PurplePrimary

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
        BotonSecundario("Cancelar", onCancelClick)
    }
}

@Preview(showBackground = true)
@Composable
fun GroupFormActionsPreview(){
    GroupFormActions({}, {})
}
