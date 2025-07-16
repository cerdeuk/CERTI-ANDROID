package org.sopt.certi.presentation.ui.workExperience

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.sopt.certi.domain.usecase.career.AddCareerUseCase
import org.sopt.certi.presentation.ui.workExperience.state.AddWorkExperienceUiState
import javax.inject.Inject

@HiltViewModel
class AddWorkExperienceViewModel @Inject constructor(
    private val addCareerUseCase: AddCareerUseCase
) : ViewModel() {
    private val _startDate = MutableStateFlow("")
    private val _endDate = MutableStateFlow("")
    private val _organizationValue = MutableStateFlow("")
    private val _roleValue = MutableStateFlow("")
    private val _descriptionValue = MutableStateFlow("")

    private val _addCareerSuccess = MutableStateFlow<Boolean>(false)
    val addCareerSuccess: StateFlow<Boolean> = _addCareerSuccess

    val addWorkExperienceUiState: StateFlow<AddWorkExperienceUiState> =
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
                addButtonEnabled = listOf(
                    startDate,
                    endDate,
                    organization,
                    role,
                    description
                ).all { it.isNotBlank() }
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = AddWorkExperienceUiState(
                startDate = "",
                endDate = "",
                organizationValue = "",
                roleValue = "",
                descriptionValue = "",
                addButtonEnabled = false
            )
        )

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

    fun addCareer() {
        viewModelScope.launch {
            addCareerUseCase(
                startAt = _startDate.value,
                endAt = _endDate.value,
                place = _organizationValue.value,
                name = _roleValue.value,
                description = _descriptionValue.value
            ).fold(
                onSuccess = {
                    _addCareerSuccess.value = true
                },
                onFailure = {
                    _addCareerSuccess.value = false
                }
            )
        }
    }

    fun resetAddCareerSuccess() {
        _addCareerSuccess.value = false
    }
}
