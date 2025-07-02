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
fun OnBoardingUnivRoute(
    padding: PaddingValues,
    navigateToGrade: () -> Unit,
    viewModel: OnBoardingViewModel
) {
    OnBoardingUnivScreen(
        navigateToGrade = navigateToGrade,
        modifier = Modifier.padding(padding)
    )
}

@Composable
fun OnBoardingUnivScreen(
    navigateToGrade: () -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = "OnBoardingUniv",
        modifier = Modifier.noRippleClickable(navigateToGrade)
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewOnBoardingUnivScreen() {
    CERTITheme {
        OnBoardingUnivScreen(
            navigateToGrade = {}
        )
    }
}
