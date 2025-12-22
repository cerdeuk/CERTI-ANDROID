package org.sopt.certi.presentation.ui.mycertification.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.util.bottomBorder
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.presentation.type.MyCertType
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun MyCertHeader(
    selectedType: MyCertType,
    onTabSelected: (MyCertType) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.mypage_certification),
            style = CertiTheme.typography.subtitle.semibold_20,
            color = CertiTheme.colors.gray600,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = screenWidthDp(24.dp)),
            textAlign = TextAlign.Center
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MyCertType.entries.forEach { type ->
                MyCertCategory(
                    category = type,
                    isSelected = (selectedType == type),
                    onClick = onTabSelected
                )
            }
        }
    }
}

@Composable
private fun MyCertCategory(
    category: MyCertType,
    onClick: (MyCertType) -> Unit,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false
) {
    Text(
        text = stringResource(category.titleResId),
        style = CertiTheme.typography.body.bold_16,
        color = if (isSelected) CertiTheme.colors.gray600 else CertiTheme.colors.gray300,
        modifier = modifier
            .noRippleClickable {
                onClick(category)
            }
            .run {
                if (isSelected) {
                    bottomBorder(
                        strokeWidth = 2.dp,
                        color = CertiTheme.colors.gray600
                    )
                } else {
                    this
                }
            }
            .padding(screenWidthDp(10.dp))

    )
}

@Preview(showBackground = true)
@Composable
private fun MyCertHeaderPreview() {
    var myCertTab by remember { mutableStateOf(MyCertType.PLANNED) }
    CERTITheme {
        MyCertHeader(
            selectedType = myCertTab,
            onTabSelected = { myCertTab = it }
        )
    }
}
