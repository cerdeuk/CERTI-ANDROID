package org.sopt.certi.presentation.ui.mypage.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun MyPageCertMenuItem(
    acquireExpectedCertCount: Int,
    acquiredCertCount: Int,
    favoriteCertCount: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .noRippleClickable(onClick)
            .background(CertiTheme.colors.white)
            .padding(horizontal = screenWidthDp(16.dp))
            .padding(top = screenHeightDp(20.dp), bottom = screenHeightDp(24.dp))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(screenWidthDp(8.dp))
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_file_24),
                contentDescription = null,
                tint = CertiTheme.colors.gray400
            )
            Text(
                text = stringResource(R.string.mypage_certification),
                style = CertiTheme.typography.caption.semibold_14,
                color = CertiTheme.colors.black
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_arrowright_24),
                contentDescription = null,
                tint = CertiTheme.colors.gray600
            )
        }
        Row(
            modifier = Modifier
                .padding(top = screenHeightDp(20.dp))
                .padding(horizontal = screenWidthDp(12.dp))
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Absolute.SpaceBetween
        ) {
            MyCertCategory(
                myCertCategory = stringResource(R.string.cert_detail_acquire_expected_button_text),
                certCount = acquireExpectedCertCount,
                modifier = Modifier.weight(weight = 1f, fill = false)
            )
            VerticalDivider()
            MyCertCategory(
                myCertCategory = stringResource(R.string.cert_detail_acquired_button_text),
                certCount = acquiredCertCount,
                modifier = Modifier.weight(weight = 1f, fill = false)
            )
            VerticalDivider()
            MyCertCategory(
                myCertCategory = stringResource(R.string.cert_list_favorite_btn),
                certCount = favoriteCertCount,
                modifier = Modifier.weight(weight = 1f, fill = false)
            )
        }
    }
}

@Composable
private fun MyCertCategory(
    myCertCategory: String,
    certCount: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(horizontal = screenWidthDp(8.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = myCertCategory,
            style = CertiTheme.typography.caption.regular_14,
            color = CertiTheme.colors.gray500,
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(R.string.mypage_cert_count, certCount),
            style = CertiTheme.typography.caption.semibold_14,
            color = CertiTheme.colors.gray600,
            modifier = Modifier.padding(top = 8.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MyPageCertMenuItemPreview() {
    CERTITheme {
        MyPageCertMenuItem(
            acquiredCertCount = 0,
            acquireExpectedCertCount = 0,
            favoriteCertCount = 0,
            onClick = {}
        )
    }
}
