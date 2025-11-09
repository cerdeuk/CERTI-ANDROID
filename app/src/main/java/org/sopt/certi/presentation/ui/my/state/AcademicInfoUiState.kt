package org.sopt.certi.presentation.ui.my.state

import org.sopt.certi.domain.type.CategoryType

data class AcademicInfoUiState(
    val selectedCategoryList: List<CategoryType> = emptyList()
)
