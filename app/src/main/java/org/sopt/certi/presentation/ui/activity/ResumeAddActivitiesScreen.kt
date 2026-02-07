package org.sopt.certi.presentation.ui.activity

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
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
import org.sopt.certi.ui.theme.CERTITheme
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
        titleResId = R.string.resume_activities_add_title,
        buttonTextResId = R.string.button_add,
        onStartDateValueChange = viewModel::onStartDateChanged,
        onEndDateValueChange = viewModel::onEndDateChanged,
        onOrganizationValueChange = viewModel::onOrganizationChanged,
        onActivityValueChange = viewModel::onActivityChanged,
        onDescriptionValue = viewModel::onDescriptionChanged,
        onButtonClick = viewModel::addActivity,
        modifier = Modifier.padding(top = padding.calculateTopPadding())
    )
}

@Composable
fun ResumeEditActivitiesRoute(
    padding: PaddingValues,
    navigateToResume: () -> Unit,
    viewModel: EditActivitiesViewModel = hiltViewModel()
) {
    val uiState by viewModel.addActivityUiState.collectAsStateWithLifecycle()
    val editSuccess by viewModel.editActivitySuccess.collectAsStateWithLifecycle()

    LaunchedEffect(editSuccess) {
        if (editSuccess) {
            navigateToResume()
            viewModel.resetEditActivitySuccess()
        }
    }

    ResumeAddActivitiesScreen(
        uiState = uiState,
        titleResId = R.string.resume_activities_edit_title,
        buttonTextResId = R.string.button_edit,
        onStartDateValueChange = viewModel::onStartDateChanged,
        onEndDateValueChange = viewModel::onEndDateChanged,
        onOrganizationValueChange = viewModel::onOrganizationChanged,
        onActivityValueChange = viewModel::onActivityChanged,
        onDescriptionValue = viewModel::onDescriptionChanged,
        onButtonClick = viewModel::editActivity,
        modifier = Modifier.padding(top = padding.calculateTopPadding())
    )
}

@Composable
fun ResumeAddActivitiesScreen(
    uiState: AddActivityUiState,
    titleResId: Int,
    buttonTextResId: Int,
    onStartDateValueChange: (String) -> Unit,
    onEndDateValueChange: (String) -> Unit,
    onOrganizationValueChange: (String) -> Unit,
    onActivityValueChange: (String) -> Unit,
    onDescriptionValue: (String) -> Unit,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = screenWidthDp(20.dp))
            .imePadding()
    ) {
        Text(
            text = stringResource(titleResId),
            style = CertiTheme.typography.subtitle.semibold_20,
            color = CertiTheme.colors.gray600,
            modifier = Modifier.padding(
                top = screenHeightDp(60.dp),
                bottom = screenHeightDp(24.dp)
            )
        )

        ResumeDateInputSection(
            title = stringResource(R.string.resume_activities_period),
            startDate = uiState.startDate,
            endDate = uiState.endDate,
            onStartDateValueChange = onStartDateValueChange,
            onEndDateValueChange = onEndDateValueChange,
            modifier = Modifier.padding(bottom = screenHeightDp(36.dp))
        )

        ResumeTextInputSection(
            title = stringResource(R.string.resume_activities_organization),
            value = uiState.organizationValue,
            onValueChange = onOrganizationValueChange,
            maxLength = 10,
            modifier = Modifier.padding(bottom = screenHeightDp(36.dp)),
            imeAction = ImeAction.Next
        )

        ResumeTextInputSection(
            title = stringResource(R.string.resume_activities_activity),
            value = uiState.activityValue,
            onValueChange = onActivityValueChange,
            maxLength = 10,
            modifier = Modifier.padding(bottom = screenHeightDp(36.dp)),
            imeAction = ImeAction.Next
        )

        ResumeTextInputSection(
            title = stringResource(R.string.resume_activities_description),
            value = uiState.descriptionValue,
            onValueChange = onDescriptionValue,
            maxLength = 16,
            modifier = Modifier.padding(bottom = screenHeightDp(36.dp)),
            imeAction = ImeAction.Done
        )

        CertiBasicButton(
            buttonText = stringResource(buttonTextResId),
            onClick = onButtonClick,
            enabled = uiState.addButtonEnabled,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = screenHeightDp(16.dp))
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ResumeAddActivitiesScreen_Preview() {
    var uiState by remember {
        mutableStateOf(
            AddActivityUiState(
                startDate = "",
                endDate = "",
                organizationValue = "",
                activityValue = "",
                descriptionValue = "",
                addButtonEnabled = false
            )
        )
    }

    CERTITheme {
        ResumeAddActivitiesScreen(
            uiState = uiState,
            onStartDateValueChange = { uiState = uiState.copy(startDate = it) },
            onEndDateValueChange = { uiState = uiState.copy(endDate = it) },
            onOrganizationValueChange = { uiState = uiState.copy(organizationValue = it) },
            onActivityValueChange = { uiState = uiState.copy(activityValue = it) },
            onDescriptionValue = { uiState = uiState.copy(descriptionValue = it) },
            onButtonClick = {},
            titleResId = R.string.resume_activities_add_title,
            buttonTextResId = R.string.button_add
        )
    }
}
