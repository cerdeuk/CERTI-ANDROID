package org.sopt.certi.presentation.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import org.sopt.certi.core.component.textfield.CertiBasicTextField
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.showIf
import org.sopt.certi.domain.model.CertificationListData
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun SearchRoute(
    padding: PaddingValues,
    viewModel: SearchViewModel = hiltViewModel()
) {
    var searchText by remember { mutableStateOf("") }
    var searchState by remember { mutableStateOf(false) }

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

    SearchScreen(
        value = searchText,
        onValueChange = { searchText = it },
        searchState = searchState,
        onSearchClick = { searchState = true },
        onLikeClick = { index ->
            certificationList = certificationList.mapIndexed { i, item ->
                if (i == index) item.copy(isLiked = !item.isLiked) else item
            }
        },
        certificationList = certificationList,
        modifier = Modifier.padding(padding)
    )
}

@Composable
private fun SearchScreen(
    value: String,
    onValueChange: (String) -> Unit,
    searchState: Boolean,
    onSearchClick: () -> Unit,
    onLikeClick: (Int) -> Unit,
    certificationList: List<CertificationListData>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(top = screenHeightDp(24.dp), start = screenWidthDp(20.dp), end = screenWidthDp(20.dp))
            .fillMaxSize()
    ) {
        CertiBasicTextField(
            value = value,
            onValueChange = onValueChange,
            onSearchClick = onSearchClick,
            modifier = Modifier.padding(vertical = screenHeightDp(12.dp))
        )

        LazyColumn(
            modifier = Modifier
                .showIf(searchState)
        ) {
            item {
                Text(
                    text = stringResource(id = R.string.search_result_title, certificationList.size),
                    style = CertiTheme.typography.caption.regular_14,
                    color = CertiTheme.colors.gray600,
                    modifier = Modifier.padding(top = screenHeightDp(12.dp), bottom = screenHeightDp(16.dp))
                )
            }

            if (certificationList.isEmpty()) {
            } else {
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
}

@Preview(showBackground = true)
@Composable
private fun PreviewSearchScreen() {
    CERTITheme {
        SearchScreen(
            value = "",
            onValueChange = {},
            onSearchClick = {},
            certificationList = listOf(),
            searchState = false,
            onLikeClick = {}
        )
    }
}
