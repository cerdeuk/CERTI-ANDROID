package org.sopt.certi.presentation.ui.my.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.core.util.heightForScreenPercentage
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.roundedBackgroundWithBorder
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.domain.type.CategoryType
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun BottomSheetButton(
    categoryType: CategoryType,
    isSelected: Boolean,
    selectNumber: Int,
    onClick: (CategoryType) -> Unit,
    modifier: Modifier = Modifier,
    clickable: Boolean = true
) {
    Row(
        modifier = modifier
            .roundedBackgroundWithBorder(
                cornerRadius = 12.dp,
                backgroundColor = if (isSelected) CertiTheme.colors.lightBlue else CertiTheme.colors.white,
                borderColor = if (isSelected) CertiTheme.colors.skyBlue else CertiTheme.colors.lightPurple,
                borderWidth = 1.dp
            )
            .heightForScreenPercentage(44.dp)
            .widthForScreenPercentage(100.dp)
            .noRippleClickable {
                if (clickable) {
                    onClick(categoryType)
                }
            }
            .padding(horizontal = screenWidthDp(10.dp)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(screenWidthDp(3.dp))
    ) {
        if (isSelected) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(15.dp)
                    .background(CertiTheme.colors.mainBlue),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = selectNumber.toString(),
                    style = CertiTheme.typography.caption.semibold_10,
                    color = CertiTheme.colors.white
                )
            }
        }

        Text(
            text = categoryType.description,
            style = CertiTheme.typography.caption.semibold_12,
            textAlign = TextAlign.Center,
            color = CertiTheme.colors.purpleBlue,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RecommendFilterSelectableButtonPreview() {
    var isSelected by remember { mutableStateOf(true) }

    BottomSheetButton(
        categoryType = CategoryType.MARKETING,
        isSelected = isSelected,
        selectNumber = 1,
        onClick = {
            isSelected = !isSelected
        }
    )
}
