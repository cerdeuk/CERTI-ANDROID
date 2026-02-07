package org.sopt.certi.presentation.ui.mypage.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun MyPageMenuItem(
    @DrawableRes iconId: Int,
    title: String,
    description: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .noRippleClickable(onClick)
            .background(CertiTheme.colors.white)
            .padding(vertical = screenHeightDp(12.dp), horizontal = screenWidthDp(12.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(iconId),
            contentDescription = null,
            tint = CertiTheme.colors.gray400,
            modifier = Modifier.padding(start = screenWidthDp(4.dp), end = screenWidthDp(20.dp))
        )
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = title,
                style = CertiTheme.typography.caption.semibold_14,
                color = CertiTheme.colors.black,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Text(
                text = description,
                style = CertiTheme.typography.caption.regular_12,
                color = CertiTheme.colors.gray600,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_arrowright_24),
            contentDescription = null,
            tint = CertiTheme.colors.gray600
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MyPageMenuItemPreview() {
    CERTITheme {
        MyPageMenuItem(
            iconId = R.drawable.ic_person_24,
            title = "개인 정보 수정",
            description = "프로필 사진 및 개인 정보 수정",
            onClick = {}
        )
    }
}
