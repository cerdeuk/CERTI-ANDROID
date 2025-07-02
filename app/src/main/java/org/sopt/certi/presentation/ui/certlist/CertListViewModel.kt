package org.sopt.certi.presentation.ui.certlist

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.sopt.certi.domain.usecase.DummyUseCase
import javax.inject.Inject

@HiltViewModel
class CertListViewModel @Inject constructor(
    private val dummyUseCase: DummyUseCase
) : ViewModel()
