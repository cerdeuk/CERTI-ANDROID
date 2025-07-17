package org.sopt.certi.presentation.ui.workExperience

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
import org.sopt.certi.domain.usecase.career.DeleteCareerUseCase
import org.sopt.certi.domain.usecase.career.GetCareerListUseCase
import org.sopt.certi.presentation.ui.workExperience.sideEffect.WorkExperienceSideEffect
import org.sopt.certi.presentation.ui.workExperience.state.WorkExperienceUiState
import javax.inject.Inject

@HiltViewModel
class WorkExperienceViewModel @Inject constructor(
    private val getCareerListUseCase: GetCareerListUseCase,
    private val deleteCareerUseCase: DeleteCareerUseCase
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

    fun getWorkExperienceList() = viewModelScope.launch {
        _experienceListLoadState.value = UiState.Loading
        getCareerListUseCase.invoke().fold(
            onSuccess = {
                val experienceList = it
                _experienceListLoadState.emit(UiState.Success(experienceList))
            },
            onFailure = {
                _experienceListLoadState.emit(UiState.Failure(it.message.toString()))
            }
        )
    }

    fun onDeleteClick(selectedId: Long) = viewModelScope.launch {
        _selectedId.value = selectedId
        _sideEffect.send(WorkExperienceSideEffect.showDeleteDialog)
    }

    fun onDeleteConfirmClick() = viewModelScope.launch {
        _selectedId.value?.let {
            deleteCareerUseCase.invoke(it).fold(
                onSuccess = {
                    _selectedId.value = null
                    getWorkExperienceList()
                },
                onFailure = {
                    _selectedId.value = null
                }
            )
        }
    }

    fun onDeleteDismissClick() {
        _selectedId.value = null
    }
}
