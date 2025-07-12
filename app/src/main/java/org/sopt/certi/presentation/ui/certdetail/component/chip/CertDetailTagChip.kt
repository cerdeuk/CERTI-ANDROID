package org.sopt.certi.presentation.ui.certdetail.component.chip

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.sopt.certi.core.util.roundedBackgroundWithBorder
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun CertDetailTagChip(
    tag: String
) {
    Box(
        modifier = Modifier
            .roundedBackgroundWithBorder(cornerRadius = 24.dp, backgroundColor = CertiTheme.colors.lightPurple)
            .padding(horizontal = screenWidthDp(8.dp), vertical = screenHeightDp(4.dp))
    ) {
        Text(
            text = tag,
            style = CertiTheme.typography.caption.semibold_12,
            color = CertiTheme.colors.mainBlue
        )
    }
}
