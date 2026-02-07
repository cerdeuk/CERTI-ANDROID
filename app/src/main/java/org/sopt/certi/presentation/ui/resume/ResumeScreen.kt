package org.sopt.certi.presentation.ui.resume

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import org.sopt.certi.R
import org.sopt.certi.core.component.topbar.DDayoTopBar
import org.sopt.certi.core.state.UiState
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.domain.model.ActivityData
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.domain.model.user.UserInfoData
import org.sopt.certi.presentation.ui.resume.component.ResumeCertificationSection
import org.sopt.certi.presentation.ui.resume.component.ResumeListSection
import org.sopt.certi.presentation.ui.resume.component.ResumeProfile
import org.sopt.certi.presentation.ui.resume.component.card.FlipCardOverlay

@Composable
fun ResumeRoute(
    padding: PaddingValues,
    navigateToMyPage: () -> Unit,
    navigateToMyCert: () -> Unit,
    navigateToWorkExperience: () -> Unit,
    navigateToActivities: () -> Unit,
    viewModel: ResumeViewModel = hiltViewModel()
) {
    val uiState by viewModel.resumeUiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.getResumeData()
    }

    if (uiState.selectedCertDetail is UiState.Success) {
        FlipCardOverlay(
            certificationData = (uiState.selectedCertDetail as UiState.Success<CertificationData>).data,
            nickname = viewModel.getUserName() ?: stringResource(R.string.resume_certification_card_nickname),
            onDismiss = viewModel::closeCertificationDetailModal
        )
    }

    when (uiState.loadState) {
        is UiState.Success -> ResumeScreen(
            userInfo = (uiState.userInfoLoadState as UiState.Success<UserInfoData>).data,
            acquiredCertificationList = (uiState.acquiredCertificationListLoadState as UiState.Success<List<CertificationData>>).data.toImmutableList(),
            experienceList = (uiState.experienceListLoadState as UiState.Success<List<ActivityData>>).data.toImmutableList(),
            activityList = (uiState.activityListLoadState as UiState.Success<List<ActivityData>>).data.toImmutableList(),
            onCertificationClick = viewModel::onCertificationClick,
            navigateToMyPage = navigateToMyPage,
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
    userInfo: UserInfoData,
    navigateToMyPage: () -> Unit,
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
        DDayoTopBar()

        LazyColumn {
            item {
                ResumeProfile(
                    name = userInfo.name,
                    university = userInfo.university,
                    major = userInfo.major,
                    birthday = userInfo.birthday ?: stringResource(R.string.resume_certification_birthday_empty),
                    profileImageUrl = userInfo.profileImageUrl,
                    navigateToMyPage = navigateToMyPage,
                    modifier = Modifier
                        .padding(horizontal = screenWidthDp(20.dp))
                        .padding(top = screenHeightDp(16.dp))
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
                    emptyText = stringResource(R.string.resume_empty_experience_message),
                    resumeListItems = experienceList,
                    bottomPadding = screenHeightDp(8.dp)
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
                    emptyText = stringResource(R.string.resume_empty_activity_message),
                    resumeListItems = activityList,
                    bottomPadding = screenHeightDp(if (activityList.isNotEmpty()) screenHeightDp(45.dp) else 23.dp)
                )
            }
        }
    }
}
