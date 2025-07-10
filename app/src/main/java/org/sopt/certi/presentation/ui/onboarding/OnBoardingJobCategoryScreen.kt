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
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.component.button.CertiBasicButton
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.presentation.type.SelectableButtonType
import org.sopt.certi.presentation.ui.onboarding.component.OnBoardingSelectableButtons
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun OnBoardingJobCategoryRoute(
    padding: PaddingValues,
    navigateToOnBoardingInfo: () -> Unit,
    viewModel: OnBoardingViewModel
) {
    var selectedOptions by remember { mutableStateOf<List<String>>(emptyList()) }

    OnBoardingJobCategoryScreen(
        selectedOptions = selectedOptions,
        onOptionsChanged = { options -> selectedOptions = options },
        navigateToOnBoardingInfo = navigateToOnBoardingInfo,
        modifier = Modifier.padding(padding)
    )
}

@Composable
fun OnBoardingJobCategoryScreen(
    selectedOptions: List<String>,
    onOptionsChanged: (List<String>) -> Unit,
    navigateToOnBoardingInfo: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(top = screenHeightDp(50.dp), bottom = screenHeightDp(22.dp), start = screenWidthDp(20.dp), end = screenWidthDp(20.dp))
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.img_onboarding_progressbar_5),
                contentDescription = null,
                modifier = Modifier
                    .widthForScreenPercentage(200.dp),
                contentScale = ContentScale.FillWidth
            )
            Spacer(modifier = Modifier.padding(top = screenHeightDp(40.dp)))

            Text(
                text = stringResource(R.string.onboarding_category_title),
                style = CertiTheme.typography.subtitle.bold_20,
                color = CertiTheme.colors.gray600
            )
            Spacer(modifier = Modifier.padding(top = screenHeightDp(4.dp)))

            Text(
                text = stringResource(R.string.onboarding_category_description),
                style = CertiTheme.typography.caption.regular_12,
                color = CertiTheme.colors.gray400
            )

            Spacer(modifier = Modifier.padding(top = screenHeightDp(18.dp)))

            OnBoardingJobCategorySection(
                selectedOptions = selectedOptions,
                onOptionsChanged = onOptionsChanged
            )
        }

        CertiBasicButton(
            buttonText = stringResource(R.string.button_next),
            onClick = navigateToOnBoardingInfo,
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
private fun OnBoardingJobCategorySection(
    selectedOptions: List<String>,
    onOptionsChanged: (List<String>) -> Unit,
    modifier: Modifier = Modifier
) {
    OnBoardingSelectableButtons(
        selectableButtonType = SelectableButtonType.CATEGORY,
        selectedOptions = selectedOptions,
        isMultiple = true,
        maxSelect = 3,
        onOptionsChanged = onOptionsChanged,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewOnBoardingJobCategoryScreen() {
    CERTITheme {
        OnBoardingJobCategoryScreen(
            selectedOptions = listOf("2"),
            onOptionsChanged = {},
            navigateToOnBoardingInfo = {}
        )
    }
}
