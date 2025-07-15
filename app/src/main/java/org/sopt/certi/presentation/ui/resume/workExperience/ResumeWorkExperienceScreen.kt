package org.sopt.certi.presentation.ui.resume.workExperience

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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import okhttp3.internal.toImmutableList
import org.sopt.certi.R
import org.sopt.certi.core.component.dialog.CertiDeleteDialog
import org.sopt.certi.core.state.UiState
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.domain.model.ActivityData
import org.sopt.certi.presentation.ui.resume.component.ResumeAddButton
import org.sopt.certi.presentation.ui.resume.component.ResumeEditListItem
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun ResumeWorkExperienceRoute(
    padding: PaddingValues,
    onNavigateToAddWordExperience: () -> Unit,
    viewModel: WorkExperienceViewModel = hiltViewModel()
) {
    val uiState by viewModel.workExperienceUiState.collectAsStateWithLifecycle()
    var onDeleteDialogShow by remember { mutableStateOf(false) }

    when(uiState.loadState){
        is UiState.Success -> ResumeWorkExperienceScreen(
            onNavigateToAddWordExperience = onNavigateToAddWordExperience,
            resumeDataList = (uiState.experienceListLoadState as UiState.Success<List<ActivityData>>).data.toImmutableList(),
            onDeleteClick = { onDeleteDialogShow = true },
            modifier = Modifier.padding(padding)
        )

        is UiState.Empty -> {}
        is UiState.Failure -> {}
        is UiState.Init -> {}
        is UiState.Loading -> {}
    }

    if (onDeleteDialogShow) {
        CertiDeleteDialog(
            onConfirmClick = { onDeleteDialogShow = false },
            onDismissClick = { onDeleteDialogShow = false }
        )
    }
}

@Composable
fun ResumeWorkExperienceScreen(
    onNavigateToAddWordExperience: () -> Unit,
    resumeDataList: List<ActivityData>,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = screenWidthDp(20.dp))
    ) {
        item {
            ResumeAddButton(
                onClick = onNavigateToAddWordExperience,
                modifier = Modifier.padding(
                    top = screenHeightDp(60.dp),
                    bottom = screenHeightDp(48.dp)
                )
            )
        }

        item {
            Text(
                text = stringResource(R.string.resume_work_experience_edit_title),
                style = CertiTheme.typography.subtitle.semibold_20,
                color = CertiTheme.colors.gray600,
                modifier = Modifier.padding(bottom = screenHeightDp(48.dp))
            )
        }

        items(resumeDataList) { resumeData ->
            ResumeEditListItem(
                resumeListItem = resumeData,
                onDeleteClick = onDeleteClick,
                modifier = Modifier.padding(bottom = screenHeightDp(36.dp))
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewResumeWorkExperienceScreen() {
    val resumeDataList = listOf(
        ActivityData(
            activityId = 1,
            startAt = "2021.11",
            endAt = "2022.01",
            organization = "서티그룹",
            role = "패션디자이너 인턴",
            description = "브랜드 리서치 및 소재 조사, 브랜드 리서치 및 소재 조사"
        ),
        ActivityData(
            activityId = 2,
            startAt = "2021.11",
            endAt = "2022.01",
            organization = "서티그룹",
            role = "패션디자이너 인턴",
            description = "브랜드 리서치 및 소재 조사"
        ),
        ActivityData(
            activityId = 3,
            startAt = "2021.11",
            endAt = "2022.01",
            organization = "서티그룹",
            role = "패션디자이너 인턴",
            description = "브랜드 리서치 및 소재 조사"
        )
    )

    var onDeleteDialogShow by remember { mutableStateOf(false) }

    CERTITheme {
        ResumeWorkExperienceScreen(
            resumeDataList = resumeDataList,
            onNavigateToAddWordExperience = {},
            onDeleteClick = { onDeleteDialogShow = true }
        )

        if (onDeleteDialogShow) {
            CertiDeleteDialog(
                onConfirmClick = { onDeleteDialogShow = false },
                onDismissClick = { onDeleteDialogShow = false }
            )
        }
    }
}
