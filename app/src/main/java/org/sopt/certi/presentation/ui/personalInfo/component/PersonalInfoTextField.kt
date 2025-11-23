package org.sopt.certi.presentation.ui.personalInfo.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
fun PersonalInfoTextField(
    label: String,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    nickNameValidType: NickNameValidType = NickNameValidType.DEFAULT,
    imeAction: ImeAction = ImeAction.Next
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
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = imeAction
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
private fun MyPageTextFieldPreview() {
    var value by remember { mutableStateOf("") }

    CERTITheme {
        Column(
            modifier = Modifier.padding(horizontal = screenWidthDp(20.dp), vertical = screenWidthDp(40.dp))
        ) {
            PersonalInfoTextField(
                label = "이름",
                placeholder = "이름을 입력해주세요.",
                value = value,
                onValueChange = { value = it }
            )
        }
    }
}
