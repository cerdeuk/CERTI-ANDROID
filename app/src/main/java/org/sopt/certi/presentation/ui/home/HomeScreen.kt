package org.sopt.certi.presentation.ui.home

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.sopt.certi.R
import org.sopt.certi.core.component.section.CertiEmptySection
import org.sopt.certi.core.component.section.MyCertificationListItemSection
import org.sopt.certi.core.component.topbar.CertiTopBar
import org.sopt.certi.core.state.UiState
import org.sopt.certi.core.util.findActivity
import org.sopt.certi.core.util.heightForScreenPercentage
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.domain.model.user.UserInfoData
import org.sopt.certi.presentation.ui.home.component.FavoriteCertificationListSection
import org.sopt.certi.presentation.ui.home.component.HomeCalendar
import org.sopt.certi.presentation.ui.home.component.NoDataCalendarItem
import org.sopt.certi.presentation.ui.home.component.RecommendedCertificationListSection
import org.sopt.certi.presentation.ui.home.component.UserInfoSection
import org.sopt.certi.presentation.ui.main.MainActivity
import org.sopt.certi.ui.theme.CertiTheme
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun HomeRoute(
    padding: PaddingValues,
    navigateToCertDetail: (certId: Long) -> Unit,
    navigateToCertRecommend: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val activity = context.findActivity() as MainActivity
    val coroutineScope = rememberCoroutineScope()

    val uiState by viewModel.homeUiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getUserInfo()
        viewModel.getRecommendedList()
        viewModel.getFavoriteList()
    }

    when (uiState.loadState) {
        is UiState.Success -> {
            val userInfo = (uiState.userInfoLoadState as? UiState.Success)?.data
            val recommendedList = (uiState.recommendedListLoadState as? UiState.Success)?.data?.toImmutableList() ?: persistentListOf()
            val favoriteList = (uiState.favoriteListLoadState as? UiState.Success)?.data?.toImmutableList() ?: persistentListOf()

            if (userInfo != null) {
                HomeScreen(
                    userInfo = userInfo,
                    recommendedList = recommendedList,
                    favoriteCertificationList = favoriteList,
                    onFavoriteClicked = viewModel::onFavoriteClicked,
                    navigateToCertRecommend = navigateToCertRecommend,
                    navigateToCertDetail = navigateToCertDetail,
                    navigateToLogin = {
                        coroutineScope.launch {
                            viewModel.withDraw()
                            delay(300)
                            viewModel.clearSharedPreference()
                            finishAndRestart(activity, context)
                        }
                    },
                    modifier = Modifier.padding(padding)
                )
            }
        }
        is UiState.Failure -> {}
        is UiState.Loading -> {}
        is UiState.Empty -> {}
        else -> {}
    }
}

@Composable
fun HomeScreen(
    userInfo: UserInfoData,
    modifier: Modifier = Modifier,
    recommendedList: ImmutableList<CertificationData> = persistentListOf(),
    favoriteCertificationList: ImmutableList<CertificationData> = persistentListOf(),
    onFavoriteClicked: (Long) -> Unit = {},
    navigateToCertRecommend: () -> Unit = {},
    navigateToCertDetail: (Long) -> Unit = {},
    navigateToLogin: () -> Unit = {}
) {
    val today = LocalDate.now(ZoneId.of("Asia/Seoul"))
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val currentDate = today.format(formatter)

    var selectedDate by remember { mutableStateOf(currentDate) }
    var certListInSelectedData by remember {
        mutableStateOf(
            listOf<CertificationData>(
//                CertificationData(
//                    certificationId = 1,
//                    certificationName = "정보처리기사",
//                    agencyName = "국가기술자격",
//                    isAcquired = true,
//                    placement = "강남",
//                    testTime = "09:00",
//                    description = "savhufhviufhdsuihvfhdishviufhdsivhiusd"
//                ),
//                CertificationData(
//                    certificationId = 2,
//                    certificationName = "정보처리기사",
//                    agencyName = "국가기술자격",
//                    isAcquired = false,
//                    testDateInformation = "2025-11-03",
//                    level = "IM3",
//                    description = "savhufhviufhdsuihvfhdishviufhdsivhiusd"
//                )
            )
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(CertiTheme.colors.white)
    ) {
        CertiTopBar(
            logoutOnClick = {
                navigateToLogin()
            },
            modifier = Modifier.fillMaxWidth()
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(top = screenHeightDp(32.dp))
        ) {
            item {
                UserInfoSection(
                    userInfoData = userInfo,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = screenWidthDp(20.dp))
                )
                Spacer(modifier = Modifier.height(screenHeightDp(36.dp)))
            }

            item {
                Box(
                    modifier = Modifier.padding(horizontal = 20.dp)
                ) {
                    // TODO 서버에서 날짜 받아서 넣어야댐
                    HomeCalendar() { day ->
                        selectedDate = day
                    }
                }

                if (certListInSelectedData.isNotEmpty()) {
                    Spacer(Modifier.heightForScreenPercentage(16.dp))
                }
            }

            item {
                // Date Text
                if (selectedDate.isNotEmpty()) {
                    val currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                    val backgroundColor = if (certListInSelectedData.isEmpty()) CertiTheme.colors.white else CertiTheme.colors.purpleWhite
                    val dayName = getDayNameOfWeek(selectedDate)
                    val (_, selectedMonth, selectedDay) = selectedDate.split("-")

                    val dateText = if (selectedDate == currentDate) {
                        stringResource(R.string.home_calendar_today_date, selectedMonth, selectedDay, dayName)
                    } else {
                        stringResource(R.string.home_calendar_date, selectedMonth, selectedDay, dayName)
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = backgroundColor)
                            .padding(horizontal = screenWidthDp(20.dp), vertical = screenHeightDp(16.dp))
                    ) {
                        Text(
                            text = dateText,
                            style = CertiTheme.typography.body.semibold_16,
                            color = CertiTheme.colors.black
                        )
                    }
                }
            }

            items(
                items = certListInSelectedData,
                key = { it.certificationId }
            ) { certificationData ->
                if (selectedDate.isNotEmpty()) {
                    Box(
                        modifier = Modifier
                            .background(color = CertiTheme.colors.purpleWhite)
                            .padding(horizontal = screenWidthDp(20.dp))
                            .padding(bottom = screenHeightDp(16.dp))
                    ) {
                        MyCertificationListItemSection(
                            certificationData = certificationData,
                            isEditMode = false,
                            onCertificationClick = {
                                // TODO 자격증 이동
                            }
                        )
                    }
                }
            }

            item {
                if (selectedDate.isNotEmpty() && certListInSelectedData.isEmpty()) {
                    NoDataCalendarItem {
                        // TODO 자격증 추가하기 이동
                    }
                }
            }

            // 즐겨찾기 자격증
            item {
                Spacer(modifier = Modifier.height(screenHeightDp(36.dp)))

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
                        Spacer(Modifier.height(screenHeightDp(16.dp)))

                        FavoriteCertificationListSection(
                            favoriteCertificationList = favoriteCertificationList,
                            onDetailClick = { certId ->
                                navigateToCertDetail(certId)
                            },
                            onFavoriteClicked = onFavoriteClicked
                        )
                    }
                }
            }

            // 추천 자격증
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = screenWidthDp(20.dp)),
                    verticalArrangement = Arrangement.spacedBy(screenHeightDp(16.dp))
                ) {
                    Spacer(Modifier.heightForScreenPercentage(36.dp))

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
                    RecommendedCertificationListSection(
                        recommendedList = recommendedList,
                        onDetailClick = { certId ->
                            navigateToCertDetail(certId)
                        }
                    )
                }

                Spacer(modifier = Modifier.height(screenHeightDp(20.dp)))
            }
        }
    }
}

