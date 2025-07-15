package org.sopt.certi.presentation.ui.resume.activity

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
import org.sopt.certi.presentation.ui.resume.main.ResumeViewModel
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun ResumeAddActivitiesRoute(
    padding: PaddingValues,
    navigateToResume: () -> Unit,
    viewModel: ResumeViewModel = hiltViewModel()
) {
    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }
    var organizationValue by remember { mutableStateOf("") }
    var activityValue by remember { mutableStateOf("") }
    var descriptionValue by remember { mutableStateOf("") }
    val addButtonEnabled by remember(startDate, endDate, organizationValue, activityValue, descriptionValue) {
        derivedStateOf {
            startDate.isNotBlank() && endDate.isNotBlank() && organizationValue.isNotBlank() && activityValue.isNotBlank() && descriptionValue.isNotBlank()
        }
    }

    ResumeAddActivitiesScreen(
        startDate = startDate,
        endDate = endDate,
        organizationValue = organizationValue,
        activityValue = activityValue,
        descriptionValue = descriptionValue,
        addButtonEnabled = addButtonEnabled,
        onStartDateValueChange = { startDate = it },
        onEndDateValueChange = { endDate = it },
        onOrganizationValueChange = { organizationValue = it },
        onActivityValueChange = { activityValue = it },
        onDescriptionValue = { descriptionValue = it },
        navigateToResume = navigateToResume,
        modifier = Modifier.padding(padding)
    )
}

@Composable
fun ResumeAddActivitiesScreen(
    startDate: String,
    endDate: String,
    organizationValue: String,
    activityValue: String,
    descriptionValue: String,
    addButtonEnabled: Boolean,
    onStartDateValueChange: (String) -> Unit,
    onEndDateValueChange: (String) -> Unit,
    onOrganizationValueChange: (String) -> Unit,
    onActivityValueChange: (String) -> Unit,
    onDescriptionValue: (String) -> Unit,
    navigateToResume: () -> Unit,
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
                    startDate = startDate,
                    endDate = endDate,
                    onStartDateValueChange = onStartDateValueChange,
                    onEndDateValueChange = onEndDateValueChange,
                    modifier = Modifier.padding(bottom = screenHeightDp(36.dp))
                )
            }

            item {
                ResumeTextInputSection(
                    title = stringResource(R.string.resume_activities_organization),
                    value = organizationValue,
                    onValueChange = onOrganizationValueChange,
                    maxLength = 30,
                    modifier = Modifier.padding(bottom = screenHeightDp(36.dp))
                )
            }

            item {
                ResumeTextInputSection(
                    title = stringResource(R.string.resume_activities_activity),
                    value = activityValue,
                    onValueChange = onActivityValueChange,
                    maxLength = 30,
                    modifier = Modifier.padding(bottom = screenHeightDp(36.dp))
                )
            }

            item {
                ResumeTextInputSection(
                    title = stringResource(R.string.resume_activities_description),
                    value = descriptionValue,
                    onValueChange = onDescriptionValue,
                    maxLength = 80,
                    modifier = Modifier.padding(bottom = screenHeightDp(36.dp))
                )
            }
        }

        CertiBasicButton(
            buttonText = stringResource(R.string.resume_add_button),
            onClick = navigateToResume,
            enabled = addButtonEnabled,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = screenHeightDp(24.dp))
                .padding(horizontal = screenWidthDp(20.dp))
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewResumeAddActivitiesScreen() {
    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }
    var organizationValue by remember { mutableStateOf("") }
    var activityValue by remember { mutableStateOf("") }
    var descriptionValue by remember { mutableStateOf("") }
    val addButtonEnabled by remember(startDate, endDate, organizationValue, activityValue, descriptionValue) {
        derivedStateOf {
            startDate.isNotBlank() && endDate.isNotBlank() && organizationValue.isNotBlank() && activityValue.isNotBlank() && descriptionValue.isNotBlank()
        }
    }

    CERTITheme {
        ResumeAddActivitiesScreen(
            startDate = startDate,
            endDate = endDate,
            organizationValue = organizationValue,
            activityValue = activityValue,
            descriptionValue = descriptionValue,
            addButtonEnabled = addButtonEnabled,
            onStartDateValueChange = { startDate = it },
            onEndDateValueChange = { endDate = it },
            onOrganizationValueChange = { organizationValue = it },
            onActivityValueChange = { activityValue = it },
            onDescriptionValue = { descriptionValue = it },
            navigateToResume = {}
        )
    }
}
