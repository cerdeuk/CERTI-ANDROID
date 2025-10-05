package org.sopt.certi.presentation.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.sopt.certi.R
import org.sopt.certi.core.component.button.CertiBasicButton
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.presentation.type.SelectableButtonType
import org.sopt.certi.presentation.ui.onboarding.component.OnBoardingSelectableButtons
import org.sopt.certi.presentation.ui.onboarding.state.JobCategoryStep
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme
import timber.log.Timber

@Composable
fun OnBoardingJobCategoryRoute(
    padding: PaddingValues,
    navigateToOnBoardingNickName: () -> Unit,
    viewModel: OnBoardingViewModel
) {
    val jobCategory by viewModel.onBoardingJobCategoryUiState.collectAsStateWithLifecycle()

    OnBoardingJobCategoryScreen(
        step = jobCategory.step,
        selectedJobCategory = jobCategory.selectedList,
        onNextClick = { selected ->
            val curStep = jobCategory.step
            viewModel.onJobCategorySelected(selected)

            if (curStep == JobCategoryStep.THIRD) {
                navigateToOnBoardingNickName()
            } else {
                viewModel.onJobCategoryNextClicked()
            }
        },
        onSkipClick = {
            viewModel.onJobCategoryNextClicked()
        },
        modifier = Modifier.padding(padding)
    )
}

@Composable
fun OnBoardingJobCategoryScreen(
    step: JobCategoryStep,
    selectedJobCategory: List<String>,
    onNextClick: (String) -> Unit,
    onSkipClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var candidate by remember{ mutableStateOf<String?>(null) }

    LaunchedEffect(step) {
        candidate = null
    }

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
                candidate = candidate,
                onCandidateChanged = { candidate = it },
                disabledOptions = selectedJobCategory
            )
        }

        if (step == JobCategoryStep.FIRST) {
            CertiBasicButton(
                buttonText = stringResource(R.string.button_next),
                onClick = { candidate?.let { onNextClick(it) } },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.BottomCenter),
                enabled = candidate != null
            )
        } else {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            color = CertiTheme.colors.gray100,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clip(RoundedCornerShape(12.dp))
                        .noRippleClickable(onClick = onSkipClick)
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.button_skip),
                        style = CertiTheme.typography.body.semibold_16,
                        color = CertiTheme.colors.gray500,
                        modifier = Modifier.padding(vertical = 18.dp)
                    )
                }
                Spacer(modifier = Modifier.padding(end = screenWidthDp(16.dp)))

                CertiBasicButton(
                    buttonText = stringResource(R.string.button_next),
                    onClick = { candidate?.let { onNextClick(it) } },
                    modifier = Modifier.weight(1f),
                    enabled = candidate != null
                )
            }
        }
    }
}

@Composable
private fun OnBoardingJobCategorySection(
    candidate: String?,
    onCandidateChanged: (String) -> Unit,
    disabledOptions: List<String>,
    modifier: Modifier = Modifier
) {
    OnBoardingSelectableButtons(
        selectableButtonType = SelectableButtonType.CATEGORY,
        selectedOption = candidate,
        onOptionChanged = onCandidateChanged,
        modifier = modifier,
        disabledOptions = disabledOptions
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewOnBoardingJobCategoryScreen() {
    CERTITheme {
        OnBoardingJobCategoryScreen(
            selectedJobCategory = listOf(),
            onSkipClick = {},
            onNextClick = {},
            step = JobCategoryStep.FIRST
        )
    }
}
