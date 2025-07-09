package org.sopt.certi.presentation.ui.resume.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.ui.theme.CERTITheme

@Composable
fun ResumeInputSection(
    title: String,
    value: String,
    onValueChange: (String) -> Unit,
    maxLength: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        ResumeInputTitle(title)
        Spacer(modifier = Modifier.height(screenHeightDp(0.03f)))
        ResumeTextField(
            value = value,
            onValueChange = onValueChange,
            maxLength = maxLength
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ResumeInputSectionPreview() {
    val text = remember { mutableStateOf("") }
    CERTITheme {
        ResumeInputSection(
            title = "근무회사",
            value = text.value,
            onValueChange = { text.value = it },
            maxLength = 10
        )
    }
}
