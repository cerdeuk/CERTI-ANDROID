package org.sopt.certi.presentation.ui.my

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.presentation.ui.my.component.MyPageMenuItem
import org.sopt.certi.presentation.ui.my.component.MyPageProfile
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun MyPageMainRoute() {
}

@Composable
fun MyPageMainScreen(
    name: String,
    email: String,
    jobList: List<String>,
    onPersonalInfoClick: () -> Unit,
    onSchoolClick: () -> Unit,
    onCertificationClick: () -> Unit,
    onSettingClick: () -> Unit,
    onQuestionsClick: () -> Unit
) {
    Column {
        MyPageProfile(
            name = name,
            email = email,
            jobList = jobList
        )
        LazyColumn(
            modifier = Modifier
                .background(CertiTheme.colors.gray0)
                .padding(horizontal = screenHeightDp(20.dp), vertical = screenHeightDp(24.dp)),
            verticalArrangement = Arrangement.spacedBy(screenHeightDp(12.dp))
        ) {
            item {
                MyPageMenuItem(
                    iconId = R.drawable.ic_person_24,
                    title = stringResource(R.string.mypage_menu_persional_info),
                    description = stringResource(R.string.mypage_menu_persional_info_description),
                    onClick = onPersonalInfoClick
                )
            }
            item {
                MyPageMenuItem(
                    iconId = R.drawable.ic_school_24,
                    title = stringResource(R.string.mypage_menu_school),
                    description = stringResource(R.string.mypage_menu_school_description),
                    onClick = onSchoolClick
                )
            }
            item {
                MyPageMenuItem(
                    iconId = R.drawable.ic_pencil_24,
                    title = stringResource(R.string.mypage_menu_certification),
                    description = stringResource(R.string.mypage_menu_certification_description),
                    onClick = onCertificationClick
                )
            }
            item {
                MyPageMenuItem(
                    iconId = R.drawable.ic_setting_24,
                    title = stringResource(R.string.mypage_menu_setting),
                    description = stringResource(R.string.mypage_menu_setting_description),
                    onClick = onSettingClick
                )
            }
            item {
                MyPageMenuItem(
                    iconId = R.drawable.ic_message_24,
                    title = stringResource(R.string.mypage_menu_questions),
                    description = stringResource(R.string.mypage_menu_questions_description),
                    onClick = onQuestionsClick
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MyPageMainPreview() {
    CERTITheme {
        MyPageMainScreen(
            name = "김서티",
            email = "certification@gmail.com",
            jobList = listOf("경영/사무", "무역/유통", "마케팅/광고/홍보"),
            onPersonalInfoClick = {},
            onSchoolClick = {},
            onCertificationClick = {},
            onSettingClick = {},
            onQuestionsClick = {}
        )
    }
}
