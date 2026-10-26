package zamudio.ramon.proyectogestorgastos.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import zamudio.ramon.proyectogestorgastos.R
import zamudio.ramon.proyectogestorgastos.model.dominio.TarjetaPago
import zamudio.ramon.proyectogestorgastos.model.dominio.TipoTarjeta
import zamudio.ramon.proyectogestorgastos.ui.theme.Typography

@Composable
fun TarjetaMetodoPago(
    tarjeta: TarjetaPago,
    logoTexto: String? = null,
    iconoBanco: Int? = null,
    colorMarca: Color = Color(0xFF6633CC),
    colorFondoMarca: Color = Color(0xFFEDE3FF),
    onEditar: () -> Unit = {},
    onEliminar: () -> Unit = {}
) {
    var menuAbierto by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(Modifier.fillMaxWidth().padding(12.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(
                    Modifier.size(38.dp).clip(RoundedCornerShape(11.dp)).background(colorFondoMarca),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    if (iconoBanco != null) {
                        Image(painterResource(iconoBanco), contentDescription = "Banco ${tarjeta.banco}", modifier = Modifier.size(23.dp))
                    } else {
                        Text(logoTexto.orEmpty(), color = colorMarca, fontWeight = FontWeight.Bold, style = Typography.titleSmall)
                    }
                }
                Column(Modifier.weight(1f).padding(start = 10.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Banco: ", style = Typography.labelSmall, color = Color(0xFF596174))
                        Text(tarjeta.banco, style = Typography.labelLarge, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis)
                        Text(
                            if (tarjeta.tipoTarjeta == TipoTarjeta.CREDITO) "Crédito" else "Débito",
                            modifier = Modifier.padding(start = 6.dp).clip(RoundedCornerShape(10.dp)).background(colorFondoMarca).padding(horizontal = 7.dp, vertical = 3.dp),
                            color = colorMarca,
                            style = Typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1
                        )
                    }
                    Row {
                        Text("Alias: ", style = Typography.labelSmall, color = Color(0xFF596174))
                        Text(tarjeta.alias, style = Typography.labelSmall, color = Color(0xFF384152), maxLines = 1, overflow = TextOverflow.Ellipsis)
                    }
                }
                androidx.compose.foundation.layout.Box {
                    Text(
                        "•••",
                        modifier = Modifier.clip(CircleShape).background(Color(0xFFF0F2FF))
                            .clickable { menuAbierto = true }.padding(horizontal = 9.dp, vertical = 7.dp),
                        color = Color(0xFF374151),
                        style = Typography.labelLarge,
                        fontWeight = FontWeight.Bold
                    )
                    DropdownMenu(expanded = menuAbierto, onDismissRequest = { menuAbierto = false }) {
                        DropdownMenuItem(text = { Text("Editar") }, onClick = { menuAbierto = false; onEditar() })
                        DropdownMenuItem(text = { Text("Eliminar") }, onClick = { menuAbierto = false; onEliminar() })
                    }
                }
            }
            Row(
                Modifier.fillMaxWidth().clip(RoundedCornerShape(10.dp)).background(Color(0xFFF4F5FA)).padding(horizontal = 11.dp, vertical = 9.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(Modifier.weight(1f)) {
                    Text("TITULAR", style = Typography.labelSmall, color = Color(0xFF5E6574))
                    Text(tarjeta.titular, style = Typography.labelLarge, fontWeight = FontWeight.Medium, maxLines = 1, overflow = TextOverflow.Ellipsis)
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text("TERMINACIÓN", style = Typography.labelSmall, color = Color(0xFF5E6574))
                    Text("••••  ${tarjeta.ultimosCuatroDigitos}", style = Typography.labelLarge, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
