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
import androidx.compose.ui.unit.dp
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
import timber.log.Timber

@Composable
fun LoginRoute(
    padding: PaddingValues,
    navigateToOnBoarding: () -> Unit,
    navigateToHome: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel(),
    kakaoLoginManager: KakaoLoginManager = KakaoLoginManager()
) {
    val context = LocalContext.current

    LoginScreen(
        padding = padding,
        onKakaoLoginClick = {
            kakaoLoginManager.login(context) { result ->
                result.onSuccess { token ->
                    viewModel.signInWithKakaoToken(
                        token = token,
                        onSuccess = { needSignUp ->
                            if (needSignUp) {
                                navigateToOnBoarding()
                            } else {
                                navigateToHome()
                            }
                        },
                        onFailure = { error ->
                            Timber.e(error)
                        }
                    )
                }.onFailure { error ->
                    Timber.e(error)
                }
            }
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
            .padding(vertical = screenHeightDp(94.dp), horizontal = screenWidthDp(18.dp)),
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
                    .padding(bottom = screenHeightDp(16.dp))
                    .heightForScreenPercentage(44.dp)
                    .widthForScreenPercentage(130.dp),
                contentScale = ContentScale.Fit
            )

            Text(
                text = stringResource(R.string.login_description),
                style = CertiTheme.typography.caption.regular_14,
                color = CertiTheme.colors.gray400
            )
            Spacer(modifier = Modifier.height(screenHeightDp(32.dp)))

            BouncingImage(
                resId = R.drawable.img_bouncing_login
            )

            Image(
                painter = painterResource(id = R.drawable.img_login_shadow),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = screenHeightDp(24.dp))
                    .heightForScreenPercentage(20.dp)
                    .widthForScreenPercentage(170.dp)
            )
        }

        Spacer(Modifier.height(screenHeightDp(32.dp)))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SocialLoginButton(
                socialLoginType = SocialLoginType.KAKAO,
                modifier = Modifier.fillMaxWidth(),
                onButtonClick = onKakaoLoginClick
            )
            Spacer(modifier = Modifier.height(screenHeightDp(16.dp)))
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
