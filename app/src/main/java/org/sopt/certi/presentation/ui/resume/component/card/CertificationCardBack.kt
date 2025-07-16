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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import org.sopt.certi.R
import org.sopt.certi.core.component.chip.CertiChipList
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.presentation.ui.resume.component.AcquiredDateChip
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme
import java.time.LocalDate

@Composable
fun CertificationCardBack(
    certificationData: CertificationData,
    nickname: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(12.dp))
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(certificationData.cardBackImageUrl)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        Column(
            modifier = Modifier
                .padding(start = screenWidthDp(24.dp), end = screenWidthDp(24.dp), top = screenHeightDp(36.dp)),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = certificationData.certificationName,
                style = CertiTheme.typography.body.bold_18,
                color = CertiTheme.colors.white
            )
            Spacer(modifier = Modifier.height(screenHeightDp(8.dp)))

            CertiChipList(
                categories = certificationData.tags
            )
            Spacer(modifier = Modifier.height(screenHeightDp(36.dp)))
            Text(
                text = certificationData.description,
                style = CertiTheme.typography.caption.regular_12,
                color = CertiTheme.colors.white
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = screenWidthDp(24.dp), bottom = screenHeightDp(34.dp)),
            verticalArrangement = Arrangement.spacedBy(screenHeightDp(4.dp))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(screenWidthDp(4.dp))
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_check_24),
                    contentDescription = null,
                    tint = CertiTheme.colors.white,
                    modifier = Modifier.size(screenWidthDp(24.dp))
                )
                Text(
                    text = stringResource(R.string.resume_acquired_title, nickname),
                    style = CertiTheme.typography.caption.semibold_14,
                    color = CertiTheme.colors.white
                )
            }

            AcquiredDateChip(certificationData = certificationData)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CertificationCardBackPreview() {
    val dummyCertification = CertificationData(
        certificationId = 1,
        certificationName = "GTQ 1급 (그래픽 기술 자격)",
        description = "디자인 관련 자격증입니다.",
        tags = listOf("디자인", "컴퓨터", "김민지"),
        createdAt = LocalDate.of(2024, 5, 12),
        cardBackImageUrl = ""
    )

    CERTITheme {
        CertificationCardBack(
            certificationData = dummyCertification,
            nickname = "김서티",
            modifier = Modifier
                .width(250.dp)
                .height(376.dp)
                .background(CertiTheme.colors.gray100)
        )
    }
}
