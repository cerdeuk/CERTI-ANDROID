package org.sopt.certi.presentation.ui.workExperience

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import org.sopt.certi.presentation.ui.resume.component.ResumeDateInputSection
import org.sopt.certi.presentation.ui.resume.component.ResumeTextInputSection
import org.sopt.certi.presentation.ui.workExperience.state.AddWorkExperienceUiState
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun ResumeAddWorkExperienceRoute(
    padding: PaddingValues,
    onNavigateToResume: () -> Unit,
    viewModel: AddWorkExperienceViewModel = hiltViewModel()
) {
    val uiState by viewModel.addWorkExperienceUiState.collectAsStateWithLifecycle()
    val addCareerSuccess by viewModel.addCareerSuccess.collectAsStateWithLifecycle()

    LaunchedEffect(addCareerSuccess) {
        if (addCareerSuccess) {
            onNavigateToResume()
            viewModel.resetAddCareerSuccess()
        }
    }

    ResumeAddWorkExperienceScreen(
        uiState = uiState,
        titleResId = R.string.resume_work_experience_add_title,
        buttonTextResId = R.string.button_add,
        onStartDateValueChange = viewModel::onStartDateChanged,
        onEndDateValueChange = viewModel::onEndDateChanged,
        onOrganizationValueChange = viewModel::onOrganizationChanged,
        onRoleValueChange = viewModel::onRoleChanged,
        onDescriptionValueChange = viewModel::onDescriptionChanged,
        onButtonClick = viewModel::addCareer,
        modifier = Modifier.padding(top = padding.calculateTopPadding())
    )
}

@Composable
fun ResumeEditWorkExperienceRoute(
    padding: PaddingValues,
    onNavigateToResume: () -> Unit,
    viewModel: EditWorkExperienceViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val editSuccess by viewModel.editCareerSuccess.collectAsStateWithLifecycle()

    LaunchedEffect(editSuccess) {
        if (editSuccess) {
            onNavigateToResume()
            viewModel.resetEditCareerSuccess()
        }
    }

    ResumeAddWorkExperienceScreen(
        uiState = uiState,
        titleResId = R.string.resume_work_experience_edit_title,
        buttonTextResId = R.string.button_edit,
        onStartDateValueChange = viewModel::onStartDateChanged,
        onEndDateValueChange = viewModel::onEndDateChanged,
        onOrganizationValueChange = viewModel::onOrganizationChanged,
        onRoleValueChange = viewModel::onRoleChanged,
        onDescriptionValueChange = viewModel::onDescriptionChanged,
        onButtonClick = viewModel::editCareer,
        modifier = Modifier.padding(top = padding.calculateTopPadding())
    )
}

@Composable
fun ResumeAddWorkExperienceScreen(
    uiState: AddWorkExperienceUiState,
    titleResId: Int,
    buttonTextResId: Int,
    onStartDateValueChange: (String) -> Unit,
    onEndDateValueChange: (String) -> Unit,
    onOrganizationValueChange: (String) -> Unit,
    onRoleValueChange: (String) -> Unit,
    onDescriptionValueChange: (String) -> Unit,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.ime.union(WindowInsets.navigationBars))
            .verticalScroll(rememberScrollState())
            .padding(horizontal = screenWidthDp(20.dp))
    ) {
        Text(
            text = stringResource(titleResId),
            style = CertiTheme.typography.subtitle.semibold_20,
            color = CertiTheme.colors.gray600,
            modifier = Modifier.padding(top = screenHeightDp(60.dp), bottom = screenHeightDp(24.dp))
        )

        ResumeDateInputSection(
            title = stringResource(R.string.resume_work_experience_period),
            startDate = uiState.startDate,
            endDate = uiState.endDate,
            onStartDateValueChange = onStartDateValueChange,
            onEndDateValueChange = onEndDateValueChange,
            modifier = Modifier.padding(bottom = screenHeightDp(36.dp))
        )

        ResumeTextInputSection(
            title = stringResource(R.string.resume_work_experience_organization),
            value = uiState.organizationValue,
            onValueChange = onOrganizationValueChange,
            maxLength = 10,
            modifier = Modifier.padding(bottom = screenHeightDp(36.dp)),
            imeAction = ImeAction.Next
        )

        ResumeTextInputSection(
            title = stringResource(R.string.resume_work_experience_role),
            value = uiState.roleValue,
            onValueChange = onRoleValueChange,
            maxLength = 10,
            modifier = Modifier.padding(bottom = screenHeightDp(36.dp)),
            imeAction = ImeAction.Next
        )

        ResumeTextInputSection(
            title = stringResource(R.string.resume_work_experience_description),
            value = uiState.descriptionValue,
            onValueChange = onDescriptionValueChange,
            maxLength = 16,
            modifier = Modifier.padding(bottom = screenHeightDp(36.dp)),
            imeAction = ImeAction.Done
        )

        CertiBasicButton(
            buttonText = stringResource(buttonTextResId),
            onClick = onButtonClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = screenHeightDp(16.dp)),
            enabled = uiState.addButtonEnabled
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewResumeAddWorkExperienceScreen() {
    CERTITheme {
        ResumeAddWorkExperienceScreen(
            uiState = AddWorkExperienceUiState(
                startDate = "",
                endDate = "",
                organizationValue = "",
                roleValue = "",
                descriptionValue = "",
                addButtonEnabled = true
            ),
            onStartDateValueChange = {},
            onEndDateValueChange = {},
            onOrganizationValueChange = {},
            onRoleValueChange = {},
            onDescriptionValueChange = {},
            onButtonClick = {},
            titleResId = R.string.resume_work_experience_add_title,
            buttonTextResId = R.string.button_add
        )
    }
}
