package org.sopt.certi.presentation.ui.resume.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.sopt.certi.core.component.chip.CertiChipList
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun ResumeCertification(
    name: String,
    createdAt: String,
    cardImageUrl: String,
    tags: List<String>,
    modifier: Modifier = Modifier
){
    Surface (
        modifier = modifier.clip(RoundedCornerShape(12.dp))
    ) {
        AsyncImage(
            model = cardImageUrl,
            contentDescription = null
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

            Text(
                text = createdAt,
                style = CertiTheme.typography.caption.regular_12,
                color = CertiTheme.colors.gray600
            )
            Spacer(modifier = Modifier.height(8.dp))

            CertiChipList(categories = tags)
        }
    }
}

@Preview
@Composable
fun ResumeCertificationPreview(){
    ResumeCertification(
        name = "adsf",
        createdAt = "2025.07.188",
        cardImageUrl = "asdf",
        tags = listOf("태그", "태그", "태그")
    )
}