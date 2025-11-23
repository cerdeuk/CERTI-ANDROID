package org.sopt.certi.presentation.ui.personalInfo.component

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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import org.sopt.certi.R
import org.sopt.certi.core.util.dropShadow
import org.sopt.certi.core.util.heightForScreenPercentage
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.parseDateToYearMonthDay
import org.sopt.certi.core.util.roundedBackgroundWithBorder
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme
import java.time.LocalDate
import java.time.YearMonth

private const val VISIBLE_DROPBOX_COUNT = 6
private val DROPBOX_ITEM_HEIGHT = 40.dp

private data class BirthdayFields(
    val year: String,
    val month: String,
    val day: String
)

@Composable
fun DateInputField(
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
        (currentDate.year - 100..currentDate.year + 100).reversed().map { it.toString() }
    }
    val monthList = remember { (1..12).map { it.toString().padStart(2, '0') } }
    val daysInMonth = remember(birthday.year, birthday.month) {
        getMaxDays(birthday.year, birthday.month)
    }
    val dayList = remember(daysInMonth) { (1..daysInMonth).map { it.toString().padStart(2, '0') } }
    val handleUpdate = { newFields: BirthdayFields ->
        updateBirthday(
            newFields = newFields,
            onStateUpdate = { birthday = it },
            onValueChange = onValueChange,
            onValidityChange = onValidityChange
        )
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
            DateDropdown(
                placeholder = "YYYY",
                items = yearList,
                value = birthday.year,
                onValueChange = { newYear ->
                    handleUpdate(birthday.copy(year = newYear))
                },
                backgroundColor = inputFieldBackgroundColor,
                initialScrollItem = currentDate.year.toString(),
                modifier = Modifier.widthIn(min = screenWidthDp(94.dp), max = screenWidthDp(114.dp))
            )
            DateDropdown(
                placeholder = "MM",
                items = monthList,
                value = birthday.month,
                onValueChange = { newMonth ->
                    handleUpdate(birthday.copy(month = newMonth))
                },
                backgroundColor = inputFieldBackgroundColor,
                modifier = Modifier.widthIn(min = screenWidthDp(76.dp), max = screenWidthDp(94.dp))
            )
            DateDropdown(
                placeholder = "DD",
                items = dayList,
                value = birthday.day,
                onValueChange = { newDay ->
                    handleUpdate(birthday.copy(day = newDay))
                },
                backgroundColor = inputFieldBackgroundColor,
                modifier = Modifier.widthIn(min = screenWidthDp(76.dp), max = screenWidthDp(94.dp))
            )
        }
    }
}

@Composable
private fun DateDropdown(
    placeholder: String,
    items: List<String>,
    value: String,
    onValueChange: (String) -> Unit,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    initialScrollItem: String? = null
) {
    var isDropdownVisible by remember { mutableStateOf(false) }

    var rowWidth by remember { mutableStateOf(0.dp) }
    var rowHeight by remember { mutableIntStateOf(0) }
    val density = LocalDensity.current

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
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (value.isEmpty()) placeholder else value,
                style = CertiTheme.typography.caption.semibold_12,
                color = if (value.isEmpty()) CertiTheme.colors.gray300 else CertiTheme.colors.black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )

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
                val lazyListState = rememberLazyListState()
                val targetItem = if (value.isNotEmpty()) value else initialScrollItem
                val initialIndex = remember(items, targetItem) {
                    targetItem?.let { items.indexOf(it) } ?: -1
                }

                LaunchedEffect(isDropdownVisible, initialIndex) {
                    if (isDropdownVisible && initialIndex >= 0) {
                        lazyListState.scrollToItem(index = initialIndex)
                    }
                }

                LazyColumn(
                    state = lazyListState,
                    modifier = Modifier
                        .dropShadow(
                            shape = RoundedCornerShape(4.dp),
                            color = CertiTheme.colors.black.copy(alpha = 0.05f),
                            blur = 20.dp
                        )
                        .clip(RoundedCornerShape(4.dp))
                        .heightForScreenPercentage(DROPBOX_ITEM_HEIGHT * VISIBLE_DROPBOX_COUNT)
                        .background(CertiTheme.colors.white)
                        .width(rowWidth)
                ) {
                    items(items) { item ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightForScreenPercentage(DROPBOX_ITEM_HEIGHT)
                                .noRippleClickable {
                                    onValueChange(item)
                                    isDropdownVisible = false
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = item,
                                style = CertiTheme.typography.caption.semibold_12,
                                color = CertiTheme.colors.black,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
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
    return "$year.$month.$day"
}

private fun getMaxDays(year: String, month: String): Int {
    return if (year.isNotEmpty() && month.isNotEmpty()) {
        runCatching {
            YearMonth.of(year.toInt(), month.toInt()).lengthOfMonth()
        }.getOrDefault(31)
    } else {
        31
    }
}

private fun updateBirthday(
    newFields: BirthdayFields,
    onStateUpdate: (BirthdayFields) -> Unit,
    onValueChange: (String) -> Unit,
    onValidityChange: (Boolean) -> Unit
) {
    var validatedFields = newFields

    val maxDays = getMaxDays(validatedFields.year, validatedFields.month)
    val currentDay = validatedFields.day.toIntOrNull()
    if (currentDay != null && currentDay > maxDays) {
        validatedFields = validatedFields.copy(day = "")
    }

    onStateUpdate(validatedFields)

    validatedFields.toFormattedDateOrNull()?.let(onValueChange)

    val isAllEmpty = validatedFields.year.isEmpty() &&
        validatedFields.month.isEmpty() &&
        validatedFields.day.isEmpty()
    val isComplete = validatedFields.year.isNotEmpty() &&
        validatedFields.month.isNotEmpty() &&
        validatedFields.day.isNotEmpty()

    onValidityChange(isAllEmpty || isComplete)
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
            DateInputField(
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
