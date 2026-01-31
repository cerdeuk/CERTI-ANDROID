package org.sopt.certi.presentation.ui.resume

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
import org.sopt.certi.core.network.TokenManager
import org.sopt.certi.core.state.UiState
import org.sopt.certi.domain.model.ActivityData
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.domain.model.user.UserInfoData
import org.sopt.certi.domain.usecase.UserInfoUseCase
import org.sopt.certi.domain.usecase.acquisition.GetAcquisitionDetailUseCase
import org.sopt.certi.domain.usecase.acquisition.GetAcquisitionListUseCase
import org.sopt.certi.domain.usecase.activity.GetActivityListUseCase
import org.sopt.certi.domain.usecase.career.GetCareerListUseCase
import org.sopt.certi.presentation.ui.resume.sideEffect.ResumeSideEffect
import org.sopt.certi.presentation.ui.resume.state.ResumeUiState
import javax.inject.Inject

@HiltViewModel
class ResumeViewModel @Inject constructor(
    private val userInfoUseCase: UserInfoUseCase,
    private val getAcquisitionListUseCase: GetAcquisitionListUseCase,
    private val getCareerListUseCase: GetCareerListUseCase,
    private val getActivityListUseCase: GetActivityListUseCase,
    private val getAcquisitionDetailUseCase: GetAcquisitionDetailUseCase,
    private val tokenManager: TokenManager
) : ViewModel() {
    private val _userInfoLoadState = MutableStateFlow<UiState<UserInfoData>>(UiState.Loading)
    private val _acquiredCertificationListLoadState = MutableStateFlow<UiState<List<CertificationData>>>(UiState.Loading)
    private val _experienceListLoadState = MutableStateFlow<UiState<List<ActivityData>>>(UiState.Loading)
    private val _activityListLoadState = MutableStateFlow<UiState<List<ActivityData>>>(UiState.Loading)
    private val _selectedCertDetail = MutableStateFlow<UiState<CertificationData>>(UiState.Loading)

    val resumeUiState: StateFlow<ResumeUiState> =
        combine(
            _userInfoLoadState,
            _acquiredCertificationListLoadState,
            _experienceListLoadState,
            _activityListLoadState,
            _selectedCertDetail
        ) { userInfo, certList, experience, activity, certDetail ->
            ResumeUiState(
                userInfoLoadState = userInfo,
                acquiredCertificationListLoadState = certList,
                experienceListLoadState = experience,
                activityListLoadState = activity,
                selectedCertDetail = certDetail
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ResumeUiState(
                userInfoLoadState = UiState.Loading,
                acquiredCertificationListLoadState = UiState.Loading,
                experienceListLoadState = UiState.Loading,
                activityListLoadState = UiState.Loading,
                selectedCertDetail = UiState.Loading
            )
        )

    private val _sideEffect = Channel<ResumeSideEffect>()
    val sideEffect = _sideEffect.receiveAsFlow()

    fun getResumeData() {
        getUserInfo()
        getAcquiredCertificationList()
        getExperienceList()
        getActivityList()
    }

    private fun getUserInfo() {
        viewModelScope.launch {
            _userInfoLoadState.value = UiState.Loading
            userInfoUseCase()
                .onSuccess { result ->
                    _userInfoLoadState.value = UiState.Success(result)
                }
                .onFailure {
                    _userInfoLoadState.value = UiState.Failure(it.toString())
                }
        }
    }

    private fun getAcquiredCertificationList() = viewModelScope.launch {
        _acquiredCertificationListLoadState.value = UiState.Loading
        getAcquisitionListUseCase.invoke().fold(
            onSuccess = {
                val acquiredCertificationList = it
                _acquiredCertificationListLoadState.emit(UiState.Success(acquiredCertificationList))
            },
            onFailure = {
                _acquiredCertificationListLoadState.emit(UiState.Failure(it.message.toString()))
            }
        )
    }

    private fun getExperienceList() = viewModelScope.launch {
        _experienceListLoadState.value = UiState.Loading
        getCareerListUseCase.invoke().fold(
            onSuccess = {
                val experienceList = it.take(4)
                _experienceListLoadState.emit(UiState.Success(experienceList))
            },
            onFailure = {
                _experienceListLoadState.emit(UiState.Failure(it.message.toString()))
            }
        )
    }

    private fun getActivityList() = viewModelScope.launch {
        _activityListLoadState.value = UiState.Loading
        getActivityListUseCase.invoke().fold(
            onSuccess = {
                val activityList = it.take(4)
                _activityListLoadState.emit(UiState.Success(activityList))
            },
            onFailure = {
                _activityListLoadState.emit(UiState.Failure(it.message.toString()))
            }
        )
    }

    fun onCertificationClick(selectedCertificationId: Long) = viewModelScope.launch {
        _selectedCertDetail.value = UiState.Loading
        getAcquisitionDetailUseCase.invoke(selectedCertificationId).fold(
            onSuccess = {
                val acquisitionDetail = it
                _selectedCertDetail.emit(UiState.Success(acquisitionDetail))
                _sideEffect.send(ResumeSideEffect.ShowCertificationDetailModal)
            },
            onFailure = {
                _selectedCertDetail.emit(UiState.Failure(it.message.toString()))
            }
        )
    }

    fun getUserName(): String? = tokenManager.getNickName()
}
