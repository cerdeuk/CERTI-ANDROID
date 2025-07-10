package org.sopt.certi.presentation.ui.resume

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import org.sopt.certi.ui.theme.CERTITheme

@Composable
fun ResumeMyCertRoute(
    padding: PaddingValues,
    viewModel: ResumeViewModel = hiltViewModel()
) {
    ResumeMyCertScreen(
        modifier = Modifier.padding(padding)
    )
}

@Composable
fun ResumeMyCertScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
    }
}

@Preview
@Composable
private fun PreviewResumeMyCertScreen() {
    CERTITheme {
        ResumeMyCertScreen()
    }
}
