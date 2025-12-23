package org.sopt.certi.presentation.ui.mycertification.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.presentation.type.MyCertType
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun CertStatus(
    isAcquired: Boolean,
    modifier: Modifier = Modifier,
    isEditMode: Boolean,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Row(
        modifier = modifier
    ) {
        ChipAcquirement(isAcquired = isAcquired)
        if (isEditMode) {
            Spacer(modifier = Modifier.weight(1f))
            CertChipButton(
                text = stringResource(R.string.my_certification_modify),
                onClick = onEditClick,
                modifier = Modifier.padding(end = screenWidthDp(12.dp))
            )
            CertChipButton(
                text = stringResource(R.string.my_certification_delete),
                onClick = onDeleteClick
            )
        }
    }
}

@Composable
private fun ChipAcquirement(
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
            text = stringResource(if (isAcquired) MyCertType.ACQUIRED.label else MyCertType.PLANNED.label),
            style = CertiTheme.typography.caption.semibold_10,
            color = CertiTheme.colors.white
        )
    }
}

@Composable
private fun CertChipButton(
    text: String,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .noRippleClickable { onClick?.invoke() }
            .border(
                width = 1.dp,
                color = CertiTheme.colors.gray300,
                shape = RoundedCornerShape(100.dp)
            )
            .padding(vertical = screenWidthDp(4.dp), horizontal = 12.dp)
    ) {
        Text(
            text = text,
            style = CertiTheme.typography.caption.regular_12,
            color = CertiTheme.colors.gray600
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CertStatusPreview() {
    CERTITheme {
        Column {
            CertStatus(
                isAcquired = true,
                isEditMode = false,
                onEditClick = {},
                onDeleteClick = {}
            )
            CertStatus(
                isAcquired = false,
                isEditMode = true,
                onEditClick = {},
                onDeleteClick = {}
            )
        }
    }
}
