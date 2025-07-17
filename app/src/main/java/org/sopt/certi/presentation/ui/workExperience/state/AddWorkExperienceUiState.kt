package org.sopt.certi.presentation.ui.workExperience.state

data class AddWorkExperienceUiState(
    val startDate: String,
    val endDate: String,
    val organizationValue: String,
    val roleValue: String,
    val descriptionValue: String,
    val addButtonEnabled: Boolean
)
