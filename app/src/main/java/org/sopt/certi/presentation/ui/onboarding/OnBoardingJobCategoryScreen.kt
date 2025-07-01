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
fun OnBoardingJobCategoryRoute(
    padding: PaddingValues,
    navigateToOnBoardingInfo: () -> Unit,
    viewModel: OnBoardingViewModel
) {
    OnBoardingJobCategoryScreen(
        navigateToOnBoardingInfo = navigateToOnBoardingInfo,
        modifier = Modifier.padding(padding)
    )
}

@Composable
fun OnBoardingJobCategoryScreen(
    navigateToOnBoardingInfo: () -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = "OnBoardingJobCategory",
        modifier = Modifier.noRippleClickable(navigateToOnBoardingInfo)
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewOnBoardingJobCategoryScreen() {
    CERTITheme {
        OnBoardingJobCategoryScreen(
            navigateToOnBoardingInfo = {}
        )
    }
}