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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.CalendarMonth
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import org.sopt.certi.R
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.ui.theme.CertiTheme
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun HomeCalendarView(
    scheduleExistDayList: List<String> = emptyList()
) {
    var currentMonth by remember { mutableStateOf(YearMonth.now()) }
    val startMonth by remember { mutableStateOf(currentMonth.minusMonths(100)) }
    val endMonth by remember { mutableStateOf(currentMonth.plusMonths(100)) }
    val firstDayOfWeek = remember { firstDayOfWeekFromLocale() }
    val today = remember { LocalDate.now() }

    var selectedDate by remember { mutableStateOf(CalendarDay(today, DayPosition.MonthDate)) }

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
                },
                onNextMonthClick = {
                    currentMonth = currentMonth.plusMonths(1)
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
                        isScheduleExist = false,
                    ) { newSelectedDate ->
                        selectedDate = newSelectedDate
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
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = calendarMonth.year.toString() + "년 " + calendarMonth.monthValue.toString() + "월",
            style = CertiTheme.typography.body.semibold_16,
        )

        Spacer(Modifier.weight(1f))

        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_calendar_arrow_left),
            contentDescription = null,
            tint = CertiTheme.colors.purpleBlue,
            modifier = Modifier.size(16.dp)
                .noRippleClickable {
                    onPrevMonthClick()
                }
        )

        Spacer(Modifier.width(20.dp))

        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_calendar_arrow_right),
            contentDescription = null,
            tint = CertiTheme.colors.purpleBlue,
            modifier = Modifier.size(16.dp)
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
            .padding(10.dp)
            .clip(CircleShape)
            .background(
                color =
                    if (day.date == today) CertiTheme.colors.mainBlue
                    else {
                        if (isSelected) CertiTheme.colors.gray100 else Color.Transparent
                    }
            )
            .clickable(
                onClick = { onClick(day) },
            ),
        contentAlignment = Alignment.Center,
    ) {
        val textColor = when (day.position) {
            DayPosition.MonthDate -> if(day.date == today) CertiTheme.colors.white else CertiTheme.colors.black
            DayPosition.InDate, DayPosition.OutDate -> CertiTheme.colors.gray200
        }

        if(isScheduleExist) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_calendar_dot),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(20.dp)
                    .padding(start = 16.dp, bottom = 16.dp)
            )
        }

        Text(
            text = day.date.dayOfMonth.toString(),
            style = CertiTheme.typography.caption.regular_12,
            color = textColor,
        )
    }
}

@Composable
private fun WeekDayHeader() {
    val weekNameList = stringArrayResource(R.array.home_calendar_week)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(36.dp),
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
                text = dayOfWeek,
            )
        }
    }
}




@Preview
@Composable
private fun PreviewHomeCalendarView() {
    HomeCalendarView()
}