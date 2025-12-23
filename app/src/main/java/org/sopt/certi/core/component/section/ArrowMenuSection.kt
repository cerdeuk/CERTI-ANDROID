package org.sopt.certi.core.component.section

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import org.sopt.certi.R
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun ArrowMenuSection(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        Text(
            text = text,
            style = CertiTheme.typography.body.semibold_16,
            color = CertiTheme.colors.black,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_arrowright_24),
            contentDescription = null,
            tint = CertiTheme.colors.black,
            modifier = Modifier.noRippleClickable { onClick() }
        )
    }
}
