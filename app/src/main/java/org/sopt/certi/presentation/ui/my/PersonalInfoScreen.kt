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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.presentation.type.NickNameValidType
import org.sopt.certi.presentation.ui.my.component.PersonalInfoDateInputField
import org.sopt.certi.presentation.ui.my.component.PersonalInfoHeader
import org.sopt.certi.presentation.ui.my.component.PersonalInfoNicknameTextField
import org.sopt.certi.presentation.ui.my.component.PersonalInfoProfileImage
import org.sopt.certi.presentation.ui.my.component.PersonalInfoTextField
import org.sopt.certi.ui.theme.CERTITheme

@Composable
fun PersonalInfoRoute() {
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PersonalInfoScreen(
    nickname: String,
    name: String,
    email: String,
    birth: String,
    onSaveClick: () -> Unit,
    onNickNameChange: (String) -> Unit,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onBirthChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    nickNameValidType: NickNameValidType = NickNameValidType.DEFAULT
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
                value = nickname,
                onValueChange = onNickNameChange,
                onButtonClick = {},
                nickNameValidType = nickNameValidType
            )
        }

        item {
            PersonalInfoTextField(
                label = stringResource(R.string.personal_name_label),
                placeholder = stringResource(R.string.personal_name_placeholder),
                value = name,
                onValueChange = onNameChange
            )
        }

        item {
            PersonalInfoTextField(
                label = stringResource(R.string.personal_email_label),
                placeholder = stringResource(R.string.personal_email_placeholder),
                value = email,
                onValueChange = onEmailChange
            )
        }

        item {
            PersonalInfoDateInputField(
                label = stringResource(R.string.personal_birthday_label),
                placeholder = stringResource(R.string.personal_birthday_placeholder),
                value = birth,
                onValueChange = onBirthChange
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MyPagePersonalInfoPreview() {
    var nickname by remember { mutableStateOf("name") }
    var name by remember { mutableStateOf("name") }
    var email by remember { mutableStateOf("") }
    var birth by remember { mutableStateOf("") }
    var nickNameValidType by remember { mutableStateOf(NickNameValidType.DEFAULT) }

    CERTITheme {
        PersonalInfoScreen(
            nickname = nickname,
            name = name,
            email = email,
            birth = birth,
            onSaveClick = {},
            onNickNameChange = { nickname = it },
            onNameChange = { name = it },
            onEmailChange = { email = it },
            onBirthChange = { birth = it },
            nickNameValidType = nickNameValidType
        )
    }
}
