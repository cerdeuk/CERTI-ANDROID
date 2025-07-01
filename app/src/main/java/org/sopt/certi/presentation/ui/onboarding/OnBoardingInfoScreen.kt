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
fun OnBoardingInfoRoute(
    padding: PaddingValues,
    navigateToHome: () -> Unit,
    viewModel: OnBoardingViewModel
) {
    OnBoardingInfoScreen(
        navigateToHome = navigateToHome,
        modifier = Modifier.padding(padding)
    )
}

@Composable
fun OnBoardingInfoScreen(
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = "OnBoardingInfo",
        modifier = Modifier.noRippleClickable(navigateToHome)
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewOnBoardingOnBoardingInfoScreen() {
    CERTITheme {
        OnBoardingInfoScreen(
            navigateToHome = {}
        )
    }
}
