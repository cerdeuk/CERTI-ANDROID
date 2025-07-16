package org.sopt.certi.presentation.ui.resume.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.util.heightForScreenPercentage
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.domain.model.ActivityData

@Composable
fun ResumeListContent(
    resumeListItems: List<ActivityData>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(
            top = screenHeightDp(16.dp),
            bottom = screenHeightDp(8.dp)
        ),
        verticalArrangement = Arrangement.spacedBy(screenHeightDp(24.dp))
    ) {
        resumeListItems.forEach { item ->
            ResumeListItem(
                resumeListItem = item
            )
        }
    }
}

@Composable
private fun ResumeListItem(
    resumeListItem: ActivityData,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = screenHeightDp(12.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.img_resume_list_item),
            contentDescription = null,
            modifier = Modifier
                .widthForScreenPercentage(36.dp)
                .heightForScreenPercentage(36.dp)
        )
        Spacer(modifier = Modifier.width(screenWidthDp(24.dp)))

        ResumeDescriptionSection(resumeListItem = resumeListItem)
    }
}

