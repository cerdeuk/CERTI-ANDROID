package org.sopt.certi.presentation.ui.workExperience

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.sopt.certi.domain.usecase.career.EditCareerUseCase
import org.sopt.certi.domain.usecase.career.GetCareerListUseCase
import org.sopt.certi.presentation.ui.workExperience.state.AddWorkExperienceUiState
import javax.inject.Inject

@HiltViewModel
class EditWorkExperienceViewModel @Inject constructor(
    private val getCareerListUseCase: GetCareerListUseCase,
    private val editCareerUseCase: EditCareerUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val activityId: Long = checkNotNull(savedStateHandle["activityId"])

    private val _startDate = MutableStateFlow("")
    private val _endDate = MutableStateFlow("")
    private val _organizationValue = MutableStateFlow("")
    private val _roleValue = MutableStateFlow("")
    private val _descriptionValue = MutableStateFlow("")

    private val _editCareerSuccess = MutableStateFlow(false)
    val editCareerSuccess: StateFlow<Boolean> = _editCareerSuccess

    val uiState: StateFlow<AddWorkExperienceUiState> =
        combine(
            _startDate,
            _endDate,
            _organizationValue,
            _roleValue,
            _descriptionValue
        ) { startDate, endDate, organization, role, description ->
            AddWorkExperienceUiState(
                startDate = startDate,
                endDate = endDate,
                organizationValue = organization,
                roleValue = role,
                descriptionValue = description,
                addButtonEnabled = listOf(startDate, endDate, organization, role, description).all { it.isNotBlank() }
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = AddWorkExperienceUiState("", "", "", "", "", false)
        )

    init {
        loadInitial()
    }

    private fun loadInitial() {
        viewModelScope.launch {
            getCareerListUseCase.invoke().fold(
                onSuccess = { list ->
                    val item = list.find { it.activityId == activityId }
                    if (item != null) {
                        _startDate.value = item.startAt
                        _endDate.value = item.endAt
                        _organizationValue.value = item.organization
                        _roleValue.value = item.role
                        _descriptionValue.value = item.description
                    } else {
                        _editCareerSuccess.value = false
                    }
                },
                onFailure = {
                    _editCareerSuccess.value = false
                }
            )
        }
    }

    fun onStartDateChanged(value: String) {
        _startDate.value = value
    }

    fun onEndDateChanged(value: String) {
        _endDate.value = value
    }

    fun onOrganizationChanged(value: String) {
        _organizationValue.value = value
    }

    fun onRoleChanged(value: String) {
        _roleValue.value = value
    }

    fun onDescriptionChanged(value: String) {
        _descriptionValue.value = value
    }

    fun editCareer() {
        viewModelScope.launch {
            editCareerUseCase(
                careerId = activityId,
                startAt = _startDate.value,
                endAt = _endDate.value,
                place = _organizationValue.value,
                name = _roleValue.value,
                description = _descriptionValue.value
            ).fold(
                onSuccess = {
                    _editCareerSuccess.value = true
                },
                onFailure = {
                    _editCareerSuccess.value = false
                }
            )
        }
    }

    fun resetEditCareerSuccess() {
        _editCareerSuccess.value = false
    }
}
