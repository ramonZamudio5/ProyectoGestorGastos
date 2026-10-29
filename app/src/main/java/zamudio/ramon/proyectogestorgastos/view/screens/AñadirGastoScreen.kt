package zamudio.ramon.proyectogestorgastos.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zamudio.ramon.proyectogestorgastos.R
import zamudio.ramon.proyectogestorgastos.model.data.usuarios
import zamudio.ramon.proyectogestorgastos.model.dominio.CategoriaPresupuesto
import zamudio.ramon.proyectogestorgastos.ui.theme.*
import zamudio.ramon.proyectogestorgastos.view.components.ItemMiembroPagador
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun AñadirGastoScreen() {
    val categorias = remember {
        mutableStateListOf(
            CategoriaPresupuesto(
                "Alimentos",
                "Supermercado y despensa",
                R.drawable.ic_presupuesto,
                7000.0,
                4850.0,
                Color(0xFF6852E8),
                Color(0xFFE7E1FF)
            ),
            CategoriaPresupuesto("Hogar y Servicios", "Luz, agua, internet, gas", R.drawable.ic_energy, 5000.0, 3900.0, Color(0xFFD55B25), Color(0xFFFFE6D8)),
            CategoriaPresupuesto("Transporte", "Gasolina y viajes diarios", R.drawable.ic_car, 3500.0, 3100.0, Color(0xFFC5A800), Color(0xFFFFF4A8)),
            CategoriaPresupuesto("Ocio y Salidas", "Restaurantes, cine y cafés", R.drawable.ic_grupos, 3000.0, 2950.0, Color(0xFFC3222B), Color(0xFFFFE1E1))
        )
    }

    var monto by remember { mutableStateOf("2400.00") }
    var concepto by remember { mutableStateOf("Cena en Puerto Madero") }
    var categoriaSeleccionadaNombre by remember { mutableStateOf(categorias.firstOrNull()?.nombre ?: "") }
    var pagadorSeleccionadoId by remember { mutableStateOf(1) }

    var fechaSeleccionada by remember { mutableStateOf(LocalDate.now()) }
    var mostrarDatePicker by remember { mutableStateOf(false) }

    val datePickerState = rememberDatePickerState()
    val formatoFecha = remember { DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.getDefault()) }

    if (mostrarDatePicker) {
        DatePickerDialog(
            onDismissRequest = { mostrarDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    datePickerState.selectedDateMillis?.let { millis ->
                        fechaSeleccionada = Instant.ofEpochMilli(millis)
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate()
                    }
                    mostrarDatePicker = false
                }) {
                    Text("Aceptar", color = PurpleButton)
                }
            },
            dismissButton = {
                TextButton(onClick = { mostrarDatePicker = false }) {
                    Text("Cancelar", color = GroupTextSecondary)
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    val miembros = usuarios

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(White),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    color = GroupBadgeBackground,
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_grupos),
                            contentDescription = null,
                            tint = GroupBadgeText,
                            modifier = Modifier.size(16.dp)
                        )
                        Text("Cancún 2025", color = GroupBadgeText, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    Box(modifier = Modifier.size(8.dp).background(PurpleButton, CircleShape))
                    Text("Gasto compartido", color = GroupTextSecondary, fontSize = 12.sp)
                }
            }
        }

        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = BackGroundPurple),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "$",
                            color = PurpleButton,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(6.dp))

                        BasicTextField(
                            value = monto,
                            onValueChange = { nuevoMonto ->
                                if (nuevoMonto.isEmpty() || nuevoMonto.matches(Regex("^\\d*\\.?\\d{0,2}$"))) {
                                    monto = nuevoMonto
                                }
                            },
                            textStyle = TextStyle(
                                fontSize = 32.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = GroupTextPrimary,
                                textAlign = TextAlign.Start
                            ),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                            cursorBrush = SolidColor(PurpleButton),
                            singleLine = true,
                            modifier = Modifier
                                .width(IntrinsicSize.Min)
                                .defaultMinSize(minWidth = 40.dp)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "MXN",
                            color = GrayText,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Surface(
                            modifier = Modifier.weight(1f),
                            color = White,
                            shape = RoundedCornerShape(12.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, GroupBorder)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_edit),
                                    contentDescription = null,
                                    tint = GrayText,
                                    modifier = Modifier.size(18.dp)
                                )
                                BasicTextField(
                                    value = concepto,
                                    onValueChange = { concepto = it },
                                    textStyle = TextStyle(
                                        fontSize = 14.sp,
                                        color = GroupTextPrimary,
                                        fontWeight = FontWeight.Medium
                                    ),
                                    singleLine = true,
                                    cursorBrush = SolidColor(PurpleButton),
                                    decorationBox = { innerTextField ->
                                        if (concepto.isEmpty()) {
                                            Text("Escribe el concepto...", fontSize = 14.sp, color = GrayText)
                                        }
                                        innerTextField()
                                    },
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }

                        Surface(
                            color = GroupBadgeBackground,
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.size(42.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_recipt),
                                    contentDescription = null,
                                    tint = PurpleButton,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }
                }
            }
        }

        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Categoría", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = GroupTextPrimary)
                    Text("${categorias.size} disponibles", color = GroupBadgeText, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                }

                LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(categorias) { cat ->
                        val esSeleccionado = cat.nombre == categoriaSeleccionadaNombre
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .clip(RoundedCornerShape(16.dp))
                                .background(if (esSeleccionado) PurpleButton else White)
                                .border(1.dp, if (esSeleccionado) Color.Transparent else GroupBorder, RoundedCornerShape(16.dp))
                                .clickable { categoriaSeleccionadaNombre = cat.nombre }
                                .padding(vertical = 12.dp, horizontal = 16.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(
                                        if (esSeleccionado) White.copy(alpha = 0.2f) else cat.colorClaro,
                                        CircleShape
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(id = cat.icono),
                                    contentDescription = cat.nombre,
                                    tint = if (esSeleccionado) White else cat.color,
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                cat.nombre,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                color = if (esSeleccionado) White else GroupTextPrimary
                            )
                        }
                    }
                }
            }
        }

        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = White),
                shape = RoundedCornerShape(16.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, GroupBorder),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Column {
                        Text(
                            text = "¿Quién pagó el gasto?",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = GroupTextPrimary
                        )
                        Text(
                            text = "Selecciona quién cubrió el total",
                            fontSize = 12.sp,
                            color = GroupTextSecondary
                        )
                    }

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(miembros) { miembro ->
                            ItemMiembroPagador(
                                miembro = miembro,
                                esSeleccionado = miembro.id == pagadorSeleccionadoId,
                                onClick = { pagadorSeleccionadoId = miembro.id }
                            )
                        }
                    }
                }
            }
        }


        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = White),
                shape = RoundedCornerShape(16.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, GroupBorder),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "Monto por integrante",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = GroupTextPrimary
                    )

                    val montoDouble = monto.toDoubleOrNull() ?: 0.0
                    val totalMiembros = miembros.size.coerceAtLeast(1)
                    val montoPorPersona = montoDouble / totalMiembros
                    val porcentajePorPersona = 100.0 / totalMiembros

                    miembros.forEach { usuario ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(GroupBalanceBackground, RoundedCornerShape(10.dp))
                                .padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(16.dp)
                                        .background(PurpleButton, RoundedCornerShape(4.dp))
                                )
                                Box(
                                    modifier = Modifier
                                        .size(28.dp)
                                        .background(GroupImagePlaceholder, CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Image(
                                        painter = painterResource(usuario.imgRes),
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop, 
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .clip(CircleShape)
                                    )
                                }
                                Column {
                                    Text(
                                        text = if (usuario.id == 1) "${usuario.nombre} (Tú)" else usuario.nombre,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 12.sp,
                                        color = GroupTextPrimary
                                    )
                                    Text(
                                        text = "${String.format("%.0f", porcentajePorPersona)}% del total",
                                        fontSize = 10.sp,
                                        color = GroupTextSecondary
                                    )
                                }
                            }
                            Text(
                                text = "$${String.format("%.2f", montoPorPersona)} MXN",
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                color = GroupTextPrimary
                            )
                        }
                    }

                    Surface(
                        color = BackGroundPurple,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                modifier = Modifier.weight(1f)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_pie_chart),
                                    contentDescription = null,
                                    tint = PurpleButton,
                                    modifier = Modifier.size(18.dp)
                                )
                                Text(
                                    text = "Total $${String.format("%.2f", montoDouble)} MXN • $${String.format("%.2f", montoPorPersona)} c/u",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = PurpleButton
                                )
                            }
                            Surface(color = White, shape = RoundedCornerShape(10.dp)) {
                                Text(
                                    text = "$totalMiembros personas",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = PurpleButton,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }
                        }
                    }
                }
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Surface(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { mostrarDatePicker = true },
                    color = White,
                    shape = RoundedCornerShape(12.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, GroupBorder)
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Surface(color = BackGroundPurple, shape = RoundedCornerShape(8.dp)) {
                            Box(modifier = Modifier.padding(6.dp)) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_calendar_outline),
                                    contentDescription = null,
                                    tint = PurpleButton,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                        Column {
                            Text("Fecha", fontSize = 10.sp, color = GroupTextSecondary)
                            Text(
                                text = fechaSeleccionada.format(formatoFecha),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = GroupTextPrimary
                            )
                        }
                    }
                }

                Surface(
                    modifier = Modifier.weight(1f),
                    color = White,
                    shape = RoundedCornerShape(12.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, GroupBorder)
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Surface(color = LightYellow, shape = RoundedCornerShape(8.dp)) {
                            Box(modifier = Modifier.padding(6.dp)) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_recipt),
                                    contentDescription = null,
                                    tint = GroupIconSecondary,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Ticket", fontSize = 10.sp, color = GroupTextSecondary)
                            Text("recibo_puert", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = GroupTextPrimary, maxLines = 1)
                        }
                        Icon(
                            painter = painterResource(id = R.drawable.ic_check),
                            contentDescription = null,
                            tint = PurpleButton,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }

        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = PurpleButton),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_recipt),
                            contentDescription = null,
                            tint = White,
                            modifier = Modifier.size(18.dp)
                        )
                        Text("Añadir Gasto Grupal", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = White)
                    }
                }

                Text(
                    text = "Cancelar",
                    color = GroupTextPrimary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { {}}
                        .padding(vertical = 8.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun PanelGrupoScreenPreview() {
    AñadirGastoScreen()
}