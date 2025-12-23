package org.sopt.certi.presentation.ui.myacademicinfo.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun MyUnivSection(
    onUnivManageClick: () -> Unit,
    onMajorManageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.my_school_info_title),
            style = CertiTheme.typography.body.semibold_16,
            color = CertiTheme.colors.gray400,
            modifier = Modifier.padding(bottom = screenHeightDp(24.dp))
        )
        MyUnivManageItem(
            text = stringResource(R.string.my_school_info_modify),
            onClick = onUnivManageClick,
            modifier = Modifier.padding(bottom = screenHeightDp(16.dp))
        )
        MyUnivManageItem(
            text = stringResource(R.string.my_school_major_modify),
            onClick = onMajorManageClick
        )
    }
}

@Composable
private fun MyUnivManageItem(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        Text(
            text = text,
            style = CertiTheme.typography.body.semibold_16,
            color = CertiTheme.colors.black,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_arrowright_24),
            contentDescription = null,
            tint = CertiTheme.colors.black,
            modifier = Modifier.noRippleClickable { onClick() }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MySchoolManagePreview() {
    CERTITheme {
        MyUnivSection(
            onUnivManageClick = {},
            onMajorManageClick = {}
        )
    }
}
