package org.sopt.certi.presentation.ui.resume

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.sopt.certi.R
import org.sopt.certi.core.component.dialog.CertiDeleteDialog
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.domain.model.ResumeCertificationListData
import org.sopt.certi.presentation.ui.resume.component.ResumeMyCertiListItem
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun ResumeMyCertRoute(
    padding: PaddingValues,
    viewModel: ResumeViewModel = hiltViewModel()
) {
    var showDialog by remember { mutableStateOf(false) }

    val dummyCertifications = listOf(
        ResumeCertificationListData(
            name = "GTQ 1급 (그래픽기술자격)",
            year = 2025,
            month = 7,
            day = 3,
            cardImageUrl = "https://mblogthumb-phinf.pstatic.net/MjAyMDAxMTBfMTgx/MDAxNTc4NjM1MTAxNjk1.m2q2MOZR3vArhqg1nC4-i2CEaVPlcPNcbic3KyTGj-cg.BBprGk0SqCmOMngKaT1CaaR_IBTJ8t-4LrOu_Nn2prAg.JPEG.p197273/88aad6.jpg?type=w800",
            tags = listOf("태그", "태그", "태그")
        ),
        ResumeCertificationListData(
            name = "GTQ 1급 (그래픽기술자격)",
            year = 2025,
            month = 7,
            day = 3,
            cardImageUrl = "https://mblogthumb-phinf.pstatic.net/MjAyMDAxMTBfMTgx/MDAxNTc4NjM1MTAxNjk1.m2q2MOZR3vArhqg1nC4-i2CEaVPlcPNcbic3KyTGj-cg.BBprGk0SqCmOMngKaT1CaaR_IBTJ8t-4LrOu_Nn2prAg.JPEG.p197273/88aad6.jpg?type=w800",
            tags = listOf("태그", "태그", "태그")
        ),
        ResumeCertificationListData(
            name = "GTQ 1급 (그래픽기술자격)",
            year = 2025,
            month = 7,
            day = 3,
            cardImageUrl = "https://mblogthumb-phinf.pstatic.net/MjAyMDAxMTBfMTgx/MDAxNTc4NjM1MTAxNjk1.m2q2MOZR3vArhqg1nC4-i2CEaVPlcPNcbic3KyTGj-cg.BBprGk0SqCmOMngKaT1CaaR_IBTJ8t-4LrOu_Nn2prAg.JPEG.p197273/88aad6.jpg?type=w800",
            tags = listOf("태그", "태그", "태그")
        )
    )

    ResumeMyCertScreen(
        certifications = dummyCertifications,
        showDialog = showDialog,
        onDeleteClick = { showDialog = true },
        onDialogConfirm = { showDialog = false },
        onDialogDismiss = { showDialog = false },
        modifier = Modifier.padding(padding)
    )
}

@Composable
fun ResumeMyCertScreen(
    certifications: List<ResumeCertificationListData>,
    showDialog: Boolean,
    onDeleteClick: (ResumeCertificationListData) -> Unit,
    onDialogConfirm: () -> Unit,
    onDialogDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (showDialog) {
        CertiDeleteDialog(
            onConfirmClick = onDialogConfirm,
            onDismissClick = onDialogDismiss
        )
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            top = screenHeightDp(60.dp),
            bottom = screenHeightDp(36.dp),
            start = screenWidthDp(20.dp)
        ),
        verticalArrangement = Arrangement.spacedBy(screenHeightDp(36.dp))
    ) {
        item {
            Text(
                text = stringResource(R.string.resume_section_certification_title),
                style = CertiTheme.typography.subtitle.semibold_20,
                color = CertiTheme.colors.gray600
            )
        }

        items(certifications) { certification ->
            ResumeMyCertiListItem(
                certification = certification,
                onDeleteClick = { onDeleteClick(certification) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewResumeMyCertScreen() {
    var showDialog by remember { mutableStateOf(false) }

    val dummyCertifications = listOf(
        ResumeCertificationListData(
            name = "GTQ 1급 (그래픽기술자격)",
            year = 2025,
            month = 7,
            day = 3,
            cardImageUrl = "https://mblogthumb-phinf.pstatic.net/MjAyMDAxMTBfMTgx/MDAxNTc4NjM1MTAxNjk1.m2q2MOZR3vArhqg1nC4-i2CEaVPlcPNcbic3KyTGj-cg.BBprGk0SqCmOMngKaT1CaaR_IBTJ8t-4LrOu_Nn2prAg.JPEG.p197273/88aad6.jpg?type=w800",
            tags = listOf("태그", "태그", "태그")
        ),
        ResumeCertificationListData(
            name = "GTQ 1급 (그래픽기술자격)",
            year = 2025,
            month = 7,
            day = 3,
            cardImageUrl = "https://mblogthumb-phinf.pstatic.net/MjAyMDAxMTBfMTgx/MDAxNTc4NjM1MTAxNjk1.m2q2MOZR3vArhqg1nC4-i2CEaVPlcPNcbic3KyTGj-cg.BBprGk0SqCmOMngKaT1CaaR_IBTJ8t-4LrOu_Nn2prAg.JPEG.p197273/88aad6.jpg?type=w800",
            tags = listOf("태그", "태그", "태그")
        ),
        ResumeCertificationListData(
            name = "GTQ 1급 (그래픽기술자격)",
            year = 2025,
            month = 7,
            day = 3,
            cardImageUrl = "https://mblogthumb-phinf.pstatic.net/MjAyMDAxMTBfMTgx/MDAxNTc4NjM1MTAxNjk1.m2q2MOZR3vArhqg1nC4-i2CEaVPlcPNcbic3KyTGj-cg.BBprGk0SqCmOMngKaT1CaaR_IBTJ8t-4LrOu_Nn2prAg.JPEG.p197273/88aad6.jpg?type=w800",
            tags = listOf("태그", "태그", "태그")
        )
    )

    CERTITheme {
        ResumeMyCertScreen(
            certifications = dummyCertifications,
            showDialog = showDialog,
            onDeleteClick = { showDialog = true },
            onDialogConfirm = { showDialog = false },
            onDialogDismiss = { showDialog = false }
        )
    }
}
