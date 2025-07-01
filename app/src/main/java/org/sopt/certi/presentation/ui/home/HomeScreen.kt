package org.sopt.certi.presentation.ui.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import org.sopt.certi.ui.theme.CERTITheme

@Composable
fun HomeRoute(
    padding: PaddingValues,
    viewModel: HomeViewModel = hiltViewModel()
) {
    HomeScreen()
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    Text(text = "Home")
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeScreen() {
    CERTITheme {
        HomeScreen()
    }
}
