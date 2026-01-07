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
    val third: String? = null,
    val draft: String? = null
) {
    val selectedList: List<String>
        get() = listOfNotNull(first, second, third)

    fun currentConfirmed(): String? = when(step){
        JobCategoryStep.FIRST -> first
        JobCategoryStep.SECOND -> second
        JobCategoryStep.THIRD -> third
    }

    fun currentUiSelection(): String? = draft?: currentConfirmed()

    fun disabledOptions(): List<String>{
        val currentSelection = currentConfirmed()
        return selectedList.filterNot { it == currentSelection }
    }
}
