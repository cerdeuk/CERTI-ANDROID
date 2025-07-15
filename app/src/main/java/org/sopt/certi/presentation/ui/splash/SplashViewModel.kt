package org.sopt.certi.presentation.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.certi.core.network.TokenManager
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val tokenManager: TokenManager
) : ViewModel() {
    private val _navigateToLogin = MutableStateFlow(false)
    val navigateToLogin = _navigateToLogin.asStateFlow()

    private val _navigateToHome = MutableStateFlow(false)
    val navigateToHome = _navigateToHome.asStateFlow()

    init {
        autoLogin()
    }

    private fun autoLogin(){
        val accessToken = tokenManager.getToken()
        val refreshToken = tokenManager.getRefreshToken()

        if(accessToken.isNotEmpty() && refreshToken.isNotEmpty()) {
            viewModelScope.launch {
                delay(3000)
                _navigateToHome.value = true
            }
        }else{
            viewModelScope.launch {
                delay(3000)
                _navigateToLogin.value = true
            }
        }
    }
}
