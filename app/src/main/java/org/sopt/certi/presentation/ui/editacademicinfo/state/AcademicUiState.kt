package org.sopt.certi.presentation.ui.editacademicinfo.state

import org.sopt.certi.domain.type.CategoryType

data class AcademicUiState(
    val selectedCategoryList: List<CategoryType> = emptyList()
)
