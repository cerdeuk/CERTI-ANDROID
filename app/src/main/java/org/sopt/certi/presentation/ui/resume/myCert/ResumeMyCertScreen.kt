package org.sopt.certi.presentation.ui.resume.myCert

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
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
import okhttp3.internal.toImmutableList
import org.sopt.certi.R
import org.sopt.certi.core.component.dialog.CertiDeleteDialog
import org.sopt.certi.core.state.UiState
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.domain.model.CertificationData
import org.sopt.certi.presentation.ui.resume.component.ResumeMyCertiListItem
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme
import java.time.LocalDate

@Composable
fun ResumeMyCertRoute(
    padding: PaddingValues,
    viewModel: MyCertViewModel = hiltViewModel()
) {
    val uiState by viewModel.myCertUiState.collectAsStateWithLifecycle()
    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.getMyCertList()
    }

    if (showDialog) {
        CertiDeleteDialog(
            onConfirmClick = { showDialog = false },
            onDismissClick = { showDialog = false }
        )
    }

    when (uiState.loadState) {
        is UiState.Success -> ResumeMyCertScreen(
            certifications = (uiState.myCertListLoadState as UiState.Success<List<CertificationData>>).data.toImmutableList(),
            onDeleteClick = { showDialog = true },
            modifier = Modifier.padding(padding)
        )
        is UiState.Empty -> {}
        is UiState.Failure -> {}
        is UiState.Init -> {}
        is UiState.Loading -> {}
    }

}

@Composable
fun ResumeMyCertScreen(
    certifications: List<CertificationData>,
    onDeleteClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
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
                onDeleteClick = onDeleteClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewResumeMyCertScreen() {
    var showDialog by remember { mutableStateOf(false) }

    val dummyCertifications = listOf(
        CertificationData(
            certificationId = 1,
            certificationName = "GTQ 1급 (그래픽기술자격)",
            createdAt = LocalDate.now(),
            cardFrontImageUrl = "https://sopt-certi-bucket.s3.ap-northeast-2.amazonaws.com/certi/color%3Dblue.png",
            tags = listOf("태그", "태그", "태그")
        ),
        CertificationData(
            certificationId = 1,
            certificationName = "GTQ 1급 (그래픽기술자격)",
            createdAt = LocalDate.now(),
            cardFrontImageUrl = "https://sopt-certi-bucket.s3.ap-northeast-2.amazonaws.com/certi/color%3Dwhite.png",
            tags = listOf("태그", "태그", "태그")
        ),
        CertificationData(
            certificationId = 1,
            certificationName = "GTQ 1급 (그래픽기술자격)",
            createdAt = LocalDate.now(),
            cardFrontImageUrl = "https://sopt-certi-bucket.s3.ap-northeast-2.amazonaws.com/certi/color%3Dyellow.png",
            tags = listOf("태그", "태그", "태그")
        ),
        CertificationData(
            certificationId = 1,
            certificationName = "GTQ 1급 (그래픽기술자격)",
            createdAt = LocalDate.now(),
            cardFrontImageUrl = "https://sopt-certi-bucket.s3.ap-northeast-2.amazonaws.com/certi/color%3Dblue.png",
            tags = listOf("태그", "태그", "태그")
        )
    )

    CERTITheme {
        ResumeMyCertScreen(
            certifications = dummyCertifications,
            onDeleteClick = { showDialog = true },
        )
    }
}
