package org.sopt.certi.presentation.ui.resume.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.domain.model.ResumeCertificationListData
import org.sopt.certi.ui.theme.CERTITheme

@Composable
fun ResumeCertificationContent(
    certifications: List<ResumeCertificationListData>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = screenHeightDp(0.02f),
                bottom = screenHeightDp(0.046f)
            ),
        horizontalArrangement = Arrangement.spacedBy(screenWidthDp(0.03f))
    ) {
        itemsIndexed(certifications) { index, certification ->
            if (index == 0) {
                Spacer(modifier = Modifier.width(screenWidthDp(0.055f)))
            }
            ResumeCertificationSmallCard(certification)
            if (index == certifications.lastIndex) {
                Spacer(modifier = Modifier.width(screenWidthDp(0.055f)))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResumeCertificationRowPreview() {
    CERTITheme {
        ResumeCertificationContent(
            certifications = listOf(
                ResumeCertificationListData(
                    name = "GTQ 1급 (그래픽기술자격)",
                    year = 2025,
                    month = 7,
                    day = 3,
                    cardImageUrl = "https://mblogthumb-phinf.pstatic.net/MjAyMDAxMTBfMTgx/MDAxNTc4NjM1MTAxNjk1.m2q2MOZR3vArhqg1nC4-i2CEaVPlcPNcbic3KyTGj-cg.BBprGk0SqCmOMngKaT1CaaR_IBTJ8t-4LrOu_Nn2prAg.JPEG.p197273/88aad6.jpg?type=w800",
                    tags = listOf("태그", "태그", "태그")
                ),
                ResumeCertificationListData(
                    name = "GTQ 1급 (그래픽기술자격)",
                    year = 2025,
                    month = 7,
                    day = 3,
                    cardImageUrl = "https://mblogthumb-phinf.pstatic.net/MjAyMDAxMTBfMTgx/MDAxNTc4NjM1MTAxNjk1.m2q2MOZR3vArhqg1nC4-i2CEaVPlcPNcbic3KyTGj-cg.BBprGk0SqCmOMngKaT1CaaR_IBTJ8t-4LrOu_Nn2prAg.JPEG.p197273/88aad6.jpg?type=w800",
                    tags = listOf("태그", "태그", "태그")
                ),
                ResumeCertificationListData(
                    name = "GTQ 1급 (그래픽기술자격)",
                    year = 2025,
                    month = 7,
                    day = 3,
                    cardImageUrl = "https://mblogthumb-phinf.pstatic.net/MjAyMDAxMTBfMTgx/MDAxNTc4NjM1MTAxNjk1.m2q2MOZR3vArhqg1nC4-i2CEaVPlcPNcbic3KyTGj-cg.BBprGk0SqCmOMngKaT1CaaR_IBTJ8t-4LrOu_Nn2prAg.JPEG.p197273/88aad6.jpg?type=w800",
                    tags = listOf("태그", "태그", "태그")
                )
            )
        )
    }
}
