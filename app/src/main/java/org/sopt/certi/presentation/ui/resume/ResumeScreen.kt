package org.sopt.certi.presentation.ui.resume

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import org.sopt.certi.core.util.showIf
import org.sopt.certi.domain.model.ResumeCertificationListData
import org.sopt.certi.domain.model.ResumeListData
import org.sopt.certi.presentation.ui.resume.component.ResumeCertificationSection
import org.sopt.certi.presentation.ui.resume.component.ResumeListSection
import org.sopt.certi.presentation.ui.resume.component.ResumeProfile
import org.sopt.certi.ui.theme.CERTITheme

@Composable
fun ResumeRoute(
    padding: PaddingValues,
    navigateToMyCert: () -> Unit,
    navigateToWorkExperience: () -> Unit,
    navigateToActivities: () -> Unit,
    viewModel: ResumeViewModel = hiltViewModel()
) {
    val dummyCategoryList = listOf("IT/인터넷")
    val dummyAcquiredCertificationList = listOf(
        ResumeCertificationListData(
            name = "GTQ 1급 (그래픽기술자격)",
            year = 2025,
            month = 7,
            day = 3,
            cardImageUrl = "https://sopt-certi-bucket.s3.ap-northeast-2.amazonaws.com/certi/color%3Dblue.png",
            tags = listOf("태그", "태그", "태그")
        ),
        ResumeCertificationListData(
            name = "GTQ 1급 (그래픽기술자격)",
            year = 2025,
            month = 7,
            day = 3,
            cardImageUrl = "https://sopt-certi-bucket.s3.ap-northeast-2.amazonaws.com/certi/color%3Dwhite.png",
            tags = listOf("태그", "태그", "태그")
        ),
        ResumeCertificationListData(
            name = "GTQ 1급 (그래픽기술자격)",
            year = 2025,
            month = 7,
            day = 3,
            cardImageUrl = "https://sopt-certi-bucket.s3.ap-northeast-2.amazonaws.com/certi/color%3Dyellow.png",
            tags = listOf("태그", "태그", "태그")
        ),
        ResumeCertificationListData(
            name = "GTQ 1급 (그래픽기술자격)",
            year = 2025,
            month = 7,
            day = 3,
            cardImageUrl = "https://sopt-certi-bucket.s3.ap-northeast-2.amazonaws.com/certi/color%3Dblue.png",
            tags = listOf("태그", "태그", "태그")
        )
    )
    val dummyExperiences = listOf(
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

    ResumeScreen(
        jobCategory = dummyCategoryList,
        acquiredCertificationList = dummyAcquiredCertificationList,
        experiences = dummyExperiences,
        activities = listOf(),
        onCertificationClick = {},
        navigateToMyCert = navigateToMyCert,
        navigateToWorkExperience = navigateToWorkExperience,
        navigateToActivities = navigateToActivities,
        modifier = Modifier.padding(padding)
    )
}

@Composable
fun ResumeScreen(
    jobCategory: List<String>,
    acquiredCertificationList: List<ResumeCertificationListData>,
    experiences: List<ResumeListData>,
    activities: List<ResumeListData>,
    onCertificationClick: () -> Unit,
    navigateToMyCert: () -> Unit,
    navigateToWorkExperience: () -> Unit,
    navigateToActivities: () -> Unit,
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
                ResumeCertificationSection(
                    title = stringResource(R.string.resume_section_certification_title),
                    onClick = navigateToMyCert,
                    onCertificationClick = onCertificationClick,
                    acquiredCertificationList = acquiredCertificationList
                )
            }

            item {
                Image(
                    painter = painterResource(R.drawable.img_resume_line),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                ResumeListSection(
                    title = stringResource(R.string.resume_section_experience_title),
                    onClick = navigateToWorkExperience,
                    resumeListItems = experiences
                )
            }

            item {
                Image(
                    painter = painterResource(R.drawable.img_resume_line),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                ResumeListSection(
                    title = stringResource(R.string.resume_section_activity_title),
                    onClick = navigateToActivities,
                    resumeListItems = activities
                )
            }

            item {
                Spacer(
                    modifier = Modifier
                        .height(screenHeightDp(52.dp))
                        .showIf(activities.isNotEmpty())
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewResumeScreen() {
    val dummyAcquiredCertificationList = listOf(
        ResumeCertificationListData(
            name = "GTQ 1급 (그래픽기술자격)",
            year = 2025,
            month = 7,
            day = 3,
            cardImageUrl = "https://sopt-certi-bucket.s3.ap-northeast-2.amazonaws.com/certi/color%3Dblue.png",
            tags = listOf("태그", "태그", "태그")
        ),
        ResumeCertificationListData(
            name = "GTQ 1급 (그래픽기술자격)",
            year = 2025,
            month = 7,
            day = 3,
            cardImageUrl = "https://sopt-certi-bucket.s3.ap-northeast-2.amazonaws.com/certi/color%3Dwhite.png",
            tags = listOf("태그", "태그", "태그")
        ),
        ResumeCertificationListData(
            name = "GTQ 1급 (그래픽기술자격)",
            year = 2025,
            month = 7,
            day = 3,
            cardImageUrl = "https://sopt-certi-bucket.s3.ap-northeast-2.amazonaws.com/certi/color%3Dyellow.png",
            tags = listOf("태그", "태그", "태그")
        ),
        ResumeCertificationListData(
            name = "GTQ 1급 (그래픽기술자격)",
            year = 2025,
            month = 7,
            day = 3,
            cardImageUrl = "https://sopt-certi-bucket.s3.ap-northeast-2.amazonaws.com/certi/color%3Dblue.png",
            tags = listOf("태그", "태그", "태그")
        )
    )
    val dummyExperiences = listOf(
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

    CERTITheme {
        ResumeScreen(
            jobCategory = listOf("IT/인터넷", "경영/사무", "경영/사무"),
            acquiredCertificationList = dummyAcquiredCertificationList,
            experiences = dummyExperiences,
            activities = listOf(),
            onCertificationClick = {},
            navigateToMyCert = {},
            navigateToWorkExperience = {},
            navigateToActivities = {}
        )
    }
}
