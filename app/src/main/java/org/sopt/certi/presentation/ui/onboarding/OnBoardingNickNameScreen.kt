package org.sopt.certi.presentation.ui.onboarding

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.sopt.certi.ui.theme.CERTITheme

@Composable
fun OnBoardingNickNameRoute(
    padding: PaddingValues,
    navigateToOnBoardingInfo: () -> Unit,
    viewModel: OnBoardingViewModel
) {
    val signUpSuccess by viewModel.signUpSuccess.collectAsStateWithLifecycle()
    val userInfo by viewModel.userInfo.collectAsStateWithLifecycle()

    LaunchedEffect(signUpSuccess, userInfo) {
        if (signUpSuccess && userInfo != null) {
            navigateToOnBoardingInfo()
        }
    }

    OnBoardingNickNameScreen(
        onSignUpClick = { viewModel.postSignUp() },
        modifier = Modifier.padding(padding)
    )
}

@Composable
fun OnBoardingNickNameScreen(
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier
) {

}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    CERTITheme {
        OnBoardingNickNameScreen(
            onSignUpClick = {}
        )
    }
}