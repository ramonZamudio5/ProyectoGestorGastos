package zamudio.ramon.proyectogestorgastos.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import zamudio.ramon.proyectogestorgastos.model.data.grupo
import zamudio.ramon.proyectogestorgastos.model.dominio.Grupo
import zamudio.ramon.proyectogestorgastos.ui.theme.Purple80
import zamudio.ramon.proyectogestorgastos.ui.theme.Typography

@Composable
fun BannerGrupo(grupo: Grupo){
    Row(modifier = Modifier.fillMaxWidth().padding(10.dp), verticalAlignment = Alignment.CenterVertically){
        Box(modifier = Modifier.padding(10.dp), contentAlignment = Alignment.Center){
            Box(Modifier.background(color= Purple80, shape = CircleShape).size(60.dp))
            Image(painterResource(grupo.imagen), contentDescription = "car", Modifier.size(50.dp))
        }
        Column(Modifier.padding(5.dp)) {
            Text(grupo.nombre, style= Typography.titleLarge)
            Text(grupo.descripcion, style= Typography.labelMedium)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BannerGrupoPreview(){
    BannerGrupo(grupo)
}