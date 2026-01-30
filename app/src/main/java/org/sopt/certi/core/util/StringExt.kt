package org.sopt.certi.core.util

import org.sopt.certi.domain.model.DateData
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DateFormatters {
    val dotDate: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
}

fun String.toLocalDateOrMin(): LocalDate =
    runCatching { LocalDate.parse(this, DateFormatters.dotDate) }
        .getOrElse {
            it.printStackTrace()
            LocalDate.MIN
        }

fun String.toLocalDateOrNull(): LocalDate? =
    runCatching { LocalDate.parse(this, DateFormatters.dotDate) }
        .getOrElse { null }

fun String.dateString(): String {
    return this.padStart(2, '0')
}

fun String.toDateData(): DateData {
    val parts = this.split("-")

    return DateData(
        year = parts[0].toInt(),
        month = parts[1].toInt(),
        day = parts[2].toInt()
    )
}

fun String.toDateFormat(): String {
    return try {
        val date = LocalDate.parse(this)
        val formatter = DateTimeFormatter.ofPattern("yyyy. MM. dd")
        date.format(formatter)
    } catch (e: Exception) {
        this
    }
}
