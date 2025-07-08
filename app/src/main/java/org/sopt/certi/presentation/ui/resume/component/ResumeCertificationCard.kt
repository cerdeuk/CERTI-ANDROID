package org.sopt.certi.presentation.ui.resume.component

import androidx.compose.foundation.Image
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.component.chip.CertiChipList
import org.sopt.certi.core.util.heightForScreenPercentage
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun ResumeCertificationCard(
    name: String,
    createdAt: String,
    cardImageUrl: String,
    tags: List<String>,
    modifier: Modifier = Modifier
){
    Box (
        modifier = modifier.clip(RoundedCornerShape(12.dp))
    ) {
        Image(
            painter = painterResource(R.drawable.img_certification_card_small),
            contentDescription = null,
            modifier = Modifier
                .heightForScreenPercentage(0.4f)
                .widthForScreenPercentage(0.58f)
        )

        Column (
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
    val parts = dateStr.split(".")
    val year = parts.getOrNull(0)?.toIntOrNull()
    val month = parts.getOrNull(1)?.toIntOrNull()
    val day = parts.getOrNull(2)?.toIntOrNull()

    val text = if (year != null && month != null && day != null) {
        stringResource(id = R.string.resume_certification_card_small, year, month, day)
    } else {
        ""
    }

    Text(
        text = text,
        style = CertiTheme.typography.caption.regular_12,
        color = CertiTheme.colors.gray600
    )
}

@Preview(showBackground = true)
@Composable
fun ResumeCertificationPreview(){
    Surface (
        modifier = Modifier.fillMaxSize()
    ) {
        ResumeCertificationCard(
            name = "GTQ 1급 (그래픽기술자격)",
            createdAt = "2025.07.03",
            cardImageUrl = "asdf",
            tags = listOf("태그", "태그", "태그")
        )
    }
}