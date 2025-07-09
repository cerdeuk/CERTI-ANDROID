package org.sopt.certi.presentation.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.certi.R
import org.sopt.certi.core.component.button.CertiBasicButton
import org.sopt.certi.core.component.textfield.CertiBasicTextField
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun OnBoardingUnivRoute(
    padding: PaddingValues,
    navigateToGrade: () -> Unit,
    viewModel: OnBoardingViewModel
) {
    var searchText by remember { mutableStateOf("") }

    OnBoardingUnivScreen(
        value = searchText,
        onValueChange = { searchText = it },
        onSearchClick = {},
        navigateToGrade = navigateToGrade,
        modifier = Modifier.padding(padding)
    )
}

@Composable
fun OnBoardingUnivScreen(
    value: String,
    onValueChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    navigateToGrade: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(top = screenHeightDp(0.064f), bottom = screenHeightDp(0.025f), start = screenWidthDp(0.05f), end = screenWidthDp(0.05f))
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.img_onboarding_progressbar_1),
                contentDescription = null,
                modifier = Modifier
                    .widthForScreenPercentage(0.55f),
                contentScale = ContentScale.FillWidth
            )
            Spacer(modifier = Modifier.padding(top = screenHeightDp(0.051f)))

            Text(
                text = stringResource(R.string.onboarding_univ_title),
                style = CertiTheme.typography.subtitle.bold_20,
                color = CertiTheme.colors.gray600
            )
            Spacer(modifier = Modifier.padding(top = screenHeightDp(0.048f)))

            OnboardingUnivSection(
                value = value,
                onValueChange = onValueChange,
                onSearchClick = onSearchClick
            )
        }

        CertiBasicButton(
            buttonText = stringResource(R.string.button_next),
            onClick = navigateToGrade,
            modifier = Modifier
                .fillMaxWidth()
                .align(
                    alignment = Alignment.BottomCenter
                ),
            enabled = true
        )
    }
}

@Composable
private fun OnboardingUnivSection(
    value: String,
    onValueChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    CertiBasicTextField(
        value = value,
        onValueChange = onValueChange,
        onSearchClick = onSearchClick,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewOnBoardingUnivScreen() {
    CERTITheme {
        var text by remember { mutableStateOf("") }
        OnBoardingUnivScreen(
            value = text,
            onValueChange = { text = it },
            onSearchClick = {},
            navigateToGrade = {}
        )
    }
}
