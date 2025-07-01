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
fun OnBoardingTrackRoute(
    padding: PaddingValues,
    navigateToMajor: () -> Unit,
    viewModel: OnBoardingViewModel
) {
    OnBoardingTrackScreen(
        navigateToMajor = navigateToMajor,
        modifier = Modifier.padding(padding)
    )
}

@Composable
fun OnBoardingTrackScreen(
    navigateToMajor: () -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = "OnBoardingTrack",
        modifier = Modifier.noRippleClickable(navigateToMajor)
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewOnBoardingTrackScreen() {
    CERTITheme {
        OnBoardingTrackScreen(
            navigateToMajor = {}
        )
    }
}
