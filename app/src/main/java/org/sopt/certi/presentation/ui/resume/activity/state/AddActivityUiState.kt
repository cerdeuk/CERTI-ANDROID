package org.sopt.certi.presentation.ui.resume.activity.state

data class AddActivityUiState(
    val startDate: String,
    val endDate: String,
    val organizationValue: String,
    val activityValue: String,
    val descriptionValue: String,
    val addButtonEnabled: Boolean
)
