package org.sopt.certi.presentation.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.sopt.certi.R
import org.sopt.certi.core.util.heightForScreenPercentage
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun SplashRoute(
    padding: PaddingValues,
    navigateToLogin: () -> Unit,
    navigateToHome: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val navigateToLoginState by viewModel.navigateToLogin.collectAsState()
    val navigateToHomeState by viewModel.navigateToHome.collectAsState()

    LaunchedEffect(navigateToLoginState) {
        if (navigateToLoginState) navigateToLogin()
    }

    LaunchedEffect(navigateToHomeState) {
        if (navigateToHomeState) navigateToHome()
    }

    SplashScreen(padding = padding)
}

@Composable
fun SplashScreen(
    padding: PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .background(color = CertiTheme.colors.purpleBlue),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_splash_logo),
            contentDescription = null,
            modifier = Modifier
                .padding(top = screenHeightDp(220.dp))
                .heightForScreenPercentage(104.dp)
                .widthForScreenPercentage(266.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSplashScreen() {
    CERTITheme {
        SplashScreen(padding = PaddingValues())
    }
}
