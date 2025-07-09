package org.sopt.certi.presentation.ui.resume.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
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
    modifier: Modifier = Modifier,
    certifications: List<ResumeCertificationListData>
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = screenHeightDp(0.046f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = screenWidthDp(0.055f)),
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

@Preview(showBackground = true)
@Composable
fun ResumeEmptyCertificationSectionPreview() {
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
fun ResumeCertificationSectionPreview() {
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
