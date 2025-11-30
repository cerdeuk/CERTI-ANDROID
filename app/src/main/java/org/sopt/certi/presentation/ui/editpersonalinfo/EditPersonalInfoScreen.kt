package org.sopt.certi.presentation.ui.editpersonalinfo

import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.sopt.certi.R
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.domain.model.DateData
import org.sopt.certi.presentation.type.NickNameValidType
import org.sopt.certi.presentation.ui.editpersonalinfo.component.DateInputField
import org.sopt.certi.presentation.ui.editpersonalinfo.component.EditPersonalInfoHeader
import org.sopt.certi.presentation.ui.editpersonalinfo.component.EditNicknameTextField
import org.sopt.certi.presentation.ui.editpersonalinfo.component.PersonalInfoProfileImage
import org.sopt.certi.presentation.ui.editpersonalinfo.component.EditPersonalInfoTextField
import org.sopt.certi.presentation.ui.editpersonalinfo.state.EditPersonalInfoUiState
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun EditPersonalInfoRoute(
    padding: PaddingValues,
    viewModel: EditPersonalInfoViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val nickNameValidTypeState by viewModel.nickNameValidTypeUiState.collectAsStateWithLifecycle()

    EditPersonalInfoScreen(
        uiState = uiState,
        nickNameValidType = nickNameValidTypeState,
        onProfileUriChange = viewModel::onProfileUriChange,
        onNickNameChange = viewModel::onNickNameChange,
        onNickNameCheckButtonClick = viewModel::onNickNameCheckButtonClick,
        onNameChange = viewModel::onNameChange,
        onEmailChange = viewModel::onEmailChange,
        onBirthChange = viewModel::onBirthChange,
        onSaveClick = viewModel::onSaveClick,
        modifier = Modifier.padding(padding)
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EditPersonalInfoScreen(
    uiState: EditPersonalInfoUiState,
    nickNameValidType: NickNameValidType,
    onProfileUriChange: (Uri?) -> Unit,
    onNickNameChange: (String) -> Unit,
    onNickNameCheckButtonClick: () -> Unit,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onBirthChange: (DateData) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current

    LazyColumn(
        modifier = modifier
            .noRippleClickable { focusManager.clearFocus() }
            .imePadding(),
        contentPadding = PaddingValues(horizontal = screenWidthDp(20.dp)),
        verticalArrangement = Arrangement.spacedBy(screenHeightDp(24.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        stickyHeader {
            EditPersonalInfoHeader(
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
            EditNicknameTextField(
                value = uiState.nickname,
                onValueChange = onNickNameChange,
                isEnable = uiState.isNicknameChanged,
                onButtonClick = onNickNameCheckButtonClick,
                nickNameValidType = nickNameValidType
            )
        }

        item {
            EditPersonalInfoTextField(
                label = stringResource(R.string.personal_name_label),
                placeholder = stringResource(R.string.personal_name_placeholder),
                value = uiState.name,
                onValueChange = onNameChange
            )
        }

        item {
            EditPersonalInfoTextField(
                label = stringResource(R.string.personal_email_label),
                placeholder = stringResource(R.string.personal_email_placeholder),
                value = uiState.email,
                onValueChange = onEmailChange,
                imeAction = ImeAction.Done
            )
        }

        item {
            DateInputField(
                label = stringResource(R.string.personal_birthday_label),
                value = uiState.birth,
                onValueChange = onBirthChange,
                inputFieldBackgroundColor = when {
                    uiState.birth.isAllEmpty -> CertiTheme.colors.white
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
    val viewModel = remember { EditPersonalInfoViewModel() }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val nickNameValidType by viewModel.nickNameValidTypeUiState.collectAsStateWithLifecycle()

    CERTITheme {
        EditPersonalInfoScreen(
            uiState = uiState,
            nickNameValidType = nickNameValidType,
            onSaveClick = viewModel::onSaveClick,
            onProfileUriChange = viewModel::onProfileUriChange,
            onNickNameChange = viewModel::onNickNameChange,
            onNickNameCheckButtonClick = viewModel::onNickNameCheckButtonClick,
            onNameChange = viewModel::onNameChange,
            onEmailChange = viewModel::onEmailChange,
            onBirthChange = viewModel::onBirthChange,
            modifier = Modifier
                .fillMaxSize()
                .background(CertiTheme.colors.white)
                .statusBarsPadding()
        )
    }
}
