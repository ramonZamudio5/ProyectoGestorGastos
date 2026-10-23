package zamudio.ramon.proyectogestorgastos.view.components

import android.graphics.Paint
import android.text.Layout
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import zamudio.ramon.proyectogestorgastos.R
import zamudio.ramon.proyectogestorgastos.ui.theme.Typography

@Composable
fun Header(innerPadding: PaddingValues){
    Row(Modifier.fillMaxWidth().background(Color.White).padding(innerPadding),verticalAlignment = Alignment.CenterVertically) {
        Image(painterResource(R.drawable.ic_logo), contentDescription = "Logo", Modifier.size(35.dp).align(
            Alignment.CenterVertically))
        Column(Modifier.padding(horizontal = 10.dp)) {
            Text("GastoClaro", style = Typography.bodyLarge, fontWeight = FontWeight.Bold)
            Text("Home", style = Typography.bodyMedium)
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = { }) {
            Icon(painter = painterResource(R.drawable.ic_notificaciones), contentDescription = "Notificaciones",
                Modifier.align(Alignment.CenterVertically).size(25.dp))
        }
        Image(painter = painterResource(R.drawable.profilePicture), contentDescription = "Foto de perfil",
            contentScale = ContentScale.Crop, modifier = Modifier.padding(end = 8.dp).size(40.dp).clip(CircleShape).clickable {})
    }
}


@Preview
@Composable
fun PreviewHeader(){
    Header(PaddingValues(15.dp))
}