package org.sopt.certi.presentation.ui.editpersonalinfo.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.presentation.type.NickNameValidType
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun EditPersonalInfoTextField(
    label: String,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    nickNameValidType: NickNameValidType = NickNameValidType.DEFAULT,
    imeAction: ImeAction = ImeAction.Next
) {
    val focusManager = LocalFocusManager.current
    var textFieldValueState by remember {
        mutableStateOf(
            TextFieldValue(
                text = value,
                selection = TextRange(value.length)
            )
        )
    }

    if (value != textFieldValueState.text) {
        textFieldValueState = textFieldValueState.copy(
            text = value,
            selection = TextRange(value.length)
        )
    }

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
            value = textFieldValueState,
            onValueChange = { newValue ->
                val filteredText = newValue.text.replace("\n", "").replace("\r", "")
                if (filteredText != newValue.text) {
                    textFieldValueState = newValue.copy(text = filteredText)
                    onValueChange(filteredText)
                } else {
                    textFieldValueState = newValue
                    onValueChange(newValue.text)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .border(
                    width = 1.dp,
                    color = nickNameValidType.color,
                    shape = RoundedCornerShape(8.dp)
                )
                .background(CertiTheme.colors.gray0)
                .padding(screenWidthDp(12.dp))
                .onFocusChanged { focusState ->
                    if (focusState.isFocused) {
                        val text = textFieldValueState.text
                        textFieldValueState = textFieldValueState.copy(
                            selection = TextRange(text.length)
                        )
                    }
                },
            textStyle = CertiTheme.typography.caption.regular_14.copy(
                color = CertiTheme.colors.black
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = imeAction
            ),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() },
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            decorationBox = { innerTextField ->
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = CertiTheme.typography.caption.semibold_14,
                        color = CertiTheme.colors.gray300
                    )
                }
                innerTextField()
            }
        )

        if (stringResource(nickNameValidType.stringRes).isNotBlank()) {
            Text(
                text = stringResource(nickNameValidType.stringRes),
                style = CertiTheme.typography.caption.regular_14,
                color = nickNameValidType.color
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EditPersonalInfoTextFieldPreview() {
    var value by remember { mutableStateOf("") }

    CERTITheme {
        Column(
            modifier = Modifier.padding(horizontal = screenWidthDp(20.dp), vertical = screenWidthDp(40.dp))
        ) {
            EditPersonalInfoTextField(
                label = "이름",
                placeholder = "이름을 입력해주세요.",
                value = value,
                onValueChange = { value = it }
            )
        }
    }
}
