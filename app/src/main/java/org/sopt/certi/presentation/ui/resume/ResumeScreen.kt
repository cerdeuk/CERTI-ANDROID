package org.sopt.certi.presentation.ui.resume

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.sopt.certi.R
import org.sopt.certi.core.component.topbar.CertiTopBar
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.domain.model.ResumeCertificationListData
import org.sopt.certi.domain.model.ResumeListData
import org.sopt.certi.presentation.ui.resume.component.ResumeCertificationSection
import org.sopt.certi.presentation.ui.resume.component.ResumeListSection
import org.sopt.certi.presentation.ui.resume.component.ResumeProfile
import org.sopt.certi.ui.theme.CERTITheme

@Composable
fun ResumeRoute(
    padding: PaddingValues,
    viewModel: ResumeViewModel = hiltViewModel()
) {
    ResumeScreen(
        jobCategory = listOf("IT/인터넷"),
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
            )
        ),
        experiences = listOf(
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
        ),
        activities = listOf(),
        modifier = Modifier.padding(padding)
    )
}

@Composable
fun ResumeScreen(
    jobCategory: List<String>,
    certifications: List<ResumeCertificationListData>,
    experiences: List<ResumeListData>,
    activities: List<ResumeListData>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        CertiTopBar()

        LazyColumn {
            item {
                ResumeProfile(
                    modifier = Modifier
                        .padding(horizontal = screenWidthDp(20.dp))
                        .padding(top = screenHeightDp(32.dp)),
                    category = jobCategory
                )
            }

            item {
                Column {
                    ResumeCertificationSection(
                        title = stringResource(R.string.resume_section_certification_title),
                        onClick = { },
                        certifications = certifications
                    )
                    Image(
                        painter = painterResource(R.drawable.img_resume_line),
                        contentDescription = null
                    )
                }
            }

            item {
                Column {
                    ResumeListSection(
                        title = stringResource(R.string.resume_section_experience_title),
                        onClick = { },
                        resumeListItems = experiences
                    )
                    Image(
                        painter = painterResource(R.drawable.img_resume_line),
                        contentDescription = null
                    )
                }
            }

            item {
                Column {
                    ResumeListSection(
                        title = stringResource(R.string.resume_section_activity_title),
                        onClick = { },
                        resumeListItems = activities
                    )
                    if (!activities.isEmpty()) {
                        Spacer(modifier = Modifier.height(screenHeightDp(52.dp)))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewResumeScreen() {
    CERTITheme {
        ResumeScreen(
            jobCategory = listOf("IT/인터넷", "경영/사무", "경영/사무"),
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
            ),
            experiences = listOf(
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
            ),
            activities = listOf()
        )
    }
}
