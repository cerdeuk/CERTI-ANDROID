package org.sopt.certi.presentation.ui.resume.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
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
import org.sopt.certi.core.component.chip.CertiChipList
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.presentation.type.CertCardColorType
import org.sopt.certi.ui.theme.CERTITheme

@Composable
fun CertificationCardFront(
    certificationData: CertificationData,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(12.dp))
    ) {
        AsyncImage(
            model = certificationData.cardFrontImageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )
        Column(
            modifier = Modifier
                .padding(horizontal = screenWidthDp(20.dp))
                .padding(top = screenHeightDp(40.dp))
        ) {
            Text(
                text = certificationData.certificationName,
                style = CertiTheme.typography.body.bold_18,
                color = CertCardColorType.fromIndex(certificationData.index).textColor
            )
            Spacer(modifier = Modifier.height(screenHeightDp(4.dp)))
            Text(
                text = stringResource(
                    R.string.resume_certification_flip_card,
                    certificationData.createdAt.year,
                    certificationData.createdAt.month,
                    certificationData.createdAt.dayOfWeek
                ),
                style = CertiTheme.typography.caption.regular_12,
                color = CertCardColorType.fromIndex(certificationData.index).textColor
            )
            Spacer(modifier = Modifier.height(screenHeightDp(8.dp)))
            CertiChipList(
                categories = certificationData.tags,
                textStyle = CertiTheme.typography.caption.semibold_12,
                backgroundColor = CertCardColorType.fromIndex(certificationData.index).chipBackgroundColor,
                textColor = CertCardColorType.fromIndex(certificationData.index).chipTextColor
            )
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ) {
            Column(
                modifier = Modifier
                    .padding(end = screenWidthDp(16.dp), bottom = screenHeightDp(16.dp))
                    .width(IntrinsicSize.Max),
                verticalArrangement = Arrangement.spacedBy(screenHeightDp(4.dp))
            ) {
                Text(
                    text = stringResource(id = R.string.resume_flip_card_touch),
                    style = CertiTheme.typography.caption.regular_12,
                    color = CertCardColorType.fromIndex(certificationData.index).textColor
                )
                HorizontalDivider(
                    thickness = 1.dp,
                    color = CertCardColorType.fromIndex(certificationData.index).textColor
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CertificationCardFrontPreview() {
    CERTITheme {
        CertificationCardFront(
            certificationData = CertificationData(
                certificationId = 1L,
                certificationName = "GTQ 1급 (그래픽기술자격)",
                cardFrontImageUrl = "",
                tags = listOf("디자인", "김민지", "김민지")
            ),
            modifier = Modifier
                .width(250.dp)
                .height(376.dp)
                .background(CertiTheme.colors.gray100)
        )
    }
}
