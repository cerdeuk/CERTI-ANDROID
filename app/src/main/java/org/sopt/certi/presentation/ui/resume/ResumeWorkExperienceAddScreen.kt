package org.sopt.certi.presentation.ui.resume

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.sopt.certi.R
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun ResumeWorkExperienceAddRoute(
    padding: PaddingValues,
    viewModel: ResumeViewModel = hiltViewModel()
) {
    val startDate by remember { mutableStateOf("") }
    val endDate by remember { mutableStateOf("") }

    ResumeWorkExperienceAddScreen(
        onStartDateValueChange = {},
        onEndDateValueChange = {},
        modifier = Modifier.padding(padding)
    )
}

@Composable
fun ResumeWorkExperienceAddScreen(
    onStartDateValueChange:()->Unit,
    onEndDateValueChange:()->Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = screenWidthDp(20.dp))
        ) {
            item {
                Text(
                    text = stringResource(R.string.resume_work_experience_add),
                    style = CertiTheme.typography.subtitle.semibold_20,
                    color = CertiTheme.colors.gray600,
                    modifier = Modifier.padding(top = screenHeightDp(60.dp))
                )
            }

            item {

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewResumeWorkExperienceAddScreen() {
    CERTITheme {
        ResumeWorkExperienceAddScreen(
            onStartDateValueChange = {},
            onEndDateValueChange = {}
        )
    }
}
