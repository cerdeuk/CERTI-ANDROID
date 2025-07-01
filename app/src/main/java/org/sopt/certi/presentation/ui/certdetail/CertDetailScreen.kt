package org.sopt.certi.presentation.ui.certdetail

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import org.sopt.certi.ui.theme.CERTITheme

@Composable
fun CertDetailRoute(
    padding: PaddingValues,
    viewModel: CertDetailViewModel = hiltViewModel()
) {
    CertDetailScreen()
}

@Composable
fun CertDetailScreen(
    modifier: Modifier = Modifier
) {
    Text(text = "CertDetail")
}

@Preview(showBackground = true)
@Composable
private fun PreviewCertDetailScreen() {
    CERTITheme {
        CertDetailScreen()
    }
}