package org.sopt.certi.presentation.ui.certlist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import org.sopt.certi.R
import org.sopt.certi.core.state.UiState
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.presentation.ui.certlist.component.CategoryTopBar
import org.sopt.certi.presentation.ui.certlist.component.Top3CertificationItem
import org.sopt.certi.presentation.ui.certlist.state.CertListUiState
import org.sopt.certi.presentation.ui.home.component.RecommendedCertificationListSection
import org.sopt.certi.presentation.ui.trackcategorycertlist.model.TrackCategoryType
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun CertListRoute(
    padding: PaddingValues,
    navigateToSearch: () -> Unit,
    navigateToCertDetail: (certId: Long) -> Unit,
    navigateToMore: (mode: String, default: String) -> Unit,
    viewModel: CertListViewModel = hiltViewModel()
) {
    val uiState by viewModel.certificationListUiState.collectAsStateWithLifecycle()
    val nickname by viewModel.nickname.collectAsStateWithLifecycle()
    val job by viewModel.job.collectAsStateWithLifecycle()
    val track by viewModel.track.collectAsStateWithLifecycle()

    when (uiState.loadState) {
        is UiState.Loading -> {}
        is UiState.Empty -> {}
        is UiState.Success -> {
            CertListScreen(
                nickname = nickname,
                track = track,
                job = job,
                certListState = uiState,
                navigateToSearch = navigateToSearch,
                navigateToCertDetail = navigateToCertDetail,
                navigateToMore = navigateToMore,
                modifier = Modifier.padding(padding)
            )
        }

        is UiState.Failure -> {}
        else -> {}
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CertListScreen(
    nickname: String,
    track: String,
    job: String,
    certListState: CertListUiState,
    navigateToSearch: () -> Unit,
    navigateToCertDetail: (Long) -> Unit,
    navigateToMore: (mode: String, default: String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        stickyHeader {
            CategoryTopBar(
                title = R.string.cert_list_top_bar,
                onClick = navigateToSearch,
                modifier = Modifier
                    .background(color = CertiTheme.colors.white)
                    .padding(horizontal = screenWidthDp(20.dp))
            )

            HorizontalDivider(
                color = CertiTheme.colors.gray100,
                thickness = screenWidthDp(1.dp)
            )
        }

        item {
            CertListRecommendSection(
                nickname = nickname,
                recommendedList = (certListState.recommendListLoadState as? UiState.Success)?.data?.toImmutableList() ?: persistentListOf(),
                onDetailClick = { certId ->
                    navigateToCertDetail(certId)
                },
                modifier = Modifier.padding(start = screenWidthDp(20.dp), end = screenWidthDp(20.dp), top = screenHeightDp(24.dp), bottom = screenHeightDp(36.dp))
            )
        }

        item {
            CertListTop3Section(
                type = TrackCategoryType.TRACK.name.lowercase(),
                titleLabel = track,
                top3List = (certListState.trackTop3ListLoadState as? UiState.Success)?.data?.toImmutableList() ?: persistentListOf(),
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 20.dp),
                navigateToMore = navigateToMore,
                navigateToCertDetail = navigateToCertDetail
            )
        }

        item {
            CertListTop3Section(
                type = TrackCategoryType.CATEGORY.name.lowercase(),
                titleLabel = job,
                top3List = (certListState.categoryTop3ListLoadState as? UiState.Success)?.data?.toImmutableList() ?: persistentListOf(),
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 14.dp),
                navigateToMore = navigateToMore,
                navigateToCertDetail = navigateToCertDetail
            )
        }
    }
}

