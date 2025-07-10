package org.sopt.certi.presentation.ui.resume.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun ResumeTextField(
    value: String,
    onValueChange: (String) -> Unit,
    maxLength: Int,
    modifier: Modifier = Modifier,
    placeholder: String = stringResource(R.string.resume_textfield_placeholder)
) {
    BasicTextField(
        value = value.take(maxLength),
        onValueChange = {
            if (it.length <= maxLength) onValueChange(it)
        },
        modifier = modifier.fillMaxWidth(),
        textStyle = CertiTheme.typography.caption.semibold_14.copy(
            color = CertiTheme.colors.gray600
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        singleLine = true,
        decorationBox = { innerTextField ->
            Column {
                Box {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            style = CertiTheme.typography.caption.semibold_14,
                            color = CertiTheme.colors.gray200
                        )
                    }
                    innerTextField()
                }
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = screenHeightDp(10.dp)),
                    thickness = 1.dp,
                    color = CertiTheme.colors.gray100
                )
                Row {
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = stringResource(R.string.resume_textfield_caption, value.length, maxLength),
                        style = CertiTheme.typography.caption.semibold_14,
                        color = CertiTheme.colors.gray200
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun ResumeTextFieldPreview() {
    val text = remember { mutableStateOf("") }
    CERTITheme {
        ResumeTextField(
            value = text.value,
            onValueChange = { text.value = it },
            maxLength = 10
        )
    }
}
