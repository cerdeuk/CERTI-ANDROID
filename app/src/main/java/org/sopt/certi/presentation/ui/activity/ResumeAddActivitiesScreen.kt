package org.sopt.certi.presentation.ui.activity

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.sopt.certi.R
import org.sopt.certi.core.component.button.CertiBasicButton
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.presentation.ui.activity.state.AddActivityUiState
import org.sopt.certi.presentation.ui.resume.component.ResumeDateInputSection
import org.sopt.certi.presentation.ui.resume.component.ResumeTextInputSection
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun ResumeAddActivitiesRoute(
    padding: PaddingValues,
    navigateToResume: () -> Unit,
    viewModel: AddActivityViewModel = hiltViewModel()
) {
    val uiState by viewModel.addActivityUiState.collectAsStateWithLifecycle()
    val addActivitySuccess by viewModel.addActivitySuccess.collectAsStateWithLifecycle()

    LaunchedEffect(addActivitySuccess) {
        if (addActivitySuccess) {
            navigateToResume()
            viewModel.resetAddActivitySuccess()
        }
    }

    ResumeAddActivitiesScreen(
        uiState = uiState,
        onStartDateValueChange = { viewModel.onStartDateChanged(it) },
        onEndDateValueChange = { viewModel.onEndDateChanged(it) },
        onOrganizationValueChange = { viewModel.onOrganizationChanged(it) },
        onActivityValueChange = { viewModel.onActivityChanged(it) },
        onDescriptionValue = { viewModel.onDescriptionChanged(it) },
        onAddClick = { viewModel.addActivity() },
        modifier = Modifier.padding(padding)
    )
}

@Composable
fun ResumeAddActivitiesScreen(
    uiState: AddActivityUiState,
    onStartDateValueChange: (String) -> Unit,
    onEndDateValueChange: (String) -> Unit,
    onOrganizationValueChange: (String) -> Unit,
    onActivityValueChange: (String) -> Unit,
    onDescriptionValue: (String) -> Unit,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(
                horizontal = screenWidthDp(20.dp)
            )
        ) {
            item {
                Text(
                    text = stringResource(R.string.resume_activities_add_title),
                    style = CertiTheme.typography.subtitle.semibold_20,
                    color = CertiTheme.colors.gray600,
                    modifier = Modifier.padding(
                        top = screenHeightDp(60.dp),
                        bottom = screenHeightDp(24.dp)
                    )
                )
            }

            item {
                ResumeDateInputSection(
                    title = stringResource(R.string.resume_activities_period),
                    startDate = uiState.startDate,
                    endDate = uiState.endDate,
                    onStartDateValueChange = onStartDateValueChange,
                    onEndDateValueChange = onEndDateValueChange,
                    modifier = Modifier.padding(bottom = screenHeightDp(36.dp))
                )
            }

            item {
                ResumeTextInputSection(
                    title = stringResource(R.string.resume_activities_organization),
                    value = uiState.organizationValue,
                    onValueChange = onOrganizationValueChange,
                    maxLength = 10,
                    modifier = Modifier.padding(bottom = screenHeightDp(36.dp)),
                    imeAction = ImeAction.Next
                )
            }

            item {
                ResumeTextInputSection(
                    title = stringResource(R.string.resume_activities_activity),
                    value = uiState.activityValue,
                    onValueChange = onActivityValueChange,
                    maxLength = 10,
                    modifier = Modifier.padding(bottom = screenHeightDp(36.dp)),
                    imeAction = ImeAction.Next
                )
            }

            item {
                ResumeTextInputSection(
                    title = stringResource(R.string.resume_activities_description),
                    value = uiState.descriptionValue,
                    onValueChange = onDescriptionValue,
                    maxLength = 16,
                    modifier = Modifier.padding(bottom = screenHeightDp(36.dp)),
                    imeAction = ImeAction.Done
                )
            }
        }

        CertiBasicButton(
            buttonText = stringResource(R.string.button_add),
            onClick = onAddClick,
            enabled = uiState.addButtonEnabled,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = screenHeightDp(24.dp))
                .padding(horizontal = screenWidthDp(20.dp))
        )
    }
}
