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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
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
import org.sopt.certi.presentation.ui.resume.sideEffect.ResumeSideEffect

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
    var showDialog by remember { mutableStateOf(false) }

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
                ResumeSideEffect.ShowCertificationDetailModal -> showDialog = true
            }
        }
    }

    if (showDialog) {
        FlipCardOverlay(
            certificationData = (uiState.selectedCertDetail as UiState.Success<CertificationData>).data,
            userInfo = userInfo,
            onDismiss = { showDialog = false }
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
