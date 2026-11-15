
package com.example.trabfinal_des.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val AppColorScheme = darkColorScheme(
    primary = CinzaBotao,
    onPrimary = TextoEscuro,

    secondary = CinzaClaro,
    onSecondary = TextoEscuro,

    tertiary = CinzaBotao,
    onTertiary = TextoEscuro,

    background = AzulFundo,
    onBackground = BrancoTexto,

    surface = AzulFundo,
    onSurface = BrancoTexto,

    surfaceVariant = Color(0xFF294B68),
    onSurfaceVariant = BrancoTexto
)

@Composable
fun TrabFinal_DESTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = AppColorScheme,
        typography = Typography,
        content = content
    )
}
