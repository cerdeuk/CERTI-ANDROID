package org.sopt.certi.presentation.ui.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.sopt.certi.R
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.domain.model.user.CertificationCount
import org.sopt.certi.domain.model.user.MyPageInfo
import org.sopt.certi.presentation.ui.mypage.component.MyPageCertMenuItem
import org.sopt.certi.presentation.ui.mypage.component.MyPageMenuItem
import org.sopt.certi.presentation.ui.mypage.component.MyPageProfile
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun MyPageMainRoute(
    padding: PaddingValues,
    navigateToPersonalInfo: () -> Unit,
    navigateToSchoolInfo: () -> Unit,
    navigateToCertManage: () -> Unit,
    navigateToSetting: () -> Unit,
    navigateToQuestion: () -> Unit,
    viewModel: MyPageMainViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
        viewModel.loadMyPageData()
    }

    MyPageMainScreen(
        uiState = uiState.myPageInfo,
        onPersonalInfoClick = navigateToPersonalInfo,
        onSchoolInfoClick = navigateToSchoolInfo,
        onCertManageClick = navigateToCertManage,
        onSettingClick = navigateToSetting,
        onQuestionsClick = navigateToQuestion,
        modifier = Modifier.padding(padding)
    )
}

@Composable
fun MyPageMainScreen(
    uiState: MyPageInfo,
    onPersonalInfoClick: () -> Unit,
    onSchoolInfoClick: () -> Unit,
    onCertManageClick: () -> Unit,
    onSettingClick: () -> Unit,
    onQuestionsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        MyPageProfile(
            name = uiState.nickname,
            email = uiState.email,
            jobList = uiState.jobs,
            profileImageUri = if (uiState.profileImageUrl.isNotBlank())uiState.profileImageUrl.toUri() else null
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(CertiTheme.colors.gray0)
                .padding(horizontal = screenHeightDp(20.dp), vertical = screenHeightDp(20.dp)),
            verticalArrangement = Arrangement.spacedBy(screenHeightDp(16.dp))
        ) {
            item {
                MyPageCertMenuItem(
                    acquireExpectedCertCount = uiState.certificationCount.planned,
                    acquiredCertCount = uiState.certificationCount.acquired,
                    favoriteCertCount = uiState.certificationCount.favorite,
                    onClick = onCertManageClick
                )
            }
            item {
                MyPageMenuItem(
                    iconId = R.drawable.ic_person_24,
                    title = stringResource(R.string.mypage_personal_info),
                    description = stringResource(R.string.mypage_personal_info_description),
                    onClick = onPersonalInfoClick
                )
            }
            item {
                MyPageMenuItem(
                    iconId = R.drawable.ic_school_24,
                    title = stringResource(R.string.mypage_school_info),
                    description = stringResource(R.string.mypage_school_info_description),
                    onClick = onSchoolInfoClick
                )
            }
            item {
                MyPageMenuItem(
                    iconId = R.drawable.ic_edit_24,
                    title = stringResource(R.string.mypage_setting),
                    description = stringResource(R.string.mypage_setting_description),
                    onClick = onSettingClick
                )
            }
            item {
                MyPageMenuItem(
                    iconId = R.drawable.ic_message_24,
                    title = stringResource(R.string.mypage_questions),
                    description = stringResource(R.string.mypage_questions_description),
                    onClick = onQuestionsClick
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MyPageMainPreview() {
    val uiState = MyPageInfo(
        nickname = "김서티",
        email = "certification@gamil.com",
        profileImageUrl = "",
        jobs = listOf("경영/사무", "무역/유통", "마케팅/광고/홍보"),
        certificationCount = CertificationCount(
            planned = 0,
            acquired = 0,
            favorite = 0
        )
    )

    CERTITheme {
        MyPageMainScreen(
            uiState = uiState,
            onPersonalInfoClick = {},
            onSchoolInfoClick = {},
            onCertManageClick = {},
            onSettingClick = {},
            onQuestionsClick = {}
        )
    }
}
