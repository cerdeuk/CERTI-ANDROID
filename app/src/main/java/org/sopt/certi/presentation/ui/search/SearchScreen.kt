package org.sopt.certi.presentation.ui.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import org.sopt.certi.R
import org.sopt.certi.core.component.section.CertificationListSection
import org.sopt.certi.core.component.textfield.CertiBasicTextField
import org.sopt.certi.core.state.UiState
import org.sopt.certi.core.util.heightForScreenPercentage
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.presentation.ui.search.state.SearchUiState
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun SearchRoute(
    padding: PaddingValues,
    navigateToCertDetail: (certId: Long) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val uiState by viewModel.searchListUiState.collectAsStateWithLifecycle()

    SearchScreen(
        searchUiState = uiState,
        onValueChange = viewModel::onKeywordChange,
        onSearchClick = { viewModel.getSearchCertificationList(uiState.keyword) },
        certificationList = (uiState.searchCertificationListLoadState as? UiState.Success)?.data.orEmpty().toImmutableList(),
        onLikeClick = viewModel::onLikeClick,
        navigateToCertDetail = navigateToCertDetail,
        modifier = Modifier.padding(padding)
    )
}

@Composable
private fun SearchScreen(
    searchUiState: SearchUiState,
    onValueChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    certificationList: ImmutableList<CertificationData>,
    onLikeClick: (Long) -> Unit,
    navigateToCertDetail: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(top = screenHeightDp(24.dp))
            .fillMaxSize()
    ) {
        CertiBasicTextField(
            value = searchUiState.keyword,
            onValueChange = onValueChange,
            onSearchClick = onSearchClick,
            modifier = Modifier.padding(vertical = screenHeightDp(12.dp), horizontal = screenWidthDp(20.dp)),
            maxLength = 20
        )

        when (searchUiState.loadState) {
            is UiState.Init -> {}
            is UiState.Success -> {
                SearchSuccessScreen(
                    certificationList = certificationList,
                    onLikeClick = onLikeClick,
                    navigateToCertDetail = navigateToCertDetail
                )
            }

            is UiState.Empty -> {
                Column(
                    modifier = Modifier.padding(horizontal = screenWidthDp(20.dp))
                ) {
                    val resultText = stringResource(id = R.string.search_result_title, 0)
                    val countString = "0"
                    val startIndex = resultText.indexOf(countString)
                    val endIndex = startIndex + countString.length

                    Text(
                        text = buildAnnotatedString {
                            append(resultText)
                            if (startIndex >= 0) {
                                addStyle(
                                    style = SpanStyle(
                                        color = CertiTheme.colors.purpleBlue,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    start = startIndex,
                                    end = endIndex
                                )
                            }
                        },
                        style = CertiTheme.typography.caption.regular_14,
                        color = CertiTheme.colors.gray600,
                        modifier = Modifier
                            .padding(top = screenHeightDp(12.dp), bottom = screenHeightDp(16.dp))
                    )
                    Spacer(modifier = Modifier.padding(screenHeightDp(90.dp)))

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(R.drawable.img_empty),
                            contentDescription = null,
                            modifier = Modifier
                                .widthForScreenPercentage(130.dp)
                                .heightForScreenPercentage(100.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                        Spacer(modifier = Modifier.height(screenHeightDp(20.dp)))

                        val keyword = searchUiState.submittedKeyword
                        val emptyDescription = stringResource(R.string.search_empty_description, keyword)
                        val keywordLength = keyword.length
                        val totalLength = emptyDescription.length

                        Text(
                            text = buildAnnotatedString {
                                append(emptyDescription)

                                addStyle(
                                    style = SpanStyle(
                                        color = CertiTheme.colors.purpleBlue,
                                        fontSize = CertiTheme.typography.caption.regular_14.fontSize,
                                        fontWeight = FontWeight.Normal
                                    ),
                                    start = 0,
                                    end = keyword.length.coerceAtMost(totalLength)
                                )

                                if (keywordLength < totalLength) {
                                    addStyle(
                                        style = SpanStyle(
                                            color = CertiTheme.colors.gray400,
                                            fontSize = CertiTheme.typography.caption.regular_14.fontSize,
                                            fontWeight = FontWeight.Normal
                                        ),
                                        start = keywordLength,
                                        end = totalLength
                                    )
                                }
                            },
                            style = CertiTheme.typography.caption.regular_14,
                            color = CertiTheme.colors.gray400,
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            is UiState.Failure -> {}
            is UiState.Loading -> {}
        }
    }
}

@Composable
private fun SearchSuccessScreen(
    certificationList: ImmutableList<CertificationData>,
    onLikeClick: (Long) -> Unit,
    navigateToCertDetail: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        item {
            val resultText = stringResource(id = R.string.search_result_title, certificationList.size)
            val countString = certificationList.size.toString()
            val startIndex = resultText.indexOf(countString)
            val endIndex = startIndex + countString.length

            Text(
                text = buildAnnotatedString {
                    append(resultText)
                    if (startIndex >= 0) {
                        addStyle(
                            style = SpanStyle(
                                color = CertiTheme.colors.purpleBlue,
                                fontWeight = FontWeight.Bold
                            ),
                            start = startIndex,
                            end = endIndex
                        )
                    }
                },
                style = CertiTheme.typography.caption.regular_14,
                color = CertiTheme.colors.gray600,
                modifier = Modifier.padding(top = screenHeightDp(12.dp), start = screenWidthDp(20.dp))
            )
        }

        items(
            items = certificationList,
            key = { it.certificationId }
        ) { item ->
            CertificationListSection(
                certificationListData = item,
                onLikeClick = { onLikeClick(item.certificationId) },
                onCertificationClick = {
                    navigateToCertDetail(item.certificationId)
                },
                modifier = Modifier
                    .padding(top = screenHeightDp(16.dp), bottom = screenHeightDp(24.dp))
                    .padding(horizontal = 20.dp)
            )

            HorizontalDivider(
                color = CertiTheme.colors.gray100,
                thickness = screenWidthDp(1.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSearchScreen() {
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
        val uiState = SearchUiState(
            keyword = "",
            submittedKeyword = "",
            searchCertificationListLoadState = UiState.Success(certificationList)
        )

        SearchScreen(
            searchUiState = uiState,
            onValueChange = {},
            onSearchClick = {},
            certificationList = (uiState.searchCertificationListLoadState as? UiState.Success)?.data.orEmpty().toImmutableList(),
            onLikeClick = {},
            navigateToCertDetail = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewEmptyScreen() {
    CERTITheme {
        SearchScreen(
            searchUiState = SearchUiState(
                keyword = "",
                submittedKeyword = "",
                searchCertificationListLoadState = UiState.Empty
            ),
            onValueChange = {},
            onSearchClick = {},
            certificationList = emptyList<CertificationData>().toImmutableList(),
            onLikeClick = {},
            navigateToCertDetail = {}
        )
    }
}
