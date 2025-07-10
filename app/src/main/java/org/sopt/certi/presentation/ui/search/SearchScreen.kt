package org.sopt.certi.presentation.ui.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.sopt.certi.R
import org.sopt.certi.core.component.section.CertificationListSection
import org.sopt.certi.core.component.textfield.CertiBasicTextField
import org.sopt.certi.core.util.heightForScreenPercentage
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.showIf
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.domain.model.ResumeData
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
            listOf<ResumeData>(
                ResumeData(
                    certificationId = 1,
                    isFavorite = false,
                    certificationName = "정보처리기사",
                    tags = listOf("컴퓨터공학", "시각디자인", "경영"),
                    agencyName = "국가기술자격",
                    applicationMethod = "실기형"
                ),
                ResumeData(
                    certificationId = 1,
                    isFavorite = false,
                    certificationName = "정보처리",
                    tags = listOf("뿡뿡", "빵빵", "IT"),
                    agencyName = "한국정보처리기관",
                    applicationMethod = "실기형"
                ),
                ResumeData(
                    certificationId = 1,
                    isFavorite = false,
                    certificationName = "정보처리기사",
                    tags = listOf("컴퓨터공학", "시각디자인", "경영"),
                    agencyName = "국가기술자격",
                    applicationMethod = "실기형"
                ),
                ResumeData(
                    certificationId = 1,
                    isFavorite = false,
                    certificationName = "정보처리",
                    tags = listOf("뿡뿡", "빵빵", "IT"),
                    agencyName = "한국정보처리기관",
                    applicationMethod = "실기형"
                ),
                ResumeData(
                    certificationId = 1,
                    isFavorite = false,
                    certificationName = "정보처리기사",
                    tags = listOf("컴퓨터공학", "시각디자인", "경영"),
                    agencyName = "국가기술자격",
                    applicationMethod = "실기형"
                ),
                ResumeData(
                    certificationId = 1,
                    isFavorite = false,
                    certificationName = "정보처리",
                    tags = listOf("뿡뿡", "빵빵", "IT"),
                    agencyName = "한국정보처리기관",
                    applicationMethod = "실기형"
                ),
                ResumeData(
                    certificationId = 1,
                    isFavorite = false,
                    certificationName = "정보처리기사",
                    tags = listOf("컴퓨터공학", "시각디자인", "경영"),
                    agencyName = "국가기술자격",
                    applicationMethod = "실기형"
                ),
                ResumeData(
                    certificationId = 1,
                    isFavorite = false,
                    certificationName = "정보처리",
                    tags = listOf("뿡뿡", "빵빵", "IT"),
                    agencyName = "한국정보처리기관",
                    applicationMethod = "실기형"
                ),
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
                if (i == index) item.copy(isFavorite = !item.isFavorite) else item
            }
        },
        certificationList = emptyList(),
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
    certificationList: List<ResumeData>,
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
                item {
                    Column(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(vertical = screenHeightDp(60.dp)),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(R.drawable.img_empty),
                            contentDescription = null,
                            modifier = Modifier
                                .widthForScreenPercentage(130.dp)
                                .heightForScreenPercentage(100.dp)
                        )
                        Spacer(modifier = Modifier.height(screenHeightDp(20.dp)))

                        Text(
                            text = buildAnnotatedString {
                                append(
                                    stringResource(R.string.search_empty_description, value)
                                )

                                addStyle(
                                    style = SpanStyle(
                                        color = CertiTheme.colors.purpleBlue,
                                        fontSize = CertiTheme.typography.caption.regular_14.fontSize,
                                        fontWeight = FontWeight.Normal
                                    ),
                                    start = 0,
                                    end = value.length
                                )

                                addStyle(
                                    style = SpanStyle(
                                        color = CertiTheme.colors.gray400,
                                        fontSize = CertiTheme.typography.caption.regular_14.fontSize,
                                        fontWeight = FontWeight.Normal
                                    ),
                                    start = value.length + 1,
                                    end = stringResource(R.string.search_empty_description).length
                                )
                            },
                            style = CertiTheme.typography.caption.regular_14,
                            color = CertiTheme.colors.gray400
                        )
                    }
                }
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
