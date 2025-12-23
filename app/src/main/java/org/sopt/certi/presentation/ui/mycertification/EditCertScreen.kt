package org.sopt.certi.presentation.ui.mycertification

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.sopt.certi.R
import org.sopt.certi.core.component.header.MyPageHeader
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.ui.theme.CERTITheme

@Composable
fun EditCertRoute(
    padding: PaddingValues,
    viewModel: MyCertViewModel = hiltViewModel()
) {
}

@Composable
fun EditCertScreen(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        MyPageHeader(
            headerTitle = stringResource(R.string.edit_certification),
            modifier = Modifier.padding(vertical = screenWidthDp(24.dp))
        )

        LazyColumn {
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EditCertPreview() {
    CERTITheme {
        EditCertScreen()
    }
}
