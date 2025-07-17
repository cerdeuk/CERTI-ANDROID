package org.sopt.certi.presentation.ui.resume.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.domain.model.ActivityData
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun ResumeDescriptionSection(
    resumeListItem: ActivityData,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.widthIn(max = screenWidthDp(120.dp))
        ) {
            Text(
                text = stringResource(
                    R.string.resume_list_item_period,
                    resumeListItem.startAt,
                    resumeListItem.endAt
                ),
                color = CertiTheme.colors.gray500,
                style = CertiTheme.typography.caption.regular_12
            )
            Spacer(modifier = Modifier.height(screenHeightDp(12.dp)))
            Text(
                text = resumeListItem.organization,
                color = CertiTheme.colors.gray500,
                style = CertiTheme.typography.caption.regular_12,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Spacer(modifier = Modifier.width(screenWidthDp(24.dp)))

        Column {
            Text(
                text = resumeListItem.role,
                color = CertiTheme.colors.gray600,
                style = CertiTheme.typography.body.semibold_16,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(screenHeightDp(10.dp)))
            Text(
                text = resumeListItem.description,
                color = CertiTheme.colors.gray600,
                style = CertiTheme.typography.caption.regular_12,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
