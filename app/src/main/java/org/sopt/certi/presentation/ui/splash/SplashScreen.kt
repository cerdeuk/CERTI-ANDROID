package org.sopt.certi.presentation.ui.splash

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import org.sopt.certi.ui.theme.CERTITheme

@Composable
fun SplashRoute(
    padding: PaddingValues,
    navigateToLogin: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val navigateToLoginState by viewModel.navigateToLogin.collectAsState()

    LaunchedEffect(navigateToLoginState) {
        if (navigateToLoginState) {
            navigateToLogin()
        }
    }
    SplashScreen(padding = padding)
}

@Composable
fun SplashScreen(
    padding: PaddingValues
) {
    Text(text = "Splash")
}

@Preview(showBackground = true)
@Composable
private fun PreviewSplashScreen() {
    CERTITheme {
        SplashScreen(padding = PaddingValues())
    }
}