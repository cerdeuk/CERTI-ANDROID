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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.component.topbar.CertiTopBar
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.domain.model.UserInfoData
import org.sopt.certi.presentation.ui.home.component.FavoriteCertificationListSection
import org.sopt.certi.presentation.ui.home.component.PreCertificationListSection
import org.sopt.certi.presentation.ui.home.component.RecommendedCertificationListSection
import org.sopt.certi.presentation.ui.home.component.UserInfoSection
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.collections.immutable.toImmutableList
import org.sopt.certi.core.component.section.CertiEmptySection
import org.sopt.certi.core.state.UiState
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.domain.model.CertificationData
import org.sopt.certi.presentation.ui.home.state.HomeUiState

@Composable
fun HomeRoute(
    padding: PaddingValues,
    navigateToCertRecommend: () -> Unit,
    navigateToPreCerti: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.homeUiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getUserInfo()
        viewModel.getRecommendedList()
        viewModel.getPreCertList()
    }

    LaunchedEffect(uiState.isFavorite) {
        viewModel.getFavoriteList(uiState.isFavorite)
    }

    val userInfo = UserInfoData(
        name = "김서티",
        university = "솝트대학교",
        major = "경영학과"
    )
    val recommendedList = listOf(
        CertificationData(
            certificationId = 1,
            certificationName = "OPIc",
            recommendScore = 90,
            tags = listOf("컴퓨터공학", "재무/세무/IR", "재무/세무/IR")
        ),
        CertificationData(
            certificationId = 2,
            certificationName = "시각디자인산업기사",
            recommendScore = 90,
            tags = listOf("컴퓨터공학", "재무/세무/IR", "재무/세무/IR")
        ),
        CertificationData(
            certificationId = 3,
            certificationName = "정보처리기사",
            recommendScore = 90,
            tags = listOf("컴퓨터공학", "재무/세무/IR", "재무/세무/IR")
        )
    )
    val preCertificationList = listOf(
        CertificationData(
            certificationId = 1,
            certificationName = "시각디자인산업기사",
            averagePeriod = "3개월",
            nearestTestDate = "2025.05.27",
            agencyName = "한국산업인력공단",
            iconIndex = 0
        ),
        CertificationData(
            certificationId = 2,
            certificationName = "시각디자인산업기사",
            averagePeriod = "3개월",
            nearestTestDate = "2025.05.27",
            agencyName = "한국산업인력공단",
            iconIndex = 1
        ),
        CertificationData(
            certificationId = 3,
            certificationName = "시각디자인산업기사",
            averagePeriod = "3개월",
            nearestTestDate = "2025.05.27",
            agencyName = "한국산업인력공단",
            iconIndex = 2
        )
    )
    val favoriteCertificationList = listOf(
        CertificationData(
            certificationId = 1,
            certificationName = "정보처리기사",
            testType = "실기형",
            agencyName = "한국산업인력공단",
            certificationType = "국가기술자격"
        ),
        CertificationData(
            certificationId = 2,
            certificationName = "시각디자인산업기사",
            testType = "실기형",
            agencyName = "한국산업인력공단",
            certificationType = "국가기술자격"
        )
    )
    when (uiState.loadState) {
        is UiState.Success -> HomeScreen(
            homeUiState = uiState,
            userInfo = (uiState.userInfoLoadState as UiState.Success).data,
            recommendedList = (uiState.recommendedListLoadState as UiState.Success).data.toImmutableList(),
            preCertificationList = (uiState.preCertificationListLoadState as UiState.Success).data.toImmutableList(),
            favoriteCertificationList = (uiState.favoriteListLoadState as UiState.Success).data.toImmutableList(),
            onFavoriteClicked = viewModel::onFavoriteClicked,
            onDetailClick = { },
            navigateToCertRecommend = { },
            navigateToPreCerti = navigateToPreCerti,
            modifier = Modifier.padding(padding)
        )
        is UiState.Failure -> {}
        is UiState.Loading -> {}
        is UiState.Empty -> {}
        is UiState.Init -> {}
    }
}

@Composable
fun HomeScreen(
    homeUiState: HomeUiState,
    userInfo: UserInfoData,
    recommendedList: List<CertificationData>,
    preCertificationList: List<CertificationData>,
    favoriteCertificationList: List<CertificationData>,
    onFavoriteClicked: (Long) -> Unit,
    onDetailClick: () -> Unit,
    navigateToCertRecommend: () -> Unit,
    navigateToPreCerti: () -> Unit,
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
                .fillMaxSize(),
            contentPadding = PaddingValues(top = screenHeightDp(12.dp))
        ) {
            item {
                UserInfoSection(userInfoData = userInfo)
                Spacer(modifier = Modifier.height(screenHeightDp(36.dp)))
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
                                .noRippleClickable { navigateToCertRecommend() }
                        )
                    }
                    RecommendedCertificationListSection(recommendedList = recommendedList, onCertificationClick = { })
                    Spacer(modifier = Modifier.height(screenHeightDp(20.dp)))
                }
            }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
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
                                .noRippleClickable { navigateToPreCerti() }
                        )
                    }
                    if (preCertificationList.isEmpty()) {
                        CertiEmptySection(
                            text = stringResource(id = R.string.home_pre_certification_empty),
                            modifier = Modifier
                                .padding(horizontal = screenWidthDp(80.dp))
                        )
                    } else {
                        Spacer(modifier = Modifier.height(screenHeightDp(16.dp)))
                        PreCertificationListSection(
                            preCertificationList = preCertificationList,
                            onDetailClick = onDetailClick
                        )
                        Spacer(modifier = Modifier.height(screenHeightDp(36.dp)))
                    }
                }
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.home_favorite_title),
                        style = CertiTheme.typography.subtitle.semibold_20,
                        color = CertiTheme.colors.gray600,
                        modifier = Modifier.padding(start = screenWidthDp(20.dp))
                    )
                    if (favoriteCertificationList.isEmpty()) {
                        CertiEmptySection(
                            text = stringResource(id = R.string.home_favorite_empty),
                            modifier = Modifier
                                .padding(horizontal = screenWidthDp(80.dp))
                        )
                    } else {
                        Spacer(
                            modifier = Modifier
                                .height(screenHeightDp(16.dp))
                        )
                        FavoriteCertificationListSection(
                            favoriteCertificationList = favoriteCertificationList,
                            onFavoriteClicked = onFavoriteClicked,
                            modifier = modifier
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeScreen() {
    CERTITheme {
    }
}
