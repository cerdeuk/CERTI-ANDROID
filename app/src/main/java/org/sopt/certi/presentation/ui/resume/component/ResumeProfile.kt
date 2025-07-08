package org.sopt.certi.presentation.ui.resume.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.util.heightForScreenPercentage
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun ResumeProfile(
    desiredJobs: List<String>,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
    ) {
        Image(
            painter = painterResource(R.drawable.img_profile),
            contentDescription = null,
            modifier = Modifier
                .widthForScreenPercentage(0.22f)
                .heightForScreenPercentage(0.1f)
        )
        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.resume_profile_title),
                style = CertiTheme.typography.body.semibold_16,
                color = CertiTheme.colors.gray600
            )
            ResumeProfileJobs(desiredJobs)
        }
    }
}

@Composable
fun ResumeProfileJobs(desiredJobs: List<String>) {
    Column {
        if (desiredJobs.size == 1) {
            ThemedText(desiredJobs[0])
        } else {
            ThemedText(stringResource(R.string.resume_profile_jobs_1, desiredJobs[0], desiredJobs[1]))
            if (desiredJobs.size == 3) {
                Spacer(modifier = Modifier.height(2.dp))
                ThemedText(stringResource(R.string.resume_profile_jobs_2, desiredJobs[2]))
            }
        }
    }
}

@Composable
fun ThemedText(text: String) {
    Text(
        text = text,
        style = CertiTheme.typography.caption.regular_14,
        color = CertiTheme.colors.mainBlue
    )
}

@Preview(showBackground = true)
@Composable
fun ResumeProfilePreview() {
    ResumeProfile(
        desiredJobs = listOf("IT/인터넷", "경영/사무", "경영/사무")
    )
}
