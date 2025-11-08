package org.sopt.certi.presentation.ui.my

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.component.textfield.CertiBasicTextField
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun MySchoolInfoRoute() {}

@Composable
fun MySchoolInfoScreen(
    value: String,
    onValueChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = screenWidthDp(20.dp))
    ) {
        Text(
            text = stringResource(R.string.onboarding_univ_title),
            style = CertiTheme.typography.subtitle.bold_20,
            color = CertiTheme.colors.gray600,
            modifier = Modifier.padding(vertical = screenHeightDp(24.dp))
        )
        CertiBasicTextField(
            value = value,
            onValueChange = onValueChange,
            onSearchClick = onSearchClick,
            modifier = Modifier.padding(top = screenHeightDp(14.dp))
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MySchoolInfoPreview() {
    CERTITheme {
        MySchoolInfoScreen(
            value = "",
            onValueChange = {},
            onSearchClick = {}
        )
    }
}