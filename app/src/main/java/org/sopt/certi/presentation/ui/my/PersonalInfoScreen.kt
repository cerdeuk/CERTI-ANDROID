package org.sopt.certi.presentation.ui.my

import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.sopt.certi.R
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.presentation.type.NickNameValidType
import org.sopt.certi.presentation.ui.my.component.BirthdayInputField
import org.sopt.certi.presentation.ui.my.component.ModifyInfoHeader
import org.sopt.certi.presentation.ui.my.component.PersonalInfoNicknameTextField
import org.sopt.certi.presentation.ui.my.component.PersonalInfoProfileImage
import org.sopt.certi.presentation.ui.my.component.PersonalInfoTextField
import org.sopt.certi.presentation.ui.my.state.PersonalInfoUiState
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun PersonalInfoRoute(
    padding: PaddingValues,
    viewModel: PersonalInfoViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val nickNameValidTypeState by viewModel.nickNameValidTypeUiState.collectAsStateWithLifecycle()

    PersonalInfoScreen(
        uiState = uiState,
        nickNameValidType = nickNameValidTypeState,
        onProfileUriChange = viewModel::onProfileUriChange,
        onNickNameChange = viewModel::onNickNameChange,
        onNickNameCheckButtonClick = viewModel::onNickNameCheckButtonClick,
        onNameChange = viewModel::onNameChange,
        onEmailChange = viewModel::onEmailChange,
        onBirthChange = viewModel::onBirthChange,
        onBirthValidityChange = viewModel::onBirthValidityChange,
        onSaveClick = viewModel::onSaveClick,
        modifier = Modifier.padding(padding)
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PersonalInfoScreen(
    uiState: PersonalInfoUiState,
    nickNameValidType: NickNameValidType,
    onProfileUriChange: (Uri?) -> Unit,
    onNickNameChange: (String) -> Unit,
    onNickNameCheckButtonClick: () -> Unit,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onBirthChange: (String) -> Unit,
    onBirthValidityChange: (Boolean) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(screenWidthDp(20.dp)),
        verticalArrangement = Arrangement.spacedBy(screenHeightDp(24.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        stickyHeader {
            ModifyInfoHeader(
                isSaveEnable = uiState.isSaveButtonEnabled,
                onSaveClick = onSaveClick,
                headerTitle = stringResource(R.string.mypage_personal_info)
            )
        }

        item {
            PersonalInfoProfileImage(
                selectedImageUri = uiState.profileUri,
                onImageUriChange = onProfileUriChange,
                modifier = Modifier.padding(bottom = screenHeightDp(12.dp), top = screenHeightDp(4.dp))
            )
        }

        item {
            PersonalInfoNicknameTextField(
                value = uiState.nickname,
                onValueChange = onNickNameChange,
                isEnable = uiState.isNicknameChanged,
                onButtonClick = onNickNameCheckButtonClick,
                nickNameValidType = nickNameValidType
            )
        }

        item {
            PersonalInfoTextField(
                label = stringResource(R.string.personal_name_label),
                placeholder = stringResource(R.string.personal_name_placeholder),
                value = uiState.name,
                onValueChange = onNameChange
            )
        }

        item {
            PersonalInfoTextField(
                label = stringResource(R.string.personal_email_label),
                placeholder = stringResource(R.string.personal_email_placeholder),
                value = uiState.email,
                onValueChange = onEmailChange,
                imeAction = ImeAction.Done
            )
        }

        item {
            BirthdayInputField(
                label = stringResource(R.string.personal_birthday_label),
                value = uiState.birth,
                onValueChange = onBirthChange,
                onValidityChange = onBirthValidityChange,
                inputFieldBackgroundColor = when {
                    uiState.birth.isEmpty() -> CertiTheme.colors.white
                    uiState.isBirthChanged -> CertiTheme.colors.white
                    else -> CertiTheme.colors.gray0
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MyPagePersonalInfoPreview() {
    val viewModel = remember { PersonalInfoViewModel() }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val nickNameValidType by viewModel.nickNameValidTypeUiState.collectAsStateWithLifecycle()

    CERTITheme {
        PersonalInfoScreen(
            uiState = uiState,
            nickNameValidType = nickNameValidType,
            onSaveClick = viewModel::onSaveClick,
            onProfileUriChange = viewModel::onProfileUriChange,
            onNickNameChange = viewModel::onNickNameChange,
            onNickNameCheckButtonClick = viewModel::onNickNameCheckButtonClick,
            onNameChange = viewModel::onNameChange,
            onEmailChange = viewModel::onEmailChange,
            onBirthChange = viewModel::onBirthChange,
            onBirthValidityChange = viewModel::onBirthValidityChange,
            modifier = Modifier
                .fillMaxSize()
                .background(CertiTheme.colors.white)
                .statusBarsPadding()
        )
    }
}
