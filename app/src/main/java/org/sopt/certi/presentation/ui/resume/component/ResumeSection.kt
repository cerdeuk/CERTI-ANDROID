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
import org.sopt.certi.domain.model.ResumeListData
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun ResumeSection(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    horizontalPadding: Float = 0.055f,
    content: @Composable (horizontalPadding: Float) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = screenHeightDp(0.046f))
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = screenWidthDp(horizontalPadding))
                .fillMaxWidth(),
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
        content(horizontalPadding)
    }
}

@Preview(showBackground = true)
@Composable
fun ResumeEmptyContentPreview() {
    CERTITheme {
        ResumeSection(
            title = stringResource(R.string.resume_section_experience_title),
            onClick = { },
            content = { horizontalPadding ->
                ResumeEmptyContent(
                    text = stringResource(R.string.resume_empty_experience_message),
                    modifier = Modifier.padding(horizontal = screenWidthDp(horizontalPadding))
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ResumeListContentPreview() {
    CERTITheme {
        ResumeSection(
            title = stringResource(R.string.resume_section_experience_title),
            onClick = { },
            content = { horizontalPadding ->
                ResumeListContent(
                    modifier = Modifier.padding(horizontal = screenWidthDp(horizontalPadding)),
                    resumeListItems = listOf(
                        ResumeListData(
                            startAt = "2021.11",
                            endAt = "2022.01",
                            organization = "서티그룹",
                            role = "패션디자이너 인턴",
                            description = "트렌드 리서치 및 소재 조사"
                        ),
                        ResumeListData(
                            startAt = "2021.11",
                            endAt = "2022.01",
                            organization = "서티그룹",
                            role = "패션디자이너 인턴",
                            description = "트렌드 리서치 및 소재 조사"
                        ),
                        ResumeListData(
                            startAt = "2021.11",
                            endAt = "2022.01",
                            organization = "서티그룹",
                            role = "패션디자이너 인턴",
                            description = "트렌드 리서치 및 소재 조사"
                        ),
                        ResumeListData(
                            startAt = "2021.11",
                            endAt = "2022.01",
                            organization = "서티그룹",
                            role = "패션디자이너 인턴",
                            description = "트렌드 리서치 및 소재 조사"
                        )
                    )
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ResumeCertificationPreview() {
    CERTITheme {
        ResumeSection(
            title = stringResource(R.string.resume_section_certification_title),
            onClick = { },
            content = { horizontalPadding ->
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
                    ),
                    horizontalPadding = horizontalPadding
                )
            }
        )
    }
}
