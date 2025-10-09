package org.sopt.certi.presentation.ui.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.res.stringResource
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.component.chip.CertiChipList
import org.sopt.certi.core.util.heightForScreenPercentage
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.roundedBackgroundWithBorder
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun RecommendedCertificationListSection(
    recommendedList: List<CertificationData>,
    onDetailClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(screenHeightDp(16.dp)),
        modifier = modifier.fillMaxWidth()
    ) {
        recommendedList.forEach { item ->
            RecommendedCertificationItem(
                recommendedCertificationData = item,
                onClick = { onDetailClick(item.certificationId) }
            )
        }
    }
}

@Composable
fun RecommendedCertificationItem(
    recommendedCertificationData: CertificationData,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .noRippleClickable { onClick() }
            .roundedBackgroundWithBorder(
                cornerRadius = 12.dp,
                backgroundColor = CertiTheme.colors.purpleWhite
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = screenWidthDp(16.dp), vertical = screenHeightDp(10.dp))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(screenWidthDp(8.dp)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = recommendedCertificationData.certificationName,
                    style = CertiTheme.typography.body.bold_18,
                    color = CertiTheme.colors.gray600
                )
                VerticalDivider(
                    modifier = Modifier
                        .heightForScreenPercentage(24.dp)
                        .widthForScreenPercentage(2.dp)
                        .padding(vertical = 5.dp),
                    color = CertiTheme.colors.gray200,
                    thickness = 1.dp
                )

                Text(
                    text = stringResource(R.string.home_recommended_score, recommendedCertificationData.recommendScore),
                    style = CertiTheme.typography.caption.bold_14.copy(
                        brush = CertiTheme.colors.gradientBluePurple
                    )
                )
            }

            Spacer(Modifier.heightForScreenPercentage(8.dp))

            Text(
                text = recommendedCertificationData.description,
                style = CertiTheme.typography.caption.regular_12,
                color = CertiTheme.colors.gray600,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(Modifier.heightForScreenPercentage(10.dp))

            CertiChipList(
                categories = recommendedCertificationData.tags,
                spacing = screenWidthDp(6.dp)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFE0E0E0)
@Composable
fun RecommendedCertificationItemPreview() {
    val sampleData = CertificationData(
        certificationId = 1,
        certificationName = "OPIc",
        recommendScore = 90,
        description = "자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다.",
        tags = listOf("컴퓨터공학", "재무/세무/IR", "재무/세무/IR")
    )

    RecommendedCertificationItem(
        recommendedCertificationData = sampleData,
        modifier = Modifier.padding(16.dp),
        onClick = {}
    )
}
