package org.sopt.certi.presentation.ui.certrecommend

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import org.sopt.certi.core.util.roundedBackgroundWithBorder
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.domain.model.CertificationData
import org.sopt.certi.domain.type.CategoryType
import org.sopt.certi.presentation.ui.certrecommend.component.bottomsheet.RecommendFilterBottomSheet
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
    val dummyRecommendList = mutableListOf<CertificationData>()

    var showFilterBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val selectedCategoryList = remember { mutableStateListOf<CategoryType>() }

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
        showFilterBottomSheet = { showFilterBottomSheet = true },
        navigateToCertDetail = navigateToCertDetail,
        modifier = Modifier.padding(padding)
    )

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
            onConfirmClick = { }
        )
    }
}

@Composable
fun CertRecommendScreen(
    recommendCertList: List<CertificationData>,
    showFilterBottomSheet: () -> Unit,
    navigateToCertDetail: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            top = screenHeightDp(34.dp),
            bottom = screenHeightDp(36.dp)
        )
    ) {
        item {
            Text(
                text = stringResource(R.string.cert_recommend_title),
                style = CertiTheme.typography.subtitle.bold_20,
                color = CertiTheme.colors.gray600,
                modifier = Modifier.padding(horizontal = screenWidthDp(20.dp))
            )
        }

        item {
            Spacer(Modifier.heightForScreenPercentage(8.dp))
        }

        item {
            Text(
                text = stringResource(R.string.cert_recommend_sub_title),
                style = CertiTheme.typography.caption.regular_14,
                color = CertiTheme.colors.gray600,
                modifier = Modifier.padding(horizontal = screenWidthDp(20.dp))
            )
        }

        item {
            Spacer(Modifier.heightForScreenPercentage(24.dp))
        }

        // 카테고리 리스트
        item {
            val categoryList = CategoryType.entries.toTypedArray()

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = screenWidthDp(20.dp)),
                horizontalArrangement = Arrangement.spacedBy(screenWidthDp(8.dp))
            ) {
                item {
                    ReselectInterestedChip(
                        chipOnClick = {
                            showFilterBottomSheet()
                        }
                    )
                }

                items(categoryList.size) {
                    CategoryChipItemLayout(categoryList[it])
                }
            }
        }

        item {
            Spacer(Modifier.heightForScreenPercentage(22.dp))
        }

        items(recommendCertList.size) {
            CertificationListSection(
                certificationListData = recommendCertList[it],
                onLikeClick = {},
                onCertificationClick = navigateToCertDetail,
                modifier = Modifier.padding(horizontal = screenWidthDp(20.dp), vertical = screenHeightDp(6.dp))
            )
        }
    }
}

@Composable
private fun CategoryChipItemLayout(categoryType: CategoryType) {
    Box(
        modifier = Modifier
            .roundedBackgroundWithBorder(cornerRadius = 24.dp, backgroundColor = CertiTheme.colors.gray0)
            .padding(horizontal = screenWidthDp(12.dp), vertical = screenHeightDp(6.dp))
    ) {
        Text(
            text = categoryType.description,
            style = CertiTheme.typography.caption.regular_12,
            color = CertiTheme.colors.gray600
        )
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
            showFilterBottomSheet = {},
            navigateToCertDetail = {}
        )
    }
}
