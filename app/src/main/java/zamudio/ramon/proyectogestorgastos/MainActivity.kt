package zamudio.ramon.proyectogestorgastos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.Scaffold
import zamudio.ramon.proyectogestorgastos.navigation.Myapp
import zamudio.ramon.proyectogestorgastos.ui.theme.ProyectoGestorGastosTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProyectoGestorGastosTheme {
                Scaffold(contentWindowInsets = WindowInsets(0, 0, 0, 0)) { innerPadding ->
                    Myapp(innerPadding)
                }
            }
        }
    }
}
