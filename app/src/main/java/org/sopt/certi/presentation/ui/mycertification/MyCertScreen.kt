package org.sopt.certi.presentation.ui.mycertification

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import org.sopt.certi.R
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.presentation.type.MyCertType
import org.sopt.certi.presentation.ui.mycertification.component.FavoriteCertItem
import org.sopt.certi.presentation.ui.mycertification.component.MyCertHeader
import org.sopt.certi.presentation.ui.mycertification.component.MyCertItem
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme
import java.time.LocalDate

@Composable
fun MyCertRoute(
    padding: PaddingValues,
    viewModel: MyCertViewModel = hiltViewModel()
) {
    val uiState by viewModel.myCertUiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CertificationScreen(
    selectedTab: MyCertType,
    certifications: ImmutableList<CertificationData>,
    onTabSelected: (MyCertType) -> Unit,
    onPlannedCertEditClick: () -> Unit,
    onAcquiredCertEditClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        MyCertHeader(
            selectedType = selectedTab,
            onTabSelected = onTabSelected,
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        when (selectedTab) {
            MyCertType.PLANNED -> {
                CertList(
                    certifications = certifications,
                    onEditClick = onPlannedCertEditClick
                )
            }
            MyCertType.ACQUIRED -> {
                CertList(
                    certifications = certifications,
                    onEditClick = onAcquiredCertEditClick
                )
            }
            MyCertType.FAVORITE -> {
                FavoriteCertList(certifications)
            }
        }
    }
}

@Composable
private fun CertList(
    certifications: ImmutableList<CertificationData>,
    onEditClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            start = screenWidthDp(20.dp),
            top = screenWidthDp(16.dp),
            end = screenWidthDp(20.dp),
            bottom = screenWidthDp(20.dp)
        ),
        verticalArrangement = Arrangement.spacedBy(screenWidthDp(16.dp))
    ) {
        item {
            Text(
                text = stringResource(R.string.edit),
                style = CertiTheme.typography.body.semibold_16,
                color = CertiTheme.colors.gray400,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .noRippleClickable(onEditClick)
            )
        }

        items(
            items = certifications,
            key = { it.certificationId }
        ) { certification ->
            MyCertItem(
                certificationData = certification
            )
        }
    }
}

@Composable
private fun FavoriteCertList(
    certifications: ImmutableList<CertificationData>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            start = screenWidthDp(20.dp),
            top = screenWidthDp(24.dp),
            end = screenWidthDp(20.dp),
            bottom = screenWidthDp(20.dp)
        ),
        verticalArrangement = Arrangement.spacedBy(screenWidthDp(16.dp))
    ) {
        items(
            items = certifications,
            key = { it.certificationId }
        ) { certification ->
            FavoriteCertItem(
                certificationData = certification,
                onFavoriteClick = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewResumeMyCertScreen() {
    val dummyCertifications = listOf(
        CertificationData(
            certificationId = 1,
            certificationName = "정보처리기사",
            certificationType = "국가기술자격",
            description = "소프트웨어 개발 관련 자격증으로, 계획수립, 분석, 설계, 구...",
            isAcquired = false,
            placement = "고양시",
            testTime = "09:00",
            createdAt = LocalDate.of(2025, 11, 23),
            level = "IM3",
            isFavorite = true,
            testType = "실기형",
            agencyName = "한국산업인력공단"
        ),
        CertificationData(
            certificationId = 2,
            certificationName = "정보처리기사",
            certificationType = "국가기술자격",
            description = "소프트웨어 개발 관련 자격증으로, 계획수립, 분석, 설계, 구...",
            isAcquired = false,
            placement = "고양시",
            testTime = "09:00",
            createdAt = LocalDate.of(2025, 11, 23),
            level = "IM3",
            isFavorite = true,
            testType = "실기형",
            agencyName = "한국산업인력공단"
        ),
        CertificationData(
            certificationId = 3,
            certificationName = "정보처리기사",
            certificationType = "국가기술자격",
            description = "소프트웨어 개발 관련 자격증으로, 계획수립, 분석, 설계, 구...",
            isAcquired = false,
            placement = "고양시",
            testTime = "09:00",
            createdAt = LocalDate.of(2025, 11, 23),
            level = "IM3",
            isFavorite = true,
            testType = "실기형",
            agencyName = "한국산업인력공단"
        ),
        CertificationData(
            certificationId = 4,
            certificationName = "정보처리기사",
            certificationType = "국가기술자격",
            description = "소프트웨어 개발 관련 자격증으로, 계획수립, 분석, 설계, 구...",
            isAcquired = false,
            placement = "고양시",
            testTime = "09:00",
            createdAt = LocalDate.of(2025, 11, 23),
            level = "IM3",
            isFavorite = true,
            testType = "실기형",
            agencyName = "한국산업인력공단"
        ),
        CertificationData(
            certificationId = 5,
            certificationName = "정보처리기사",
            certificationType = "국가기술자격",
            description = "소프트웨어 개발 관련 자격증으로, 계획수립, 분석, 설계, 구...",
            isAcquired = false,
            placement = "고양시",
            testTime = "09:00",
            createdAt = LocalDate.of(2025, 11, 23),
            level = "IM3",
            isFavorite = true,
            testType = "실기형",
            agencyName = "한국산업인력공단"
        )
    )

    var selectedTab by remember { mutableStateOf(MyCertType.PLANNED) }

    CERTITheme {
        CertificationScreen(
            selectedTab = selectedTab,
            certifications = dummyCertifications.toImmutableList(),
            onTabSelected = { selectedTab = it },
            onPlannedCertEditClick = {},
            onAcquiredCertEditClick = {}
        )
    }
}
