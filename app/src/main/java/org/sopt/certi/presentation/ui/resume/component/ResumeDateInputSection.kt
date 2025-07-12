package org.sopt.certi.presentation.ui.resume.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.util.roundedBackgroundWithBorder
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun ResumeDateInputSection(
    startDate: String,
    endDate: String,
    onStartDateValueChange: (String) -> Unit,
    onEndDateValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    title: String = stringResource(R.string.resume_work_experience_period)
) {
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
                onValueChange = onStartDateValueChange
            )

            Text(
                text = stringResource(R.string.resume_date_input_text_1),
                style = CertiTheme.typography.caption.semibold_14,
                color = CertiTheme.colors.gray600,
                modifier = Modifier.padding(horizontal = screenWidthDp(8.dp))
            )

            ResumeDateTextField(
                value = endDate,
                onValueChange = onEndDateValueChange
            )

            Text(
                text = stringResource(R.string.resume_date_input_text_2),
                style = CertiTheme.typography.caption.semibold_14,
                color = CertiTheme.colors.gray600,
                modifier = Modifier.padding(horizontal = screenWidthDp(8.dp))
            )
        }
    }
}

@Composable
private fun ResumeDateTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    BasicTextField(
        value = value.take(10),
        onValueChange = onValueChange,
        textStyle = CertiTheme.typography.caption.semibold_12.copy(
            color = CertiTheme.colors.gray300
        ),
        modifier = modifier
            .widthForScreenPercentage(108.dp)
            .roundedBackgroundWithBorder(
                cornerRadius = 1.dp,
                backgroundColor = CertiTheme.colors.white,
                borderColor = CertiTheme.colors.gray100,
                borderWidth = 1.dp
            ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier.padding(vertical = screenHeightDp(8.dp), horizontal = screenWidthDp(12.dp)),
                contentAlignment = Alignment.CenterStart
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = stringResource(R.string.resume_textfield_date),
                        style = CertiTheme.typography.caption.semibold_12,
                        color = CertiTheme.colors.gray200
                    )
                }
                innerTextField()
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun ResumeDateInputSectionPreview() {
    val startDate = remember { mutableStateOf("") }
    val endDate = remember { mutableStateOf("") }

    CERTITheme {
        ResumeDateInputSection(
            startDate = startDate.value,
            endDate = endDate.value,
            onStartDateValueChange = { startDate.value = it },
            onEndDateValueChange = { endDate.value = it }
        )
    }
}
