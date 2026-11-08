package zamudio.ramon.proyectogestorgastos.view.components


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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import zamudio.ramon.proyectogestorgastos.ui.theme.Typography

@Composable
fun BotonPrincipal(texto: String, onClick: ()-> Unit, icon: Int? = null){
    Button(onClick= onClick,
        modifier = Modifier.fillMaxWidth().height(52.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
    ) {
        Text(texto, Modifier.padding(start = 8.dp), style = Typography.labelLarge, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.width(8.dp))
        icon?.let {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
        }

    }
}

@Composable
fun BotonSecundario(texto: String, onClick: ()-> Unit, icon: Int? = null){
    Button(onClick= onClick,
        modifier = Modifier.fillMaxWidth().height(52.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surface, contentColor = MaterialTheme.colorScheme.onSurface)
    ) {
        Text(texto, Modifier.padding(start = 8.dp), style = Typography.labelLarge, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.width(8.dp))
        icon?.let {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBotones(){
    BotonSecundario("Cerrar Sesion", {})
}