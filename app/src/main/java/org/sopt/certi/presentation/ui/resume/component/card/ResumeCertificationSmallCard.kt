package org.sopt.certi.presentation.ui.resume.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.sopt.certi.R
import org.sopt.certi.core.component.chip.ResetBadge
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.toSpacedDotDate
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun ResumeCertificationSmallCard(
    certification: CertificationData,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .widthForScreenPercentage(200.dp)
            .aspectRatio(2f / 3f)
            .noRippleClickable(onClick)
    ) {
        AsyncImage(
            model = certification.cardFrontImageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        Text(
            text = stringResource(
                id = R.string.resume_certification_card_small,
                certification.acquisitionDate.toSpacedDotDate()
            ),
            style = CertiTheme.typography.caption.regular_10,
            color = CertiTheme.colors.white,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = screenWidthDp(60.dp))
                .align(Alignment.TopCenter)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = screenWidthDp(32.dp))
                .padding(bottom = screenWidthDp(47.dp))
                .align(Alignment.BottomStart),
            verticalArrangement = Arrangement.spacedBy(screenWidthDp(5.dp))
        ) {
            Text(
                text = certification.certificationName,
                style = CertiTheme.typography.caption.semibold_10,
                color = CertiTheme.colors.blueWhite,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(screenWidthDp(6.dp))
            ) {
                certification.tags.forEach { text ->
                    ResetBadge(
                        text = text,
                        textColor = CertiTheme.colors.mainBlue,
                        backgroundColor = CertiTheme.colors.white
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ResumeCertificationSmallCardPreview() {
    CERTITheme {
        ResumeCertificationSmallCard(
            certification =
            CertificationData(
                certificationId = 1,
                certificationName = "GTQ 1급 (그래픽기술자격) GTQ 1급 (그래픽기술자격) GTQ 1급 (그래픽기술자격) GTQ 1급 (그래픽기술자격)",
                acquisitionDate = "2026-02-07",
                cardFrontImageUrl = "",
                tags = listOf("태그태그태그", "태그태그태그", "태그태그")
            ),
            onClick = {},
            modifier = Modifier.background(CertiTheme.colors.mainBlue)
        )
    }
}
