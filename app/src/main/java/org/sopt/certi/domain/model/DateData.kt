package org.sopt.certi.domain.model

data class DateData(
    val year: Int? = null,
    val month: Int? = null,
    val day: Int? = null
) {
    val yearText: String get() = year?.toString() ?: ""
    val monthText: String get() = month?.let { "%02d".format(it) } ?: ""
    val dayText: String get() = day?.let { "%02d".format(it) } ?: ""

    val isAllEmpty: Boolean get() = year == null && month == null && day == null
    val isComplete: Boolean get() = year != null && month != null && day != null
    val isValid: Boolean get() = isAllEmpty || isComplete
}
