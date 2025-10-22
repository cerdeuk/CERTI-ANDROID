package org.sopt.certi.presentation.ui.my.component

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.presentation.type.NickNameValidType
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun MyPageNicknameTextField(
    label: String,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    nickNameValidType: NickNameValidType = NickNameValidType.DEFAULT,
    isButtonEnable: Boolean = false
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(screenWidthDp(12.dp))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = label,
                style = CertiTheme.typography.body.semibold_16,
                color = CertiTheme.colors.gray600
            )
            MyPageTextFieldButton(
                text = "중복확인",
                onClick = onButtonClick,
                isEnable = isButtonEnable
            )
        }
        BasicTextField(
            value = value,
            onValueChange = {
                if (it.length <= 7) {
                    onValueChange(it)
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
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
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
                    Text(
                        text = "${value.length}/7",
                        style = CertiTheme.typography.caption.semibold_12,
                        color = CertiTheme.colors.gray500,
                        modifier = Modifier.padding(start = screenWidthDp(8.dp))
                    )
                }
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
private fun MyPageTextFieldPreview() {
    val nickname by remember { mutableStateOf("name") }
    var value by remember { mutableStateOf("") }
    var nickNameValidType by remember { mutableStateOf(NickNameValidType.DEFAULT) }
    var isChecked by remember { mutableStateOf(false) }
    var isValid by remember { mutableStateOf(false) }

    CERTITheme {
        Column(
            modifier = Modifier.padding(horizontal = screenWidthDp(20.dp), vertical = screenWidthDp(40.dp))
        ) {
            MyPageNicknameTextField(
                label = "닉네임",
                placeholder = "닉네임을 입력해주세요.",
                value = value,
                onValueChange = { newValue ->
                    value = newValue
                    isChecked = false
                    nickNameValidType = when {
                        newValue.isEmpty() -> NickNameValidType.EMPTY
                        newValue.contains("tlqkf") -> NickNameValidType.INVALID
                        else -> NickNameValidType.UNCHECKED
                    }
                },
                onButtonClick = {
                    isChecked = true
                    isValid = !isValid
                    nickNameValidType = when {
                        value.contains("tlqkf") -> NickNameValidType.INVALID
                        isValid -> NickNameValidType.VALID
                        else -> NickNameValidType.DUPLICATE
                    }
                },
                nickNameValidType = nickNameValidType,
                isButtonEnable = value != nickname
            )
        }
    }
}
