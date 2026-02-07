package org.sopt.certi.presentation.ui.certdetail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.sopt.certi.core.network.TokenManager
import org.sopt.certi.domain.model.comment.CommentItemData
import org.sopt.certi.core.state.UiState
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.domain.model.comment.RegisterCommentRequest
import org.sopt.certi.domain.model.user.UserInfoData
import org.sopt.certi.domain.usecase.UserInfoUseCase
import org.sopt.certi.domain.usecase.acquisition.AcquiredCertUseCase
import org.sopt.certi.domain.usecase.certification.GetCertInfoUseCase
import org.sopt.certi.domain.usecase.comment.DeleteCommentUseCase
import org.sopt.certi.domain.usecase.comment.GetCommentListUseCase
import org.sopt.certi.domain.usecase.comment.LikeCommentUseCase
import org.sopt.certi.domain.usecase.comment.RegisterCommentUseCase
import org.sopt.certi.domain.usecase.precert.AcquireExpectCertUseCase
import org.sopt.certi.presentation.type.CommentSortType
import org.sopt.certi.presentation.ui.certdetail.sideeffect.DetailSideEffect
import org.sopt.certi.presentation.ui.certdetail.state.DetailUiState
import javax.inject.Inject

@HiltViewModel
class CertDetailViewModel @Inject constructor(
    private val getCertInfoUseCase: GetCertInfoUseCase,
    private val acquiredCertUseCase: AcquiredCertUseCase,
    private val acquireExpectCertUseCase: AcquireExpectCertUseCase,
    private val getCommentListUseCase: GetCommentListUseCase,
    private val registerCommentUseCase: RegisterCommentUseCase,
    private val likeCommentUseCase: LikeCommentUseCase,
    private val deleteCommentUseCase: DeleteCommentUseCase,
    private val tokenManager: TokenManager
) : ViewModel() {

    private val _certDetailInfo = MutableStateFlow<UiState<CertificationData>>(UiState.Init)

    private val _commentPagingData = MutableStateFlow<PagingData<CommentItemData>>(PagingData.empty())
    val commentPagingData: StateFlow<PagingData<CommentItemData>> = _commentPagingData.asStateFlow()

    private val _totalCommentCount = MutableStateFlow(0)
    val totalCommentCount: StateFlow<Int> = _totalCommentCount.asStateFlow()

    private val _myUserId = MutableStateFlow(0L)
    val myUserId: StateFlow<Long> = _myUserId.asStateFlow()

    private val _registerCommentSuccess = MutableStateFlow<UiState<Boolean>>(UiState.Init)
    val registerCommentSuccess: StateFlow<UiState<Boolean>> = _registerCommentSuccess.asStateFlow()


    val detailUiState: StateFlow<DetailUiState> =
        combine(
            _certDetailInfo
        ) { loadState ->
            DetailUiState(
                detailCertificationLoadState = loadState[0]
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = DetailUiState(
                detailCertificationLoadState = UiState.Init
            )
        )

    private val _sideEffect = Channel<DetailSideEffect>()
    val sideEffect = _sideEffect.receiveAsFlow()


    fun getCertDetailInfo(certId: Long) = viewModelScope.launch {
        getCertInfoUseCase.invoke(certId).fold(
            onSuccess = {
                _certDetailInfo.emit(UiState.Success(it))
            },
            onFailure = {
                _certDetailInfo.emit(UiState.Failure(it.message.toString()))
            }
        )
    }

    fun acquireExpectCert(certId: Long, city: String? = null, state: String? = null, timeDate: String? = null) = viewModelScope.launch {
        acquireExpectCertUseCase.invoke(certId, city, state, timeDate).fold(
            onSuccess = {
                if (it) {
                    _sideEffect.send(DetailSideEffect.ShowAcquireExpectSuccessToast)
                } else {
                    _sideEffect.send(DetailSideEffect.ShowAcquireExpectFailToast)
                }
            },
            onFailure = {
                if (it.message?.contains("Conflict") == true) {
                    _sideEffect.send(DetailSideEffect.ShowAcquiredFailToast)
                }
            }
        )
    }

    fun acquiredCert(certId: Long) = viewModelScope.launch {
        acquiredCertUseCase.invoke(certId).fold(
            onSuccess = {
                if (it) {
                    _sideEffect.send(DetailSideEffect.ShowAcquiredSuccessDialog)
                } else {
                    _sideEffect.send(DetailSideEffect.ShowAcquiredFailToast)
                }
            },
            onFailure = {}
        )
    }

    fun getMyUserId() = viewModelScope.launch {
        _myUserId.value = tokenManager.getUserId()
    }

    fun getCommentList(certId: Long, commentSortType: CommentSortType) = viewModelScope.launch {
        val sortValue = if (commentSortType == CommentSortType.Famous) {
            listOf("likeCount", "desc")
        } else {
            listOf()
        }

        getCommentListUseCase.getCommentList(certId, sortValue)
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect { pagingData ->
                _commentPagingData.value = pagingData

                getTotalCommentCount()
                _registerCommentSuccess.emit(UiState.Init)
            }
    }

    private fun getTotalCommentCount() = viewModelScope.launch {
        getCommentListUseCase.getTotalCommentCount()
            .collect {
                _totalCommentCount.value = it
            }
    }

    fun registerComment(certId: Long, content: String) = viewModelScope.launch {
        registerCommentUseCase.invoke(registerCommentRequest = RegisterCommentRequest(content, certId)).fold(
            onSuccess = {
                _registerCommentSuccess.emit(UiState.Success(true))
            },
            onFailure = {
                _registerCommentSuccess.emit(UiState.Failure(it.message.toString()))
            }
        )
    }

    fun likeComment(commentId: Long) = viewModelScope.launch {
        likeCommentUseCase.invoke(commentId).fold(
            onSuccess = {},
            onFailure = {}
        )
    }

    fun deleteComment(commentId: Long) = viewModelScope.launch {
        deleteCommentUseCase.invoke(commentId).fold(
            onSuccess = {},
            onFailure = {}
        )
    }

    fun reportComment(commentId: Long) = viewModelScope.launch {

    }
}
