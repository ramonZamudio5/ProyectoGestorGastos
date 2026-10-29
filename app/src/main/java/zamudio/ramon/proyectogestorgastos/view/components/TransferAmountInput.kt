package zamudio.ramon.proyectogestorgastos.view.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupFieldBackground
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupTextPrimary
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupTextSecondary
import zamudio.ramon.proyectogestorgastos.R
import zamudio.ramon.proyectogestorgastos.ui.theme.PurplePrimary
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupDeleteAccent

@Composable
fun TransferAmountInput(
    montoTexto: String,
    deudaTotal: Double,
    modifier: Modifier = Modifier,
    onMontoChange: (String) -> Unit = {},
    onLiquidarTotalClick: () -> Unit = {},
    errorMessage: String? = null
){
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Monto transferido",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = GroupTextSecondary
            )

            Row(
                modifier = Modifier.clickable { onLiquidarTotalClick() },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_energy),
                    contentDescription = null,
                    tint = PurplePrimary,
                    modifier = Modifier.size(14.dp)
                )
                Text(
                    text = "Liquidar total ($${String.format("%,.2f", deudaTotal)} MXN)",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = PurplePrimary
                )
            }
        }

        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            color = GroupFieldBackground
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = PurplePrimary
                )
                Spacer(modifier = Modifier.width(6.dp))
                TextField(
                    value = montoTexto,
                    onValueChange = onMontoChange,
                    isError = errorMessage != null,
                    modifier = Modifier.weight(1f),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = GroupTextPrimary
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    singleLine = true
                )
                Text(
                    text = "MXN",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = GroupTextSecondary
                )
            }
        }
        if (errorMessage != null) {
            Text(
                text = errorMessage,
                color = GroupDeleteAccent,
                fontSize = 12.sp
            )
        }
    }
}

@Preview
@Composable
fun TransferAmountInputPreview(){
    TransferAmountInput("1000.00", 10000.50)
}
