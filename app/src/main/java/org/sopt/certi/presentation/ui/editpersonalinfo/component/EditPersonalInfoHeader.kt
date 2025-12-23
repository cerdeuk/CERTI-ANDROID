package org.sopt.certi.presentation.ui.editpersonalinfo.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun EditPersonalInfoHeader(
    isSaveEnable: Boolean,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
    headerTitle: String = ""
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(CertiTheme.colors.white)
            .padding(vertical = screenHeightDp(20.dp))
    ) {
        Text(
            text = headerTitle,
            style = CertiTheme.typography.subtitle.semibold_20,
            color = CertiTheme.colors.gray600,
            modifier = Modifier.align(Alignment.Center)
        )
        Text(
            text = stringResource(R.string.personal_save),
            style = CertiTheme.typography.body.semibold_18,
            color = if (isSaveEnable) CertiTheme.colors.mainBlue else CertiTheme.colors.gray400,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .noRippleClickable { onSaveClick() }
        )
    }
}
