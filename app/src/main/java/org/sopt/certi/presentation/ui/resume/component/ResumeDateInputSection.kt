package org.sopt.certi.presentation.ui.resume.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun ResumeDateInputSection(
    modifier: Modifier = Modifier,
    title: String = stringResource(R.string.resume_section_certification_title),
    startDate: MutableState<TextFieldValue>,
    endDate: MutableState<TextFieldValue>,
    onStartDateValueChange: (TextFieldValue) -> Unit,
    onEndDateValueChange: (TextFieldValue) -> Unit
){
    Column (
        modifier = modifier
    ) {
        ResumeInputTitle(title = title)
        Spacer(modifier = Modifier.height(screenHeightDp(24.dp)))

        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Absolute.SpaceBetween
        ) {
            ResumeDateTextField(
                value = startDate.value,
                onValueChange = onStartDateValueChange
            )

            Text(
                text = stringResource(R.string.resume_date_input_text_1),
                style = CertiTheme.typography.caption.semibold_14,
                color = CertiTheme.colors.gray600,
                modifier = Modifier.padding(horizontal = screenWidthDp(8.dp))
            )

            ResumeDateTextField(
                value = endDate.value,
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

@Preview(showBackground = true)
@Composable
private fun ResumeDateInputSectionPreview(){
    val startDate = remember { mutableStateOf(TextFieldValue("")) }
    val endDate = remember { mutableStateOf(TextFieldValue("")) }

    CERTITheme {
        ResumeDateInputSection(
            startDate = startDate,
            endDate = endDate,
            onStartDateValueChange = {startDate.value = it},
            onEndDateValueChange = {endDate.value = it}
        )
    }
}