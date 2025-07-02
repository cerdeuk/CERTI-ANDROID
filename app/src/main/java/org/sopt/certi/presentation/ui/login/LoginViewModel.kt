package org.sopt.certi.presentation.ui.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.sopt.certi.domain.usecase.DummyUseCase
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val dummyUseCase: DummyUseCase
) : ViewModel()
