package org.sopt.certi.presentation.ui.resume.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun ResumeProfile(
    desiredJobs: List<String>,
    modifier: Modifier = Modifier
){
    Row (
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.img_profile),
            contentDescription = null,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(
                text = stringResource(R.string.resume_profile),
                style = CertiTheme.typography.body.semibold_16,
                color = CertiTheme.colors.gray600
            )
            Spacer(modifier= Modifier.height(12.dp))

            Row {
                desiredJobs.forEach { job ->
                    Text(
                        text = job,
                        style = CertiTheme.typography.caption.regular_14,
                        color = CertiTheme.colors.mainBlue
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResumeProfilePreview(){
    ResumeProfile(
        desiredJobs = listOf("IT/인터넷", "경영/사무", "경영/사무")
    )
}