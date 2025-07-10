package org.sopt.certi.presentation.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.component.topbar.CertiTopBar
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.domain.model.PreCertificationData
import org.sopt.certi.domain.model.RecommendedCertificationData
import org.sopt.certi.domain.model.UserInfoData
import org.sopt.certi.presentation.ui.home.component.PreCertificationListSection
import org.sopt.certi.presentation.ui.home.component.RecommendedCertificationListSection
import org.sopt.certi.presentation.ui.home.component.UserInfoSection
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun HomeRoute(
    padding: PaddingValues,
    viewModel: HomeViewModel
) {
    val userInfo = UserInfoData(
        name = "김서티",
        university = "솝트대학교",
        major = "경영학과",
        track = "인문계열",
        category = listOf("경영/사무", "무역/유통", "마케팅/광고/홍보")
    )
    val recommendedList = listOf(
        RecommendedCertificationData(
            name = "OPIc",
            score = 90,
            categories = listOf("컴퓨터공학", "재무/세무/IR", "재무/세무/IR")
        ),
        RecommendedCertificationData(
            name = "정보처리기사",
            score = 90,
            categories = listOf("컴퓨터공학", "재무/세무/IR", "재무/세무/IR")
        ),
        RecommendedCertificationData(
            name = "시각디자인산업기사",
            score = 90,
            categories = listOf("재무/세무/IR", "재무/세무/IR", "재무/세무/IR")
        )
    )
    val preCertificationList = listOf(
        PreCertificationData(
            certificationId = 1,
            certificationName = "시각디자인산업기사",
            averagePeriod = "3개월",
            testDate = "2025.05.27",
            agencyName = "한국산업인력공단",
            iconIndex = 0
        ),
        PreCertificationData(
            certificationId = 2,
            certificationName = "시각디자인산업기사",
            averagePeriod = "3개월",
            testDate = "2025.05.27",
            agencyName = "한국산업인력공단",
            iconIndex = 1
        ),
        PreCertificationData(
            certificationId = 3,
            certificationName = "시각디자인산업기사",
            averagePeriod = "3개월",
            testDate = "2025.05.27",
            agencyName = "한국산업인력공단",
            iconIndex = 2
        )
    )

    HomeScreen(
        userInfo = userInfo,
        recommendedList = recommendedList,
        preCertificationList = preCertificationList,
        modifier = Modifier.padding(padding)
    )
}

@Composable
fun HomeScreen(
    userInfo: UserInfoData,
    recommendedList: List<RecommendedCertificationData>,
    preCertificationList: List<PreCertificationData>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(CertiTheme.colors.white)
    ) {
        CertiTopBar()
        UserInfoSection(userInfoData = userInfo)
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,

            ) {
                Text(
                    text = stringResource(R.string.home_recommend_title, userInfo.name),
                    style = CertiTheme.typography.subtitle.semibold_20,
                    color = CertiTheme.colors.gray600
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrowright_36),
                    contentDescription = null,
                    tint = CertiTheme.colors.gray600,
                    modifier = Modifier
                        .width(screenWidthDp(24.dp))
                        .height(screenHeightDp(24.dp))

                )
            }
            RecommendedCertificationListSection(recommendedList = recommendedList)
        }
        Spacer(modifier = Modifier.height(screenHeightDp(36.dp)))

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.home_pre_certification_title),
                    style = CertiTheme.typography.subtitle.semibold_20,
                    color = CertiTheme.colors.gray600
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrowright_36),
                    contentDescription = null,
                    tint = CertiTheme.colors.gray600,
                    modifier = Modifier
                        .width(screenWidthDp(24.dp))
                        .height(screenHeightDp(24.dp))
                )
            }
            PreCertificationListSection(preCertificationList = preCertificationList)
        }
        Spacer(modifier = Modifier.height(screenHeightDp(36.dp)))

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(R.string.home_favorite_title),
                style = CertiTheme.typography.subtitle.semibold_20,
                color = CertiTheme.colors.gray600
            )

        }
    }
}





@Preview(showBackground = true)
@Composable
private fun PreviewHomeScreen() {
    CERTITheme {
        HomeScreen()
    }
}
