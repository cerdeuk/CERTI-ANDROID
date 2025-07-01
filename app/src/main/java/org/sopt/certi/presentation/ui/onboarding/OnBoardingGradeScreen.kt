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
fun OnBoardingGradeRoute(
    padding: PaddingValues,
    navigateToTrack: () -> Unit,
    viewModel: OnBoardingViewModel
) {
    OnBoardingGradeScreen(
        navigateToTrack = navigateToTrack,
        modifier = Modifier.padding(padding)
    )
}

@Composable
fun OnBoardingGradeScreen(
    navigateToTrack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = "OnBoardingGrade",
        modifier = Modifier.noRippleClickable(navigateToTrack)
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewOnBoardingGradeScreen() {
    CERTITheme {
        OnBoardingGradeScreen(
            navigateToTrack = {}
        )
    }
}