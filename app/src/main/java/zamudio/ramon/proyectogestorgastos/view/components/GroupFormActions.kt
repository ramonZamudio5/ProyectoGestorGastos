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
import zamudio.ramon.proyectogestorgastos.ui.theme.White
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
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Botón Principal: Guardar Cambios / Siguiente
        Button(
            onClick = onNextClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PurplePrimary)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_check),
                contentDescription = null,
                tint = White,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                style = GroupTextStyles.ActionBold,
                text = stringResource(R.string.group_save_changes),
                color = White
            )
        }

        // Botón Secundario: Cancelar / Descartar
        OutlinedButton(
            onClick = onCancelClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.outlinedButtonColors(containerColor = White)
        ) {
            Text(
                style = GroupTextStyles.BodySemiBold,
                text = stringResource(R.string.group_cancel_discard),
                color = GroupSectionText
            )
        }
    }
}

@Preview
@Composable
fun GroupFormActionsPreview(){
    GroupFormActions({}, {})
}