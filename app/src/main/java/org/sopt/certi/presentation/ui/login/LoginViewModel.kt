package org.sopt.certi.presentation.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.auth.model.OAuthToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.certi.core.network.TokenManager
import org.sopt.certi.domain.usecase.SignInUseCase
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
                onSuccess = {
                    onSuccess(it.needSignUp)
                    tokenManager.savePreSignupToken(it.preSignupToken)
                    tokenManager.saveUserInformation(it.userInformation)
                },
                onFailure = {
                    Timber.e(it)
                    onFailure(it)
                }
            )
        }
    }
}
