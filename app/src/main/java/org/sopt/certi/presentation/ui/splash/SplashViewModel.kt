package org.sopt.certi.presentation.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {
    private val _navigateToLogin = MutableStateFlow(false)
    val navigateToLogin = _navigateToLogin.asStateFlow()

    init {
        viewModelScope.launch {
            delay(3000)
            _navigateToLogin.value = true
        }
    }
}
