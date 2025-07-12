package org.sopt.certi.presentation.ui.resume

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
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
import org.sopt.certi.core.component.button.CertiBasicButton
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.presentation.ui.resume.component.ResumeDateInputSection
import org.sopt.certi.presentation.ui.resume.component.ResumeTextInputSection
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun ResumeAddWorkExperienceRoute(
    padding: PaddingValues,
    onNavigateToResume: () -> Unit,
    viewModel: ResumeViewModel = hiltViewModel()
) {
    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }
    var organizationValue by remember { mutableStateOf("") }
    var roleValue by remember { mutableStateOf("") }
    var descriptionValue by remember { mutableStateOf("") }
    val addButtonEnabled by remember(startDate, endDate, organizationValue, roleValue, descriptionValue) {
        derivedStateOf {
            startDate.isNotBlank() && endDate.isNotBlank() && organizationValue.isNotBlank() && roleValue.isNotBlank() && descriptionValue.isNotBlank()
        }
    }

    ResumeAddWorkExperienceScreen(
        startDate = startDate,
        endDate = endDate,
        onStartDateValueChange = { startDate = it },
        onEndDateValueChange = { endDate = it },
        organizationValue = organizationValue,
        onOrganizationValueChange = { organizationValue = it },
        roleValue = roleValue,
        onRoleValueChange = { roleValue = it },
        descriptionValue = descriptionValue,
        onDescriptionValueChange = { descriptionValue = it },
        onNavigateToResume = onNavigateToResume,
        addButtonEnabled = addButtonEnabled,
        modifier = Modifier.padding(padding)
    )
}

@Composable
fun ResumeAddWorkExperienceScreen(
    startDate: String,
    endDate: String,
    onStartDateValueChange: (String) -> Unit,
    onEndDateValueChange: (String) -> Unit,
    organizationValue: String,
    onOrganizationValueChange: (String) -> Unit,
    roleValue: String,
    onRoleValueChange: (String) -> Unit,
    descriptionValue: String,
    onDescriptionValueChange: (String) -> Unit,
    onNavigateToResume: () -> Unit,
    addButtonEnabled: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(horizontal = screenWidthDp(20.dp))
        ) {
            item {
                Text(
                    text = stringResource(R.string.resume_work_experience_add_title),
                    style = CertiTheme.typography.subtitle.semibold_20,
                    color = CertiTheme.colors.gray600,
                    modifier = Modifier.padding(top = screenHeightDp(60.dp), bottom = screenHeightDp(24.dp))
                )
            }

            item {
                ResumeDateInputSection(
                    title = stringResource(R.string.resume_work_experience_add_title),
                    startDate = startDate,
                    endDate = endDate,
                    onStartDateValueChange = onStartDateValueChange,
                    onEndDateValueChange = onEndDateValueChange,
                    modifier = Modifier.padding(bottom = screenHeightDp(36.dp))
                )
            }

            item {
                ResumeTextInputSection(
                    title = stringResource(R.string.resume_work_experience_organization),
                    value = organizationValue,
                    onValueChange = onOrganizationValueChange,
                    maxLength = 30,
                    modifier = Modifier.padding(bottom = screenHeightDp(36.dp))
                )
            }

            item {
                ResumeTextInputSection(
                    title = stringResource(R.string.resume_work_experience_role),
                    value = roleValue,
                    onValueChange = onRoleValueChange,
                    maxLength = 30,
                    modifier = Modifier.padding(bottom = screenHeightDp(36.dp))
                )
            }

            item {
                ResumeTextInputSection(
                    title = stringResource(R.string.resume_work_experience_description),
                    value = descriptionValue,
                    onValueChange = onDescriptionValueChange,
                    maxLength = 80,
                    modifier = Modifier.padding(bottom = screenHeightDp(36.dp))
                )
            }
        }

        CertiBasicButton(
            buttonText = stringResource(R.string.button_add),
            onClick = onNavigateToResume,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = screenHeightDp(24.dp))
                .padding(horizontal = screenWidthDp(20.dp)),
            enabled = addButtonEnabled
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewResumeAddWorkExperienceScreen() {
    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }
    var organizationValue by remember { mutableStateOf("") }
    var roleValue by remember { mutableStateOf("") }
    var descriptionValue by remember { mutableStateOf("") }
    val addButtonEnabled by remember(startDate, endDate, organizationValue, roleValue, descriptionValue) {
        derivedStateOf {
            startDate.isNotBlank() && endDate.isNotBlank() && organizationValue.isNotBlank() && roleValue.isNotBlank() && descriptionValue.isNotBlank()
        }
    }

    CERTITheme {
        ResumeAddWorkExperienceScreen(
            startDate = startDate,
            endDate = endDate,
            onStartDateValueChange = { startDate = it },
            onEndDateValueChange = { endDate = it },
            organizationValue = organizationValue,
            onOrganizationValueChange = { organizationValue = it },
            roleValue = roleValue,
            onRoleValueChange = { roleValue = it },
            descriptionValue = descriptionValue,
            onDescriptionValueChange = { descriptionValue = it },
            onNavigateToResume = {},
            addButtonEnabled = addButtonEnabled
        )
    }
}
