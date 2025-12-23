package org.sopt.certi.presentation.ui.mycertification

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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import org.sopt.certi.R
import org.sopt.certi.core.state.UiState
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.presentation.type.MyCertType
import org.sopt.certi.presentation.ui.mycertification.component.FavoriteCertItem
import org.sopt.certi.presentation.ui.mycertification.component.MyCertHeader
import org.sopt.certi.presentation.ui.mycertification.component.MyCertItem
import org.sopt.certi.presentation.ui.mycertification.state.MyCertUiState
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun MyCertRoute(
    padding: PaddingValues,
    navigateToEditCert: () -> Unit,
    viewModel: MyCertViewModel = hiltViewModel()
) {
    val uiState by viewModel.myCertUiState.collectAsStateWithLifecycle()

    CertificationScreen(
        uiState = uiState,
        certifications = (uiState.myCertListLoadState as UiState.Success<List<CertificationData>>).data.toImmutableList(),
        onTabSelected = viewModel::updateSelectedTab,
        onEditClick = navigateToEditCert,
        modifier = Modifier.padding(padding)
    )
}

@Composable
fun CertificationScreen(
    uiState: MyCertUiState,
    certifications: ImmutableList<CertificationData>,
    onTabSelected: (MyCertType) -> Unit,
    onEditClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        MyCertHeader(
            selectedType = uiState.selectedTab,
            onTabSelected = onTabSelected,
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        when (uiState.selectedTab) {
            MyCertType.PLANNED -> {
                CertList(
                    certifications = certifications,
                    onEditClick = onEditClick
                )
            }

            MyCertType.ACQUIRED -> {
                CertList(
                    certifications = certifications,
                    onEditClick = onEditClick
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
    var selectedTab by remember { mutableStateOf(MyCertType.PLANNED) }
    val uiState by remember {
        mutableStateOf(
            MyCertUiState(
                selectedTab = MyCertType.PLANNED,
                myCertListLoadState = UiState.Loading,
                selectedCertificationId = null
            )
        )
    }

    CERTITheme {
        CertificationScreen(
            uiState = uiState,
            certifications = dummyCertifications.toImmutableList(),
            onTabSelected = { selectedTab = it },
            onEditClick = {}
        )
    }
}
