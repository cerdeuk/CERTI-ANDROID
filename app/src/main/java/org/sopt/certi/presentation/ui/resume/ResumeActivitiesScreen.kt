package org.sopt.certi.presentation.ui.resume

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
import org.sopt.certi.domain.model.ActivityData
import org.sopt.certi.presentation.ui.resume.component.ResumeAddButton
import org.sopt.certi.presentation.ui.resume.component.ResumeEditListItem
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun ResumeActivitiesRoute(
    padding: PaddingValues,
    onNavigateToAddActivities: () -> Unit,
    viewModel: ResumeViewModel = hiltViewModel()
) {
    var showDialog by remember { mutableStateOf(false) }

    val resumeDataList = listOf(
        ActivityData(
            startAt = "2021.11",
            endAt = "2022.01",
            organization = "서티그룹",
            role = "패션디자이너 인턴",
            description = "브랜드 리서치 및 소재 조사"
        ),
        ActivityData(
            startAt = "2021.11",
            endAt = "2022.01",
            organization = "서티그룹",
            role = "패션디자이너 인턴",
            description = "브랜드 리서치 및 소재 조사"
        ),
        ActivityData(
            startAt = "2021.11",
            endAt = "2022.01",
            organization = "서티그룹",
            role = "패션디자이너 인턴",
            description = "브랜드 리서치 및 소재 조사"
        ),
        ActivityData(
            startAt = "2021.11",
            endAt = "2022.01",
            organization = "서티그룹",
            role = "패션디자이너 인턴",
            description = "브랜드 리서치 및 소재 조사"
        ),
        ActivityData(
            startAt = "2021.11",
            endAt = "2022.01",
            organization = "서티그룹",
            role = "패션디자이너 인턴",
            description = "브랜드 리서치 및 소재 조사"
        ),
        ActivityData(
            startAt = "2021.11",
            endAt = "2022.01",
            organization = "서티그룹",
            role = "패션디자이너 인턴",
            description = "브랜드 리서치 및 소재 조사"
        ),
        ActivityData(
            startAt = "2021.11",
            endAt = "2022.01",
            organization = "서티그룹",
            role = "패션디자이너 인턴",
            description = "브랜드 리서치 및 소재 조사"
        ),
        ActivityData(
            startAt = "2021.11",
            endAt = "2022.01",
            organization = "서티그룹",
            role = "패션디자이너 인턴",
            description = "브랜드 리서치 및 소재 조사"
        ),
        ActivityData(
            startAt = "2021.11",
            endAt = "2022.01",
            organization = "서티그룹",
            role = "패션디자이너 인턴",
            description = "브랜드 리서치 및 소재 조사"
        )
    )

    ResumeActivitiesScreen(
        onNavigateToAdd = onNavigateToAddActivities,
        resumeDataList = resumeDataList,
        onDeleteDialogShow = showDialog,
        onDeleteClick = { showDialog = true },
        onDialogConfirm = { showDialog = false },
        onDialogDismiss = { showDialog = false },
        modifier = Modifier.padding(padding)
    )
}

@Composable
fun ResumeActivitiesScreen(
    onNavigateToAdd: () -> Unit,
    resumeDataList: List<ActivityData>,
    onDeleteDialogShow: Boolean,
    onDeleteClick: () -> Unit,
    onDialogConfirm: () -> Unit,
    onDialogDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (onDeleteDialogShow) {
        CertiDeleteDialog(
            onConfirmClick = onDialogConfirm,
            onDismissClick = onDialogDismiss
        )
    }
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
            startAt = "2021.11",
            endAt = "2022.01",
            organization = "서티그룹",
            role = "패션디자이너 인턴",
            description = "브랜드 리서치 및 소재 조사"
        ),
        ActivityData(
            startAt = "2021.11",
            endAt = "2022.01",
            organization = "서티그룹",
            role = "패션디자이너 인턴",
            description = "브랜드 리서치 및 소재 조사"
        ),
        ActivityData(
            startAt = "2021.11",
            endAt = "2022.01",
            organization = "서티그룹",
            role = "패션디자이너 인턴",
            description = "브랜드 리서치 및 소재 조사"
        )
    )

    var showDialog by remember { mutableStateOf(false) }

    CERTITheme {
        ResumeActivitiesScreen(
            resumeDataList = resumeDataList,
            onNavigateToAdd = {},
            onDeleteDialogShow = false,
            onDeleteClick = { showDialog = true },
            onDialogConfirm = { showDialog = false },
            onDialogDismiss = { showDialog = false }
        )
    }
}
