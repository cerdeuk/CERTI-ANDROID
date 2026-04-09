package org.sopt.certi.presentation.ui.home.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.sopt.certi.domain.model.user.UserInfoData
import org.sopt.certi.ui.theme.CertiTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import org.sopt.certi.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.certi.ui.theme.CERTITheme
import androidx.compose.material3.VerticalDivider
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberAsyncImagePainter
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp

@Composable
fun UserInfoSection(
    userInfoData: UserInfoData,
    modifier: Modifier = Modifier
) {
    val displayName = if (userInfoData.nickname.length >= 4) {
        userInfoData.nickname.take(3) + "…"
    } else {
        userInfoData.nickname
    }

    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = screenHeightDp(2.dp))
                .height(IntrinsicSize.Max),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = if (userInfoData.profileImageUrl.isNullOrBlank()) {
                    painterResource(id = R.drawable.img_profile)
                } else {
                    rememberAsyncImagePainter(userInfoData.profileImageUrl)
                },
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(screenWidthDp(80.dp))
                    .height(screenHeightDp(80.dp))
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(screenWidthDp(8.dp)))

            Text(
                text = displayName,
                style = CertiTheme.typography.body.semibold_16,
                color = CertiTheme.colors.gray600
            )
            Spacer(modifier = Modifier.width(screenWidthDp(8.dp)))
            VerticalDivider(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(vertical = screenHeightDp(18.dp)),
                color = CertiTheme.colors.gray100,
                thickness = 2.dp

            )
            Spacer(modifier = Modifier.width(screenWidthDp(8.dp)))

            Column(
                verticalArrangement = Arrangement.spacedBy(screenHeightDp(5.dp))
            ) {
                Text(
                    text = userInfoData.university,
                    style = CertiTheme.typography.body.semibold_16,
                    color = CertiTheme.colors.gray600
                )
                Text(
                    text = userInfoData.major,
                    style = CertiTheme.typography.body.semibold_16,
                    color = CertiTheme.colors.gray600
                )
            }
        }
        Spacer(modifier = Modifier.height(screenHeightDp(12.dp)))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUserInfoSection() {
    val dummyUserInfo = UserInfoData(
        name = "김서티야",
        university = "솝트대학교",
        major = "서티취득학과",
        category = listOf()
    )

    CERTITheme {
        UserInfoSection(
            userInfoData = dummyUserInfo,
            modifier = Modifier.padding(horizontal = screenWidthDp(20.dp))
        )
    }
}
