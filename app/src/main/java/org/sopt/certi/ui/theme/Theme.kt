package org.sopt.certi.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    background = White,
    primary = MainBlue
)

object CertiTheme {
    val colors: CertiColors
        @Composable
        @ReadOnlyComposable
        get() = LocalCertiColorsProvider.current

    val typography: CertiTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalCertiTypographyProvider.current
}

@Composable
fun ProvideCertiColorsAndTypography(
    colors: CertiColors,
    typography: CertiTypography,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalCertiColorsProvider provides colors,
        LocalCertiTypographyProvider provides typography,
        content = content
    )
}

@Composable
fun CERTITheme(
    content: @Composable () -> Unit
) {
    ProvideCertiColorsAndTypography(
        colors = defaultCertiColors,
        typography = defaultCertiTypography
    ) {
        val view = LocalView.current
        if (!view.isInEditMode) {
            SideEffect {
                (view.context as Activity).window.run {
                    WindowCompat.getInsetsController(this, view).isAppearanceLightStatusBars = true
                }
            }
        }

        MaterialTheme(
            colorScheme = LightColorScheme,
            content = content
        )
    }
}
