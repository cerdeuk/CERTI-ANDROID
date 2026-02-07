package org.sopt.certi.presentation.ui.resume.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.component.section.CertiEmptySection
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.domain.model.ActivityData
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun ResumeListSection(
    title: String,
    onClick: () -> Unit,
    emptyText: String,
    resumeListItems: List<ActivityData>,
    bottomPadding: Dp,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = screenHeightDp(36.dp))
            .padding(horizontal = screenWidthDp(20.dp))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = CertiTheme.typography.subtitle.semibold_20,
                color = CertiTheme.colors.gray600,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_arrowright_36),
                contentDescription = null,
                modifier = Modifier.noRippleClickable { onClick() }
            )
        }
        if (resumeListItems.isEmpty()) {
            CertiEmptySection(
                text = emptyText,
                modifier = Modifier.padding(vertical = screenHeightDp(60.dp))
            )
        } else {
            ResumeListContent(
                resumeListItems = resumeListItems,
                modifier = Modifier.padding(
                    top = screenHeightDp(16.dp),
                    bottom = bottomPadding
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ResumeEmptyListSectionPreview() {
    CERTITheme {
        ResumeListSection(
            title = stringResource(R.string.resume_section_experience_title),
            onClick = { },
            emptyText = stringResource(R.string.resume_empty_experience_message),
            resumeListItems = listOf(),
            bottomPadding = 0.dp
        )
    }
}
