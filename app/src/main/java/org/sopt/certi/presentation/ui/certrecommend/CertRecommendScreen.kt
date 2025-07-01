package org.sopt.certi.presentation.ui.certrecommend

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.ui.theme.CERTITheme

@Composable
fun CertRecommendRoute(
    padding: PaddingValues,
    navigateToCertDetail: () -> Unit,
    viewModel: CertRecommendViewModel = hiltViewModel()
) {
    CertRecommendScreen(
        navigateToCertDetail = navigateToCertDetail,
        modifier = Modifier.padding(padding)
    )
}

@Composable
fun CertRecommendScreen(
    navigateToCertDetail: () -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = "CertRecommend",
        modifier = Modifier.noRippleClickable(navigateToCertDetail)
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewCertRecommendScreen() {
    CERTITheme {
        CertRecommendScreen(
            navigateToCertDetail = {}
        )
    }
}
