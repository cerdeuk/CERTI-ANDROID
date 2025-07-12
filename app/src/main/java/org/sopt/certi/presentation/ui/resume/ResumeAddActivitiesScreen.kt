package org.sopt.certi.presentation.ui.resume

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import org.sopt.certi.ui.theme.CERTITheme

@Composable
fun ResumeAddActivitiesRoute(
    padding: PaddingValues,
    onNavigateToResume: () -> Unit,
    viewModel: ResumeViewModel = hiltViewModel()
) {

}

@Composable
fun ResumeAddActivitiesScreen(
    modifier: Modifier = Modifier
){

}

@Preview
@Composable
private fun PreviewResumeAddActivitiesScreen() {
    CERTITheme {
        ResumeAddActivitiesScreen()
    }
}