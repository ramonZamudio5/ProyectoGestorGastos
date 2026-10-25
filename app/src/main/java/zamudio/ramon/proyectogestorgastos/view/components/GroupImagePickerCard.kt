package zamudio.ramon.proyectogestorgastos.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zamudio.ramon.proyectogestorgastos.R

enum class ImagePickerMode {
    FOTO_REAL,
    ICONO_EMOJI
}

@Composable
fun GroupImagePickerCard(
    modifier: Modifier = Modifier,
    onTakePhotoClick: () -> Unit = {},
    onGalleryClick: () -> Unit = {},
    onRemovePhotoClick: () -> Unit = {}
){
    var selectedMode by remember { mutableStateOf(ImagePickerMode.FOTO_REAL) }

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 1. Imagen / Preview circular grande
            Box(
                modifier = Modifier
                    .size(110.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE0E0E0)),
                contentAlignment = Alignment.Center
            ) {
                // Placeholder para simular imagen de playa o icono
                Image(
                    painter = painterResource(R.drawable.group_default_image),
                    contentDescription = "Foto de portada",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp)
                    .clip(RoundedCornerShape(50))
                    .background(Color(0xFFF3F1FA))
                    .padding(4.dp)
            ){
                Row(
                    modifier = Modifier.fillMaxSize()
                ) {
                    // Opción: Foto Real
                    val isFotoReal = selectedMode == ImagePickerMode.FOTO_REAL
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(50))
                            .background(if (isFotoReal) Color.White else Color.Transparent)
                            .clickable { selectedMode = ImagePickerMode.FOTO_REAL },
                        contentAlignment = Alignment.Center
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(R.drawable.ic_image),
                                contentDescription = null,
                                tint = if (isFotoReal) Color(0xFF673AB7) else Color.Gray,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Foto Real",
                                fontSize = 12.sp,
                                fontWeight = if (isFotoReal) FontWeight.Bold else FontWeight.Medium,
                                color = if (isFotoReal) Color(0xFF1E2022) else Color.Gray
                            )
                        }
                    }

                    // Opción: Ícono / Emoji
                    val isIcono = selectedMode == ImagePickerMode.ICONO_EMOJI
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(50))
                            .background(if (isIcono) Color.White else Color.Transparent)
                            .clickable { selectedMode = ImagePickerMode.ICONO_EMOJI },
                        contentAlignment = Alignment.Center
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(R.drawable.ic_emojie),
                                contentDescription = null,
                                tint = if (isIcono) Color(0xFF673AB7) else Color.Gray,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Ícono / Emoji",
                                fontSize = 12.sp,
                                fontWeight = if (isIcono) FontWeight.Bold else FontWeight.Medium,
                                color = if (isIcono) Color(0xFF1E2022) else Color.Gray
                            )
                        }
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Tomar Foto
                ActionButtonSquare(
                    title = "Tomar Foto",
                    icon = painterResource(R.drawable.ic_camara),
                    accentColor = Color(0xFF673AB7),
                    backgroundColor = Color(0xFFEDE7F6),
                    onClick = onTakePhotoClick,
                    modifier = Modifier.weight(1f)
                )

                // Galería
                ActionButtonSquare(
                    title = "Galería",
                    icon = painterResource(R.drawable.ic_image),
                    accentColor = Color(0xFF5E35B1),
                    backgroundColor = Color(0xFFEDE7F6),
                    onClick = onGalleryClick,
                    modifier = Modifier.weight(1f)
                )

                // Quitar Foto
                ActionButtonSquare(
                    title = "Quitar Foto",
                    icon = painterResource(R.drawable.ic_delete),
                    accentColor = Color(0xFFE53935),
                    backgroundColor = Color(0xFFFFEBEE),
                    onClick = onRemovePhotoClick,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Preview
@Composable
fun GroupImagePickerCardPreview(){
    GroupImagePickerCard()
}