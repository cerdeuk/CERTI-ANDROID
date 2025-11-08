package org.sopt.certi.presentation.ui.my.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.util.pressedClickable
import org.sopt.certi.core.util.roundedBackgroundWithBorder
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun MyJobCategorySection(
    jobCategoryList: List<String>,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.resume_profile_title),
            style = CertiTheme.typography.body.semibold_16,
            color = CertiTheme.colors.gray400
        )
        JobCategoryList(
            jobCategoryList = jobCategoryList,
            modifier = Modifier.padding(vertical = screenHeightDp(16.dp))
        )
        ReselectInterestedChip(
            onClick = onClick
        )
    }
}

@Composable
private fun JobCategoryList(
    jobCategoryList: List<String>,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(screenWidthDp(8.dp))
    ) {
        jobCategoryList.forEach { job ->
            JobCategoryChip(
                categoryType = job
            )
        }
    }
}

@Composable
private fun JobCategoryChip(
    categoryType: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(horizontal = screenWidthDp(12.dp), vertical = screenHeightDp(8.dp))
            .roundedBackgroundWithBorder(cornerRadius = 24.dp, backgroundColor = CertiTheme.colors.gray0)
    ) {
        Text(
            text = categoryType,
            style = CertiTheme.typography.caption.regular_12,
            color = CertiTheme.colors.gray600
        )
    }
}

@Composable
private fun ReselectInterestedChip(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isPressed by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .background(color = if (isPressed) CertiTheme.colors.lightBlue else CertiTheme.colors.white, shape = RoundedCornerShape(24.dp))
            .clip(RoundedCornerShape(24.dp))
            .border(width = 1.dp, color = CertiTheme.colors.mainBlue, shape = RoundedCornerShape(24.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .pressedClickable(
                changePressed = {
                    isPressed = it
                },
                onClick = onClick
            )
    ) {
        Text(
            text = stringResource(R.string.reselect_interested_chip_text),
            style = CertiTheme.typography.caption.semibold_12,
            color = CertiTheme.colors.mainBlue
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MyJobCategoryManagePreview() {
    CERTITheme {
        MyJobCategorySection(
            jobCategoryList = listOf("재무/세무/IR"),
            onClick = {}
        )
    }
}