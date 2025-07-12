package org.sopt.certi.presentation.ui.resume.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.sopt.certi.ui.theme.CertiTheme
import androidx.compose.material3.Text

@Composable
fun CertificationCardFront() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = CertiTheme.colors.skyBlue
            )
    ) {
        Text("앞면")
    }
}
