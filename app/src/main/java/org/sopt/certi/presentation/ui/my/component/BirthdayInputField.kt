package org.sopt.certi.presentation.ui.my.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import org.sopt.certi.R
import org.sopt.certi.core.util.dropShadow
import org.sopt.certi.core.util.heightForScreenPercentage
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.padIfNeeded
import org.sopt.certi.core.util.parseDateToYearMonthDay
import org.sopt.certi.core.util.roundedBackgroundWithBorder
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme
import java.time.LocalDate
import java.time.YearMonth

private data class BirthdayFields(
    val year: String,
    val month: String,
    val day: String
)

@Composable
fun BirthdayInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    onValidityChange: (Boolean) -> Unit,
    inputFieldBackgroundColor: Color,
    modifier: Modifier = Modifier
) {
    val parsedDate = value.parseDateToYearMonthDay()

    var birthday by remember(value) {
        mutableStateOf(
            BirthdayFields(
                year = parsedDate.first,
                month = parsedDate.second,
                day = parsedDate.third
            )
        )
    }

    val currentDate = remember { LocalDate.now() }

    val yearList = remember(currentDate) {
        (currentDate.year - 100..currentDate.year + 100).reversed().toList()
    }

    val monthList = remember { (1..12).toList() }

    val daysInMonth = remember(birthday.year, birthday.month) {
        if (birthday.year.isNotEmpty() && birthday.month.isNotEmpty()) {
            try {
                YearMonth.of(birthday.year.toInt(), birthday.month.toInt()).lengthOfMonth()
            } catch (e: Exception) { 31 }
        } else { 31 }
    }
    val dayList = remember(daysInMonth) { (1..daysInMonth).toList() }

    val onDateSelected = { fields: BirthdayFields ->
        fields.toFormattedDateOrNull()?.let(onValueChange)
    }

    LaunchedEffect(birthday.year, birthday.month, birthday.day) {
        var current = birthday

        val dayInt = current.day.toIntOrNull()
        if (current.day.isNotEmpty() && (dayInt == null || dayInt > daysInMonth)) {
            current = current.copy(day = "")
            birthday = current
        }

        val isAllEmpty = current.year.isEmpty() &&
            current.month.isEmpty() &&
            current.day.isEmpty()

        val isComplete = current.year.isNotEmpty() &&
            current.month.isNotEmpty() &&
            current.day.isNotEmpty()

        val isValid = isAllEmpty || isComplete

        onValidityChange(isValid)
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(screenWidthDp(12.dp))
    ) {
        Text(
            text = label,
            style = CertiTheme.typography.body.semibold_16,
            color = CertiTheme.colors.gray600
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(screenWidthDp(8.dp))
        ) {
            DatePickerField(
                placeholder = "YYYY",
                dateList = yearList,
                value = birthday.year,
                onValueChange = { newYear ->
                    birthday = birthday.copy(year = newYear)
                    onDateSelected(birthday)
                },
                backgroundColor = inputFieldBackgroundColor,
                initialScrollItem = currentDate.year,
                modifier = Modifier.widthIn(min = screenWidthDp(94.dp), max = screenWidthDp(114.dp))
            )
            DatePickerField(
                placeholder = "MM",
                dateList = monthList,
                value = birthday.month,
                onValueChange = { newMonth ->
                    birthday = birthday.copy(month = newMonth)
                    onDateSelected(birthday)
                },
                backgroundColor = inputFieldBackgroundColor,
                padDisplayValue = true,
                modifier = Modifier.widthIn(min = screenWidthDp(76.dp), max = screenWidthDp(94.dp))
            )
            DatePickerField(
                placeholder = "DD",
                dateList = dayList,
                value = birthday.day,
                onValueChange = { newDay ->
                    birthday = birthday.copy(day = newDay)
                    onDateSelected(birthday)
                },
                backgroundColor = inputFieldBackgroundColor,
                padDisplayValue = true,
                modifier = Modifier.widthIn(min = screenWidthDp(76.dp), max = screenWidthDp(94.dp))
            )
        }
    }
}

