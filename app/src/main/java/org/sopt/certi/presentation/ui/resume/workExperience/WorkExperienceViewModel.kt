package org.sopt.certi.presentation.ui.resume.workExperience

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.sopt.certi.core.state.UiState
import org.sopt.certi.domain.model.ActivityData
import org.sopt.certi.domain.usecase.DummyUseCase
import org.sopt.certi.presentation.ui.resume.workExperience.sideEffect.WorkExperienceSideEffect
import org.sopt.certi.presentation.ui.resume.workExperience.state.WorkExperienceUiState
import javax.inject.Inject

@HiltViewModel
class WorkExperienceViewModel @Inject constructor(
    private val dummyUseCase: DummyUseCase
) : ViewModel() {
    private val _experienceListLoadState = MutableStateFlow<UiState<List<ActivityData>>>(UiState.Loading)
    private val _selectedId = MutableStateFlow<Long?>(null)

    val workExperienceUiState: StateFlow<WorkExperienceUiState> =
        combine(
            _experienceListLoadState,
            _selectedId
        ) { experienceList, selectedId ->
            WorkExperienceUiState(
                experienceListLoadState = experienceList,
                selectedId = selectedId
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = WorkExperienceUiState(
                experienceListLoadState = UiState.Loading,
                selectedId = null
            )
        )

    private val _sideEffect = Channel<WorkExperienceSideEffect>()
    val sideEffect = _sideEffect.receiveAsFlow()

    fun getWorkExperienceList() {
        val resumeDataList = {
            emptyList<ActivityData>()
        }
        _experienceListLoadState.value = UiState.Success(resumeDataList())
    }

    fun onDeleteClick(selectedId: Long) = viewModelScope.launch {
        _selectedId.value = selectedId
        _sideEffect.send(WorkExperienceSideEffect.showDeleteDialog)
    }

    fun onDeleteConfirmclick() {
        val resumeDataList = {
            emptyList<ActivityData>()
        }
        _selectedId.value = null
        _experienceListLoadState.value = UiState.Success(resumeDataList())
    }
}
