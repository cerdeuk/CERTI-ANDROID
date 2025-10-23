package org.sopt.certi.presentation.ui.my

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.presentation.ui.my.component.PersonalInfoNicknameTextField
import org.sopt.certi.presentation.ui.my.component.PersonalInfoTextField
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun PersonalInfoRoute() {
}

@Composable
fun PersonalInfoScreen(
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        MyPagePersonalInfoHeader(onSaveClick = onSaveClick)

        LazyColumn(
            contentPadding = PaddingValues(screenWidthDp(20.dp)),
            verticalArrangement = Arrangement.spacedBy(screenHeightDp(24.dp))
        ) {
            item {
                PersonalInfoNicknameTextField(
                    value = "",
                    onValueChange = {},
                    onButtonClick = {}
                )
            }

            item {
                PersonalInfoTextField(
                    label = stringResource(R.string.personal_name_label),
                    placeholder = stringResource(R.string.personal_name_placeholder),
                    value = "",
                    onValueChange = {}
                )
            }

            item {
                PersonalInfoTextField(
                    label = stringResource(R.string.personal_email_label),
                    placeholder = stringResource(R.string.personal_email_placeholder),
                    value = "",
                    onValueChange = {}
                )
            }

            item {
                PersonalInfoTextField(
                    label = stringResource(R.string.personal_birthday_label),
                    placeholder = stringResource(R.string.personal_birthday_placeholder),
                    value = "",
                    onValueChange = {}
                )
            }
        }
    }
}

@Composable
private fun MyPagePersonalInfoHeader(
    onSaveClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(screenWidthDp(20.dp))
    ) {
        Text(
            text = stringResource(R.string.mypage_personal_info),
            style = CertiTheme.typography.subtitle.semibold_20,
            color = CertiTheme.colors.gray600,
            modifier = Modifier.align(Alignment.Center)
        )
        Text(
            text = stringResource(R.string.personal_save),
            style = CertiTheme.typography.body.semibold_18,
            color = CertiTheme.colors.gray400,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .noRippleClickable { onSaveClick() }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MyPagePersonalInfoPreview() {
    CERTITheme {
        PersonalInfoScreen(
            onSaveClick = {}
        )
    }
}
