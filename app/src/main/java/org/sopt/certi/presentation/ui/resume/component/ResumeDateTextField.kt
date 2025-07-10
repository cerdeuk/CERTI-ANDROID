package org.sopt.certi.presentation.ui.resume.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun ResumeDateTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = stringResource(R.string.resume_textfield_date)
) {
    BasicTextField(
        value = value,
        onValueChange = {
            val digitOnly = it.text.filter { ch -> ch.isDigit() }.take(8)
            val formatted = formatDateInput(digitOnly)

            val newValue = TextFieldValue(
                text = formatted,
                selection = TextRange(formatted.length)
            )
            onValueChange(newValue)
        },
        textStyle = CertiTheme.typography.caption.semibold_12.copy(
            color = CertiTheme.colors.gray600
        ),
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .border(
                width = 1.dp,
                color = CertiTheme.colors.gray100,
                shape = RoundedCornerShape(4.dp)
            )
            .background(CertiTheme.colors.white),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier.padding(vertical = screenHeightDp(8.dp), horizontal = screenWidthDp(12.dp)),
                contentAlignment = Alignment.CenterStart
            ) {
                if (value.text.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = CertiTheme.typography.caption.semibold_12,
                        color = CertiTheme.colors.gray200
                    )
                }
                innerTextField()
            }
        }
    )
}

private fun formatDateInput(input: String): String {
    return when {
        input.length in 1..4 -> input + if (input.length == 4) "." else ""
        input.length in 5..6 -> "${input.substring(0, 4)}.${input.substring(4)}."
        input.length in 7..8 -> "${input.substring(0, 4)}.${input.substring(4, 6)}.${input.substring(6)}"
        else -> input
    }
}

@Preview(showBackground = true)
@Composable
fun ResumeDateInputPreview() {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    CERTITheme {
        ResumeDateTextField(
            value = textState.value,
            onValueChange = { textState.value = it }
        )
    }
}
