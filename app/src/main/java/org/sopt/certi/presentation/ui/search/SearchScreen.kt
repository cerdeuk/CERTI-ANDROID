package org.sopt.certi.presentation.ui.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
    viewModel: SearchViewModel = hiltViewModel()
) {
    val uiState by viewModel.searchListUiState.collectAsStateWithLifecycle()

    SearchScreen(
        searchUiState = uiState,
        onValueChange = viewModel::onKeywordChange,
        onSearchClick = { viewModel.getSearchCertificationList(uiState.keyword) },
        certificationList = (uiState.searchCertificationListLoadState as? UiState.Success)?.data.orEmpty().toImmutableList(),
        onLikeClick = viewModel::onLikeClick,
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
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(top = screenHeightDp(24.dp), start = screenWidthDp(20.dp), end = screenWidthDp(20.dp))
            .fillMaxSize()
    ) {
        CertiBasicTextField(
            value = searchUiState.keyword,
            onValueChange = onValueChange,
            onSearchClick = onSearchClick,
            modifier = Modifier.padding(vertical = screenHeightDp(12.dp))
        )

        when (searchUiState.loadState) {
            is UiState.Init -> {}
            is UiState.Success -> {
                SearchSuccessScreen(
                    certificationList = certificationList,
                    onLikeClick = onLikeClick
                )
            }

            is UiState.Empty -> {
                Column {
                    Text(
                        text = stringResource(id = R.string.search_result_title, 0),
                        style = CertiTheme.typography.caption.regular_14,
                        color = CertiTheme.colors.gray600,
                        modifier = Modifier.padding(top = screenHeightDp(12.dp), bottom = screenHeightDp(16.dp))
                    )
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
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
                            modifier = Modifier.align(Alignment.CenterHorizontally)
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
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        item {
            Text(
                text = stringResource(id = R.string.search_result_title, certificationList.size),
                style = CertiTheme.typography.caption.regular_14,
                color = CertiTheme.colors.gray600,
                modifier = Modifier.padding(top = screenHeightDp(12.dp), bottom = screenHeightDp(16.dp))
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
                    // 상세페이지로 화면 전환
                },
                modifier = Modifier.padding(bottom = screenHeightDp(12.dp))
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSearchScreen() {
    CERTITheme {
    }
}
