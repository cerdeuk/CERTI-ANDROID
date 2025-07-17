package org.sopt.certi.core.component.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun CertiBasicTextField(
    value: String,
    onValueChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = stringResource(id = R.string.textfield_placeholder),
    maxLength: Int = 30
) {
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = CertiTheme.colors.white,
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 1.dp,
                color = CertiTheme.colors.gray300,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = screenWidthDp(12.dp), vertical = screenHeightDp(16.dp))
            .noRippleClickable {
                if (value.isNotEmpty()) {
                    onValueChange("")
                }
                focusRequester.requestFocus()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BasicTextField(
            value = value,
            onValueChange = {
                if (it.length <= maxLength) {
                    onValueChange(it)
                }
            },
            textStyle = CertiTheme.typography.body.regular_16.copy(
                color = CertiTheme.colors.black
            ),
            cursorBrush = SolidColor(if (value.isEmpty()) CertiTheme.colors.white else CertiTheme.colors.black),
            maxLines = 1,
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClick()
                    keyboardController?.hide()
                },
                onDone = {
                    onSearchClick()
                    keyboardController?.hide()
                }
            ),
            decorationBox = { innerTextField ->
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = CertiTheme.typography.body.regular_16,
                        color = CertiTheme.colors.gray200
                    )
                }
                innerTextField()
            },
            modifier = Modifier.focusRequester(focusRequester)
        )

        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_search_24),
            contentDescription = stringResource(R.string.textfield_placeholder),
            tint = CertiTheme.colors.gray200,
            modifier = Modifier.noRippleClickable(onSearchClick)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCertiBasicTextField() {
    val text = remember { mutableStateOf("") }

    CERTITheme {
        CertiBasicTextField(
            value = text.value,
            onValueChange = { text.value = it },
            onSearchClick = {}
        )
    }
}
