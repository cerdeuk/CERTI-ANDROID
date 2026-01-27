package org.sopt.certi.presentation.ui.certdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.sopt.certi.R
import org.sopt.certi.core.component.dialog.CertAcquiredDialog
import org.sopt.certi.core.component.toast.ShowToastRoute
import org.sopt.certi.core.component.webview.CertWebView
import org.sopt.certi.core.state.UiState
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.presentation.model.ToastConfig
import org.sopt.certi.presentation.ui.certdetail.component.tab.CertDetailTab
import org.sopt.certi.presentation.ui.certdetail.component.tab.DetailTabType
import org.sopt.certi.presentation.ui.certdetail.screen.CertDetailCommentRoute
import org.sopt.certi.presentation.ui.certdetail.screen.CertDetailInfoScreen
import org.sopt.certi.presentation.ui.certdetail.sideeffect.DetailSideEffect
import org.sopt.certi.ui.theme.CERTITheme

@Composable
fun CertDetailRoute(
    padding: PaddingValues,
    certId: Long,
    navigateToResume: () -> Unit,
    viewModel: CertDetailViewModel = hiltViewModel()
) {
    var showAcquiredDialog by remember { mutableStateOf(false) }
    var showWebView by remember { mutableStateOf(false) }
    var showAcquireExpectSuccessToast by remember { mutableStateOf(false) }
    var showAcquireExpectFailToast by remember { mutableStateOf(false) }
    var showAcquiredFailToast by remember { mutableStateOf(false) }

    val uiState by viewModel.detailUiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getCertDetailInfo(certId)

        viewModel.sideEffect.collect {
            when (it) {
                DetailSideEffect.ShowAcquiredSuccessDialog -> showAcquiredDialog = true
                DetailSideEffect.ShowAcquiredFailToast -> showAcquiredFailToast = true
                DetailSideEffect.ShowAcquireExpectFailToast -> showAcquireExpectFailToast = true
                DetailSideEffect.ShowAcquireExpectSuccessToast -> showAcquireExpectSuccessToast = true
            }
        }
    }

    when (uiState.loadState) {
        is UiState.Success -> {
            val certData = (uiState.detailCertificationLoadState as UiState.Success).data

            CertDetailScreen(
                certData = certData,
                modifier = Modifier.padding(padding),
                showWebView = {
                    showWebView = true
                },
                acquireExpectCert = {
                    viewModel.acquireExpectCert(certId)
                },
                acquiredCert = {
                    viewModel.acquiredCert(certId)
                }
            )

            if (showAcquiredDialog) {
                CertAcquiredDialog(
                    certName = certData.certificationName,
                    onConfirmClick = {
                        navigateToResume()
                    },
                    setShowDialog = { showAcquiredDialog = it }
                )
            }

            if (showWebView) {
                CertWebView(
                    url = certData.applicationUrl,
                    closeWebView = { showWebView = false }
                )
            }

            if (showAcquireExpectSuccessToast) {
                ShowToastRoute(
                    toastConfig = ToastConfig(
                        titleMessage = stringResource(R.string.toast_acquire_expect_success_title),
                        contentMessage = stringResource(R.string.toast_acquire_expect_success_content),
                        endToastAction = { showAcquireExpectSuccessToast = false }
                    )
                )
            }

            if (showAcquireExpectFailToast) {
                ShowToastRoute(
                    toastConfig = ToastConfig(
                        titleMessage = stringResource(R.string.toast_acquire_expect_fail_title),
                        contentMessage = stringResource(R.string.toast_acquire_expect_fail_content),
                        endToastAction = { showAcquireExpectFailToast = false }
                    )
                )
            }

            if (showAcquiredFailToast) {
                ShowToastRoute(
                    toastConfig = ToastConfig(
                        titleMessage = stringResource(R.string.toast_acquired_fail_title),
                        contentMessage = stringResource(R.string.toast_acquired_fail_content),
                        endToastAction = { showAcquiredFailToast = false }
                    )
                )
            }
        }
        else -> {}
    }
}

@Composable
fun CertDetailScreen(
    certData: CertificationData,
    modifier: Modifier = Modifier,
    showWebView: () -> Unit = {},
    acquireExpectCert: () -> Unit = {},
    acquiredCert: () -> Unit = {}
) {
    var selectedTab by remember { mutableStateOf(DetailTabType.Info) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .imePadding()
    ) {
        CertDetailTab(
            modifier = Modifier.padding(horizontal = screenWidthDp(20.dp)),
            tabClicked = { tab ->
                selectedTab = tab
            }
        )

        when (selectedTab) {
            DetailTabType.Info -> {
                CertDetailInfoScreen(
                    certData = certData,
                    showWebView = {
                        showWebView()
                    },
                    acquireExpectCert = {
                        acquireExpectCert()
                    },
                    acquiredCert = {
                        acquiredCert()
                    }
                )
            }
            DetailTabType.Comment -> {
                CertDetailCommentRoute()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCertDetailScreen() {
    val dummyCertData = CertificationData(
        certificationId = 0,
        certificationName = "GTQ 1급 (그래픽기술자격)",
        averagePeriod = "100일",
        agencyName = "국가기술자격",
        testType = "실기형",
        tags = listOf("aa", "bb", "cc"),
        charge = "12000",
        description = "2D 그래픽 툴의 기능을 활용한 사고의 시각화를 통해 이미지 제작, 수정, 편집 및 그래픽 디자인을 창출하는 업무를 수행하고 이를 통해 비지니스 커뮤니케이션을 원활하게 한다. 1급과 2급, 급수의 차이는 이 업무를 수행하는 툴 활용 능력의 범위와 숙련도 등의 고도화 차이이다.",
        testDate = "매월 넷째주 토요일 정기시험 시행 (총 12회)",
        applicationMethod = "온라인(한국생산성본부 홈페이지)",
        applicationUrl = "asdf",
        expirationPeriod = "1년"
    )

    CERTITheme {
        CertDetailScreen(
            certData = dummyCertData
        )
    }
}