@Composable
private fun DatePickerField(
    placeholder: String,
    dateList: List<Int>,
    value: String,
    onValueChange: (String) -> Unit,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    padDisplayValue: Boolean = false,
    initialScrollItem: Int? = null
) {
    var isDropdownVisible by remember { mutableStateOf(false) }

    var rowWidth by remember { mutableStateOf(0.dp) }
    var rowHeight by remember { mutableIntStateOf(0) }
    val density = LocalDensity.current

    val lazyListState = rememberLazyListState()
    val targetItem = value.toIntOrNull() ?: initialScrollItem
    val initialIndex = remember(dateList, targetItem) {
        targetItem?.let { dateList.indexOf(it) } ?: -1
    }

    LaunchedEffect(isDropdownVisible, initialIndex) {
        if (isDropdownVisible && initialIndex >= 0) {
            lazyListState.scrollToItem(index = initialIndex)
        }
    }

    Column(
        modifier = modifier.width(IntrinsicSize.Max)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .roundedBackgroundWithBorder(
                    cornerRadius = 8.dp,
                    backgroundColor = backgroundColor,
                    borderColor = CertiTheme.colors.gray200,
                    borderWidth = 1.dp
                )
                .noRippleClickable { isDropdownVisible = !isDropdownVisible }
                .onSizeChanged {
                    rowWidth = with(density) { it.width.toDp() }
                    rowHeight = it.height
                }
                .padding(vertical = screenHeightDp(8.dp), horizontal = screenWidthDp(12.dp)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (value.isEmpty()) {
                Text(
                    text = placeholder,
                    style = CertiTheme.typography.caption.semibold_14,
                    color = CertiTheme.colors.gray300
                )
            } else {
                Text(
                    text = value.padIfNeeded(padDisplayValue),
                    style = CertiTheme.typography.caption.regular_14,
                    color = CertiTheme.colors.black
                )
            }
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_arrowdown_24),
                contentDescription = null,
                tint = CertiTheme.colors.gray400
            )
        }
        if (isDropdownVisible) {
            Popup(
                alignment = Alignment.TopStart,
                offset = IntOffset(x = 0, y = rowHeight),
                onDismissRequest = { isDropdownVisible = false },
                properties = PopupProperties(focusable = true)
            ) {
                LazyColumn(
                    state = lazyListState,
                    modifier = Modifier
                        .dropShadow(
                            shape = RoundedCornerShape(4.dp),
                            color = CertiTheme.colors.black.copy(alpha = 0.05f),
                            blur = 20.dp
                        )
                        .clip(RoundedCornerShape(4.dp))
                        .heightForScreenPercentage(240.dp)
                        .background(CertiTheme.colors.white)
                        .width(rowWidth)
                ) {
                    items(
                        items = dateList,
                        key = { it }
                    ) { date ->
                        val dateString = date.toString()
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightForScreenPercentage(40.dp)
                                .noRippleClickable {
                                    onValueChange(dateString)
                                    isDropdownVisible = false
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = dateString.padIfNeeded(padDisplayValue),
                                style = CertiTheme.typography.caption.semibold_12,
                                color = CertiTheme.colors.black
                            )
                            HorizontalDivider(
                                modifier = Modifier.align(Alignment.BottomCenter),
                                color = CertiTheme.colors.gray100
                            )
                        }
                    }
                }
            }
        }
    }
}

private fun BirthdayFields.toFormattedDateOrNull(): String? {
    if (year.isEmpty() || month.isEmpty() || day.isEmpty()) return null
    val formattedMonth = month.padStart(2, '0')
    val formattedDay = day.padStart(2, '0')
    return "$year.$formattedMonth.$formattedDay"
}

@Preview(showBackground = true)
@Composable
private fun MyPageDateInputFieldPreview() {
    var value by remember { mutableStateOf("2004.04.05") }
    var isValid by remember { mutableStateOf(true) }

    CERTITheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            BirthdayInputField(
                label = "생년월일",
                value = value,
                onValueChange = { value = it },
                onValidityChange = { isValid = it },
                inputFieldBackgroundColor = CertiTheme.colors.white,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
        }
    }
}
