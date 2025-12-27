package org.sopt.certi.core.component.listitem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import org.sopt.certi.R
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun MenuRow(
    text: String,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    trailingContent: @Composable () -> Unit = {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_arrowright_24),
            contentDescription = null,
            tint = CertiTheme.colors.gray600
        )
    }
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .then(
                if (onClick != null) {
                    Modifier.noRippleClickable(onClick)
                } else {
                    Modifier
                }
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = text,
            style = CertiTheme.typography.body.semibold_16,
            color = CertiTheme.colors.black
        )
        trailingContent()
    }
}
