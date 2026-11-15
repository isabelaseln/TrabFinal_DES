
package com.example.trabfinal_des.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val AppColorScheme = darkColorScheme(
    primary = AzulVivo,
    onPrimary = BrancoTexto,

    secondary = Verde,
    onSecondary = TextoEscuro,

    tertiary = Amarelo,
    onTertiary = TextoEscuro,

    background = AzulFundo,
    onBackground = BrancoTexto,

    surface = AzulCard,
    onSurface = BrancoTexto,

    surfaceVariant = AzulFundo,
    onSurfaceVariant = CinzaTexto
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
