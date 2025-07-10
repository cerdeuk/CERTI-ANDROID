package org.sopt.certi.presentation.ui.certlist.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.roundedBackgroundWithBorder
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun CategoryFavoriteButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isFavorite: Boolean = false
) {
    Box(
        modifier = modifier
            .roundedBackgroundWithBorder(
                cornerRadius = 24.dp,
                borderWidth = 1.dp,
                borderColor = if (isFavorite) CertiTheme.colors.mainBlue else CertiTheme.colors.gray100,
                backgroundColor = CertiTheme.colors.white
            )
            .noRippleClickable(onClick)
    ) {
        Text(
            text = stringResource(R.string.cert_list_favorite_btn),
            style = CertiTheme.typography.caption.semibold_12,
            color = if (isFavorite) CertiTheme.colors.mainBlue else CertiTheme.colors.gray400,
            modifier = Modifier.padding(vertical = screenHeightDp(6.dp), horizontal = screenWidthDp(12.dp))
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCategoryFavoriteButton() {
    var isFavorite by remember { mutableStateOf(false) }

    CERTITheme {
        CategoryFavoriteButton(
            onClick = { isFavorite = !isFavorite },
            isFavorite = isFavorite
        )
    }
}