private fun getDayNameOfWeek(dateString: String): String {
    val date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    return date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREA)
}

private fun finishAndRestart(activity: MainActivity, context: Context) {
    val intent = Intent(context, MainActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    activity.startActivity(intent)
    activity.finish()
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val userInfo = UserInfoData(
        name = "김솝트",
        university = "SOPT대학교",
        major = "컴퓨터공학과",
        category = listOf("IT", "데이터"),
        track = "Android"
    )
    val recommendedList = persistentListOf(
        CertificationData(
            certificationId = 1,
            certificationName = "OPIc",
            recommendScore = 90,
            description = "자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다.",
            tags = listOf("컴퓨터공학", "재무/세무/IR", "재무/세무/IR")
        ),
        CertificationData(
            certificationId = 2,
            certificationName = "ABCD",
            recommendScore = 80,
            description = "자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다.",
            tags = listOf("컴퓨터공학", "재무/세무/IR", "재무/세무/IR")
        ),
        CertificationData(
            certificationId = 3,
            certificationName = "QWER",
            recommendScore = 85,
            description = "자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다.",
            tags = listOf("컴퓨터공학", "재무/세무/IR", "재무/세무/IR")
        )
    )
    val favoriteList = persistentListOf(
        CertificationData(
            certificationId = 3,
            certificationName = "리눅스마스터 1급",
            certificationType = "국가기술자격",
            isFavorite = true,
            testType = "실기형",
            agencyName = "한국산업인력공단"
        ),
        CertificationData(
            certificationId = 4,
            certificationName = "리눅스마스터 2급",
            certificationType = "국가기술자격",
            isFavorite = true,
            testType = "실기형",
            agencyName = "한국산업인력공단"
        )
    )

    HomeScreen(
        userInfo = userInfo,
        recommendedList = recommendedList,
        favoriteCertificationList = favoriteList
    )
}

@Preview(showBackground = true, name = "HomeScreen With Empty Lists")
@Composable
fun HomeScreenEmptyPreview() {
    val userInfo = UserInfoData(
        name = "김솝트",
        university = "SOPT대학교",
        major = "컴퓨터공학과"
    )

    val recommendedList = persistentListOf(
        CertificationData(
            certificationId = 1,
            certificationName = "OPIc",
            recommendScore = 90,
            description = "자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다.",
            tags = listOf("컴퓨터공학", "재무/세무/IR", "재무/세무/IR")
        ),
        CertificationData(
            certificationId = 2,
            certificationName = "ABCD",
            recommendScore = 80,
            description = "자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다.",
            tags = listOf("컴퓨터공학", "재무/세무/IR", "재무/세무/IR")
        ),
        CertificationData(
            certificationId = 3,
            certificationName = "QWER",
            recommendScore = 85,
            description = "자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다.",
            tags = listOf("컴퓨터공학", "재무/세무/IR", "재무/세무/IR")
        )
    )

    HomeScreen(
        userInfo = userInfo,
        recommendedList = recommendedList
    )
}
