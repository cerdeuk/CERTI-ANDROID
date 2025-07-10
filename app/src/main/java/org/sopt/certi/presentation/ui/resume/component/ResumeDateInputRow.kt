package org.sopt.certi.presentation.ui.resume.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun ResumeDateInputRow(
    startDate: MutableState<TextFieldValue>,
    endDate: MutableState<TextFieldValue>,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ResumeDateTextField(
            value = startDate.value,
            onValueChange = { startDate.value = it }
        )
        Spacer(modifier = Modifier.width(screenWidthDp(8.dp)))

        Text(
            text = stringResource(R.string.resume_date_input_text_1),
            style = CertiTheme.typography.caption.semibold_14,
            color = CertiTheme.colors.gray600
        )
        Spacer(modifier = Modifier.width(screenWidthDp(8.dp)))

        ResumeDateTextField(
            value = endDate.value,
            onValueChange = { endDate.value = it }
        )
        Spacer(modifier = Modifier.width(screenWidthDp(8.dp)))

        Text(
            text = stringResource(R.string.resume_date_input_text_2),
            style = CertiTheme.typography.caption.semibold_14,
            color = CertiTheme.colors.gray600
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ResumeDateInputRowPreview() {
    val startDate = remember { mutableStateOf(TextFieldValue("")) }
    val endDate = remember { mutableStateOf(TextFieldValue("")) }

    CERTITheme {
        ResumeDateInputRow(
            startDate = startDate,
            endDate = endDate
        )
    }
}
