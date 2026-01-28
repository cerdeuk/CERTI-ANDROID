package org.sopt.certi.domain.model

data class DateData(
    val year: Int? = null,
    val month: Int? = null,
    val day: Int? = null
) {
    val isAllEmpty: Boolean get() = year == null && month == null && day == null
    val isComplete: Boolean get() = year != null && month != null && day != null
    val isValid: Boolean get() = isAllEmpty || isComplete
}
