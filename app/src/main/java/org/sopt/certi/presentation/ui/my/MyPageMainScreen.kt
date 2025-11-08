package org.sopt.certi.presentation.ui.my

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.sopt.certi.R
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.presentation.ui.my.component.MyPageCertMenuItem
import org.sopt.certi.presentation.ui.my.component.MyPageMenuItem
import org.sopt.certi.presentation.ui.my.component.MyPageProfile
import org.sopt.certi.presentation.ui.my.state.MyPageUiSate
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

    MyPageMainScreen(
        uiState = uiState,
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
    uiState: MyPageUiSate,
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
            name = uiState.name,
            email = uiState.email,
            jobList = uiState.jobList
        )
        LazyColumn(
            modifier = Modifier
                .background(CertiTheme.colors.gray0)
                .padding(horizontal = screenHeightDp(20.dp), vertical = screenHeightDp(20.dp)),
            verticalArrangement = Arrangement.spacedBy(screenHeightDp(16.dp))
        ) {
            item {
                MyPageCertMenuItem(
                    acquireExpectedCertCount = uiState.acquireExpectedCertCount,
                    acquiredCertCount = uiState.acquiredCertCount,
                    favoriteCertCount = uiState.favoriteCertCount,
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
    val uiState by remember {
        mutableStateOf(
            MyPageUiSate(
                name = "김서티",
                email = "certification@gmail.com",
                jobList = listOf("경영/사무", "무역/유통", "마케팅/광고/홍보"),
                acquireExpectedCertCount = 0,
                acquiredCertCount = 0,
                favoriteCertCount = 0
            )
        )
    }

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
