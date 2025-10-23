package org.sopt.certi.presentation.ui.my.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.component.calendar.DatePickerCalendar
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@Composable
fun PersonalInfoDateInputField(
    label: String,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val formatter = remember { DateTimeFormatter.ofPattern("yyyy.MM.dd") }
    var currentMonth by remember { mutableStateOf(YearMonth.now()) }
    var visible by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(screenWidthDp(12.dp))
    ) {
        Text(
            text = label,
            style = CertiTheme.typography.body.semibold_16,
            color = CertiTheme.colors.gray600
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .border(1.dp, CertiTheme.colors.gray200, RoundedCornerShape(8.dp))
                .noRippleClickable { visible = !visible }
                .padding(screenWidthDp(12.dp)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box {
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = CertiTheme.typography.caption.semibold_14,
                        color = CertiTheme.colors.gray300
                    )
                } else {
                    Text(
                        text = value,
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

        AnimatedVisibility(
            visible = visible,
            enter = expandVertically(expandFrom = Alignment.Top),
            exit = shrinkVertically(shrinkTowards = Alignment.Top)
        ) {
            DatePickerCalendar(
                selectedDate = null,
                currentMonth = currentMonth,
                onDateSelected = { selectedLocalDate ->
                    val formattedDate = selectedLocalDate.format(formatter)
                    onValueChange(formattedDate)
                    visible = false
                },
                onMonthChanged = { newMonth ->
                    currentMonth = newMonth
                },
                modifier = Modifier.padding(top = screenWidthDp(8.dp))
            )
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
                placeholder = "생년월일을 선택해주세요.",
                value = value,
                onValueChange = { value = it }
            )
        }
    }
}
