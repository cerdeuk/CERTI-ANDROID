package org.sopt.certi.presentation.ui.activity

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
import org.sopt.certi.domain.usecase.activity.EditActivityUseCase
import org.sopt.certi.domain.usecase.activity.GetActivityListUseCase
import org.sopt.certi.presentation.ui.activity.state.AddActivityUiState
import javax.inject.Inject

@HiltViewModel
class EditActivitiesViewModel @Inject constructor(
    private val getActivityListUseCase: GetActivityListUseCase,
    private val editActivityUseCase: EditActivityUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val activityId: Long = checkNotNull(savedStateHandle["activityId"])

    private val _startDate = MutableStateFlow("")
    private val _endDate = MutableStateFlow("")
    private val _organizationValue = MutableStateFlow("")
    private val _activityValue = MutableStateFlow("")
    private val _descriptionValue = MutableStateFlow("")

    private val _editActivitySuccess = MutableStateFlow(false)
    val editActivitySuccess: StateFlow<Boolean> = _editActivitySuccess

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
                addButtonEnabled = listOf(startDate, endDate, organization, activity, description).all { it.isNotBlank() }
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = AddActivityUiState("", "", "", "", "", false)
        )

    init {
        loadInitial()
    }

    private fun loadInitial() {
        viewModelScope.launch {
            getActivityListUseCase.invoke().fold(
                onSuccess = { list ->
                    val item = list.find { it.activityId == activityId }
                    if (item != null) {
                        _startDate.value = item.startAt
                        _endDate.value = item.endAt
                        _organizationValue.value = item.organization
                        _activityValue.value = item.role /* 또는 name/activityValue 필드에 맞게 */
                        _descriptionValue.value = item.description
                    }
                },
                onFailure = { }
            )
        }
    }

    fun onStartDateChanged(value: String) { _startDate.value = value }
    fun onEndDateChanged(value: String) { _endDate.value = value }
    fun onOrganizationChanged(value: String) { _organizationValue.value = value }
    fun onActivityChanged(value: String) { _activityValue.value = value }
    fun onDescriptionChanged(value: String) { _descriptionValue.value = value }

    fun editActivity() {
        viewModelScope.launch {
            editActivityUseCase(
                activityId = activityId,
                startAt = _startDate.value,
                endAt = _endDate.value,
                place = _organizationValue.value,
                name = _activityValue.value,
                description = _descriptionValue.value
            ).fold(
                onSuccess = { _editActivitySuccess.value = true },
                onFailure = { _editActivitySuccess.value = false }
            )
        }
    }

    fun resetEditActivitySuccess() {
        _editActivitySuccess.value = false
    }
}
