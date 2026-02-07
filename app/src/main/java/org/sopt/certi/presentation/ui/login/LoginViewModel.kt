package org.sopt.certi.presentation.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.auth.model.OAuthToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.certi.core.network.TokenManager
import org.sopt.certi.domain.usecase.auth.SignInUseCase
import org.sopt.certi.presentation.type.SocialLoginType
import timber.log.Timber
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val tokenManager: TokenManager
) : ViewModel() {
    fun signInWithKakaoToken(
        token: OAuthToken,
        onSuccess: (needSignUp: Boolean) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        viewModelScope.launch {
            signInUseCase(token.accessToken, SocialLoginType.KAKAO.name.lowercase(Locale.ROOT)).fold(
                onSuccess = { result ->
                    onSuccess(result.needSignUp)
                    if (result.needSignUp) {
                        tokenManager.savePreSignupToken(result.preSignupToken)
                        tokenManager.saveUserInformation(result.userInformation)
                    } else {
                        result.jwtResponse?.let {
                            tokenManager.saveToken(it.accessToken)
                            tokenManager.saveRefreshToken(it.refreshToken)
                        }
                    }
                },
                onFailure = {
                    Timber.e(it)
                    onFailure(it)
                }
            )
        }
    }

    fun signInWithGoogleIdToken(
        idToken: String,
        onSuccess: (needSignUp: Boolean) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        viewModelScope.launch {
            signInUseCase(idToken, SocialLoginType.GOOGLE.name.lowercase(Locale.ROOT)).fold(
                onSuccess = { result ->
                    onSuccess(result.needSignUp)
                    if (result.needSignUp) {
                        tokenManager.savePreSignupToken(result.preSignupToken)
                        tokenManager.saveUserInformation(result.userInformation)
                    } else {
                        result.jwtResponse?.let {
                            tokenManager.saveToken(it.accessToken)
                            tokenManager.saveRefreshToken(it.refreshToken)
                        }
                    }
                },
                onFailure = {
                    Timber.e(it)
                    onFailure(it)
                }
            )
        }
    }
}
