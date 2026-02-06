package org.sopt.certi.core.util

import org.sopt.certi.domain.model.DateData
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

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

fun String.toSpacedDotDate(): String {
    return try {
        val standardInput = this.replace(".", "-")
        val date = LocalDate.parse(standardInput)
        val formatter = DateTimeFormatter.ofPattern("yyyy. MM. dd")
        date.format(formatter)
    } catch (e: Exception) {
        this
    }
}

fun String.toLocalizedDate(): String {
    return try {
        val standardInput = this.replace(".", "-")
        val date = LocalDate.parse(standardInput)
        val formatter = DateTimeFormatter
            .ofLocalizedDate(FormatStyle.LONG)
            .withLocale(Locale.getDefault())
        date.format(formatter)
    } catch (e: Exception) {
        this
    }
}

fun String.splitDateTime(): Pair<String, String> {
    return try {
        val parsed = LocalDateTime.parse(this)
        val date = parsed.format(DateFormatters.dotDate)
        val time = parsed.toLocalTime().toString()
        date to time
    } catch (e: Exception) {
        this to ""
    }
}
