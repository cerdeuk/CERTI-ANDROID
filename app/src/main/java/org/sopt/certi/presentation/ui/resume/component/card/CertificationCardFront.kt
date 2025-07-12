package org.sopt.certi.presentation.ui.resume.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import org.sopt.certi.domain.model.CertificationData
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
                .padding(start = 20.dp, top = 40.dp)
        ) {
            Text(
                text = certificationData.certificationName,
                style = CertiTheme.typography.caption.regular_14,
                color = CertiTheme.colors.gray600
            )
            Spacer(modifier = Modifier.height(screenHeightDp(8.dp)))
            CertiChipList(
                categories = certificationData.tags
            )
            Spacer(modifier = Modifier.height(screenHeightDp(224.dp)))
            Spacer(modifier = Modifier.width(screenWidthDp(16.dp)))
        }
        Column(
            modifier = Modifier
                .padding(end = screenWidthDp(16.dp), bottom = screenHeightDp(16.dp)),
            verticalArrangement = Arrangement.spacedBy(screenHeightDp(4.dp))
        ) {
            Text(
                text = stringResource(id = R.string.resume_flip_card_touch),
                style = CertiTheme.typography.caption.regular_12,
                color = CertiTheme.colors.mainBlue
            )
            Box(
                modifier = Modifier
                    .height(screenHeightDp(1.dp))
                    .width(screenWidthDp(90.dp))
                    .background(CertiTheme.colors.mainBlue)
            ) {

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
                cardFrontImageUrl = "https://dummyimage.com/250x376/87cefa/ffffff&text=GTQ+1급",
                tags = listOf("디자인", "컴퓨터그래픽스")
            ),
            modifier = Modifier
                .width(250.dp)
                .height(376.dp)
                .background(CertiTheme.colors.gray100)
        )
    }
}
