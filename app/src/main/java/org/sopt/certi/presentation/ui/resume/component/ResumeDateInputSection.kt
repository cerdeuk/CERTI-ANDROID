package org.sopt.certi.presentation.ui.resume.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.component.calendar.DatePickerCalendar
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.roundedBackgroundWithBorder
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme
import java.time.YearMonth
import java.time.format.DateTimeFormatter

private enum class EditingField {
    START, END, NONE
}

@Composable
fun ResumeDateInputSection(
    title: String,
    startDate: String,
    endDate: String,
    onStartDateValueChange: (String) -> Unit,
    onEndDateValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var editingField by remember { mutableStateOf(EditingField.NONE) }
    val formatter = remember { DateTimeFormatter.ofPattern("yyyy.MM.dd") }

    Column(
        modifier = modifier
    ) {
        ResumeInputTitle(title = title)

        Spacer(modifier = Modifier.height(screenHeightDp(24.dp)))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            ResumeDateTextField(
                value = startDate,
                onClick = {
                    editingField = if (editingField == EditingField.START) EditingField.NONE else EditingField.START
                },
                placeholder = stringResource(R.string.resume_start_placeholder)
            )

            Text(
                text = stringResource(R.string.resume_date_input_text_1),
                style = CertiTheme.typography.caption.semibold_14,
                color = CertiTheme.colors.gray600,
                modifier = Modifier.padding(horizontal = screenWidthDp(8.dp))
            )

            ResumeDateTextField(
                value = endDate,
                onClick = {
                    editingField = if (editingField == EditingField.END) EditingField.NONE else EditingField.END
                },
                placeholder = stringResource(R.string.resume_end_placeholder)
            )

            Text(
                text = stringResource(R.string.resume_date_input_text_2),
                style = CertiTheme.typography.caption.semibold_14,
                color = CertiTheme.colors.gray600,
                modifier = Modifier.padding(horizontal = screenWidthDp(8.dp))
            )
        }

        AnimatedVisibility(
            visible = editingField != EditingField.NONE,
            enter = expandVertically(expandFrom = Alignment.Top),
            exit = shrinkVertically(shrinkTowards = Alignment.Top)
        ) {
            DatePickerCalendar(
                selectedDate = null,
                currentMonth = YearMonth.now(),
                onDateSelected = { selectedLocalDate ->
                    val formattedDate = selectedLocalDate.format(formatter)
                    when (editingField) {
                        EditingField.START -> onStartDateValueChange(formattedDate)
                        EditingField.END -> onEndDateValueChange(formattedDate)
                        EditingField.NONE -> {}
                    }
                    editingField = EditingField.NONE
                },
                onMonthChanged = {},
                modifier = Modifier.padding(top = screenWidthDp(12.dp))
            )
        }
    }
}

@Composable
private fun ResumeDateTextField(
    value: String,
    onClick: () -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .widthForScreenPercentage(120.dp)
            .roundedBackgroundWithBorder(
                cornerRadius = 4.dp,
                backgroundColor = CertiTheme.colors.white,
                borderColor = CertiTheme.colors.gray100,
                borderWidth = 1.dp
            )
            .noRippleClickable { onClick() }
            .padding(screenWidthDp(12.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (value.isEmpty()) {
            Text(
                text = placeholder,
                style = CertiTheme.typography.caption.semibold_12,
                color = CertiTheme.colors.gray300
            )
        } else {
            Text(
                text = value,
                style = CertiTheme.typography.caption.semibold_12,
                color = CertiTheme.colors.gray600
            )
        }

        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_arrowdown_24),
            contentDescription = null,
            tint = CertiTheme.colors.gray400
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ResumeDateInputSectionPreview() {
    val startDate = remember { mutableStateOf("") }
    val endDate = remember { mutableStateOf("") }

    CERTITheme {
        ResumeDateInputSection(
            title = stringResource(R.string.resume_activities_period),
            startDate = startDate.value,
            endDate = endDate.value,
            onStartDateValueChange = { startDate.value = it },
            onEndDateValueChange = { endDate.value = it }
        )
    }
}
