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
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.certi.ui.theme.CERTITheme


@Composable
fun UserInfoSection(
    userInfoData: UserInfoData,
    modifier: Modifier = Modifier
) {
    val fullText = stringResource(R.string.home_fill_completed, userInfoData.percentage)
    val percentageText = "${userInfoData.percentage}%"
    val start = fullText.indexOf(percentageText)
    val end = start + percentageText.length

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 36.dp)
    ) {
        Text(
            text = stringResource(id = R.string.home_user_info_name, userInfoData.name),
            style = CertiTheme.typography.subtitle.bold_20,
            color = CertiTheme.colors.gray600
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_profile),
                contentDescription = null,
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = userInfoData.name,
                style = CertiTheme.typography.body.semibold_16,
                color = CertiTheme.colors.gray600
            )
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .width(2.dp)
                    .padding(vertical = 3.dp)
                    .background(CertiTheme.colors.gray100)
            )
            Spacer(modifier = Modifier.width(8.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
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
        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
                .height(5.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(CertiTheme.colors.gray100)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(userInfoData.percentage / 100f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(12.dp))
                    .background(CertiTheme.colors.purpleBlue)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = buildAnnotatedString {
                append(fullText)
                addStyle(
                    style = SpanStyle(
                        fontSize = CertiTheme.typography.caption.bold_14.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = CertiTheme.colors.mainBlue
                    ),
                    start = start,
                    end = end
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
        name = "김서티",
        university = "솝트대학교",
        major = "서티취득학과",
        percentage = 48
    )

    CERTITheme {
        UserInfoSection(userInfoData = dummyUserInfo)
    }
}