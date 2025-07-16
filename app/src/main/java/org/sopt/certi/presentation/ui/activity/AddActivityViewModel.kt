package org.sopt.certi.presentation.ui.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.sopt.certi.domain.usecase.activity.AddActivityUseCase
import org.sopt.certi.presentation.ui.activity.state.AddActivityUiState
import javax.inject.Inject

@HiltViewModel
class AddActivityViewModel @Inject constructor(
    private val addActivityUseCase: AddActivityUseCase
) : ViewModel() {
    private val _startDate = MutableStateFlow("")
    private val _endDate = MutableStateFlow("")
    private val _organizationValue = MutableStateFlow("")
    private val _activityValue = MutableStateFlow("")
    private val _descriptionValue = MutableStateFlow("")

    private val _addActivitySuccess = MutableStateFlow<Boolean>(false)
    val addActivitySuccess: StateFlow<Boolean> = _addActivitySuccess

    val addActivityUiState: StateFlow<AddActivityUiState> =
        combine(
            _startDate,
            _endDate,
            _organizationValue,
            _activityValue,
            _descriptionValue
        ) { startDate, endDate, organization, activity, description ->
            AddActivityUiState(
                startDate = startDate,
                endDate = endDate,
                organizationValue = organization,
                activityValue = activity,
                descriptionValue = description,
                addButtonEnabled = listOf(
                    startDate,
                    endDate,
                    organization,
                    activity,
                    description
                ).all { it.isNotBlank() }
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = AddActivityUiState(
                startDate = "",
                endDate = "",
                organizationValue = "",
                activityValue = "",
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

    fun onActivityChanged(value: String) {
        _activityValue.value = value
    }

    fun onDescriptionChanged(value: String) {
        _descriptionValue.value = value
    }

    fun addActivity() {
        viewModelScope.launch {
            addActivityUseCase(
                startAt = _startDate.value,
                endAt = _endDate.value,
                place = _organizationValue.value,
                name = _activityValue.value,
                description = _descriptionValue.value
            ).fold(
                onSuccess = {
                    _addActivitySuccess.value = true
                },
                onFailure = {
                    _addActivitySuccess.value = false
                }
            )
        }
    }

    fun resetAddActivitySuccess() {
        _addActivitySuccess.value = false
    }
}
