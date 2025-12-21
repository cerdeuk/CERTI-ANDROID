package org.sopt.certi.presentation.ui.myacademicinfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.sopt.certi.R
import org.sopt.certi.core.component.header.EditInfoHeader
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.presentation.ui.myacademicinfo.component.JobCategorySection
import org.sopt.certi.presentation.ui.myacademicinfo.component.MyUnivSection
import org.sopt.certi.presentation.ui.myacademicinfo.component.SelectJobCategoryBottomSheet
import org.sopt.certi.presentation.ui.myacademicinfo.state.AcademicUiState
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun UnivInfoRoute() { }

@Composable
fun UnivInfoScreen(
    uiState: AcademicUiState,
    onSchoolManageClick: () -> Unit,
    onMajorManageClick: () -> Unit,
    onReselectCategoryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EditInfoHeader(
            headerTitle = stringResource(R.string.my_academic_info_title),
            modifier = Modifier.padding(vertical = screenWidthDp(20.dp))
        )
        MyUnivSection(
            onSchoolManageClick = onSchoolManageClick,
            onMajorManageClick = onMajorManageClick,
            modifier = Modifier
                .padding(top = screenHeightDp(32.dp), bottom = screenHeightDp(24.dp))
                .padding(horizontal = screenWidthDp(20.dp))
        )
        HorizontalDivider(color = CertiTheme.colors.gray100)
        JobCategorySection(
            jobCategoryList = uiState.selectedCategoryList,
            onClick = onReselectCategoryClick,
            modifier = Modifier.padding(horizontal = screenWidthDp(20.dp), vertical = screenHeightDp(24.dp))
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun AcademicInfoPreview() {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    var showBottomSheet by remember { mutableStateOf(false) }
    val viewModel = remember { AcademicInfoViewModel() }
    val uiState by viewModel.academicUiState.collectAsStateWithLifecycle()
    val editingList by viewModel.editingCategoryList.collectAsStateWithLifecycle()

    CERTITheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(CertiTheme.colors.white)
                .statusBarsPadding()
        ) {
            UnivInfoScreen(
                uiState = uiState,
                onSchoolManageClick = {},
                onMajorManageClick = {},
                onReselectCategoryClick = {
                    viewModel.startEditing()
                    showBottomSheet = !showBottomSheet
                }
            )

            if (showBottomSheet) {
                SelectJobCategoryBottomSheet(
                    sheetState = sheetState,
                    selectedList = editingList,
                    onItemClick = viewModel::editJobCategory,
                    changeBottomSheetVisibility = { showBottomSheet = it },
                    onConfirmClick = viewModel::saveChanges
                )
            }
        }
    }
}
