package zamudio.ramon.proyectogestorgastos.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupFieldBackground
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupTextPlaceholder
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupTextPrimary
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupTextSecondary
import zamudio.ramon.proyectogestorgastos.ui.theme.White
import androidx.compose.ui.res.stringResource
import zamudio.ramon.proyectogestorgastos.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zamudio.ramon.proyectogestorgastos.ui.theme.PurplePrimary

@Composable
fun GroupBudgetInputCard(
    budget: String,
    modifier: Modifier = Modifier,
    onBudgetChange: (String) -> Unit = {}
){
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
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Cabecera: Título y etiqueta stringResource(R.string.group_optional)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.group_budget_title),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = GroupTextPrimary
                )
                Text(
                    text = stringResource(R.string.group_optional),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = GroupTextSecondary
                )
            }
            // Campo de entrada con '$' a la izquierda y 'MXN' fijo a la derecha
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(GroupFieldBackground)
                    .padding(horizontal = 14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.group_currency_symbol),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = PurplePrimary
                )

                Spacer(modifier = Modifier.width(8.dp))

                BasicTextField(
                    value = budget,
                    onValueChange = { input ->
                        // Filtra para admitir solo dígitos y un solo punto decimal
                        if (input.all { it.isDigit() || it == '.' }) {
                            onBudgetChange(input)
                        }
                    },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    textStyle = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = GroupTextPrimary
                    ),
                    cursorBrush = SolidColor(PurplePrimary),
                    decorationBox = { innerTextField ->
                        if (budget.isEmpty()) {
                            Text(
                                text = stringResource(R.string.group_budget_placeholder),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = GroupTextPlaceholder
                            )
                        }
                        innerTextField()
                    }
                )

                Text(
                    text = stringResource(R.string.group_currency_code),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = GroupTextSecondary
                )
            }

        }
    }
}

@Preview
@Composable
fun GroupBudgetInputCardPreview(){
    GroupBudgetInputCard("")
}