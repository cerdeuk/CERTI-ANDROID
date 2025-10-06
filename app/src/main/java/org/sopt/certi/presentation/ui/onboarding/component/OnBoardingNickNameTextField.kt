package org.sopt.certi.presentation.ui.onboarding.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.presentation.type.NickNameValidType
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun OnBoardingNickNameTextField(
    nickname: String,
    onNicknameChange: (String) -> Unit,
    onDeleteClick: () -> Unit,
    onFocusChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    nicknameValidState: NickNameValidType = NickNameValidType.IDLE,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    LaunchedEffect(isFocused) {
        onFocusChange(isFocused)
    }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        val keyboardController = LocalSoftwareKeyboardController.current

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            BasicTextField(
                value = nickname,
                onValueChange = {
                    if (it.length <= 7) onNicknameChange(it)
                },
                cursorBrush = SolidColor(CertiTheme.colors.black),
                maxLines = 1,
                singleLine = true,
                interactionSource = interactionSource,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                    }
                ),
                textStyle = CertiTheme.typography.body.regular_16.copy(
                    color = CertiTheme.colors.black
                ),
                decorationBox = { innerTextField ->
                    if (nickname.isEmpty()) {
                        Text(
                            text = stringResource(R.string.onboarding_nickname_textfield_hint),
                            color = CertiTheme.colors.gray400,
                            style = CertiTheme.typography.body.regular_16
                        )
                    }
                    innerTextField()
                }
            )

            Row(
                modifier = Modifier
                    .align(Alignment.CenterEnd),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (nickname.isNotEmpty()) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_textfield_delete),
                        contentDescription = null,
                        tint = CertiTheme.colors.gray400,
                        modifier = Modifier
                            .widthForScreenPercentage(24.dp)
                            .noRippleClickable(onDeleteClick)
                    )
                }

                Text(
                    text = stringResource(R.string.onboarding_nickname_textfield_length, nickname.length),
                    color = CertiTheme.colors.gray500,
                    style = CertiTheme.typography.caption.semibold_12,
                    modifier = Modifier.padding(start = screenWidthDp(10.dp))
                )
            }
        }

        HorizontalDivider(
            color = nicknameValidState.color,
            modifier = Modifier.padding(top = screenHeightDp(13.dp)),
            thickness = screenHeightDp(1.dp),
        )
        Spacer(modifier = Modifier.padding(top = screenHeightDp(12.dp)))

        Text(
            text = stringResource(nicknameValidState.stringRes),
            color = nicknameValidState.color,
            style = CertiTheme.typography.caption.regular_14
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewOnBoardingNickNameTextField() {
    CERTITheme {
        OnBoardingNickNameTextField(
            nickname = "",
            onNicknameChange = {},
            onDeleteClick = {},
            onFocusChange = {},
            nicknameValidState = NickNameValidType.IDLE
        )
    }
}