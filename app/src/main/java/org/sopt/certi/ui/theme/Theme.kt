package org.sopt.certi.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    background = BackgroundColor,
    primary = MainColor500
)

@Composable
fun ProvideCertiColors(
    colors: CertiColors,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalCertiColorsProvider provides colors,
        content = content
    )
}

@Composable
fun CERTITheme(
    content: @Composable () -> Unit
) {
    ProvideCertiColors(
        colors = defaultCertiColors
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
