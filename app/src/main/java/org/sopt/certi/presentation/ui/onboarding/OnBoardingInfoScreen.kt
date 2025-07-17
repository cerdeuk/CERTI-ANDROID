package org.sopt.certi.presentation.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.sopt.certi.R
import org.sopt.certi.core.component.button.CertiBasicButton
import org.sopt.certi.core.component.chip.CertiDefaultChip
import org.sopt.certi.core.util.heightForScreenPercentage
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.domain.model.user.UserInfoData
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.sopt.certi.core.component.loading.LoadingLayout

@Composable
fun OnBoardingInfoRoute(
    padding: PaddingValues,
    navigateToHome: () -> Unit,
    viewModel: OnBoardingViewModel
) {
    val coroutineScope = rememberCoroutineScope()
    val userInfo by viewModel.userInfo.collectAsStateWithLifecycle()

    var showLoadingLayout by remember { mutableStateOf(false) }

    userInfo?.let {
        OnBoardingInfoScreen(
            userInfo = it,
            navigateToHome = {
                coroutineScope.launch {
                    showLoadingLayout = true
                    delay(2000)
                    navigateToHome()
                    delay(500)
                    showLoadingLayout = false
                }
            },
            modifier = Modifier.padding(padding)
        )
    }

    if (showLoadingLayout) {
        LoadingLayout(
            userName = userInfo?.name ?: ""
        )
    }
}

@Composable
fun OnBoardingInfoScreen(
    userInfo: UserInfoData,
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(top = screenHeightDp(68.dp), bottom = screenHeightDp(22.dp), start = screenWidthDp(20.dp))
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = buildAnnotatedString {
                        append(stringResource(R.string.onboarding_info_nickname, userInfo.name))

                        addStyle(
                            style = SpanStyle(
                                color = CertiTheme.colors.purpleBlue,
                                fontSize = CertiTheme.typography.subtitle.bold_20.fontSize,
                                fontWeight = FontWeight.Bold
                            ),
                            start = 0,
                            end = userInfo.name.length.coerceAtMost(userInfo.name.length)
                        )
                    },
                    style = CertiTheme.typography.subtitle.bold_20,
                    color = CertiTheme.colors.black
                )

                Image(
                    painterResource(R.drawable.img_onboarding_image_small),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = screenWidthDp(4.dp))
                        .widthForScreenPercentage(60.dp)
                        .heightForScreenPercentage(58.dp)
                )
            }
            Spacer(modifier = Modifier.height(screenHeightDp(12.dp)))

            Text(
                text = stringResource(R.string.onboarding_info_title),
                style = CertiTheme.typography.subtitle.bold_20,
                color = CertiTheme.colors.gray600
            )
            Spacer(modifier = Modifier.height(screenHeightDp(46.dp)))

            Text(
                text = stringResource(R.string.onboarding_info_final_education_title),
                style = CertiTheme.typography.body.semibold_18,
                color = CertiTheme.colors.black,
                modifier = Modifier.padding(bottom = screenHeightDp(14.dp))
            )

            Text(
                text = userInfo.university,
                style = CertiTheme.typography.body.semibold_16,
                color = CertiTheme.colors.mainBlue
            )
            Spacer(modifier = Modifier.height(screenHeightDp(36.dp)))

            Text(
                text = stringResource(R.string.onboarding_info_major_title),
                style = CertiTheme.typography.body.semibold_18,
                color = CertiTheme.colors.black,
                modifier = Modifier.padding(bottom = screenHeightDp(14.dp))
            )

            Text(
                text = userInfo.track + " " + userInfo.major,
                style = CertiTheme.typography.body.semibold_16,
                color = CertiTheme.colors.mainBlue
            )
            Spacer(modifier = Modifier.height(screenHeightDp(36.dp)))

            Text(
                text = stringResource(R.string.onboarding_info_category_title),
                style = CertiTheme.typography.body.semibold_18,
                color = CertiTheme.colors.black,
                modifier = Modifier.padding(bottom = screenHeightDp(14.dp))
            )

            userInfo.category.forEach {
                CertiDefaultChip(
                    text = it,
                    textStyle = CertiTheme.typography.caption.semibold_14,
                    backgroundColor = CertiTheme.colors.purpleWhite
                )
                Spacer(modifier = Modifier.height(screenHeightDp(14.dp)))
            }
        }

        Image(
            painter = painterResource(id = R.drawable.img_onboarding_firework),
            contentDescription = null,
            modifier = Modifier
                .padding(bottom = screenHeightDp(56.dp))
                .align(Alignment.BottomEnd)
                .widthForScreenPercentage(214.dp)
                .heightForScreenPercentage(310.dp)
        )

        CertiBasicButton(
            buttonText = stringResource(R.string.button_start),
            onClick = navigateToHome,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = screenWidthDp(20.dp))
                .align(
                    alignment = Alignment.BottomCenter
                ),
            enabled = true
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewOnBoardingOnBoardingInfoScreen() {
    val userInfo = UserInfoData(
        name = "김서티",
        university = "솝트대학교",
        major = "경영학과",
        category = listOf("경영/사무", "무역/유통", "마케팅/광고/홍보")
    )

    CERTITheme {
        OnBoardingInfoScreen(
            userInfo = userInfo,
            navigateToHome = {}
        )
    }
}
