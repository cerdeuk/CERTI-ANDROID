package org.sopt.certi.presentation.ui.my

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.certi.R
import org.sopt.certi.ui.theme.CERTITheme

@Composable
fun MyPagePersonalInfoRoute() {

}

@Composable
fun MyPagePersonalInfoScreen() {
    Column {
        Row {
            Text(
                text = stringResource(R.string.per)
            )
        }
    }
}

@Preview
@Composable
private fun MyPagePersonalInfoPreview() {
    CERTITheme {
        MyPagePersonalInfoScreen()
    }
}