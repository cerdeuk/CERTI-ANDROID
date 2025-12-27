package org.sopt.certi.core.component.header

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import org.sopt.certi.R
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun MyPageHeader(
    modifier: Modifier = Modifier,
    headerTitle: String? = null,
    isSaveEnable: Boolean = false,
    onSaveClick: (() -> Unit)? = null
) {
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        headerTitle?.let {
            Text(
                text = headerTitle,
                style = CertiTheme.typography.subtitle.semibold_20,
                color = CertiTheme.colors.gray600,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        onSaveClick?.let {
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
