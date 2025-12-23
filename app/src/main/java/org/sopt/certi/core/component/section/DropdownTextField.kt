package org.sopt.certi.core.component.section

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.roundedBackgroundWithBorder
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun DropdownTextField(
    value: String,
    placeholder: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = CertiTheme.colors.white,
    textColor: Color = CertiTheme.colors.black
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .roundedBackgroundWithBorder(
                cornerRadius = 8.dp,
                backgroundColor = backgroundColor,
                borderColor = CertiTheme.colors.gray200,
                borderWidth = 1.dp
            )
            .noRippleClickable(onClick)
            .padding(vertical = screenWidthDp(8.dp), horizontal = screenWidthDp(12.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = if (value.isEmpty()) placeholder else value,
            style = CertiTheme.typography.caption.semibold_12,
            color = if (value.isEmpty()) CertiTheme.colors.gray300 else textColor,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )

        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_arrowdown_24),
            contentDescription = null,
            tint = CertiTheme.colors.gray400
        )
    }
}
