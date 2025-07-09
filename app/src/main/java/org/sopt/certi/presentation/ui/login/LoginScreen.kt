package org.sopt.certi.presentation.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import org.sopt.certi.R
import org.sopt.certi.core.util.heightForScreenPercentage
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.presentation.type.SocialLoginType
import org.sopt.certi.presentation.ui.login.component.BouncingImage
import org.sopt.certi.presentation.ui.login.component.SocialLoginButton
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun LoginRoute(
    padding: PaddingValues,
    navigateToOnBoarding: () -> Unit,
    navigateToHome: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    LoginScreen(
        padding = padding,
        onKakaoLoginClick = {
//            viewModel.loginWithKakao(context) : 추후 카카오 로그인 구현 시 사용
            navigateToOnBoarding()
        },
        onGoogleLoginClick = { navigateToHome() }
    )
}

@Composable
fun LoginScreen(
    padding: PaddingValues,
    onKakaoLoginClick: () -> Unit,
    onGoogleLoginClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = screenHeightDp(0.12f), horizontal = screenWidthDp(0.05f)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_logo),
                contentDescription = null,
                modifier = Modifier
                    .padding(bottom = screenHeightDp(0.02f))
                    .heightForScreenPercentage(0.056f)
                    .widthForScreenPercentage(0.36f),
                contentScale = ContentScale.Fit
            )

            Text(
                text = stringResource(R.string.login_description),
                style = CertiTheme.typography.caption.regular_14,
                color = CertiTheme.colors.gray400
            )
            Spacer(modifier = Modifier.height(screenHeightDp(0.04f)))

            BouncingImage(
                resId = R.drawable.img_bouncing_login,
                heightRatio = 0.31f,
                widthRatio = 0.7f
            )

            Image(
                painter = painterResource(id = R.drawable.img_login_shadow),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = screenHeightDp(0.03f))
                    .heightForScreenPercentage(0.025f)
                    .widthForScreenPercentage(0.47f)
            )
        }

        Spacer(Modifier.height(screenHeightDp(0.04f)))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SocialLoginButton(
                socialLoginType = SocialLoginType.KAKAO,
                modifier = Modifier.fillMaxWidth(),
                onButtonClick = onKakaoLoginClick
            )
            Spacer(modifier = Modifier.height(screenHeightDp(0.02f)))
            SocialLoginButton(
                socialLoginType = SocialLoginType.GOOGLE,
                modifier = Modifier.fillMaxWidth(),
                onButtonClick = onGoogleLoginClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeScreen() {
    CERTITheme {
        LoginScreen(
            padding = PaddingValues(),
            onKakaoLoginClick = {},
            onGoogleLoginClick = {}
        )
    }
}
