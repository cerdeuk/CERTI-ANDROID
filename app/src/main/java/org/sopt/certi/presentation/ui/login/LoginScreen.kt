package org.sopt.certi.presentation.ui.login

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.ui.theme.CERTITheme

@Composable
fun LoginRoute(
    padding: PaddingValues,
    navigateToOnBoarding: () -> Unit,
    navigateToHome: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    LoginScreen(
        padding = padding,
        navigateToOnBoarding = navigateToOnBoarding,
        navigateToHome = navigateToHome
    )
}

@Composable
fun LoginScreen(
    padding: PaddingValues,
    navigateToOnBoarding: () -> Unit,
    navigateToHome: () -> Unit
) {
    Text(
        text = "Login",
        modifier = Modifier.noRippleClickable(navigateToOnBoarding)
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeScreen() {
    CERTITheme {
        LoginScreen(
            padding = PaddingValues(),
            navigateToOnBoarding = {},
            navigateToHome = {}
        )
    }
}