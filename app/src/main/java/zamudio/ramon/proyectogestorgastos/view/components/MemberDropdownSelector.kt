package zamudio.ramon.proyectogestorgastos.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupFieldBackground
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupTextPrimary
import zamudio.ramon.proyectogestorgastos.ui.theme.GroupTextSecondary
import zamudio.ramon.proyectogestorgastos.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemberDropdownSelector(
    label: String,
    members: List<String>,
    modifier : Modifier = Modifier,
    selectedMember: String,
    onMemberSelected: (String) -> Unit = {}
){
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = GroupTextSecondary
        )

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            Surface (
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable),
                shape = RoundedCornerShape(12.dp),
                color = GroupFieldBackground
            ) {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = selectedMember,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = GroupTextPrimary
                    )
                    Icon(
                        painter = painterResource(R.drawable.ic_keyboard_down),
                        contentDescription = null,
                        tint = GroupTextSecondary
                    )
                }
            }

            ExposedDropdownMenu (
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                members.forEach { miembro ->
                    DropdownMenuItem(
                        text = { Text(miembro) },
                        onClick = {
                            expanded = false
                            onMemberSelected(miembro)
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun MemberDropdownSelectorPreview(){
    var selectedMember by remember { mutableStateOf("Saul Apodaca") }
    MemberDropdownSelector("¿Quién recibió?",
        listOf("Saul Apodaca", "Camila Zubia", "Ramon Zamudio", "Adel Mendez"),
        selectedMember = selectedMember,
        onMemberSelected = { selectedMember = it })
}
