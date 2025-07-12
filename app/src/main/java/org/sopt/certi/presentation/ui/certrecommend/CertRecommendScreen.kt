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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.sopt.certi.R
import org.sopt.certi.core.component.section.CertificationListSection
import org.sopt.certi.core.util.heightForScreenPercentage
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.domain.model.CertificationData
import org.sopt.certi.domain.type.CategoryType
import org.sopt.certi.presentation.ui.certrecommend.component.bottomsheet.RecommendFilterBottomSheet
import org.sopt.certi.presentation.ui.certrecommend.component.chip.RecommendCategoryChip
import org.sopt.certi.presentation.ui.certrecommend.component.chip.ReselectInterestedChip
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CertRecommendRoute(
    padding: PaddingValues,
    navigateToCertDetail: () -> Unit,
    viewModel: CertRecommendViewModel = hiltViewModel()
) {
    /** 필터 바텀시트 **/
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    var showFilterBottomSheet by remember { mutableStateOf(false) }
    val selectedCategoryList = remember { mutableStateListOf<CategoryType>() }
    val localSelectedCategoryList = remember { mutableStateListOf<CategoryType>() }

    // FIXME 더미 데이터
    val dummyRecommendList = remember { mutableStateListOf<CertificationData>() }
    for (i in 0L..11L) {
        dummyRecommendList.add(
            CertificationData(
                certificationId = i,
                certificationName = "GTQ 1급 (그래픽기술자격)",
                agencyName = "국가기술자격",
                createdAt = LocalDate.now(),
                cardFrontImageUrl = "https://sopt-certi-bucket.s3.ap-northeast-2.amazonaws.com/certi/color%3Dblue.png",
                tags = listOf("태그", "태그", "태그")
            )
        )
    }

    CertRecommendScreen(
        recommendCertList = dummyRecommendList,
        selectedCategoryList = selectedCategoryList,
        showFilterBottomSheet = { showFilterBottomSheet = true },
        likeOnClick = { index ->
            val modifiedList = dummyRecommendList.mapIndexed { i, item ->
                if (i == index) item.copy(isFavorite = !item.isFavorite) else item
            }

            dummyRecommendList.clear()
            dummyRecommendList.addAll(modifiedList)
        },
        navigateToCertDetail = navigateToCertDetail,
        modifier = Modifier.padding(padding)
    )

    if (showFilterBottomSheet) {
        RecommendFilterBottomSheet(
            sheetState = sheetState,
            selectedList = localSelectedCategoryList,
            onItemClick = { categoryType ->
                if (localSelectedCategoryList.contains(categoryType)) {
                    localSelectedCategoryList.remove(categoryType)
                } else {
                    localSelectedCategoryList.add(categoryType)
                }
            },
            changeBottomSheetVisibility = { showFilterBottomSheet = it },
            onConfirmClick = {
                selectedCategoryList.clear()
                selectedCategoryList.addAll(localSelectedCategoryList)
                // TODO 필터 선택 완료 후 로직
            },
            onDismissClick = {
                localSelectedCategoryList.clear()
                localSelectedCategoryList.addAll(selectedCategoryList)
            }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CertRecommendScreen(
    recommendCertList: List<CertificationData>,
    selectedCategoryList: List<CategoryType>,
    showFilterBottomSheet: () -> Unit,
    likeOnClick: (Int) -> Unit,
    navigateToCertDetail: () -> Unit,
    modifier: Modifier = Modifier
) {
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
                        // TODO 즐겨찾기 로직 처리
                        likeOnClick(index)
                    },
                    onCertificationClick = navigateToCertDetail,
                    modifier = Modifier.padding(horizontal = screenWidthDp(20.dp), vertical = screenHeightDp(6.dp))
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCertRecommendScreen() {
    CERTITheme {
        val dummyRecommendList = mutableListOf<CertificationData>()

        for (i in 0L..11L) {
            dummyRecommendList.add(
                CertificationData(
                    certificationId = i,
                    certificationName = "GTQ 1급 (그래픽기술자격)",
                    agencyName = "국가기술자격",
                    createdAt = LocalDate.now(),
                    cardFrontImageUrl = "https://sopt-certi-bucket.s3.ap-northeast-2.amazonaws.com/certi/color%3Dblue.png",
                    tags = listOf("태그", "태그", "태그")
                )
            )
        }

        CertRecommendScreen(
            recommendCertList = dummyRecommendList,
            selectedCategoryList = emptyList(),
            showFilterBottomSheet = {},
            navigateToCertDetail = {},
            likeOnClick = {}
        )
    }
}
