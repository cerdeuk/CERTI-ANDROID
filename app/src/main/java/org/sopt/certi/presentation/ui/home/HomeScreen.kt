package org.sopt.certi.presentation.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.component.topbar.CertiTopBar
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.domain.model.FavoriteCertificationData
import org.sopt.certi.domain.model.PreCertificationData
import org.sopt.certi.domain.model.RecommendedCertificationData
import org.sopt.certi.domain.model.UserInfoData
import org.sopt.certi.presentation.ui.home.component.FavoriteCertificationListSection
import org.sopt.certi.presentation.ui.home.component.PreCertificationListSection
import org.sopt.certi.presentation.ui.home.component.RecommendedCertificationListSection
import org.sopt.certi.presentation.ui.home.component.UserInfoSection
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeRoute(
    padding: PaddingValues,
    viewModel: HomeViewModel = hiltViewModel()
) {
    var favoriteIds by remember { mutableStateOf(setOf<Int>()) }
    val isFavorite: (FavoriteCertificationData) -> Boolean = { data ->
        favoriteIds.contains(data.certificationId)
    }
    val onFavoriteClicked: (FavoriteCertificationData) -> Unit = { data ->
        favoriteIds = if (favoriteIds.contains(data.certificationId)) {
            favoriteIds - data.certificationId
        } else {
            favoriteIds + data.certificationId
        }
    }

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
            name = "시각디자인산업기사",
            score = 90,
            categories = listOf("컴퓨터공학", "재무/세무/IR", "재무/세무/IR")
        ),
        RecommendedCertificationData(
            name = "정보처리기사",
            score = 90,
            categories = listOf("컴퓨터공학", "재무/세무/IR", "재무/세무/IR")
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
    val favoriteCertificationList = listOf(
        FavoriteCertificationData(
            certificationId = 1,
            certificationName = "정보처리기사",
            testType = "실기형",
            agencyName = "한국산업인력공단",
            qualificationType = "국가기술자격"
        ),
        FavoriteCertificationData(
            certificationId = 2,
            certificationName = "시각디자인산업기사",
            testType = "실기형",
            agencyName = "한국산업인력공단",
            qualificationType = "국가기술자격"
        )
    )

    HomeScreen(
        userInfo = userInfo,
        recommendedList = recommendedList,
        preCertificationList = preCertificationList,
        favoriteCertificationList = favoriteCertificationList,
        isFavorite = isFavorite,
        onFavoriteClicked = onFavoriteClicked,
        modifier = Modifier.padding(padding)
    )
}

@Composable
fun HomeScreen(
    userInfo: UserInfoData,
    recommendedList: List<RecommendedCertificationData>,
    preCertificationList: List<PreCertificationData>,
    favoriteCertificationList: List<FavoriteCertificationData>,
    isFavorite: (FavoriteCertificationData) -> Boolean = { true },
    onFavoriteClicked: (FavoriteCertificationData) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(CertiTheme.colors.white)
    ) {
        CertiTopBar()

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            contentPadding = PaddingValues(top = screenHeightDp(12.dp)),
            verticalArrangement = Arrangement.spacedBy(screenHeightDp(36.dp))
        ) {
            item {
                UserInfoSection(userInfoData = userInfo)
            }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = screenWidthDp(20.dp)),
                    verticalArrangement = Arrangement.spacedBy(screenHeightDp(16.dp))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween

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
            }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(screenHeightDp(16.dp))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = screenHeightDp(20.dp)),
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
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = screenHeightDp(36.dp)),
                    verticalArrangement = Arrangement.spacedBy(screenHeightDp(16.dp))
                ) {
                    Text(
                        text = stringResource(R.string.home_favorite_title),
                        style = CertiTheme.typography.subtitle.semibold_20,
                        color = CertiTheme.colors.gray600,
                        modifier = Modifier.padding(start = screenWidthDp(20.dp))
                    )
                    FavoriteCertificationListSection(
                        favoriteCertificationList = favoriteCertificationList,
                        isFavorite = isFavorite,
                        onFavoriteClicked = onFavoriteClicked,
                        modifier = modifier
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeScreen() {
    var favoriteIds by remember { mutableStateOf(setOf(1)) }

    val favoriteCertificationList = listOf(
        FavoriteCertificationData(
            certificationId = 1,
            certificationName = "정보처리기사",
            testType = "실기형",
            agencyName = "한국산업인력공단",
            qualificationType = "국가기술자격"
        ),
        FavoriteCertificationData(
            certificationId = 2,
            certificationName = "시각디자인산업기사",
            testType = "실기형",
            agencyName = "한국산업인력공단",
            qualificationType = "국가기술자격"
        )
    )

    CERTITheme {
        HomeScreen(
            userInfo = UserInfoData(
                name = "김서티",
                university = "솝트대학교",
                major = "경영학과",
                track = "인문계열",
                percentage = 57,
                category = listOf("경영/사무", "무역/유통", "마케팅/광고/홍보")
            ),
            recommendedList = listOf(
                RecommendedCertificationData(
                    name = "OPIc",
                    score = 90,
                    categories = listOf("컴퓨터공학", "재무/세무/IR", "재무/세무/IR")
                ),
                RecommendedCertificationData(
                    name = "시각디자인산업기사",
                    score = 90,
                    categories = listOf("컴퓨터공학", "재무/세무/IR", "재무/세무/IR")
                ),
                RecommendedCertificationData(
                    name = "정보처리기사",
                    score = 90,
                    categories = listOf("컴퓨터공학", "재무/세무/IR", "재무/세무/IR")
                )
            ),
            preCertificationList = listOf(
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
                    certificationName = "정보처리기사",
                    averagePeriod = "4개월",
                    testDate = "2025.06.10",
                    agencyName = "한국산업인력공단",
                    iconIndex = 1
                )
            ),
            favoriteCertificationList = favoriteCertificationList,
            isFavorite = { data -> favoriteIds.contains(data.certificationId) },
            onFavoriteClicked = { data ->
                favoriteIds = if (favoriteIds.contains(data.certificationId)) {
                    favoriteIds - data.certificationId
                } else {
                    favoriteIds + data.certificationId
                }
            }
        )
    }
}
