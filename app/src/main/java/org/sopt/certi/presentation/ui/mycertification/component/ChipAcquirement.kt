package org.sopt.certi.presentation.ui.mycertification.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.presentation.type.MyCertType
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun ChipAcquirement(
    isAcquired: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(100.dp))
            .background(if (isAcquired) CertiTheme.colors.mainBlue else CertiTheme.colors.purpleBlue)
            .padding(vertical = 4.dp, horizontal = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_progresscheck_white),
            contentDescription = null,
            tint = Color.Unspecified
        )

        Text(
            text = stringResource(if (isAcquired) MyCertType.ACQUIRED.titleResId else MyCertType.PLANNED.titleResId),
            style = CertiTheme.typography.caption.semibold_10,
            color = CertiTheme.colors.white
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ChipAcquirementPreview() {
    CERTITheme {
        Column {
            ChipAcquirement(
                isAcquired = true
            )
            ChipAcquirement(
                isAcquired = false
            )
        }
    }
}
