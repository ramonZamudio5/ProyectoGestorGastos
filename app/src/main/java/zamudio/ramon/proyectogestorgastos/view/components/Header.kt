package zamudio.ramon.proyectogestorgastos.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import zamudio.ramon.proyectogestorgastos.R
import zamudio.ramon.proyectogestorgastos.navigation.Perfil
import zamudio.ramon.proyectogestorgastos.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header(navControl: NavController){
    TopAppBar(modifier = Modifier, colors = topAppBarColors(
            containerColor = Color.White
        ),
        navigationIcon = {
            Image(
                painter = painterResource(R.drawable.ic_logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .padding(start = 12.dp, end = 8.dp)
                    .size(35.dp)
            )
        },
        title = {
            Column {
                Text(
                    text = "GastoClaro",
                    style = Typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Home",
                    style = Typography.bodyMedium
                )
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(R.drawable.ic_notificaciones),
                    contentDescription = "Notificaciones",
                    modifier = Modifier.size(25.dp)
                )
            }
            Image(
                painter = painterResource(R.drawable.profile_picture),
                contentDescription = "Foto de perfil",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(end = 12.dp)
                    .size(40.dp)
                    .clip(CircleShape)
                    .clickable { navControl.navigate(Perfil) }
            )
        }
    )
}

@Preview
@Composable
fun PreviewHeader(){
}
