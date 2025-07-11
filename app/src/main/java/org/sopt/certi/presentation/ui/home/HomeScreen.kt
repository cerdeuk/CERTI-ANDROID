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
import org.sopt.certi.core.component.section.CertiEmptySection
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.showIf
import org.sopt.certi.domain.model.ResumeData

@Composable
fun HomeRoute(
    padding: PaddingValues,
    navigateToRecommend: () -> Unit,
    navigateToPreCerti: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    var isFavorite by remember { mutableStateOf(false) }

    val userInfo = UserInfoData(
        name = "김서티",
        university = "솝트대학교",
        major = "경영학과"
    )
    val recommendedList = listOf(
        ResumeData(
            certificationId = 1,
            certificationName = "OPIc",
            recommendScore = 90,
            tags = listOf("컴퓨터공학", "재무/세무/IR", "재무/세무/IR")
        ),
        ResumeData(
            certificationId = 2,
            certificationName = "시각디자인산업기사",
            recommendScore = 90,
            tags = listOf("컴퓨터공학", "재무/세무/IR", "재무/세무/IR")
        ),
        ResumeData(
            certificationId = 3,
            certificationName = "정보처리기사",
            recommendScore = 90,
            tags = listOf("컴퓨터공학", "재무/세무/IR", "재무/세무/IR")
        )
    )
    val preCertificationList = listOf(
        ResumeData(
            certificationId = 1,
            certificationName = "시각디자인산업기사",
            averagePeriod = "3개월",
            nearestTestDate = "2025.05.27",
            agencyName = "한국산업인력공단",
            iconIndex = 0
        ),
        ResumeData(
            certificationId = 2,
            certificationName = "시각디자인산업기사",
            averagePeriod = "3개월",
            nearestTestDate = "2025.05.27",
            agencyName = "한국산업인력공단",
            iconIndex = 1
        ),
        ResumeData(
            certificationId = 3,
            certificationName = "시각디자인산업기사",
            averagePeriod = "3개월",
            nearestTestDate = "2025.05.27",
            agencyName = "한국산업인력공단",
            iconIndex = 2
        )
    )
    val favoriteCertificationList = listOf(
        ResumeData(
            certificationId = 1,
            certificationName = "정보처리기사",
            testType = "실기형",
            agencyName = "한국산업인력공단",
            certificationType = "국가기술자격"
        ),
        ResumeData(
            certificationId = 2,
            certificationName = "시각디자인산업기사",
            testType = "실기형",
            agencyName = "한국산업인력공단",
            certificationType = "국가기술자격"
        )
    )

    HomeScreen(
        userInfo = userInfo,
        recommendedList = recommendedList,
        preCertificationList = preCertificationList,
        favoriteCertificationList = favoriteCertificationList,
        isFavorite = isFavorite,
        onFavoriteClicked = { isFavorite = !isFavorite },
        navigateToRecommend = navigateToRecommend,
        navigateToPreCerti = navigateToPreCerti,
        modifier = Modifier.padding(padding)
    )
}

@Composable
fun HomeScreen(
    userInfo: UserInfoData,
    recommendedList: List<ResumeData>,
    preCertificationList: List<ResumeData>,
    favoriteCertificationList: List<ResumeData>,
    isFavorite: Boolean = true,
    onFavoriteClicked: () -> Unit,
    navigateToRecommend: () -> Unit,
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
                                .noRippleClickable { navigateToRecommend() }

                        )
                    }
                    RecommendedCertificationListSection(recommendedList = recommendedList, onCertificationClick = { })
                    Spacer(modifier = Modifier.height(screenHeightDp(16.dp)))
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
                        Spacer(
                            modifier = Modifier
                                .height(screenHeightDp(36.dp))
                                .showIf(preCertificationList.isNotEmpty())
                        )
                        PreCertificationListSection(preCertificationList = preCertificationList)
                    }
                }
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = screenHeightDp(36.dp))
                ) {
                    Text(
                        text = stringResource(R.string.home_favorite_title),
                        style = CertiTheme.typography.subtitle.semibold_20,
                        color = CertiTheme.colors.gray600,
                        modifier = Modifier.padding(start = screenWidthDp(20.dp))
                    )
                    if (favoriteCertificationList.isEmpty()) {
                        CertiEmptySection(
                            text = stringResource(id = R.string.home_pre_certification_empty),
                            modifier = Modifier
                                .padding(horizontal = screenWidthDp(80.dp))
                        )
                    } else {
                        Spacer(
                            modifier = Modifier
                                .height(screenHeightDp(16.dp))
                                .showIf(favoriteCertificationList.isNotEmpty())
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
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeScreen() {
    var isFavorite by remember { mutableStateOf(true) }

    val favoriteCertificationList = listOf(
        ResumeData(
            certificationId = 1,
            certificationName = "정보처리기사",
            testType = "실기형",
            agencyName = "한국산업인력공단",
            certificationType = "국가기술자격"
        ),
        ResumeData(
            certificationId = 2,
            certificationName = "시각디자인산업기사",
            testType = "실기형",
            agencyName = "한국산업인력공단",
            certificationType = "국가기술자격"
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
                ResumeData(
                    certificationId = 1,
                    certificationName = "OPIc",
                    recommendScore = 90,
                    tags = listOf("컴퓨터공학", "재무/세무/IR", "재무/세무/IR")
                ),
                ResumeData(
                    certificationId = 2,
                    certificationName = "시각디자인산업기사",
                    recommendScore = 90,
                    tags = listOf("컴퓨터공학", "재무/세무/IR", "재무/세무/IR")
                ),
                ResumeData(
                    certificationId = 3,
                    certificationName = "정보처리기사",
                    recommendScore = 90,
                    tags = listOf("컴퓨터공학", "재무/세무/IR", "재무/세무/IR")
                )
            ),
            preCertificationList = listOf(),
            favoriteCertificationList = favoriteCertificationList,
            isFavorite = true,
            onFavoriteClicked = { isFavorite = !isFavorite },
            navigateToRecommend = { },
            navigateToPreCerti = { }
        )
    }
}
