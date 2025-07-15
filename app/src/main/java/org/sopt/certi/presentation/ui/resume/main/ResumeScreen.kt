package org.sopt.certi.presentation.ui.resume.main

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.collections.immutable.persistentListOf
import androidx.lifecycle.flowWithLifecycle
import org.sopt.certi.R
import org.sopt.certi.core.component.topbar.CertiTopBar
import org.sopt.certi.core.state.UiState
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.showIf
import org.sopt.certi.domain.model.ActivityData
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.domain.model.user.UserInfoData
import org.sopt.certi.presentation.ui.resume.component.ResumeCertificationSection
import org.sopt.certi.presentation.ui.resume.component.ResumeListSection
import org.sopt.certi.presentation.ui.resume.component.ResumeProfile
import org.sopt.certi.presentation.ui.resume.component.card.FlipCardOverlay
import org.sopt.certi.presentation.ui.resume.main.sideEffect.ResumeSideEffect
import org.sopt.certi.ui.theme.CERTITheme
import java.time.LocalDate

@Composable
fun ResumeRoute(
    padding: PaddingValues,
    navigateToMyCert: () -> Unit,
    navigateToWorkExperience: () -> Unit,
    navigateToActivities: () -> Unit,
    viewModel: ResumeViewModel = hiltViewModel()
) {
    val uiState by viewModel.resumeUiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current
    val showDialog = remember { mutableStateOf(false) }

    val userInfo = UserInfoData(
        name = "김민지",
        university = "",
        major = ""
    )

    LaunchedEffect(Unit) {
        viewModel.getResumeData()
    }

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycleOwner.lifecycle).collect {
            when (it) {
                is ResumeSideEffect.ShowCertificationDetailModal -> {
                    showDialog.value = true
                }
                ResumeSideEffect.HideCertificationDetailModal -> {
                    showDialog.value = false
                }
            }
        }
    }

    if (showDialog.value) {
        FlipCardOverlay(
            certificationData = (uiState.selectedCertDetail as UiState.Success<CertificationData>).data,
            userInfo = userInfo,
            onDismiss = { viewModel.onCertificationDetailDismiss() }
        )
    }

    when (uiState.loadState) {
        is UiState.Success -> ResumeScreen(
            jobCategory = (uiState.jobCategoryLoadState as UiState.Success<List<String>>).data.toImmutableList(),
            acquiredCertificationList = (uiState.acquiredCertificationListLoadState as UiState.Success<List<CertificationData>>).data.toImmutableList(),
            experienceList = (uiState.experienceListLoadState as UiState.Success<List<ActivityData>>).data.toImmutableList(),
            activityList = (uiState.activityListLoadState as UiState.Success<List<ActivityData>>).data.toImmutableList(),
            onCertificationClick = { certificationId ->
                viewModel.onCertificationClick(certificationId)
            },
            navigateToMyCert = navigateToMyCert,
            navigateToWorkExperience = navigateToWorkExperience,
            navigateToActivities = navigateToActivities,
            modifier = Modifier.padding(padding)
        )
        is UiState.Failure -> {}
        is UiState.Loading -> {}
        is UiState.Empty -> {}
        is UiState.Init -> {}
    }
}

@Composable
fun ResumeScreen(
    jobCategory: ImmutableList<String>,
    acquiredCertificationList: ImmutableList<CertificationData>,
    experienceList: ImmutableList<ActivityData>,
    activityList: ImmutableList<ActivityData>,
    onCertificationClick: (Long) -> Unit,
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
                    resumeListItems = experienceList
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
                    resumeListItems = activityList
                )
            }

            item {
                Spacer(
                    modifier = Modifier
                        .height(screenHeightDp(52.dp))
                        .showIf(activityList.isNotEmpty())
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewResumeScreen() {
    val dummyAcquiredCertificationList = listOf(
        CertificationData(
            certificationId = 1,
            certificationName = "GTQ 1급 (그래픽기술자격)",
            createdAt = LocalDate.now(),
            cardFrontImageUrl = "https://sopt-certi-bucket.s3.ap-northeast-2.amazonaws.com/certi/color%3Dblue.png",
            tags = listOf("태그", "태그", "태그")
        ),
        CertificationData(
            certificationId = 1,
            certificationName = "GTQ 1급 (그래픽기술자격)",
            createdAt = LocalDate.now(),
            cardFrontImageUrl = "https://sopt-certi-bucket.s3.ap-northeast-2.amazonaws.com/certi/color%3Dwhite.png",
            tags = listOf("태그", "태그", "태그")
        ),
        CertificationData(
            certificationId = 1,
            certificationName = "GTQ 1급 (그래픽기술자격)",
            createdAt = LocalDate.now(),
            cardFrontImageUrl = "https://sopt-certi-bucket.s3.ap-northeast-2.amazonaws.com/certi/color%3Dyellow.png",
            tags = listOf("태그", "태그", "태그")
        ),
        CertificationData(
            certificationId = 1,
            certificationName = "GTQ 1급 (그래픽기술자격)",
            createdAt = LocalDate.now(),
            cardFrontImageUrl = "https://sopt-certi-bucket.s3.ap-northeast-2.amazonaws.com/certi/color%3Dblue.png",
            tags = listOf("태그", "태그", "태그")
        )
    )
    val dummyExperiences = listOf(
        ActivityData(
            startAt = "2021.11",
            endAt = "2022.01",
            organization = "서티그룹, 서티그룹, 서티그룹, 서티그룹",
            role = "패션디자이너 인턴, 패션디자이너 인턴",
            description = "트렌드 리서치 및 소재 조사"
        ),
        ActivityData(
            startAt = "2021.11",
            endAt = "2022.01",
            organization = "서티그룹",
            role = "패션디자이너 인턴",
            description = "트렌드 리서치 및 소재 조사"
        )
    )

    CERTITheme {
        ResumeScreen(
            jobCategory = listOf("IT/인터넷", "경영/사무", "경영/사무").toImmutableList(),
            acquiredCertificationList = dummyAcquiredCertificationList.toImmutableList(),
            experienceList = dummyExperiences.toImmutableList(),
            activityList = persistentListOf(),
            onCertificationClick = {},
            navigateToMyCert = {},
            navigateToWorkExperience = {},
            navigateToActivities = {}
        )
    }
}
