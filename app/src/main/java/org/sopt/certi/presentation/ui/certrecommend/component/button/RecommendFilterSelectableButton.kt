package org.sopt.certi.presentation.ui.certrecommend.component.button

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.core.util.heightForScreenPercentage
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.roundedBackgroundWithBorder
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.domain.type.CategoryType
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun RecommendFilterSelectableButton(
    categoryType: CategoryType,
    isSelected: Boolean,
    onClick: (CategoryType) -> Unit,
    modifier: Modifier = Modifier,
    clickable: Boolean = true
) {
    Box(
        modifier = modifier
            .roundedBackgroundWithBorder(
                cornerRadius = 12.dp,
                backgroundColor = if (isSelected) CertiTheme.colors.lightBlue else CertiTheme.colors.white,
                borderColor = if (isSelected) CertiTheme.colors.skyBlue else CertiTheme.colors.lightPurple,
                borderWidth = 1.dp
            )
            .noRippleClickable {
                if (clickable) {
                    onClick(categoryType)
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = categoryType.description,
            style = CertiTheme.typography.caption.semibold_12,
            textAlign = TextAlign.Center,
            color = CertiTheme.colors.purpleBlue,
            modifier = Modifier
                .padding(vertical = screenHeightDp(14.dp), horizontal = screenWidthDp(10.dp))
                .heightForScreenPercentage(18.dp)
                .wrapContentHeight()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RecommendFilterSelectableButtonPreview() {
    var isSelected by remember { mutableStateOf(false) }

    RecommendFilterSelectableButton(
        categoryType = CategoryType.MARKETING,
        isSelected = isSelected,
        onClick = {
            isSelected = !isSelected
        }
    )
}
