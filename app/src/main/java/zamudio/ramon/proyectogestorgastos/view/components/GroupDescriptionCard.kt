package zamudio.ramon.proyectogestorgastos.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupFieldBackground
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupTextPrimary
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupTextSecondary
import zamudio.ramon.proyectogestorgastos.ui.theme.White
import androidx.compose.ui.res.stringResource
import zamudio.ramon.proyectogestorgastos.R
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupTextStyles
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import zamudio.ramon.proyectogestorgastos.ui.theme.PurplePrimary

@Composable
fun GroupDescriptionCard(
    description: String,
    modifier: Modifier = Modifier,
    onDescriptionChange: (String) -> Unit = {},
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Cabecera: Título y etiqueta stringResource(R.string.group_description_visibility)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    style = GroupTextStyles.BodyBold,
                    text = stringResource(R.string.group_description_title),
                    color = GroupTextPrimary
                )
                Text(
                    style = GroupTextStyles.LabelNormal,
                    text = stringResource(R.string.group_description_visibility),
                    color = GroupTextSecondary
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 90.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(GroupFieldBackground)
                    .padding(14.dp)
            ) {
                BasicTextField(
                    value = description,
                    onValueChange = onDescriptionChange,
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = GroupTextStyles.BodyNormalLine20.copy(
                        color = GroupTextPrimary
                    ),
                    cursorBrush = SolidColor(PurplePrimary),
                    decorationBox = { innerTextField ->
                        if (description.isEmpty()) {
                            Text(
                                style = GroupTextStyles.BodyDefaultLine20,
                                text = stringResource(R.string.group_description_hint),
                                color = GroupTextSecondary
                            )
                        }
                        innerTextField()
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun GroupDescriptionCardPreview() {
    GroupDescriptionCard("")
}