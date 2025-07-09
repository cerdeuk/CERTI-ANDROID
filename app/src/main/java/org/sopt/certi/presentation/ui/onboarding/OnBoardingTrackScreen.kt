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
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.presentation.type.SelectableButtonType
import org.sopt.certi.presentation.ui.onboarding.component.OnBoardingSelectableButtons
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun OnBoardingTrackRoute(
    padding: PaddingValues,
    navigateToMajor: () -> Unit,
    viewModel: OnBoardingViewModel
) {
    var selectedOption by remember { mutableStateOf("") }

    OnBoardingTrackScreen(
        selectedOptions = listOfNotNull(selectedOption),
        onOptionsChanged = { options -> selectedOption = options.firstOrNull().toString() },
        navigateToMajor = navigateToMajor,
        modifier = Modifier.padding(padding)
    )
}

@Composable
fun OnBoardingTrackScreen(
    selectedOptions: List<String>,
    onOptionsChanged: (List<String>) -> Unit,
    navigateToMajor: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(top = screenHeightDp(50.dp), bottom = screenHeightDp(22.dp), start = screenWidthDp(20.dp), end = screenWidthDp(20.dp))
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.img_onboarding_progressbar_3),
                contentDescription = null,
                modifier = Modifier
                    .widthForScreenPercentage(200.dp),
                contentScale = ContentScale.FillWidth
            )
            Spacer(modifier = Modifier.padding(top = screenHeightDp(40.dp)))

            Text(
                text = stringResource(R.string.onboarding_track_title),
                style = CertiTheme.typography.subtitle.bold_20,
                color = CertiTheme.colors.gray600
            )
            Spacer(modifier = Modifier.padding(top = screenHeightDp(38.dp)))

            OnBoardingTrackSection(
                selectedOptions = selectedOptions,
                onOptionsChanged = onOptionsChanged
            )
        }

        CertiBasicButton(
            buttonText = stringResource(R.string.button_next),
            onClick = navigateToMajor,
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
private fun OnBoardingTrackSection(
    selectedOptions: List<String>,
    onOptionsChanged: (List<String>) -> Unit,
    modifier: Modifier = Modifier
) {
    OnBoardingSelectableButtons(
        selectableButtonType = SelectableButtonType.TRACK,
        selectedOptions = selectedOptions,
        isMultiple = false,
        onOptionsChanged = onOptionsChanged
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewOnBoardingTrackScreen() {
    CERTITheme {
        OnBoardingTrackScreen(
            selectedOptions = listOf("2"),
            onOptionsChanged = {},
            navigateToMajor = {}
        )
    }
}
