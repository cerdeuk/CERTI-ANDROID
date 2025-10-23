package org.sopt.certi.presentation.ui.my.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun PersonalInfoTextFieldButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnable: Boolean = false
) {
    Box(
        modifier = modifier
            .border(
                width = 1.dp,
                color = when {
                    isEnable -> CertiTheme.colors.gray300
                    else -> CertiTheme.colors.gray200
                },
                shape = RoundedCornerShape(100.dp)
            )
            .noRippleClickable {
                if (isEnable) {
                    onClick()
                }
            }
            .padding(vertical = screenWidthDp(4.dp), horizontal = screenWidthDp(12.dp))
    ) {
        Text(
            text = text,
            style = CertiTheme.typography.caption.regular_12,
            color = when {
                isEnable -> CertiTheme.colors.gray600
                else -> CertiTheme.colors.gray300
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MyPageTextFieldButtonPreview() {
    CERTITheme {
        PersonalInfoTextFieldButton(
            text = "중복확인",
            onClick = {}
        )
    }
}
