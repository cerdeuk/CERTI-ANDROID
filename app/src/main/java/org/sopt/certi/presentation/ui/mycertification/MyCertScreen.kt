package org.sopt.certi.presentation.ui.mycertification

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
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
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import org.sopt.certi.R
import org.sopt.certi.core.component.bottomsheet.RegisterTestInfoBottomSheet
import org.sopt.certi.core.component.dialog.CertiDeleteDialog
import org.sopt.certi.core.component.topbar.MyPageTopBar
import org.sopt.certi.core.state.UiState
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.presentation.type.MyCertType
import org.sopt.certi.presentation.ui.mycertification.component.CertificationList
import org.sopt.certi.presentation.ui.mycertification.component.FavoriteCertList
import org.sopt.certi.presentation.ui.mycertification.component.MyCertHeader
import org.sopt.certi.presentation.ui.mycertification.state.MyCertUiState
import org.sopt.certi.ui.theme.CERTITheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCertRoute(
    padding: PaddingValues,
    navigateToCertDetail: (Long) -> Unit,
    viewModel: MyCertViewModel = hiltViewModel()
) {
    val uiState by viewModel.myCertUiState.collectAsStateWithLifecycle()

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    BackHandler(enabled = uiState.isEditMode) { viewModel.onEditModeToggle() }

    uiState.deleteTargetId?.let {
        CertiDeleteDialog(
            onConfirmClick = {
                viewModel.deleteItem()
                viewModel.closeDeleteDialog()
            },
            onDismissClick = { viewModel.closeDeleteDialog() }
        )
    }

    uiState.editTargetCertification?.let { data ->
        RegisterTestInfoBottomSheet(
            sheetState = sheetState,
            certTitle = data.certificationName,
            place1List = emptyList(),
            place2List = emptyList(),
            forModify = true,
            onDismissClick = viewModel::closeEditSheet,
            certificationData = data
        )
    }

    when (val state = uiState.myCertListLoadState) {
        is UiState.Success -> CertificationScreen(
            uiState = uiState,
            certifications = state.data.toImmutableList(),
            onTabSelected = viewModel::updateSelectedTab,
            onEditModeToggle = viewModel::onEditModeToggle,
            onCertificationClick = navigateToCertDetail,
            onFavoriteToggle = viewModel::onFavoriteToggle,
            onEditClick = viewModel::editItem,
            onDeleteClick = viewModel::openDeleteDialog,
            modifier = Modifier.padding(padding)
        )
        is UiState.Empty -> {}
        is UiState.Failure -> {}
        is UiState.Init -> {}
        is UiState.Loading -> {}
    }
}

@Composable
fun CertificationScreen(
    uiState: MyCertUiState,
    certifications: ImmutableList<CertificationData>,
    onTabSelected: (MyCertType) -> Unit,
    onEditModeToggle: () -> Unit,
    onCertificationClick: (Long) -> Unit,
    onFavoriteToggle: (Long) -> Unit,
    onEditClick: (Long) -> Unit,
    onDeleteClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        if (uiState.isEditMode) {
            MyPageTopBar(
                headerTitle = stringResource(R.string.edit_certification),
                modifier = Modifier.padding(vertical = screenWidthDp(24.dp))
            )
        } else {
            MyCertHeader(
                selectedType = uiState.selectedTab,
                onTabSelected = onTabSelected,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
        }

        when (uiState.selectedTab) {
            MyCertType.FAVORITE -> {
                FavoriteCertList(
                    certifications = certifications,
                    onFavoriteToggle = onFavoriteToggle,
                    onCertificationClick = onCertificationClick
                )
            }
            else -> {
                CertificationList(
                    isEditMode = uiState.isEditMode,
                    certifications = certifications,
                    onEditModeToggle = onEditModeToggle,
                    onCertificationClick = onCertificationClick,
                    onEditClick = onEditClick,
                    onDeleteClick = onDeleteClick
                )
            }
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
                isEditMode = false,
                selectedTab = MyCertType.PLANNED,
                myCertListLoadState = UiState.Loading,
                editTargetCertification = null,
                deleteTargetId = null
            )
        )
    }

    CERTITheme {
        CertificationScreen(
            uiState = uiState,
            certifications = dummyPlannedCertifications.toImmutableList(),
            onTabSelected = { selectedTab = it },
            onEditModeToggle = {},
            onCertificationClick = {},
            onFavoriteToggle = {},
            onEditClick = {},
            onDeleteClick = {}
        )
    }
}