@Composable
private fun CertListRecommendSection(
    nickname: String,
    recommendedList: ImmutableList<CertificationData>,
    onDetailClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    var infoVisible by remember { mutableStateOf(false) }
    Column(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.cert_list_recommend_title, nickname),
                style = CertiTheme.typography.subtitle.bold_20,
                color = CertiTheme.colors.gray600,
                modifier = Modifier.padding(end = screenWidthDp(4.dp))
            )

            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_information_24),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.noRippleClickable {
                    infoVisible = !infoVisible
                }
            )
        }

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            RecommendedCertificationListSection(
                recommendedList = recommendedList,
                onDetailClick = { certId ->
                    onDetailClick(certId)
                },
                modifier = Modifier.padding(top = 16.dp)
            )

            if (infoVisible) {
                Column(
                    modifier = Modifier
                        .width(IntrinsicSize.Max)
                        .padding(top = 2.dp, end = 14.dp)
                        .background(color = CertiTheme.colors.white, shape = RoundedCornerShape(8.dp))
                        .border(width = 1.dp, color = CertiTheme.colors.gray400, shape = RoundedCornerShape(8.dp))
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                        .align(Alignment.TopEnd)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 3.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(R.string.cert_list_recommend_subtitle),
                            style = CertiTheme.typography.caption.semibold_12,
                            color = CertiTheme.colors.gray500
                        )

                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_close_20),
                            contentDescription = null,
                            modifier = Modifier
                                .noRippleClickable {
                                    infoVisible = !infoVisible
                                },
                            tint = CertiTheme.colors.gray300
                        )
                    }

                    Text(
                        text = stringResource(R.string.cert_list_recommend_description),
                        style = CertiTheme.typography.caption.regular_12,
                        color = CertiTheme.colors.gray400
                    )
                }
            }
        }
    }
}

@Composable
private fun CertListTop3Section(
    type: String,
    titleLabel: String,
    top3List: ImmutableList<CertificationData>,
    navigateToMore: (mode: String, default: String) -> Unit,
    navigateToCertDetail: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.cert_list_recommend_certification_top3_title, titleLabel),
                style = CertiTheme.typography.subtitle.bold_20,
                color = CertiTheme.colors.gray600
            )

            Text(
                text = stringResource(R.string.cert_list_recommend_certification_top3_more),
                style = CertiTheme.typography.caption.regular_12,
                color = CertiTheme.colors.gray400,
                modifier = Modifier.noRippleClickable {
                    navigateToMore(type, titleLabel)
                }
            )
        }

        Top3CertificationItem(
            top3List = top3List,
            navigateToCertDetail = { certId ->
                navigateToCertDetail(certId)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCertListScreen() {
    CERTITheme {
        var certificationList by remember {
            mutableStateOf(
                listOf(
                    CertificationData(
                        certificationId = 1,
                        certificationName = "정보처리기사",
                        tags = listOf("시각디자인", "컴퓨터공학", "경영"),
                        isFavorite = true,
                        testType = "실기형",
                        recommendScore = 20,
                        agencyName = "국가기술자격",
                        description = "자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다.자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다.자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다."
                    ),
                    CertificationData(
                        certificationId = 2,
                        certificationName = "GTQ 1급 (그래픽 기술 자격)",
                        tags = listOf("시각디자인", "컴퓨터공학", "경영"),
                        isFavorite = false,
                        testType = "실기형",
                        recommendScore = 90,
                        agencyName = "국가기술자격",
                        description = "자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다.자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다.자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다."
                    ),
                    CertificationData(
                        certificationId = 3,
                        certificationName = "TOEIC 900+",
                        tags = listOf("경영", "시각디자인", "컴퓨터공학"),
                        isFavorite = true,
                        testType = "실기형",
                        recommendScore = 80,
                        agencyName = "국가기술자격",
                        description = "자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다.자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다.자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다."
                    )
                ).toImmutableList()
            )
        }

        val uiState = CertListUiState(
            recommendListLoadState = UiState.Success(certificationList),
            trackTop3ListLoadState = UiState.Success(certificationList),
            categoryTop3ListLoadState = UiState.Success(certificationList)
        )

        CertListScreen(
            nickname = "김서티",
            track = "공학계열",
            job = "안드로이드",
            certListState = uiState,
            navigateToSearch = { },
            navigateToCertDetail = { },
            navigateToMore = { } as (String, String) -> Unit
        )
    }
}
