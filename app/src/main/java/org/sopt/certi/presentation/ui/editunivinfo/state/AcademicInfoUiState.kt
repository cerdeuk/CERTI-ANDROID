package org.sopt.certi.presentation.ui.editunivinfo.state

import org.sopt.certi.domain.type.CategoryType

data class AcademicInfoUiState(
    val selectedCategoryList: List<CategoryType> = emptyList()
)