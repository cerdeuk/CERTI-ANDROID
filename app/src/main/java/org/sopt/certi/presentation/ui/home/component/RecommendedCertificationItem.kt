package org.sopt.certi.presentation.ui.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.res.stringResource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.component.chip.CertiChipList
import org.sopt.certi.core.util.heightForScreenPercentage
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.domain.model.RecommendedCertificationData
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun RecommendedCertificationItem(
    recommendedCertificationData: RecommendedCertificationData,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(CertiTheme.colors.purpleWhite)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp, start = 16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = recommendedCertificationData.name,
                    style = CertiTheme.typography.body.bold_18,
                    color = CertiTheme.colors.gray600
                )
                VerticalDivider(
                    modifier = Modifier
                        .heightForScreenPercentage(0.03f)
                        .widthForScreenPercentage(0.002f)
                        .padding(vertical = 5.dp),
                    color = CertiTheme.colors.gray200,
                    thickness = 1.dp
                )
                Text(
                    text = stringResource(R.string.home_recommended_score, recommendedCertificationData.score),
                    style = CertiTheme.typography.caption.regular_14,
                    color = CertiTheme.colors.gray500
                )
            }
            CertiChipList(
                categories = recommendedCertificationData.categories,
                modifier = Modifier,
                spacing = 6.dp
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFE0E0E0)
@Composable
fun RecommendedCertificationItemPreview() {
    val sampleData = RecommendedCertificationData(
        name = "OPIc",
        score = 90,
        categories = listOf("컴퓨터공학", "재무/세무/IR", "재무/세무/IR")
    )

    RecommendedCertificationItem(
        recommendedCertificationData = sampleData,
        modifier = Modifier.padding(16.dp)
    )
}
