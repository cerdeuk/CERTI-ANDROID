package org.sopt.certi.presentation.ui.certlist

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import org.sopt.certi.ui.theme.CERTITheme

@Composable
fun CertListRoute(
    padding: PaddingValues,
    viewModel: CertListViewModel = hiltViewModel()
) {
    CertListScreen(
        modifier = Modifier.padding(padding)
    )
}

@Composable
fun CertListScreen(
    modifier: Modifier = Modifier
) {
    Text(
        text = "CertList"
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewCertListScreen() {
    CERTITheme {
        CertListScreen()
    }
}
