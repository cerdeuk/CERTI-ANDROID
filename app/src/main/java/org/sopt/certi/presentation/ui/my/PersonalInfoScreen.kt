package org.sopt.certi.presentation.ui.my

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
import org.sopt.certi.presentation.ui.my.component.PersonalInfoDateInputField
import org.sopt.certi.presentation.ui.my.component.PersonalInfoHeader
import org.sopt.certi.presentation.ui.my.component.PersonalInfoNicknameTextField
import org.sopt.certi.presentation.ui.my.component.PersonalInfoProfileImage
import org.sopt.certi.presentation.ui.my.component.PersonalInfoTextField
import org.sopt.certi.presentation.ui.my.state.PersonalInfoUiState
import org.sopt.certi.ui.theme.CERTITheme

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
        onSaveClick = { viewModel.onSaveClick() },
        onNickNameChange = { viewModel.onNickNameChange(it) },
        onNameChange = { viewModel.onNameChange(it) },
        onEmailChange = { viewModel.onEmailChange(it) },
        onBirthChange = { viewModel.onBirthChange(it) },
        modifier = Modifier.padding(padding)
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PersonalInfoScreen(
    uiState: PersonalInfoUiState,
    nickNameValidType: NickNameValidType,
    onSaveClick: () -> Unit,
    onNickNameChange: (String) -> Unit,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onBirthChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(screenWidthDp(20.dp)),
        verticalArrangement = Arrangement.spacedBy(screenHeightDp(24.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        stickyHeader {
            PersonalInfoHeader(onSaveClick = onSaveClick)
        }

        item {
            PersonalInfoProfileImage(
                selectedImageUri = null,
                onImageSelected = {},
                modifier = Modifier.padding(bottom = screenHeightDp(12.dp), top = screenHeightDp(4.dp))
            )
        }

        item {
            PersonalInfoNicknameTextField(
                value = uiState.nickname,
                onValueChange = onNickNameChange,
                onButtonClick = {},
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
            PersonalInfoDateInputField(
                label = stringResource(R.string.personal_birthday_label),
                placeholder = stringResource(R.string.personal_birthday_placeholder),
                value = uiState.birth,
                onValueChange = onBirthChange
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MyPagePersonalInfoPreview() {
    val uiState by remember {
        mutableStateOf(
            PersonalInfoUiState(
                nickname = "nick",
                name = "name",
                email = "certification@gamil.com",
                birth = ""
            )
        )
    }
    val nickNameValidType by remember { mutableStateOf(NickNameValidType.DEFAULT) }

    CERTITheme {
        PersonalInfoScreen(
            uiState = uiState,
            nickNameValidType = nickNameValidType,
            onSaveClick = { },
            onNickNameChange = { },
            onNameChange = { },
            onEmailChange = { },
            onBirthChange = { }
        )
    }
}
