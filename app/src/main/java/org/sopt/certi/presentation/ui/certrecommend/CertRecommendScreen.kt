package org.sopt.certi.presentation.ui.certrecommend

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import org.sopt.certi.R
import org.sopt.certi.core.component.section.CertificationListSection
import org.sopt.certi.core.state.UiState
import org.sopt.certi.core.util.heightForScreenPercentage
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.domain.model.CertificationData
import org.sopt.certi.domain.type.CategoryType
import org.sopt.certi.presentation.ui.certrecommend.component.bottomsheet.RecommendFilterBottomSheet
import org.sopt.certi.presentation.ui.certrecommend.component.chip.RecommendCategoryChip
import org.sopt.certi.presentation.ui.certrecommend.component.chip.ReselectInterestedChip
import org.sopt.certi.presentation.ui.certrecommend.sideeffect.RecommendSideEffect
import org.sopt.certi.ui.theme.CertiTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CertRecommendRoute(
    padding: PaddingValues,
    navigateToCertDetail: (certId: Long) -> Unit,
    viewModel: CertRecommendViewModel = hiltViewModel()
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    /** 필터 바텀시트 **/
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    var showFilterBottomSheet by remember { mutableStateOf(false) }
    val selectedCategoryList = remember { mutableStateListOf<CategoryType>() }

    val recommendUiState by viewModel.recommendUiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getJobList()
    }

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycleOwner.lifecycle).collect {
            when (it) {
                is RecommendSideEffect.ShowFilterBottomSheer -> showFilterBottomSheet = true
            }
        }
    }

    when (recommendUiState.loadState) {
        is UiState.Success -> {
            selectedCategoryList.clear()
            selectedCategoryList.addAll(stringListToCategoryList((recommendUiState.jobListLoadState as UiState.Success).data))

            CertRecommendScreen(
                recommendCertList = (recommendUiState.recommendCertListLoadState as UiState.Success).data.toImmutableList(),
                selectedCategoryList = stringListToCategoryList((recommendUiState.jobListLoadState as UiState.Success).data.toImmutableList()).toImmutableList(),
                showFilterBottomSheet = { viewModel.showFilterBottomSheet() },
                likeOnClick = { certId ->
                    viewModel.onLikeClick(certId)
                },
                navigateToCertDetail = navigateToCertDetail,
                modifier = Modifier.padding(padding)
            )
        }
        else -> {}
    }

    if (showFilterBottomSheet) {
        RecommendFilterBottomSheet(
            sheetState = sheetState,
            selectedList = selectedCategoryList,
            onItemClick = { categoryType ->
                if (selectedCategoryList.contains(categoryType)) {
                    selectedCategoryList.remove(categoryType)
                } else {
                    selectedCategoryList.add(categoryType)
                }
            },
            changeBottomSheetVisibility = { showFilterBottomSheet = it },
            onConfirmClick = {
                val categoryStringList = mutableListOf<String>()
                selectedCategoryList.forEach { item ->
                    categoryStringList.add(item.description)
                }

                viewModel.editJob(categoryStringList)
            },
            onDismissClick = {
                selectedCategoryList.clear()
            }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CertRecommendScreen(
    recommendCertList: ImmutableList<CertificationData>,
    selectedCategoryList: ImmutableList<CategoryType>,
    showFilterBottomSheet: () -> Unit,
    likeOnClick: (Long) -> Unit,
    navigateToCertDetail: (certId: Long) -> Unit,
    modifier: Modifier = Modifier
) {
//    when (recommendUiState.loadState) {
//        is UiState.Success -> {
//            recommendCertList.clear()
//            recommendCertList.addAll((recommendUiState.recommendCertListLoadState as UiState.Success).data)
//
//            val jobList = (recommendUiState.jobListLoadState as UiState.Success).data
//            val categoryList = mutableListOf<CategoryType>()
//            jobList.forEach { item ->
//                categoryList.add(CategoryType.getByDescription(item) ?: return@forEach)
//            }
//
//            selectedCategoryList.clear()
//            selectedCategoryList.addAll(categoryList)
//        }
//        else -> {}
//    }

    CompositionLocalProvider(LocalOverscrollConfiguration provides null) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                bottom = screenHeightDp(36.dp)
            )
        ) {
            stickyHeader {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(CertiTheme.colors.white)
                ) {
                    Spacer(Modifier.heightForScreenPercentage(34.dp))
                    Text(
                        text = stringResource(R.string.cert_recommend_title),
                        style = CertiTheme.typography.subtitle.bold_20,
                        color = CertiTheme.colors.gray600,
                        modifier = Modifier.padding(horizontal = screenWidthDp(20.dp))
                    )
                    Spacer(Modifier.heightForScreenPercentage(8.dp))
                    Text(
                        text = stringResource(R.string.cert_recommend_sub_title),
                        style = CertiTheme.typography.caption.regular_14,
                        color = CertiTheme.colors.gray600,
                        modifier = Modifier.padding(horizontal = screenWidthDp(20.dp))
                    )
                    Spacer(Modifier.heightForScreenPercentage(24.dp))

                    // 카테고리 리스트
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(horizontal = screenWidthDp(20.dp)),
                        horizontalArrangement = Arrangement.spacedBy(screenWidthDp(8.dp))
                    ) {
                        item {
                            ReselectInterestedChip(chipOnClick = { showFilterBottomSheet() })
                        }
                        items(selectedCategoryList.size) { index ->
                            RecommendCategoryChip(selectedCategoryList[index])
                        }
                    }
                    Spacer(Modifier.heightForScreenPercentage(22.dp))
                }
            }

            items(recommendCertList.size) { index ->
                CertificationListSection(
                    certificationListData = recommendCertList[index],
                    onLikeClick = {
                        likeOnClick(recommendCertList[index].certificationId)
                    },
                    onCertificationClick = {
                        navigateToCertDetail(recommendCertList[index].certificationId)
                    },
                    modifier = Modifier.padding(horizontal = screenWidthDp(20.dp), vertical = screenHeightDp(6.dp))
                )
            }
        }
    }
}

private fun stringListToCategoryList(stringCategoryList: List<String>): List<CategoryType> {
    val categoryList = mutableListOf<CategoryType>()
    stringCategoryList.forEach { item ->
        categoryList.add(CategoryType.getByDescription(item) ?: return@forEach)
    }

    return categoryList
}
