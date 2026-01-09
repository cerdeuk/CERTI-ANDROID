package org.sopt.certi.presentation.ui.setting.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun MarketingConfirmSnackbar(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        color = CertiTheme.colors.mainBlue,
        shape = RoundedCornerShape(4.dp)
    ) {
        Text(
            text = stringResource(R.string.setting_snackbar_message),
            style = CertiTheme.typography.caption.semibold_14,
            color = CertiTheme.colors.white,
            modifier = Modifier.padding(horizontal = screenWidthDp(16.dp), vertical = screenHeightDp(6.dp))
        )
    }
}
