package org.sopt.certi.presentation.ui.certlist

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import org.sopt.certi.core.component.section.CertificationListSection
import org.sopt.certi.core.state.UiState
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.domain.model.CertificationData
import org.sopt.certi.presentation.ui.certlist.component.CategoryBar
import org.sopt.certi.presentation.ui.certlist.component.CategoryFavoriteButton
import org.sopt.certi.presentation.ui.certlist.component.CategoryTopBar
import org.sopt.certi.presentation.ui.certlist.state.CertListUiState
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun CertListRoute(
    padding: PaddingValues,
    navigateToSearch: () -> Unit,
    viewModel: CertListViewModel = hiltViewModel()
) {
    val uiState by viewModel.certificationListUiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.selectedCategory, uiState.isFavorite) {
        viewModel.getCertificationList(uiState.isFavorite, uiState.selectedCategory)
    }

    when (uiState.loadState) {
        is UiState.Success -> CertListScreen(
            certListState = uiState,
            navigateToSearch = navigateToSearch,
            onCategorySelected = viewModel::onCategorySelected,
            onFavoriteButtonClick = viewModel::onFavoriteClick,
            certificationList = (uiState.certificationListLoadState as UiState.Success).data.toImmutableList(),
            onLikeClick = viewModel::onLikeClick,
            modifier = Modifier.padding(padding)
        )
        is UiState.Failure -> {}
        is UiState.Loading -> {}
        is UiState.Empty -> {}
        is UiState.Init -> {}
    }
}

@Composable
private fun CertListScreen(
    certListState: CertListUiState,
    navigateToSearch: () -> Unit,
    onCategorySelected: (Int) -> Unit,
    onFavoriteButtonClick: () -> Unit,
    certificationList: ImmutableList<CertificationData>,
    onLikeClick: (Long) -> Unit,
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
            selectedCategory = certListState.selectedCategory,
            onCategorySelected = onCategorySelected
        )
        HorizontalDivider(
            color = CertiTheme.colors.gray100,
            thickness = screenWidthDp(1.dp)
        )

        CategoryFavoriteButton(
            onClick = onFavoriteButtonClick,
            isFavorite = certListState.isFavorite,
            modifier = Modifier.padding(horizontal = screenWidthDp(20.dp), vertical = screenHeightDp(12.dp))
        )

        LazyColumn(
            modifier = Modifier
                .padding(horizontal = screenWidthDp(20.dp))
                .fillMaxSize()
        ) {
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
}

@Preview(showBackground = true)
@Composable
private fun PreviewCertListScreen() {
    CERTITheme {
    }
}
