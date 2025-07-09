package org.sopt.certi.presentation.ui.onboarding

import android.icu.number.Scale
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.component.button.CertiBasicButton
import org.sopt.certi.core.util.heightForScreenPercentage
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.ui.theme.CERTITheme

@Composable
fun OnBoardingUnivRoute(
    padding: PaddingValues,
    navigateToGrade: () -> Unit,
    viewModel: OnBoardingViewModel
) {
    OnBoardingUnivScreen(
        navigateToGrade = navigateToGrade,
        modifier = Modifier.padding(padding)
    )
}

@Composable
fun OnBoardingUnivScreen(
    navigateToGrade: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(top = screenHeightDp(0.064f), bottom = screenHeightDp(0.025f), start = screenWidthDp(0.05f), end = screenWidthDp(0.05f))
    ){
        Image(
            painter = painterResource(id = R.drawable.img_onboarding_progressbar_1),
            contentDescription = null,
            modifier = Modifier
                .widthForScreenPercentage(0.61f),
            contentScale = ContentScale.FillWidth
        )

        CertiBasicButton(

        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewOnBoardingUnivScreen() {
    CERTITheme {
        OnBoardingUnivScreen(
            navigateToGrade = {}
        )
    }
}
