package zamudio.ramon.proyectogestorgastos.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import zamudio.ramon.proyectogestorgastos.R

val PlusJakartaSans = FontFamily(
    Font(R.font.plus_jakarta_sans_regular, FontWeight.Normal),
    Font(R.font.plus_jakarta_sans_medium, FontWeight.Medium),
    Font(R.font.plus_jakarta_sans_bold, FontWeight.Bold),
    Font(R.font.plus_jakarta_sans_extrabold, FontWeight.ExtraBold)
)

val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
    labelLarge = TextStyle(
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    bodySmall = TextStyle(
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    )
)

// Estilos de las pantallas y componentes de grupos.
object GroupTextStyles {
    val CaptionBold = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold
    )

    val SupportingBold = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 13.sp,
        fontWeight = FontWeight.Bold
    )

    val AmountBold = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    )

    val LabelNormal = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal
    )

    val BodyBold = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold
    )

    val LabelBold = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold
    )

    val LabelDefault = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 12.sp
    )

    val CaptionDefault = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 11.sp
    )

    val CardTitleBold = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold
    )

    val AvatarExtraBold = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 10.sp,
        fontWeight = FontWeight.ExtraBold
    )

    val SuccessTitleBold = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold
    )

    val BodyDefaultLine20 = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 14.sp,
        lineHeight = 20.sp
    )

    val BodyNormalLine20 = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 20.sp
    )

    val BodySemiBold = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold
    )

    val ActionBold = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold
    )

    val LabelMedium = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium
    )

    val TitleExtraBold = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 20.sp,
        fontWeight = FontWeight.ExtraBold
    )

    val LabelDefaultLine16 = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 12.sp,
        lineHeight = 16.sp
    )

    val ActionDefault = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 15.sp
    )

    val ActionSemiBold = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 15.sp,
        fontWeight = FontWeight.SemiBold
    )

    val LabelSemiBold = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold
    )

    val CaptionMedium = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 11.sp,
        fontWeight = FontWeight.Medium
    )

    val AvatarBold = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 10.sp,
        fontWeight = FontWeight.Bold
    )

    val CaptionSemiBold = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 11.sp,
        fontWeight = FontWeight.SemiBold
    )

    val ScreenTitleBold = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )

    val SupportingDefaultLine18 = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 13.sp,
        lineHeight = 18.sp
    )

    val TitleBold = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )

}
