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
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun ResumeProfile(
    category: List<String>,
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
                .widthForScreenPercentage(80.dp)
                .heightForScreenPercentage(80.dp)
        )
        Spacer(modifier = Modifier.width(screenWidthDp(12.dp)))

        Column(
            modifier = Modifier
                .padding(vertical = screenHeightDp(4.dp))
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.resume_profile_title),
                style = CertiTheme.typography.body.semibold_16,
                color = CertiTheme.colors.gray600
            )
            ResumeProfileJobs(category)
        }
    }
}

@Composable
fun ResumeProfileJobs(category: List<String>) {
    Column {
        if (category.size == 1) {
            ThemedText(category[0])
        } else {
            ThemedText(stringResource(R.string.resume_profile_jobs_1, category[0], category[1]))
            if (category.size == 3) {
                Spacer(modifier = Modifier.height(screenHeightDp(2.dp)))
                ThemedText(stringResource(R.string.resume_profile_jobs_2, category[2]))
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
        category = listOf("IT/인터넷", "경영/사무", "경영/사무")
    )
}
