package org.sopt.certi.presentation.ui.resume.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun ResumeCertificationCardSmall(
    name: String,
    createdAt: String,
    cardImageUrl: String,
    tags: List<String>,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.clip(RoundedCornerShape(12.dp))
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(cardImageUrl)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .widthForScreenPercentage(0.55f)
                .heightForScreenPercentage(0.38f),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 32.dp)
        ) {
            Text(
                text = name,
                style = CertiTheme.typography.caption.bold_14,
                color = CertiTheme.colors.gray600
            )
            Spacer(modifier = Modifier.height(4.dp))

            FormatAcquisitionDate(createdAt)
            Spacer(modifier = Modifier.height(8.dp))

            CertiChipList(categories = tags)
        }
    }
}

@Composable
fun FormatAcquisitionDate(dateStr: String) {
    val (year, month, day) = dateStr.split(".").map { it.toInt() }

    val text = stringResource(id = R.string.resume_certification_card_small, year, month, day)

    Text(
        text = text,
        style = CertiTheme.typography.caption.regular_12,
        color = CertiTheme.colors.gray600
    )
}

@Preview(showBackground = true)
@Composable
fun ResumeCertificationPreview() {
    CERTITheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            ResumeCertificationCardSmall(
                name = "GTQ 1급 (그래픽기술자격)",
                createdAt = "2025.07.03",
                cardImageUrl = "https://mblogthumb-phinf.pstatic.net/MjAyMDAxMTBfMTgx/MDAxNTc4NjM1MTAxNjk1.m2q2MOZR3vArhqg1nC4-i2CEaVPlcPNcbic3KyTGj-cg.BBprGk0SqCmOMngKaT1CaaR_IBTJ8t-4LrOu_Nn2prAg.JPEG.p197273/88aad6.jpg?type=w800",
                tags = listOf("태그", "태그", "태그")
            )
        }
    }
}
