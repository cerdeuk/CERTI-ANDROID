package org.sopt.certi.presentation.ui.resume

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.sopt.certi.core.component.topbar.CertiTopBar
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.presentation.ui.resume.component.ResumeProfile
import org.sopt.certi.ui.theme.CERTITheme

@Composable
fun ResumeRoute(
    padding: PaddingValues,
    viewModel: ResumeViewModel = hiltViewModel()
) {
    ResumeScreen(
        modifier = Modifier.padding(padding),
        jobCategory = listOf()
    )
}

@Composable
fun ResumeScreen(
    modifier: Modifier = Modifier,
    jobCategory: List<String>
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        CertiTopBar()

        LazyColumn {
            item {
                ResumeProfile(
                    modifier = Modifier
                        .padding(horizontal = screenWidthDp(20.dp))
                        .padding(top = screenHeightDp(32.dp), bottom = screenHeightDp(36.dp)),
                    category = jobCategory
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewResumeScreen() {
    CERTITheme {
        ResumeScreen(
            jobCategory = listOf("IT/인터넷", "경영/사무", "경영/사무")
        )
    }
}
