package org.sopt.certi.core.component.header

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun EditInfoHeader(
    modifier: Modifier = Modifier,
    headerTitle: String? = null,
    isSaveEnable: Boolean = false,
    onSaveClick: (() -> Unit)? = null
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(CertiTheme.colors.white)
    ) {
        if (headerTitle != null) {
            Text(
                text = headerTitle,
                style = CertiTheme.typography.subtitle.semibold_20,
                color = CertiTheme.colors.gray600,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        if (onSaveClick != null) {
            Text(
                text = stringResource(R.string.personal_save),
                style = CertiTheme.typography.body.semibold_18,
                color = if (isSaveEnable) CertiTheme.colors.mainBlue else CertiTheme.colors.gray400,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .noRippleClickable {
                        if (isSaveEnable) {
                            onSaveClick()
                        }
                    }
            )
        }
    }
}

@Preview
@Composable
private fun EditPersonalInfoHeaderPreview() {
    CERTITheme {
        EditInfoHeader(
            headerTitle = "",
            isSaveEnable = false,
            onSaveClick = {},
            modifier = Modifier.padding(vertical = screenHeightDp(22.dp), horizontal = screenWidthDp(20.dp))
        )
    }
}
