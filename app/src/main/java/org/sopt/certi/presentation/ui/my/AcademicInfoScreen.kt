package org.sopt.certi.presentation.ui.my

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.presentation.ui.my.component.MyJobCategorySection
import org.sopt.certi.presentation.ui.my.component.MySchoolSection
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun AcademicInfoRoute() {
}

@Composable
fun AcademicInfoScreen(
    onSchoolManageClick: () -> Unit,
    onMajorManageClick: () -> Unit,
    jobCategoryList: List<String>,
    onReselectCategoryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.my_academic_info_title),
            style = CertiTheme.typography.subtitle.semibold_20,
            color = CertiTheme.colors.gray600,
            modifier = Modifier.padding(vertical = screenHeightDp(22.dp))
        )
        MySchoolSection(
            onSchoolManageClick = onSchoolManageClick,
            onMajorManageClick = onMajorManageClick,
            modifier = Modifier
                .padding(top = screenHeightDp(32.dp), bottom = screenHeightDp(24.dp))
                .padding(horizontal = screenWidthDp(20.dp))
        )
        HorizontalDivider(
            color = CertiTheme.colors.gray100
        )
        MyJobCategorySection(
            jobCategoryList = jobCategoryList,
            onClick = onReselectCategoryClick,
            modifier = Modifier.padding(horizontal = screenWidthDp(20.dp), vertical = screenHeightDp(24.dp))
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MySchoolInfoPreview() {
    CERTITheme {
        AcademicInfoScreen(
            onSchoolManageClick = {},
            onMajorManageClick = {},
            jobCategoryList = listOf("재무/세무/IR", "재무/세무/IR", "재무/세무/IR"),
            onReselectCategoryClick = {}
        )
    }
}
