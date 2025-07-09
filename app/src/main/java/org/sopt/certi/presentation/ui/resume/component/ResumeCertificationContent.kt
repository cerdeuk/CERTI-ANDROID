package org.sopt.certi.presentation.ui.resume.component

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.certi.ui.theme.CERTITheme

@Composable
fun ResumeCertificationContent(
    modifier: Modifier = Modifier
) {
    LazyRow {

    }
}

@Preview(showBackground = true)
@Composable
fun ResumeCertificationRowPreview() {
    CERTITheme {
        ResumeCertificationContent()
    }
}
