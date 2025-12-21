package org.sopt.certi.presentation.ui.certlist.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun CategoryTopBar(
    @StringRes title: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(title),
            style = CertiTheme.typography.subtitle.semibold_20,
            color = CertiTheme.colors.gray600,
            modifier = Modifier
                .padding(vertical = screenHeightDp(22.dp))
                .align(Alignment.Center)
        )

        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_search_24),
            contentDescription = null,
            tint = CertiTheme.colors.gray400,
            modifier = Modifier
                .noRippleClickable(onClick)
                .align(Alignment.CenterEnd)
                .size(screenWidthDp(24.dp))
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCategoryTopBar() {
    CERTITheme {
        CategoryTopBar(
            title = R.string.category_cert_list_top_bar,
            onClick = {}
        )
    }
}
