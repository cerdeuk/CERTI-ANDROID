package org.sopt.certi.presentation.ui.trackcategorycertlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.sopt.certi.R
import org.sopt.certi.core.component.section.CertificationListSection
import org.sopt.certi.core.state.UiState
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.presentation.ui.certlist.component.CategoryBar
import org.sopt.certi.presentation.ui.certlist.component.CategoryFilterButton
import org.sopt.certi.presentation.ui.certlist.component.CategoryTopBar
import org.sopt.certi.presentation.ui.trackcategorycertlist.state.TrackCategoryCertListUiState
import org.sopt.certi.presentation.ui.trackcategorycertlist.model.TrackCategoryType
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun TrackCategoryCertListRoute(
    padding: PaddingValues,
    navigateToSearch: () -> Unit,
    navigateToCertDetail: (certId: Long) -> Unit,
    viewModel: TrackCategoryCertListViewModel = hiltViewModel()
) {
    val uiState by viewModel.certificationListUiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.selectedCategory, uiState.isFavorite) {
        viewModel.getCertificationList(uiState.isFavorite, uiState.selectedCategory)
    }

    TrackCategoryCertListScreen(
        type = viewModel.mode,
        certListState = uiState,
        navigateToSearch = navigateToSearch,
        navigateToCertDetail = navigateToCertDetail,
        onCategorySelected = viewModel::onCategorySelected,
        onFavoriteButtonClick = viewModel::onFavoriteClick,
        onLikeClick = viewModel::onLikeClick,
        modifier = Modifier.padding(padding)
    )
}

@Composable
private fun TrackCategoryCertListScreen(
    type: TrackCategoryType,
    certListState: TrackCategoryCertListUiState,
    navigateToSearch: () -> Unit,
    navigateToCertDetail: (Long) -> Unit,
    onCategorySelected: (Int) -> Unit,
    onFavoriteButtonClick: () -> Unit,
    onLikeClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        CategoryTopBar(
            title = type.titleResId,
            onClick = navigateToSearch,
            modifier = Modifier.padding(horizontal = screenWidthDp(20.dp))
        )

        CategoryBar(
            labels = type.type,
            selectedCategory = certListState.selectedCategory,
            onCategorySelected = onCategorySelected
        )
        HorizontalDivider(
            color = CertiTheme.colors.gray100,
            thickness = screenWidthDp(1.dp)
        )

        CategoryFilterButton(
            label = stringResource(R.string.cert_list_favorite_btn),
            onClick = onFavoriteButtonClick,
            isClicked = certListState.isFavorite,
            modifier = Modifier
                .padding(vertical = screenHeightDp(12.dp), horizontal = screenWidthDp(20.dp))
        )

        HorizontalDivider(
            color = CertiTheme.colors.gray100,
            thickness = screenWidthDp(1.dp)
        )

        when (certListState.certificationListLoadState) {
            is UiState.Success ->
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(
                        items = certListState.certificationListLoadState.data,
                        key = { it.certificationId }
                    ) { item ->
                        CertificationListSection(
                            certificationListData = item,
                            onLikeClick = { onLikeClick(item.certificationId) },
                            onCertificationClick = {
                                navigateToCertDetail(item.certificationId)
                            },
                            modifier = Modifier
                                .padding(horizontal = screenWidthDp(20.dp))
                                .padding(top = screenHeightDp(16.dp), bottom = screenHeightDp(24.dp))
                        )

                        HorizontalDivider(
                            color = CertiTheme.colors.gray100,
                            thickness = screenWidthDp(1.dp)
                        )
                    }
                }

            is UiState.Failure -> {}
            is UiState.Loading -> {}
            is UiState.Empty -> {}
            is UiState.Init -> {}
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CategoryCertListScreenPreview() {
    CERTITheme {
        var selectedCategory by remember { mutableIntStateOf(0) }
        var certificationList by remember {
            mutableStateOf(
                listOf(
                    CertificationData(
                        certificationId = 1,
                        certificationName = "정보처리기사",
                        tags = listOf("시각디자인", "컴퓨터공학", "경영"),
                        isFavorite = true,
                        testType = "실기형",
                        agencyName = "국가기술자격",
                        description = "자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다.자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다.자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다."
                    ),
                    CertificationData(
                        certificationId = 2,
                        certificationName = "GTQ 1급 (그래픽 기술 자격)",
                        tags = listOf("시각디자인", "컴퓨터공학", "경영"),
                        isFavorite = false,
                        testType = "실기형",
                        agencyName = "국가기술자격",
                        description = "자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다.자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다.자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다."
                    ),
                    CertificationData(
                        certificationId = 3,
                        certificationName = "TOEIC 900+",
                        tags = listOf("경영", "시각디자인", "컴퓨터공학"),
                        isFavorite = true,
                        testType = "실기형",
                        agencyName = "국가기술자격",
                        description = "자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다.자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다.자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다."
                    )
                )
            )
        }

        val uiState = TrackCategoryCertListUiState(
            certificationListLoadState = if (certificationList.isEmpty()) UiState.Empty else UiState.Success(certificationList),
            selectedCategory = selectedCategory,
            isFavorite = false
        )

        TrackCategoryCertListScreen(
            type = TrackCategoryType.TRACK,
            certListState = uiState,
            navigateToSearch = { },
            navigateToCertDetail = { },
            onCategorySelected = { index ->
                selectedCategory = index
            },
            onFavoriteButtonClick = {},
            onLikeClick = {}
        )
    }
}
