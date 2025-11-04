package org.sopt.certi.presentation.ui.certdetail.component.tab

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun CertDetailTab(
    tabClicked: (DetailTabType) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedTab by remember { mutableStateOf(DetailTabType.Info) }

    Row(
        modifier = modifier
            .height(screenHeightDp(42.dp)),
        horizontalArrangement = Arrangement.spacedBy(screenWidthDp(32.dp))
    ) {
        CertDetailTabButton(
            detailTabType = DetailTabType.Info,
            isSelected = selectedTab == DetailTabType.Info,
            onClick = {
                tabClicked(DetailTabType.Info)
                selectedTab = DetailTabType.Info
            }
        )

        CertDetailTabButton(
            detailTabType = DetailTabType.Comment,
            isSelected = selectedTab == DetailTabType.Comment,
            onClick = {
                tabClicked(DetailTabType.Comment)
                selectedTab = DetailTabType.Comment
            }
        )
    }
}

@Composable
fun CertDetailTabButton(
    detailTabType: DetailTabType,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val textColor = if (isSelected) CertiTheme.colors.black else CertiTheme.colors.gray300
    val viewWidth = when (detailTabType) {
        DetailTabType.Info -> 76.dp
        DetailTabType.Comment -> 48.dp
    }

    Column(
        modifier = modifier
            .fillMaxHeight()
            .width(viewWidth)
            .noRippleClickable {
                onClick()
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.weight(1f))

        Text(
            text = stringResource(detailTabType.tabTextId),
            style = CertiTheme.typography.body.bold_16,
            color = textColor
        )

        Spacer(Modifier.weight(1f))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(
                    if (isSelected) CertiTheme.colors.gray600 else CertiTheme.colors.transParent
                )
        )
    }
}

enum class DetailTabType(val tabTextId: Int) {
    Info(R.string.cert_detail_info_tab),
    Comment(R.string.cert_detail_comment_tab)
}

@Preview(showBackground = true)
@Composable
private fun PreviewCertDetailTab() {
    CertDetailTab(
        tabClicked = {}
    )
}
