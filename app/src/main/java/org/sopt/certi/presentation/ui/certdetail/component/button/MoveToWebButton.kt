package org.sopt.certi.presentation.ui.certdetail.component.button

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.util.pressedClickable
import org.sopt.certi.core.util.roundedBackgroundWithBorder
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun MoveToWebButton(
    onClick: () -> Unit
) {
    var isPressed by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .roundedBackgroundWithBorder(
                cornerRadius = 12.dp,
                backgroundColor = if (isPressed) CertiTheme.colors.lightBlue else CertiTheme.colors.purpleWhite
            )
            .pressedClickable(
                changePressed = {
                    isPressed = it
                },
                onClick = {
                    onClick.invoke()
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.padding(vertical = screenHeightDp(8.dp), horizontal = screenWidthDp(26.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_link_16),
                contentDescription = null,
                tint = Color.Unspecified
            )

            Spacer(Modifier.widthForScreenPercentage(4.dp))

            Text(
                text = stringResource(R.string.cert_detail_move_to_web_title),
                style = CertiTheme.typography.caption.semibold_12,
                color = CertiTheme.colors.purpleBlue
            )
        }
    }
}

@Preview
@Composable
fun PreviewMoveToWebButton() {
    MoveToWebButton { }
}
