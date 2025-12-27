package org.sopt.certi.presentation.ui.setting.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun LogoutButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, CertiTheme.colors.gray300, RoundedCornerShape(8.dp))
            .noRippleClickable(onClick)
            .padding(vertical = screenHeightDp(14.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.logout),
            style = CertiTheme.typography.caption.semibold_14,
            color = CertiTheme.colors.black
        )
    }
}
