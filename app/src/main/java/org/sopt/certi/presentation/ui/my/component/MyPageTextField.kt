package org.sopt.certi.presentation.ui.my.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.component.calendar.DatePickerCalendar
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun MyPageTextField(
    label: String,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    trailingIcon: (@Composable (() -> Unit))? = null,
    onTrailingClick: (() -> Unit)? = null
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(screenWidthDp(12.dp))
    ) {
        Text(
            text = label,
            style = CertiTheme.typography.body.semibold_16,
            color = CertiTheme.colors.gray600
        )
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .border(1.dp, CertiTheme.colors.gray200, RoundedCornerShape(8.dp))
                .background(CertiTheme.colors.gray0)
                .padding(screenWidthDp(12.dp)),
            textStyle = CertiTheme.typography.caption.regular_14.copy(
                color = CertiTheme.colors.black
            ),
            maxLines = 1,
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        if (value.isEmpty()) {
                            Text(
                                text = placeholder,
                                style = CertiTheme.typography.caption.semibold_14,
                                color = CertiTheme.colors.gray300
                            )
                        }
                        innerTextField()
                    }

                    when {
                        trailingIcon != null -> Box(
                            modifier = Modifier
                                .padding(start = screenWidthDp(8.dp))
                                .noRippleClickable {
                                    onTrailingClick?.invoke()
                                }
                        ) { trailingIcon() }
                    }
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MyPageTextFieldPreview() {
    var value by remember { mutableStateOf("") }
    var showCalendar by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf<LocalDate?>(LocalDate.now()) }
    var currentMonth by remember { mutableStateOf(YearMonth.now()) }

    CERTITheme {
        Column(
            modifier = Modifier.padding(horizontal = screenWidthDp(20.dp), vertical = screenWidthDp(40.dp))
        ) {
            MyPageTextField(
                label = "생년월일",
                placeholder = "생년월일을 선택해주세요.",
                value = value,
                onValueChange = { value = it },
                trailingIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_arrowdown_24),
                        contentDescription = null,
                        tint = CertiTheme.colors.gray400
                    )
                },
                onTrailingClick = { showCalendar = !showCalendar }
            )

            AnimatedVisibility(showCalendar) {
                DatePickerCalendar(
                    selectedDate = selectedDate,
                    currentMonth = currentMonth,
                    onDateSelected = { newDate ->
                        selectedDate = newDate
                    },
                    onMonthChanged = { newMonth ->
                        currentMonth = newMonth
                    },
                    modifier = Modifier.padding(top = screenWidthDp(8.dp))
                )
            }
        }
    }
}
