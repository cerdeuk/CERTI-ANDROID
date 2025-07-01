package org.sopt.certi.presentation.ui.onboarding

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.ui.theme.CERTITheme

@Composable
fun OnBoardingMajorRoute(
    padding: PaddingValues,
    navigateToJobCategory: () -> Unit,
    viewModel: OnBoardingViewModel
) {
    OnBoardingMajorScreen(
        navigateToJobCategory = navigateToJobCategory,
        modifier = Modifier.padding(padding)
    )
}

@Composable
fun OnBoardingMajorScreen(
    navigateToJobCategory: () -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = "OnBoardingMajor",
        modifier = Modifier.noRippleClickable(navigateToJobCategory)
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewOnBoardingMajorScreen() {
    CERTITheme {
        OnBoardingMajorScreen(
            navigateToJobCategory = {}
        )
    }
}
