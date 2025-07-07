package org.sopt.certi.presentation.ui.login.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.presentation.type.SocialLoginType
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun SocialLoginButton(
    socialLoginType: SocialLoginType,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = socialLoginType.backgroundColor,
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = if (socialLoginType == SocialLoginType.GOOGLE) 1.dp else 0.dp,
                color = CertiTheme.colors.gray200,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(vertical = 16.dp)
            .noRippleClickable(onButtonClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = socialLoginType.icon),
            contentDescription = null,
            modifier = Modifier.size(22.dp)
        )

        Text(
            text = stringResource(socialLoginType.label),
            style = CertiTheme.typography.body.semibold_16,
            color = CertiTheme.colors.black,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSocialLoginButton1() {
    CERTITheme {
        SocialLoginButton(
            socialLoginType = SocialLoginType.KAKAO,
            onButtonClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSocialLoginButton2() {
    CERTITheme {
        SocialLoginButton(
            socialLoginType = SocialLoginType.GOOGLE,
            onButtonClick = {}
        )
    }
}
