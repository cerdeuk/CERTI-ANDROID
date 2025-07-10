package org.sopt.certi.presentation.ui.resume.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.domain.model.ResumeCertificationListData
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun ResumeCertificationSection(
    title: String,
    onClick: () -> Unit,
    certifications: List<ResumeCertificationListData>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = screenHeightDp(36.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = screenWidthDp(20.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = CertiTheme.typography.subtitle.semibold_20,
                color = CertiTheme.colors.gray600,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_arrowright_36),
                contentDescription = null,
                modifier = Modifier.noRippleClickable { onClick() }
            )
        }
        if (certifications.isEmpty()) {
            ResumeEmptyContent(
                text = stringResource(R.string.resume_empty_experience_message)
            )
        } else {
            ResumeCertificationContent(certifications = certifications)
        }
    }
}

@Composable
private fun ResumeCertificationContent(
    certifications: List<ResumeCertificationListData>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = screenHeightDp(16.dp),
                bottom = screenHeightDp(36.dp)
            ),
        horizontalArrangement = Arrangement.spacedBy(screenWidthDp(12.dp))
    ) {
        itemsIndexed(certifications) { index, certification ->
            if (index == 0) {
                Spacer(modifier = Modifier.width(screenWidthDp(20.dp)))
            }
            ResumeCertificationSmallCard(certification)
            if (index == certifications.lastIndex) {
                Spacer(modifier = Modifier.width(screenWidthDp(20.dp)))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ResumeEmptyCertificationSectionPreview() {
    CERTITheme {
        ResumeListSection(
            title = stringResource(R.string.resume_section_experience_title),
            onClick = { },
            resumeListItems = listOf()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ResumeCertificationSectionPreview() {
    CERTITheme {
        ResumeCertificationSection(
            title = stringResource(R.string.resume_section_experience_title),
            onClick = { },
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
