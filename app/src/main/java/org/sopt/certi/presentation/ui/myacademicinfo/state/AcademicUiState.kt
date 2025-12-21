package org.sopt.certi.presentation.ui.myacademicinfo.state

import org.sopt.certi.domain.type.CategoryType

data class AcademicUiState(
    val selectedCategoryList: List<CategoryType> = emptyList()
)
