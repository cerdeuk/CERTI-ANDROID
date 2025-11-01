package org.sopt.certi.presentation.ui.certlist.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.domain.type.CategoryType
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun CategoryBar(
    modifier: Modifier = Modifier,
    labels: List<String>,
    selectedCategory: Int = 0,
    onCategorySelected: (Int) -> Unit = {}
) {
    LazyRow(
        modifier = modifier
            .padding(vertical = screenHeightDp(10.dp))
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(screenWidthDp(22.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(labels.size) { index ->
            if (index == 0) {
                Spacer(Modifier.width(screenWidthDp(18.dp)))
            }

            val category = labels[index]

            Text(
                text = category,
                style = if (index == selectedCategory) CertiTheme.typography.body.bold_16 else CertiTheme.typography.body.semibold_16,
                color = if (index == selectedCategory) CertiTheme.colors.gray500 else CertiTheme.colors.gray400,
                modifier = Modifier.noRippleClickable { onCategorySelected(index) }
            )

            if (index == labels.lastIndex) {
                Spacer(Modifier.width(screenWidthDp(18.dp)))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCategoryBar() {
    var selectedIndex by remember { mutableIntStateOf(0) }

    CERTITheme {
        CategoryBar(
            labels = CategoryType.entries.map { it.description },
            selectedCategory = selectedIndex,
            onCategorySelected = { selectedIndex = it }
        )
    }
}
