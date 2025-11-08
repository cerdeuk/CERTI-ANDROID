package org.sopt.certi.presentation.ui.my.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import org.sopt.certi.R
import org.sopt.certi.core.util.dropShadow
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun PersonalInfoDateInputField(
    label: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val currentDate by remember { mutableStateOf(LocalDate.now()) }

    var yearValue by remember { mutableStateOf("") }
    var monthValue by remember { mutableStateOf("") }
    var dayValue by remember { mutableStateOf("") }

    val yearList = remember(currentDate) {
        (currentDate.year - 100..currentDate.year + 100).reversed().toList()
    }
    val monthList = remember { (1..12).toList() }

    val daysInMonth = remember(yearValue, monthValue) {
        if (yearValue.isNotEmpty() && monthValue.isNotEmpty()) {
            try {
                YearMonth.of(yearValue.toInt(), monthValue.toInt()).lengthOfMonth()
            } catch (e: Exception) { 31 }
        } else { 31 }
    }
    val dayList = remember(daysInMonth) { (1..daysInMonth).toList() }

    LaunchedEffect(yearValue, monthValue) {
        val dayInt = dayValue.toIntOrNull() ?: 0
        if (dayValue.isNotEmpty() && dayInt > daysInMonth) {
            dayValue = ""
        }
    }

    LaunchedEffect(yearValue, monthValue, dayValue) {
        if (yearValue.isNotEmpty() && monthValue.isNotEmpty() && dayValue.isNotEmpty()) {
            val formattedMonth = monthValue.padStart(2, '0')
            val formattedDay = dayValue.padStart(2, '0')
            onValueChange("$yearValue.$formattedMonth.$formattedDay")
        } else {
            onValueChange("")
        }
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
                selectedValue = yearValue,
                onValueChange = { yearValue = it},
                initialScrollItem = currentDate.year
            )
            DatePickerField(
                placeholder = "MM",
                dateList = monthList,
                selectedValue = monthValue,
                onValueChange = { monthValue = it },
                padDisplayValue = true
            )
            DatePickerField(
                placeholder = "DD",
                dateList = dayList,
                selectedValue = dayValue,
                onValueChange = { dayValue = it },
                padDisplayValue = true
            )
        }
    }
}

@Composable
private fun DatePickerField(
    placeholder: String,
    dateList: List<Int>,
    selectedValue: String,
    onValueChange: (String) -> Unit,
    padDisplayValue: Boolean = false,
    initialScrollItem: Int? = null
){
    var visible by remember { mutableStateOf(false) }

    var rowWidth by remember { mutableStateOf(0.dp) }
    var rowHeight by remember { mutableStateOf(0) }
    val density = LocalDensity.current

    val lazyListState = rememberLazyListState()
    val targetItem = selectedValue.toIntOrNull() ?: initialScrollItem
    val initialIndex = remember(dateList, targetItem) {
        targetItem?.let { dateList.indexOf(it) } ?: -1
    }

    LaunchedEffect(visible) {
        if (visible && initialIndex >= 0) {
            lazyListState.scrollToItem(index = initialIndex)
        }
    }

    Column {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .border(1.dp, CertiTheme.colors.gray200, RoundedCornerShape(8.dp))
                .background(CertiTheme.colors.gray0)
                .noRippleClickable { visible = !visible }
                .onSizeChanged {
                    rowWidth = with(density) { it.width.toDp() }
                    rowHeight = it.height
                }
                .padding(vertical = screenHeightDp(8.dp), horizontal = screenWidthDp(12.dp)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(screenWidthDp(12.dp))
        ) {
            Box {
                if (selectedValue.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = CertiTheme.typography.caption.semibold_14,
                        color = CertiTheme.colors.gray300
                    )
                } else {
                    Text(
                        text = if (padDisplayValue) selectedValue.padStart(2, '0') else selectedValue,
                        style = CertiTheme.typography.caption.regular_14,
                        color = CertiTheme.colors.black
                    )
                }
            }
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_arrowdown_24),
                contentDescription = null,
                tint = CertiTheme.colors.gray400
            )
        }
        if(visible) {
            Popup(
                alignment = Alignment.TopStart,
                offset = IntOffset(x = 0, y = rowHeight),
                onDismissRequest = { visible = false },
                properties = PopupProperties(focusable = true)
            ) {
                LazyColumn(
                    state = lazyListState,
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .heightIn(max = screenHeightDp(200.dp))
                        .dropShadow(
                            shape = RoundedCornerShape(4.dp),
                            color = Color.Black.copy(0.05f),
                            blur = 20.dp
                        )
                        .background(CertiTheme.colors.white, RoundedCornerShape(4.dp))
                        .border(1.dp, CertiTheme.colors.gray100, RoundedCornerShape(4.dp))
                        .width(rowWidth)
                ) {
                    items(
                        items = dateList,
                        key = { it }
                    ) { date ->
                        val dateString = date.toString()
                        Text(
                            text = if (padDisplayValue) dateString.padStart(2, '0') else dateString,
                            style = CertiTheme.typography.caption.semibold_12,
                            color = CertiTheme.colors.black,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .noRippleClickable {
                                    onValueChange(dateString)
                                    visible = false
                                }
                                .border(1.dp, CertiTheme.colors.gray100)
                                .padding(vertical = screenHeightDp(12.dp))
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MyPageDateInputFieldPreview() {
    var value by remember { mutableStateOf("") }

    CERTITheme {
        Column(
            modifier = Modifier.padding(horizontal = screenWidthDp(20.dp), vertical = screenWidthDp(40.dp))
        ) {
            PersonalInfoDateInputField(
                label = "생년월일",
                onValueChange = { value = it }
            )
        }
    }
}
