package org.sopt.certi.presentation.ui.resume.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.sopt.certi.ui.theme.CertiTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.sopt.certi.R
import org.sopt.certi.core.component.chip.ResetBadge
import org.sopt.certi.core.util.bottomBorder
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.toSpacedDotDate
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.ui.theme.CERTITheme

@Composable
fun CertificationCardFront(
    certificationData: CertificationData,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.clip(RoundedCornerShape(12.dp))
    ) {
        AsyncImage(
            model = certificationData.cardFrontImageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = stringResource(
                    R.string.resume_certification_card_small,
                    certificationData.acquisitionDate.toSpacedDotDate()
                ),
                style = CertiTheme.typography.caption.regular_10,
                color = CertiTheme.colors.white,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = screenWidthDp(77.dp))
            )

            Spacer(modifier = Modifier.weight(1f))

            Column(
                modifier = Modifier
                    .padding(horizontal = screenWidthDp(28.dp))
                    .padding(bottom = screenWidthDp(11.dp)),
                verticalArrangement = Arrangement.spacedBy(screenWidthDp(6.dp))
            ) {
                Text(
                    text = certificationData.certificationName,
                    style = CertiTheme.typography.body.bold_18,
                    color = CertiTheme.colors.blueWhite
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(screenWidthDp(6.dp))
                ) {
                    certificationData.tags.forEach { text ->
                        ResetBadge(
                            text = text,
                            textStyle = CertiTheme.typography.caption.regular_10,
                            backgroundColor = CertiTheme.colors.white,
                            textColor = CertiTheme.colors.mainBlue
                        )
                    }
                }
            }

            Text(
                text = stringResource(id = R.string.resume_flip_card_touch),
                style = CertiTheme.typography.caption.semibold_10,
                color = CertiTheme.colors.purpleWhite,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = screenWidthDp(45.dp))
                    .bottomBorder(
                        strokeWidth = 1.dp,
                        color = CertiTheme.colors.purpleWhite
                    )
                    .padding(vertical = screenHeightDp(2.dp))
            )
        }
    }
}

@Preview
@Composable
fun CertificationCardFrontPreview() {
    CERTITheme {
        CertificationCardFront(
            certificationData = CertificationData(
                certificationId = 1L,
                certificationName = "GTQ 1급 (그래픽기술자격)",
                cardFrontImageUrl = "",
                tags = listOf("디자인", "김민지", "김민지"),
                acquisitionDate = "2026-02-04"
            ),
            modifier = Modifier
                .width(250.dp)
                .height(376.dp)
                .background(CertiTheme.colors.mainBlue)
        )
    }
}
