package org.sopt.certi.presentation.ui.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import org.sopt.certi.domain.usecase.DummyUseCase
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val dummyUseCase: DummyUseCase
) : ViewModel() {
    fun loginWithKakao(context: Context) {
        viewModelScope.launch {
            runCatching {
                suspendCancellableCoroutine { continuation ->
                    performKakaoLogin(context) { token, error ->
                        when {
                            error != null -> continuation.resumeWithException(error)
                            token != null -> continuation.resume(token)
                        }
                    }
                }
            }.onSuccess {
            }.onFailure {
            }
        }
    }

    fun performKakaoLogin(context: Context, callback: (OAuthToken?, Throwable?) -> Unit) {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                if (error is ClientError && error.reason == ClientErrorCause.Cancelled) return@loginWithKakaoTalk
                if (token != null) callback(token, null) else UserApiClient.instance.loginWithKakaoAccount(context = context, callback = callback)
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(context = context, callback = callback)
        }
    }
}
