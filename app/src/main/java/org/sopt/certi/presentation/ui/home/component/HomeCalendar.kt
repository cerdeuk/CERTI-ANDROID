package org.sopt.certi.presentation.ui.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import org.sopt.certi.R
import org.sopt.certi.core.util.heightForScreenPercentage
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.ui.theme.CertiTheme
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun HomeCalendar(
    scheduleExistDayList: List<String> = emptyList(),
    onMonthMove: (Int, Int) -> Unit,
    dayOnClick: (String) -> Unit
) {
    var currentMonth by rememberSaveable { mutableStateOf(YearMonth.now()) }
    val startMonth by rememberSaveable { mutableStateOf(currentMonth.minusMonths(100)) }
    val endMonth by rememberSaveable { mutableStateOf(currentMonth.plusMonths(100)) }
    val firstDayOfWeek = remember { firstDayOfWeekFromLocale() }
    val today = remember { LocalDate.now() }

    var selectedDate by rememberSaveable { mutableStateOf(CalendarDay(today, DayPosition.MonthDate)) }

    val state = rememberCalendarState(
        startMonth = startMonth,
        endMonth = endMonth,
        firstVisibleMonth = currentMonth,
        firstDayOfWeek = firstDayOfWeek
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CertiTheme.colors.white)
    ) {
        Column {
            MonthHeader(
                calendarMonth = currentMonth,
                onPrevMonthClick = {
                    currentMonth = currentMonth.minusMonths(1)
                    onMonthMove(currentMonth.year, currentMonth.monthValue)
                },
                onNextMonthClick = {
                    currentMonth = currentMonth.plusMonths(1)
                    onMonthMove(currentMonth.year, currentMonth.monthValue)
                }
            )
            WeekDayHeader()
            HorizontalCalendar(
                state = state,
                dayContent = { day ->
                    Day(
                        day = day,
                        isSelected = day == selectedDate,
                        today = today,
                        isScheduleExist = scheduleExistDayList.contains(day.date.toString())
                    ) { newSelectedDate ->
                        selectedDate = newSelectedDate
                        dayOnClick(day.date.toString())
                    }
                }
            )
        }
    }
}

@Composable
private fun MonthHeader(
    calendarMonth: YearMonth,
    onPrevMonthClick: () -> Unit,
    onNextMonthClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = screenHeightDp(12.dp))
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = calendarMonth.year.toString() + "년 " + calendarMonth.monthValue.toString() + "월",
            style = CertiTheme.typography.body.semibold_16
        )

        Spacer(Modifier.weight(1f))

        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_calendar_arrow_left),
            contentDescription = null,
            tint = CertiTheme.colors.purpleBlue,
            modifier = Modifier
                .widthForScreenPercentage(16.dp)
                .heightForScreenPercentage(16.dp)
                .noRippleClickable {
                    onPrevMonthClick()
                }
        )

        Spacer(Modifier.widthForScreenPercentage(20.dp))

        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_calendar_arrow_right),
            contentDescription = null,
            tint = CertiTheme.colors.purpleBlue,
            modifier = Modifier
                .widthForScreenPercentage(16.dp)
                .heightForScreenPercentage(16.dp)
                .noRippleClickable {
                    onNextMonthClick()
                }
        )
    }
}

@Composable
private fun Day(
    day: CalendarDay,
    isSelected: Boolean,
    today: LocalDate,
    isScheduleExist: Boolean = false,
    onClick: (CalendarDay) -> Unit
) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(horizontal = screenWidthDp(10.dp), vertical = screenHeightDp(10.dp))
            .clip(CircleShape)
            .background(
                color =
                if (day.date == today) {
                    if (isSelected) CertiTheme.colors.mainBlue else CertiTheme.colors.gray100
                } else {
                    if (isSelected) CertiTheme.colors.mainBlue else Color.Transparent
                }
            )
            .clickable(
                onClick = { onClick(day) }
            ),
        contentAlignment = Alignment.Center
    ) {
        val textColor = when (day.position) {
            DayPosition.MonthDate -> if (isSelected) CertiTheme.colors.white else CertiTheme.colors.black
            DayPosition.InDate, DayPosition.OutDate -> CertiTheme.colors.gray200
        }

        if (isScheduleExist) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_calendar_dot),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier
                    .widthForScreenPercentage(20.dp)
                    .heightForScreenPercentage(20.dp)
                    .size(20.dp)
                    .padding(start = screenWidthDp(16.dp), bottom = screenHeightDp(16.dp))
            )
        }

        Text(
            text = day.date.dayOfMonth.toString(),
            style = CertiTheme.typography.caption.regular_12,
            color = textColor
        )
    }
}

@Composable
private fun WeekDayHeader() {
    val weekNameList = stringArrayResource(R.array.home_calendar_week)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightForScreenPercentage(36.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        for (dayOfWeek in weekNameList) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically),
                style = CertiTheme.typography.caption.regular_12,
                color = CertiTheme.colors.gray400,
                textAlign = TextAlign.Center,
                text = dayOfWeek
            )
        }
    }
}

@Preview
@Composable
private fun PreviewHomeCalendarView() {
    HomeCalendar(
        onMonthMove = { _, _ -> },
        scheduleExistDayList = listOf("2026-02-06", "2025-10-17", "2025-10-18")
    ) {
    }
}
