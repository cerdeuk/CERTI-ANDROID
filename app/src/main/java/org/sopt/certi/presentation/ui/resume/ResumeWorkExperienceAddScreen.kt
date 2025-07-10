package org.sopt.certi.presentation.ui.resume

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import org.sopt.certi.ui.theme.CERTITheme

@Composable
fun ResumeWorkExperienceAddRoute(
    padding: PaddingValues,
    viewModel: ResumeViewModel = hiltViewModel()
) {
    ResumeWorkExperienceAddScreen(
        modifier = Modifier.padding(padding)
    )
}

@Composable
fun ResumeWorkExperienceAddScreen(
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
    ) {
        Text("ResumeWorkExperienceAddScreen")
    }
}

@Preview
@Composable
private fun PreviewResumeWorkExperienceAddScreen() {
    CERTITheme {
        ResumeWorkExperienceAddScreen()
    }
}
