package org.sopt.certi.presentation.ui.certrecommend.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import org.sopt.certi.ui.theme.CertiTheme
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.domain.type.CategoryType

@Composable
fun RecommendFilterSelectableButton(
    categoryType: CategoryType,
    onClick: (CategoryType) -> Unit,
    modifier: Modifier = Modifier
) {
    var isSelected by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .background(
                color = if (isSelected) CertiTheme.colors.lightBlue else CertiTheme.colors.white,
                shape = RoundedCornerShape(12.dp)
            )
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 1.dp,
                color = if (isSelected) CertiTheme.colors.skyBlue else CertiTheme.colors.lightPurple,
                shape = RoundedCornerShape(12.dp)
            )
            .noRippleClickable {
                isSelected = !isSelected
                onClick(categoryType)
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = categoryType.description,
            style = CertiTheme.typography.caption.semibold_12,
            color = CertiTheme.colors.purpleBlue,
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 10.dp)
        )
    }
}

@Preview
@Composable
fun RecommendFilterSelectableButtonPreview() {
    RecommendFilterSelectableButton(
        categoryType = CategoryType.MARKETING,
        onClick = {}
    )
}
