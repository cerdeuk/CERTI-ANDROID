package org.sopt.certi.presentation.ui.onboarding.state

enum class JobCategoryStep {
    FIRST,
    SECOND,
    THIRD
}

data class OnBoardingJobCategoryUiState(
    val step: JobCategoryStep = JobCategoryStep.FIRST,
    val first: String? = null,
    val second: String? = null,
    val third: String? = null
) {
    val selectedList: List<String>
        get() = listOfNotNull(first, second, third)
}
