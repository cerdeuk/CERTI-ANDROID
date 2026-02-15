package org.sopt.certi.core.component.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.ui.theme.CertiTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CertItemTitleSection(
    certName: String,
    certType: String,
    modifier: Modifier = Modifier,
    onFavoriteClick: (() -> Unit)? = null,
    isFavorite: Boolean = false
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(screenWidthDp(8.dp))
    ) {
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(screenWidthDp(8.dp)),
            verticalArrangement = Arrangement.spacedBy(screenHeightDp(4.dp))
        ) {
            Text(
                text = certName,
                style = CertiTheme.typography.subtitle.semibold_20,
                color = CertiTheme.colors.black,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Text(
                text = certType,
                style = CertiTheme.typography.caption.regular_12,
                color = CertiTheme.colors.black,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        onFavoriteClick?.let {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_star_24),
                contentDescription = null,
                tint = if (isFavorite) CertiTheme.colors.subYellow else CertiTheme.colors.gray100,
                modifier = Modifier.noRippleClickable(onFavoriteClick)
            )
        }
    }
}
