package org.sopt.certi.core.component.chip

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.util.pressedClickable
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.ui.theme.CertiTheme

enum class CertiEditChipType(
    @StringRes val label: Int
) {
    MODIFY(R.string.my_certification_modify),
    DELETE(R.string.my_certification_delete)
}

@Composable
fun CertiEditChip(
    type: CertiEditChipType,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    var isPressed by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .background(
                color = if (isPressed) {
                    CertiTheme.colors.gray100
                } else {
                    CertiTheme.colors.white
                },
                shape = RoundedCornerShape(100.dp)
            )
            .border(width = 1.dp, color = CertiTheme.colors.gray300, shape = RoundedCornerShape(100.dp))
            .padding(vertical = screenWidthDp(4.dp), horizontal = 12.dp)
            .pressedClickable(
                changePressed = {
                    isPressed = it
                },
                onClick = {
                    onClick()
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(type.label),
            style = CertiTheme.typography.caption.regular_12,
            color = CertiTheme.colors.gray600
        )
    }
}

@Preview
@Composable
private fun PreviewCertiEditChip() {
    Row {
        CertiEditChip(type = CertiEditChipType.MODIFY) {}
        Spacer(Modifier.widthForScreenPercentage(4.dp))
        CertiEditChip(type = CertiEditChipType.DELETE) {}
    }
}
