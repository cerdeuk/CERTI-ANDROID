package org.sopt.certi.presentation.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.sopt.certi.R
import org.sopt.certi.core.component.button.CertiBasicButton
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.presentation.type.NickNameValidType
import org.sopt.certi.presentation.ui.onboarding.component.OnBoardingNickNameTextField
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme
import org.sopt.certi.ui.theme.defaultCertiColors

@Composable
fun OnBoardingNickNameRoute(
    padding: PaddingValues,
    navigateToOnBoardingInfo: () -> Unit,
    viewModel: OnBoardingViewModel
) {
    val signUpSuccess by viewModel.signUpSuccess.collectAsStateWithLifecycle()
    val userInfo by viewModel.userInfo.collectAsStateWithLifecycle()
    val nickname by viewModel.nickname.collectAsStateWithLifecycle()
    val nicknameValidState by viewModel.nicknameValidState.collectAsStateWithLifecycle()

    LaunchedEffect(signUpSuccess, userInfo) {
        if (signUpSuccess && userInfo != null) {
            navigateToOnBoardingInfo()
        }
    }

    OnBoardingNickNameScreen(
        nickname = nickname,
        onNicknameChange = { viewModel.onNicknameChange(it) },
        onFocusChange = viewModel::onNickNameFocusChange,
        onDuplicateCheckClick = viewModel::onDuplicateCheckClick,
        nicknameValidState = nicknameValidState,
        onSignUpClick = { viewModel.postSignUp() },
        modifier = Modifier.padding(padding)
    )
}

@Composable
private fun OnBoardingNickNameScreen(
    nickname: String,
    onNicknameChange: (String) -> Unit,
    onFocusChange: (Boolean) -> Unit,
    onDuplicateCheckClick: () -> Unit,
    nicknameValidState: NickNameValidType,
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(top = screenHeightDp(50.dp), bottom = screenHeightDp(22.dp), start = screenWidthDp(20.dp), end = screenWidthDp(20.dp))
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.img_onboarding_progressbar_6),
                contentDescription = null,
                modifier = Modifier
                    .widthForScreenPercentage(200.dp),
                contentScale = ContentScale.FillWidth
            )
            Spacer(modifier = Modifier.padding(top = screenHeightDp(40.dp)))

            Text(
                text = stringResource(R.string.onboarding_nickname_title),
                style = CertiTheme.typography.subtitle.bold_20,
                color = CertiTheme.colors.gray600
            )
            Spacer(modifier = Modifier.padding(top = screenHeightDp(40.dp)))

            Text(
                text = stringResource(R.string.onboarding_nickname_textfield_title),
                style = CertiTheme.typography.caption.regular_12,
                color = CertiTheme.colors.gray600
            )
            Spacer(modifier = Modifier.padding(top = screenHeightDp(20.dp)))

            OnBoardingNickNameTextField(
                nickname = nickname,
                onNicknameChange = onNicknameChange,
                onDeleteClick = { onNicknameChange("") },
                nicknameValidState = nicknameValidState,
                onFocusChange = onFocusChange
            )
            Spacer(modifier = Modifier.padding(top = screenHeightDp(16.dp)))

            Text(
                text = stringResource(R.string.onboarding_nickname_duplicate_check),
                style = CertiTheme.typography.caption.regular_14,
                color = CertiTheme.colors.gray500,
                modifier = Modifier
                    .drawBehind {
                        val strokeWidth = 1.dp.toPx()
                        val y = size.height - strokeWidth / 2 + 6
                        drawLine(
                            color = defaultCertiColors.gray500,
                            start = Offset(0f, y),
                            end = Offset(size.width, y),
                            strokeWidth = strokeWidth
                        )
                    }
                    .noRippleClickable(onDuplicateCheckClick)
            )
        }

        CertiBasicButton(
            buttonText = stringResource(R.string.button_start),
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.BottomCenter),
            enabled = nicknameValidState == NickNameValidType.VALID
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    CERTITheme {
        OnBoardingNickNameScreen(
            nickname = "",
            onNicknameChange = {},
            onDuplicateCheckClick = {},
            nicknameValidState = NickNameValidType.IDLE,
            onSignUpClick = {},
            onFocusChange = {}
        )
    }
}
