package org.sopt.certi.presentation.ui.certlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.sopt.certi.core.component.section.CertificationListSection
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.domain.model.CertificationListData
import org.sopt.certi.presentation.ui.certlist.component.CategoryBar
import org.sopt.certi.presentation.ui.certlist.component.CategoryFavoriteButton
import org.sopt.certi.presentation.ui.certlist.component.CategoryTopBar
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun CertListRoute(
    padding: PaddingValues,
    navigateToSearch: () -> Unit,
    viewModel: CertListViewModel = hiltViewModel()
) {
    var selectedIndex by remember { mutableIntStateOf(0) }
    var isFavorite by remember { mutableStateOf(false) }
    var certificationList by remember {
        mutableStateOf(
            listOf<CertificationListData>(
                CertificationListData(
                    certificationId = 1,
                    isLiked = false,
                    certificationName = "정보처리기사",
                    categories = listOf("컴퓨터공학", "시각디자인", "경영"),
                    agency = "국가기술자격",
                    applicationMethod = "실기형"
                ),
                CertificationListData(
                    certificationId = 1,
                    isLiked = false,
                    certificationName = "정보처리",
                    categories = listOf("뿡뿡", "빵빵", "IT"),
                    agency = "한국정보처리기관",
                    applicationMethod = "실기형"
                ),
                CertificationListData(
                    certificationId = 1,
                    isLiked = false,
                    certificationName = "정보처리기사",
                    categories = listOf("컴퓨터공학", "시각디자인", "경영"),
                    agency = "국가기술자격",
                    applicationMethod = "실기형"
                ),
                CertificationListData(
                    certificationId = 1,
                    isLiked = false,
                    certificationName = "정보처리",
                    categories = listOf("뿡뿡", "빵빵", "IT"),
                    agency = "한국정보처리기관",
                    applicationMethod = "실기형"
                ),
                CertificationListData(
                    certificationId = 1,
                    isLiked = false,
                    certificationName = "정보처리기사",
                    categories = listOf("컴퓨터공학", "시각디자인", "경영"),
                    agency = "국가기술자격",
                    applicationMethod = "실기형"
                ),
                CertificationListData(
                    certificationId = 1,
                    isLiked = false,
                    certificationName = "정보처리",
                    categories = listOf("뿡뿡", "빵빵", "IT"),
                    agency = "한국정보처리기관",
                    applicationMethod = "실기형"
                ),
                CertificationListData(
                    certificationId = 1,
                    isLiked = false,
                    certificationName = "정보처리기사",
                    categories = listOf("컴퓨터공학", "시각디자인", "경영"),
                    agency = "국가기술자격",
                    applicationMethod = "실기형"
                ),
                CertificationListData(
                    certificationId = 1,
                    isLiked = false,
                    certificationName = "정보처리",
                    categories = listOf("뿡뿡", "빵빵", "IT"),
                    agency = "한국정보처리기관",
                    applicationMethod = "실기형"
                ),
                CertificationListData(
                    certificationId = 1,
                    isLiked = false,
                    certificationName = "정보처리기사",
                    categories = listOf("컴퓨터공학", "시각디자인", "경영"),
                    agency = "국가기술자격",
                    applicationMethod = "실기형"
                )
            )
        )
    }

    CertListScreen(
        navigateToSearch = navigateToSearch,
        selectedCategory = selectedIndex,
        onCategorySelected = { selectedIndex = it },
        isFavorite = isFavorite,
        onFavoriteClick = { isFavorite = !isFavorite },
        certificationList = certificationList,
        onLikeClick = { index ->
            certificationList = certificationList.mapIndexed { i, item ->
                if (i == index) item.copy(isLiked = !item.isLiked) else item
            }
        },
        modifier = Modifier.padding(padding)
    )
}

@Composable
private fun CertListScreen(
    navigateToSearch: () -> Unit,
    selectedCategory: Int,
    onCategorySelected: (Int) -> Unit,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    certificationList: List<CertificationListData>,
    onLikeClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        CategoryTopBar(
            onClick = navigateToSearch,
            modifier = Modifier.padding(horizontal = screenWidthDp(20.dp))
        )

        CategoryBar(
            selectedCategory = selectedCategory,
            onCategorySelected = onCategorySelected
        )
        HorizontalDivider(
            color = CertiTheme.colors.gray100,
            thickness = screenWidthDp(1.dp)
        )

        CategoryFavoriteButton(
            onClick = onFavoriteClick,
            isFavorite = isFavorite,
            modifier = Modifier.padding(horizontal = screenWidthDp(20.dp), vertical = screenHeightDp(12.dp))
        )

        LazyColumn(
            modifier = Modifier
                .padding(horizontal = screenWidthDp(20.dp))
                .fillMaxSize()
        ) {
            items(certificationList.size) { index ->
                CertificationListSection(
                    certificationListData = certificationList[index],
                    onLikeClick = { onLikeClick(index) },
                    onCertificationClick = {
                        // 상세페이지로 화면 전환
                    },
                    modifier = Modifier.padding(bottom = screenHeightDp(12.dp))
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCertListScreen() {
    CERTITheme {
        CertListScreen(
            navigateToSearch = {},
            selectedCategory = 0,
            onCategorySelected = {},
            isFavorite = false,
            onFavoriteClick = {},
            certificationList = listOf(),
            onLikeClick = {}
        )
    }
}
