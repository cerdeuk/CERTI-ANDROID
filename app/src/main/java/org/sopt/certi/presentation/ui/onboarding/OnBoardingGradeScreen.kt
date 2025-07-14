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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
fun OnBoardingGradeRoute(
    padding: PaddingValues,
    navigateToTrack: () -> Unit,
    viewModel: OnBoardingViewModel
) {
    val grade by viewModel.grade.collectAsStateWithLifecycle()

    OnBoardingGradeScreen(
        selectedGrade = grade,
        onGradeSelected = viewModel::onGradeSelected,
        navigateToTrack = navigateToTrack,
        modifier = Modifier.padding(padding)
    )
}

@Composable
fun OnBoardingGradeScreen(
    selectedGrade: String?,
    onGradeSelected: (String?) -> Unit,
    navigateToTrack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(top = screenHeightDp(50.dp), bottom = screenHeightDp(22.dp), start = screenWidthDp(20.dp), end = screenWidthDp(20.dp))
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.img_onboarding_progressbar_2),
                contentDescription = null,
                modifier = Modifier
                    .widthForScreenPercentage(200.dp),
                contentScale = ContentScale.FillWidth
            )
            Spacer(modifier = Modifier.padding(top = screenHeightDp(40.dp)))

            Text(
                text = stringResource(R.string.onboarding_grade_title),
                style = CertiTheme.typography.subtitle.bold_20,
                color = CertiTheme.colors.gray600
            )
            Spacer(modifier = Modifier.padding(top = screenHeightDp(38.dp)))

            OnBoardingGradeSection(
                selectedGrade = selectedGrade,
                onGradeSelected = onGradeSelected
            )
        }

        CertiBasicButton(
            buttonText = stringResource(R.string.button_next),
            onClick = navigateToTrack,
            modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.BottomCenter),
            enabled = (selectedGrade != null)
        )
    }
}

@Composable
private fun OnBoardingGradeSection(
    selectedGrade: String?,
    onGradeSelected: (String?) -> Unit,
    modifier: Modifier = Modifier
) {
    OnBoardingSelectableButtons(
        selectableButtonType = SelectableButtonType.GRADE,
        selectedOptions = selectedGrade?.let { listOf(it) } ?: emptyList(),
        onOptionsChanged = { options -> onGradeSelected(options.firstOrNull()) },
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewOnBoardingGradeScreen() {
    CERTITheme {
    }
}
