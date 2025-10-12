package org.sopt.certi.core.component.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import com.kizitonwose.calendar.core.yearMonth
import org.sopt.certi.R
import org.sopt.certi.core.util.dropShadow
import org.sopt.certi.core.util.heightForScreenPercentage
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@Composable
fun DatePickerCalendar(
    selectedDate: LocalDate?,
    currentMonth: YearMonth,
    onDateSelected: (LocalDate) -> Unit,
    onMonthChanged: (YearMonth) -> Unit,
    modifier: Modifier = Modifier
) {
    val initialMonth = remember { currentMonth }
    val startMonth = remember { currentMonth.minusMonths(100) }
    val endMonth = remember { currentMonth.plusMonths(100) }
    val firstDayOfWeek = remember { firstDayOfWeekFromLocale() }

    val state = rememberCalendarState(
        startMonth = startMonth,
        endMonth = endMonth,
        firstVisibleMonth = initialMonth,
        firstDayOfWeek = firstDayOfWeek
    )

    LaunchedEffect(currentMonth) {
        state.animateScrollToMonth(currentMonth)
    }

    LaunchedEffect(state) {
        snapshotFlow { state.firstVisibleMonth.yearMonth }
            .collect { month -> onMonthChanged(month) }
    }

    Column(
        modifier = modifier
            .dropShadow(
                color = Color.Black.copy(alpha = 0.08f),
                shape = RoundedCornerShape(12.dp),
                blur = 20.dp
            )
            .clip(RoundedCornerShape(12.dp))
            .background(CertiTheme.colors.white)
            .padding(screenWidthDp(12.dp))
    ) {
        CalendarHeader(
            currentMonth = currentMonth,
            goToPreviousMonth = { onMonthChanged(currentMonth.minusMonths(1)) },
            goToNextMonth = { onMonthChanged(currentMonth.plusMonths(1)) },
            modifier = Modifier.padding(bottom = screenHeightDp(12.dp))
        )

        DaysOfWeekTitle()

        HorizontalCalendar(
            state = state,
            dayContent = { day ->
                Day(
                    day = day,
                    isSelected = selectedDate == day.date,
                    onDateClick = {
                        if (day.position != DayPosition.MonthDate) {
                            onMonthChanged(day.date.yearMonth)
                        }
                        onDateSelected(day.date)
                    }
                )
            }
        )
    }
}

@Composable
private fun Day(
    day: CalendarDay,
    isSelected: Boolean,
    onDateClick: (LocalDate) -> Unit
) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(screenWidthDp(4.dp))
            .clip(CircleShape)
            .background(
                color = if (isSelected) CertiTheme.colors.gray100 else Color.Transparent
            )
            .noRippleClickable { onDateClick(day.date) },
        contentAlignment = Alignment.Center
    ) {
        val textColor = when (day.position) {
            DayPosition.MonthDate -> when {
                isSelected -> CertiTheme.colors.black
                day.date == LocalDate.now() -> CertiTheme.colors.black
                else -> CertiTheme.colors.black
            }
            DayPosition.InDate, DayPosition.OutDate -> CertiTheme.colors.gray200
        }

        Text(
            text = day.date.dayOfMonth.toString(),
            color = textColor,
            style = CertiTheme.typography.caption.regular_14
        )
    }
}

@Composable
private fun CalendarHeader(
    currentMonth: YearMonth,
    goToPreviousMonth: () -> Unit,
    goToNextMonth: () -> Unit,
    modifier: Modifier = Modifier
) {
    val formatter = remember { DateTimeFormatter.ofPattern("yyyy년 M월") }

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = currentMonth.format(formatter),
            style = CertiTheme.typography.body.semibold_16,
            modifier = Modifier.weight(1f)
        )

        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_calendar_arrow_left),
            contentDescription = null,
            tint = CertiTheme.colors.purpleBlue,
            modifier = Modifier
                .widthForScreenPercentage(16.dp)
                .heightForScreenPercentage(16.dp)
                .noRippleClickable {
                    goToPreviousMonth()
                }
        )
        Spacer(modifier = Modifier.widthForScreenPercentage(20.dp))
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_calendar_arrow_right),
            contentDescription = null,
            tint = CertiTheme.colors.purpleBlue,
            modifier = Modifier
                .widthForScreenPercentage(16.dp)
                .heightForScreenPercentage(16.dp)
                .noRippleClickable {
                    goToNextMonth()
                }
        )
    }
}

@Composable
private fun DaysOfWeekTitle() {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        val daysOfWeek = stringArrayResource(R.array.home_calendar_week)
        for (day in daysOfWeek) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = day,
                    style = CertiTheme.typography.caption.regular_14,
                    color = CertiTheme.colors.gray400
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewDatePickerCalendar() {
    var selectedDate by remember { mutableStateOf<LocalDate?>(LocalDate.now()) }
    var currentMonth by remember { mutableStateOf(YearMonth.now()) }

    CERTITheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(screenWidthDp(20.dp))
        ) {
            DatePickerCalendar(
                selectedDate = selectedDate,
                currentMonth = currentMonth,
                onDateSelected = { newDate ->
                    selectedDate = newDate
                },
                onMonthChanged = { newMonth ->
                    currentMonth = newMonth
                }
            )
        }
    }
}
