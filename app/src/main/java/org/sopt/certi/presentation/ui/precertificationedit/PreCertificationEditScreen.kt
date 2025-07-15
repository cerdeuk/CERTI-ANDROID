package org.sopt.certi.presentation.ui.precertificationedit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.sopt.certi.R
import org.sopt.certi.core.component.dialog.CertiDeleteDialog
import org.sopt.certi.core.state.UiState
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.presentation.ui.precertificationedit.component.PreCertificationItem
import org.sopt.certi.presentation.ui.precertificationedit.state.PreCertiEditUiState
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun PreCertificationEditRoute(
    padding: PaddingValues,
    viewModel: PreCertiEditViewModel = hiltViewModel()
) {
    val uiState by viewModel.preCertiEditUiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.preCertiListLoadState) {
        viewModel.getPreCertiEditList()
    }

    when (uiState.loadState) {
        is UiState.Success -> PreCertificationEditScreen(
            preCertiEditState = uiState,
            preCertificationList = (uiState.preCertiListLoadState as UiState.Success).data.toImmutableList(),
            onDelete = viewModel::onDeleteClick,
            onDialogConfirm = viewModel::onDialogConfirm,
            onDialogDismiss = viewModel::onDialogDismiss,
            modifier = Modifier.padding(padding)
        )
        is UiState.Failure -> {}
        is UiState.Loading -> {}
        is UiState.Empty -> {}
        is UiState.Init -> {}
    }
}

@Composable
fun PreCertificationEditScreen(
    preCertiEditState: PreCertiEditUiState,
    preCertificationList: ImmutableList<CertificationData>,
    onDelete: (Long) -> Unit,
    onDialogConfirm: () -> Unit = {},
    onDialogDismiss: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(top = screenHeightDp(60.dp), start = screenWidthDp(20.dp), end = screenWidthDp(92.dp))
    ) {
        if (preCertiEditState.showDialog) {
            CertiDeleteDialog(
                onConfirmClick = onDialogConfirm,
                onDismissClick = onDialogDismiss
            )
        }

        Text(
            text = stringResource(R.string.home_pre_certification_title),
            style = CertiTheme.typography.subtitle.semibold_20,
            color = CertiTheme.colors.gray600
        )

        LazyColumn(
            contentPadding = PaddingValues(vertical = screenHeightDp(36.dp)),
            verticalArrangement = Arrangement.spacedBy(screenHeightDp(36.dp))
        ) {
            items(preCertificationList) { item ->
                PreCertificationItem(
                    preCertificationData = item,
                    onDelete = { onDelete(item.certificationId) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreCertificationEditScreenPreview() {
    CERTITheme {
    }
}
