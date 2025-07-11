package org.sopt.certi.presentation.ui.certrecommend.component.chip

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.sopt.certi.core.util.roundedBackgroundWithBorder
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.domain.type.CategoryType
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun RecommendCategoryChip(
    categoryType: CategoryType
) {
    Box(
        modifier = Modifier
            .roundedBackgroundWithBorder(cornerRadius = 24.dp, backgroundColor = CertiTheme.colors.gray0)
            .padding(horizontal = screenWidthDp(12.dp), vertical = screenHeightDp(6.dp))
    ) {
        Text(
            text = categoryType.description,
            style = CertiTheme.typography.caption.regular_12,
            color = CertiTheme.colors.gray600
        )
    }
}
