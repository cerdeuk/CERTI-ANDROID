package org.sopt.certi.presentation.ui.resume.workExperience

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import org.sopt.certi.core.state.UiState
import org.sopt.certi.domain.model.ActivityData
import org.sopt.certi.domain.usecase.DummyUseCase
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

    fun getWorkExperienceList() {
        val resumeDataList = {
            listOf(
                ActivityData(
                    activityId = 1,
                    startAt = "2021.11",
                    endAt = "2022.01",
                    organization = "서티그룹",
                    role = "패션디자이너 인턴",
                    description = "브랜드 리서치 및 소재 조사"
                ),
                ActivityData(
                    activityId = 2,
                    startAt = "2021.11",
                    endAt = "2022.01",
                    organization = "서티그룹",
                    role = "패션디자이너 인턴",
                    description = "브랜드 리서치 및 소재 조사"
                ),
                ActivityData(
                    activityId = 3,
                    startAt = "2021.11",
                    endAt = "2022.01",
                    organization = "서티그룹",
                    role = "패션디자이너 인턴",
                    description = "브랜드 리서치 및 소재 조사"
                ),
                ActivityData(
                    activityId = 4,
                    startAt = "2021.11",
                    endAt = "2022.01",
                    organization = "서티그룹",
                    role = "패션디자이너 인턴",
                    description = "브랜드 리서치 및 소재 조사"
                ),
                ActivityData(
                    activityId = 5,
                    startAt = "2021.11",
                    endAt = "2022.01",
                    organization = "서티그룹",
                    role = "패션디자이너 인턴",
                    description = "브랜드 리서치 및 소재 조사"
                )
            )
        }
        _experienceListLoadState.value = UiState.Success(resumeDataList())
    }

    fun onDeleteClick(selectedId: Long) {
        _selectedId.value = selectedId
    }

    fun onDeleteConfirmclick() {
        val resumeDataList = {
            listOf(
                ActivityData(
                    activityId = 2,
                    startAt = "2021.11",
                    endAt = "2022.01",
                    organization = "서티그룹",
                    role = "패션디자이너 인턴",
                    description = "브랜드 리서치 및 소재 조사"
                ),
                ActivityData(
                    activityId = 3,
                    startAt = "2021.11",
                    endAt = "2022.01",
                    organization = "서티그룹",
                    role = "패션디자이너 인턴",
                    description = "브랜드 리서치 및 소재 조사"
                ),
                ActivityData(
                    activityId = 4,
                    startAt = "2021.11",
                    endAt = "2022.01",
                    organization = "서티그룹",
                    role = "패션디자이너 인턴",
                    description = "브랜드 리서치 및 소재 조사"
                ),
                ActivityData(
                    activityId = 5,
                    startAt = "2021.11",
                    endAt = "2022.01",
                    organization = "서티그룹",
                    role = "패션디자이너 인턴",
                    description = "브랜드 리서치 및 소재 조사"
                )
            )
        }
        _selectedId.value = null
        _experienceListLoadState.value = UiState.Success(resumeDataList())
    }
}
