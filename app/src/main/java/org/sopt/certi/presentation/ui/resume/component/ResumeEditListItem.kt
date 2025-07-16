package org.sopt.certi.presentation.ui.resume.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.domain.model.ActivityData
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun ResumeEditListItem(
    resumeListItem: ActivityData,
    onDeleteClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ResumeDescriptionSection(
            resumeListItem = resumeListItem,
            modifier = Modifier
                .weight(1f)
                .padding(end = screenWidthDp(22.dp))
        )

        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_close_36),
            contentDescription = null,
            tint = CertiTheme.colors.gray300,
            modifier = Modifier
                .noRippleClickable { onDeleteClick(resumeListItem.activityId) }
        )
    }
}

