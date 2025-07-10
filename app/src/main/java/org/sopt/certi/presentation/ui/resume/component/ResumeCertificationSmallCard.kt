package org.sopt.certi.presentation.ui.resume.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import org.sopt.certi.R
import org.sopt.certi.core.component.chip.CertiChipList
import org.sopt.certi.core.util.heightForScreenPercentage
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.domain.model.ResumeCertificationListData
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun ResumeCertificationSmallCard(
    certification: ResumeCertificationListData,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.clip(RoundedCornerShape(12.dp))
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(certification.cardImageUrl)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .widthForScreenPercentage(200.dp)
                .heightForScreenPercentage(300.dp),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .padding(
                    horizontal = screenWidthDp(16.dp),
                    vertical = screenHeightDp(32.dp)
                )
        ) {
            Text(
                text = certification.name,
                style = CertiTheme.typography.caption.bold_14,
                color = CertiTheme.colors.gray600
            )
            Spacer(modifier = Modifier.height(screenHeightDp(4.dp)))

            Text(
                text = stringResource(
                    id = R.string.resume_certification_card_small,
                    certification.year,
                    certification.month,
                    certification.day
                ),
                style = CertiTheme.typography.caption.regular_12,
                color = CertiTheme.colors.gray600
            )
            Spacer(modifier = Modifier.height(screenHeightDp(8.dp)))

            CertiChipList(categories = certification.tags)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ResumeCertificationSmallCardPreview() {
    CERTITheme {
        ResumeCertificationSmallCard(
            certification = ResumeCertificationListData(
                name = "GTQ 1급 (그래픽기술자격)",
                year = 2025,
                month = 7,
                day = 3,
                cardImageUrl = "https://sopt-certi-bucket.s3.ap-northeast-2.amazonaws.com/certi/color%3Dblue.png",
                tags = listOf("태그", "태그", "태그")
            )
        )
    }
}
