package org.sopt.certi.presentation.ui.certlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.presentation.ui.certlist.component.CategoryBar
import org.sopt.certi.presentation.ui.certlist.component.CategoryFavoriteButton
import org.sopt.certi.presentation.ui.certlist.component.CategoryTopBar
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun CertListRoute(
    padding: PaddingValues,
    viewModel: CertListViewModel = hiltViewModel()
) {
    var selectedIndex by remember { mutableIntStateOf(0) }
    var isFavorite by remember { mutableStateOf(false) }

    CertListScreen(
        selectedCategory = selectedIndex,
        onCategorySelected = { selectedIndex = it },
        isFavorite = isFavorite,
        onFavoriteClick = { isFavorite = !isFavorite },
        modifier = Modifier.padding(padding)
    )
}

@Composable
private fun CertListScreen(
    selectedCategory: Int,
    onCategorySelected: (Int) -> Unit,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        CategoryTopBar(
            onClick = {},
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
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCertListScreen() {
    CERTITheme {
        CertListScreen(
            selectedCategory = 0,
            onCategorySelected = {},
            isFavorite = false,
            onFavoriteClick = {}
        )
    }
}
