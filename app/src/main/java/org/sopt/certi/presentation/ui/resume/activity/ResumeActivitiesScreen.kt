package org.sopt.certi.presentation.ui.resume.activity

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
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import org.sopt.certi.R
import org.sopt.certi.core.component.dialog.CertiDeleteDialog
import org.sopt.certi.core.state.UiState
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.domain.model.ActivityData
import org.sopt.certi.presentation.ui.resume.activity.sideEffect.ActivitySideEffect
import org.sopt.certi.presentation.ui.resume.component.ResumeAddButton
import org.sopt.certi.presentation.ui.resume.component.ResumeEditListItem
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun ResumeActivitiesRoute(
    padding: PaddingValues,
    onNavigateToAddActivities: () -> Unit,
    viewModel: ActivityViewModel = hiltViewModel()
) {
    val uiState by viewModel.activityUiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current
    var onDeleteDialogShow by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.getActivityList()
    }

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycleOwner.lifecycle).collect {
            when (it) {
                ActivitySideEffect.showDeleteDialog -> onDeleteDialogShow = true
            }
        }
    }

    when (uiState.loadState) {
        is UiState.Success -> ResumeActivitiesScreen(
            onNavigateToAdd = onNavigateToAddActivities,
            resumeDataList = (uiState.activityListLoadState as UiState.Success).data.toImmutableList(),
            onDeleteClick = { viewModel.onDeleteClick(it) },
            modifier = Modifier.padding(padding)
        )
        is UiState.Empty -> {}
        is UiState.Failure -> {}
        is UiState.Init -> {}
        is UiState.Loading -> {}
    }

    if (onDeleteDialogShow) {
        CertiDeleteDialog(
            onConfirmClick = {
                onDeleteDialogShow = false
                viewModel.onDeleteConfirmclick()
            },
            onDismissClick = { onDeleteDialogShow = false }
        )
    }
}

@Composable
fun ResumeActivitiesScreen(
    onNavigateToAdd: () -> Unit,
    resumeDataList: ImmutableList<ActivityData>,
    onDeleteClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = screenWidthDp(20.dp))
    ) {
        item {
            ResumeAddButton(
                onClick = onNavigateToAdd,
                modifier = Modifier.padding(
                    top = screenHeightDp(60.dp),
                    bottom = screenHeightDp(48.dp)
                )
            )
        }

        item {
            Text(
                text = stringResource(R.string.resume_activities_add_title),
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
private fun PreviewResumeActivitiesScreen() {
    val resumeDataList = listOf(
        ActivityData(
            activityId = 1,
            startAt = "2021.11",
            endAt = "2022.01",
            organization = "sopt",
            role = "동아리 36기 기획",
            description = "서비스 기획 및 아이디어 도출"
        ),
        ActivityData(
            activityId = 2,
            startAt = "2021.11",
            endAt = "2022.01",
            organization = "sopt",
            role = "동아리 36기 기획",
            description = "서비스 기획 및 아이디어 도출"
        ),
        ActivityData(
            activityId = 3,
            startAt = "2021.11",
            endAt = "2022.01",
            organization = "sopt",
            role = "동아리 36기 기획",
            description = "서비스 기획 및 아이디어 도출"
        )
    )

    var onDeleteDialogShow by remember { mutableStateOf(false) }

    CERTITheme {
        ResumeActivitiesScreen(
            resumeDataList = resumeDataList.toImmutableList(),
            onNavigateToAdd = {},
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
