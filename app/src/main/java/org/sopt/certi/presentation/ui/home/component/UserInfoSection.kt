package org.sopt.certi.presentation.ui.home.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.sopt.certi.domain.model.UserInfoData
import org.sopt.certi.ui.theme.CertiTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import org.sopt.certi.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.certi.ui.theme.CERTITheme
import androidx.compose.material3.VerticalDivider
import org.sopt.certi.core.util.roundedBackgroundWithBorder
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp

@Composable
fun UserInfoSection(
    userInfoData: UserInfoData,
    modifier: Modifier = Modifier
) {
    val displayName = if (userInfoData.name.length >= 4) {
        userInfoData.name.take(3) + "…"
    } else {
        userInfoData.name
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = screenWidthDp(20.dp), vertical = screenHeightDp(36.dp))
    ) {
        Text(
            text = stringResource(id = R.string.home_user_info_name, displayName),
            style = CertiTheme.typography.subtitle.bold_20,
            color = CertiTheme.colors.gray600
        )
        Spacer(modifier = Modifier.height(screenHeightDp(24.dp)))

        Row(
            modifier = Modifier
                .padding(vertical = screenHeightDp(2.dp))
                .height(IntrinsicSize.Max),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_profile),
                contentDescription = null,
                modifier = Modifier
                    .width(screenWidthDp(80.dp))
                    .height(screenHeightDp(80.dp))
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

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = screenHeightDp(4.dp))
                .height(screenHeightDp(4.dp))
                .roundedBackgroundWithBorder(
                    cornerRadius = 12.dp,
                    backgroundColor = CertiTheme.colors.gray100
                )

        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(userInfoData.percentage / 100f)
                    .fillMaxHeight()
                    .roundedBackgroundWithBorder(
                        cornerRadius = 12.dp,
                        backgroundColor = CertiTheme.colors.purpleBlue
                    )
            )
        }
        Spacer(modifier = Modifier.height(screenHeightDp(8.dp)))

        Text(
            text = buildAnnotatedString {
                append(stringResource(R.string.home_fill_completed, userInfoData.percentage))
                addStyle(
                    style = SpanStyle(
                        fontSize = CertiTheme.typography.caption.bold_14.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = CertiTheme.colors.mainBlue
                    ),
                    start = stringResource(R.string.home_fill_completed, userInfoData.percentage).indexOf("${userInfoData.percentage}%"),
                    end = stringResource(R.string.home_fill_completed, userInfoData.percentage).indexOf("${userInfoData.percentage}%") + "${userInfoData.percentage}%".length
                )
            },
            style = CertiTheme.typography.caption.regular_14,
            color = CertiTheme.colors.gray600
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUserInfoSection() {
    val dummyUserInfo = UserInfoData(
        name = "김서티야",
        university = "솝트대학교",
        major = "서티취득학과",
        percentage = 48,
        category = listOf()
    )

    CERTITheme {
        UserInfoSection(userInfoData = dummyUserInfo)
    }
}
