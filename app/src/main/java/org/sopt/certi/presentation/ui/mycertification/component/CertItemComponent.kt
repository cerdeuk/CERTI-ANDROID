package org.sopt.certi.presentation.ui.mycertification.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun CertItemTitle(
    certName: String,
    certType: String,
    modifier: Modifier = Modifier,
    onFavoriteClick: (() -> Unit)? = null,
    isFavorite: Boolean = false
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(screenWidthDp(8.dp))
    ) {
        Text(
            text = certName,
            style = CertiTheme.typography.subtitle.semibold_20,
            color = CertiTheme.colors.black
        )
        Text(
            text = certType,
            style = CertiTheme.typography.caption.regular_12,
            color = CertiTheme.colors.black
        )

        Spacer(modifier = Modifier.weight(1f))

        if (onFavoriteClick != null) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_star_24),
                contentDescription = null,
                tint = if (isFavorite) CertiTheme.colors.subYellow else CertiTheme.colors.gray100,
                modifier = Modifier.noRippleClickable(onFavoriteClick)
            )
        }
    }
}

@Composable
fun CertInfo(
    @DrawableRes iconRes: Int,
    testInfo: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(screenWidthDp(4.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(iconRes),
            contentDescription = null,
            tint = CertiTheme.colors.gray300
        )
        Text(
            text = testInfo,
            style = CertiTheme.typography.caption.regular_14,
            color = CertiTheme.colors.black
        )
    }
}
