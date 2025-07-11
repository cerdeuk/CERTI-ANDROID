package org.sopt.certi.presentation.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.ui.theme.CertiTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.certi.R
import org.sopt.certi.core.component.dialog.CertiDeleteDialog
import org.sopt.certi.domain.model.CertificationData
import org.sopt.certi.presentation.ui.home.component.PreCertificationItem
import org.sopt.certi.ui.theme.CERTITheme

@Composable
fun PreCertificationEditRoute(
    padding: PaddingValues,
    viewModel: HomeViewModel = hiltViewModel()
) {
    var showDialog by remember { mutableStateOf(false) }

    val preCertificationList = listOf(
        CertificationData(
            certificationId = 1,
            certificationName = "시각디자인산업기사",
            averagePeriod = "3개월",
            nearestTestDate = "2025.05.27",
            agencyName = "한국산업인력공단",
            iconIndex = 0
        ),
        CertificationData(
            certificationId = 2,
            certificationName = "시각디자인산업기사",
            averagePeriod = "3개월",
            nearestTestDate = "2025.05.27",
            agencyName = "한국산업인력공단",
            iconIndex = 1
        ),
        CertificationData(
            certificationId = 3,
            certificationName = "시각디자인산업기사",
            averagePeriod = "3개월",
            nearestTestDate = "2025.05.27",
            agencyName = "한국산업인력공단",
            iconIndex = 2
        )
    )
    PreCertificationEditScreen(
        preCertificationList = preCertificationList,
        showDialog = showDialog,
        onDelete = { showDialog = true },
        onDialogConfirm = { showDialog = false },
        onDialogDismiss = { showDialog = false },
        modifier = Modifier.padding(padding)
    )
}

@Composable
fun PreCertificationEditScreen(
    preCertificationList: List<CertificationData>,
    showDialog: Boolean,
    onDelete: () -> Unit = {},
    onDialogConfirm: () -> Unit = {},
    onDialogDismiss: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(top = screenHeightDp(60.dp), start = screenWidthDp(20.dp), end = screenWidthDp(92.dp))
    ) {
        if (showDialog) {
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
                    onDelete = { }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreCertificationEditScreenPreview() {
    var showDialog by remember { mutableStateOf(false) }

    val mockList = listOf(
        CertificationData(
            certificationId = 1,
            certificationName = "시각디자인산업기사",
            averagePeriod = "3개월",
            nearestTestDate = "2025.05.27",
            agencyName = "한국산업인력공단",
            iconIndex = 0
        ),
        CertificationData(
            certificationId = 2,
            certificationName = "시각디자인산업기사",
            averagePeriod = "3개월",
            nearestTestDate = "2025.05.27",
            agencyName = "한국산업인력공단",
            iconIndex = 1
        ),
        CertificationData(
            certificationId = 3,
            certificationName = "시각디자인산업기사",
            averagePeriod = "3개월",
            nearestTestDate = "2025.05.27",
            agencyName = "한국산업인력공단",
            iconIndex = 2
        )
    )

    CERTITheme {
        PreCertificationEditScreen(
            preCertificationList = mockList,
            showDialog = true,
            onDelete = { showDialog = true },
            onDialogConfirm = { showDialog = false },
            onDialogDismiss = { showDialog = false },
            modifier = Modifier
        )
    }
}
