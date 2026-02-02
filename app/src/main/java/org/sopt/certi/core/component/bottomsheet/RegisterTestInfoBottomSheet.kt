package org.sopt.certi.core.component.bottomsheet

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import kotlinx.coroutines.launch
import org.sopt.certi.R
import org.sopt.certi.core.component.button.CertiBasicButton
import org.sopt.certi.core.component.calendar.DatePickerCalendar
import org.sopt.certi.core.component.timepicker.CustomTimePicker
import org.sopt.certi.core.util.dateString
import org.sopt.certi.core.util.dropShadow
import org.sopt.certi.core.util.heightForScreenPercentage
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.region.RegionJsonParser
import org.sopt.certi.core.util.roundedBackgroundWithBorder
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.toLocalDateOrNull
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.ui.theme.CertiTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterTestInfoBottomSheet(
    sheetState: SheetState,
    forModify: Boolean,
    certTitle: String,
    onConfirm: (city: String, state: String, timeDate: String) -> Unit,
    onConfirmWithNoData: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    certificationData: CertificationData? = null
) {
    val density = LocalDensity.current
    val scope = rememberCoroutineScope()

    val context = LocalContext.current
    val cityList = remember { RegionJsonParser.getCities(context) }

    // Data
    var dateText by remember { mutableStateOf("") }
    var cityText by remember { mutableStateOf("") }
    var districtText by remember { mutableStateOf("") }
    var timeData by remember { mutableStateOf(Pair(-1, -1)) }

    // District list based on selected city
    var districtList by remember { mutableStateOf<List<String>>(emptyList()) }

    // Update district list when city is selected
    LaunchedEffect(cityText) {
        districtList = if (cityText.isNotEmpty()) {
            RegionJsonParser.getDistricts(context, cityText)
        } else {
            emptyList()
        }
        // Reset district selection when city changes
        if (districtText.isNotEmpty() && cityText.isNotEmpty()) {
            val isValidDistrict = districtList.contains(districtText)
            if (!isValidDistrict) {
                districtText = ""
            }
        }
    }

    // Date
    var showCalendar by remember { mutableStateOf(false) }

    // Place
    var showPlaceP1List by remember { mutableStateOf(false) }
    var showPlaceP2List by remember { mutableStateOf(false) }

    var placeItemWidth by remember { mutableStateOf(0.dp) }

    var buttonEnable by remember { mutableStateOf(false) }

    LaunchedEffect(dateText, cityText, districtText, timeData) {
        buttonEnable = dateText.isNotEmpty() &&
            cityText.isNotEmpty() &&
            districtText.isNotEmpty() &&
            timeData.first != -1 &&
            timeData.second != -1
    }

    LaunchedEffect(forModify) {
        if (forModify && certificationData != null) {
            // TODO data 형식에 맞게 여기서 삽입

            dateText = certificationData.testDate
            cityText = certificationData.city
            districtText = certificationData.state
//            timeData = certificationData.testTime
        }
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
        containerColor = CertiTheme.colors.white,
        sheetState = sheetState,
        modifier = modifier
            .wrapContentHeight(),
        dragHandle = {
            Box(
                modifier = Modifier
                    .padding(top = screenHeightDp(20.dp))
                    .widthForScreenPercentage(80.dp)
                    .heightForScreenPercentage(5.dp)
                    .roundedBackgroundWithBorder(12.dp, CertiTheme.colors.gray200)
            )
        }
    ) {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .pointerInput(Unit) {
                    detectVerticalDragGestures(
                        onDragEnd = {},
                        onDragCancel = {},
                        onVerticalDrag = { _, _ -> /* 아무것도 안 함 */ }
                    )
                }
                .fillMaxWidth()
        ) {
            Spacer(Modifier.heightForScreenPercentage(35.dp))

            Text(
                text = stringResource(R.string.test_info_bottomsheet_title),
                style = CertiTheme.typography.body.bold_18,
                color = CertiTheme.colors.black,
                modifier = Modifier.padding(horizontal = screenWidthDp(20.dp))
            )

            Text(
                text = certTitle,
                style = CertiTheme.typography.caption.semibold_14,
                color = CertiTheme.colors.gray400,
                modifier = Modifier.padding(horizontal = screenWidthDp(20.dp))
            )

            Spacer(Modifier.heightForScreenPercentage(32.dp))

            // Date Title
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = screenWidthDp(20.dp))
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_check_24),
                    contentDescription = null,
                    tint = CertiTheme.colors.gray500
                )

                Spacer(Modifier.widthForScreenPercentage(4.dp))

                Text(
                    text = stringResource(R.string.test_info_bottomsheet_date_title),
                    style = CertiTheme.typography.body.semibold_16,
                    color = CertiTheme.colors.gray600
                )
            }

            Spacer(Modifier.heightForScreenPercentage(12.dp))

            // Date Box
            Row(
                modifier = Modifier
                    .padding(horizontal = screenWidthDp(20.dp))
                    .fillMaxWidth()
                    .heightForScreenPercentage(40.dp)
                    .border(width = 1.dp, color = CertiTheme.colors.gray200, shape = RoundedCornerShape(4.dp))
                    .clip(RoundedCornerShape(4.dp))
                    .padding(horizontal = screenWidthDp(12.dp))
                    .noRippleClickable {
                        showCalendar = true
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = dateText.ifEmpty { stringResource(R.string.test_info_bottomsheet_date_hint) },
                    style = CertiTheme.typography.caption.semibold_12,
                    color = if (dateText.isEmpty()) CertiTheme.colors.gray300 else CertiTheme.colors.black
                )

                Spacer(Modifier.weight(1f))

                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_arrowdown_24),
                    tint = CertiTheme.colors.gray400,
                    contentDescription = null
                )
            }

            Spacer(Modifier.heightForScreenPercentage(6.dp))

            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                if (showCalendar) {
                    // Calendar
                    DatePickerCalendar(
                        selectedDate = dateText.toLocalDateOrNull(),
                        onDateSelected = { date ->
                            dateText = date.toString().replace("-", ".")
                            showCalendar = false
                        },
                        modifier = Modifier
                            .zIndex(1f)
                            .padding(horizontal = screenWidthDp(8.dp))
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = screenWidthDp(20.dp))
                ) {
                    Spacer(Modifier.heightForScreenPercentage(18.dp))

                    // Place Title
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_check_24),
                            contentDescription = null,
                            tint = CertiTheme.colors.black
                        )

                        Spacer(Modifier.widthForScreenPercentage(4.dp))

                        Text(
                            text = stringResource(R.string.test_info_bottomsheet_place_title),
                            style = CertiTheme.typography.body.semibold_16,
                            color = CertiTheme.colors.gray600
                        )
                    }

                    Spacer(Modifier.heightForScreenPercentage(12.dp))

                    // Place Box
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        // Place P1
                        Row(
                            modifier = Modifier
                                .weight(1f)
                                .heightForScreenPercentage(40.dp)
                                .border(width = 1.dp, color = CertiTheme.colors.gray200, shape = RoundedCornerShape(4.dp))
                                .clip(RoundedCornerShape(4.dp))
                                .onGloballyPositioned { coordinates ->
                                    placeItemWidth = with(density) {
                                        coordinates.size.width.toDp()
                                    }
                                }
                                .padding(horizontal = screenWidthDp(12.dp))
                                .noRippleClickable {
                                    showPlaceP1List = true
                                },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = cityText.ifEmpty { stringResource(R.string.test_info_bottomsheet_place_p1_hint) },
                                style = CertiTheme.typography.caption.semibold_12,
                                color = if (cityText.isEmpty()) CertiTheme.colors.gray300 else CertiTheme.colors.black
                            )

                            Spacer(Modifier.weight(1f))

                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.ic_arrowdown_24),
                                tint = CertiTheme.colors.gray400,
                                contentDescription = null
                            )
                        }

                        Spacer(Modifier.widthForScreenPercentage(12.dp))

                        // Place P2
                        Row(
                            modifier = Modifier
                                .weight(1f)
                                .heightForScreenPercentage(40.dp)
                                .border(width = 1.dp, color = CertiTheme.colors.gray200, shape = RoundedCornerShape(4.dp))
                                .clip(RoundedCornerShape(4.dp))
                                .padding(horizontal = screenWidthDp(12.dp))
                                .noRippleClickable {
                                    if (cityText.isNotEmpty()) {
                                        showPlaceP2List = true
                                    }
                                },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = districtText.ifEmpty { stringResource(R.string.test_info_bottomsheet_place_p2_hint) },
                                style = CertiTheme.typography.caption.semibold_12,
                                color = if (districtText.isEmpty()) CertiTheme.colors.gray300 else CertiTheme.colors.black
                            )

                            Spacer(Modifier.weight(1f))

                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.ic_arrowdown_24),
                                tint = CertiTheme.colors.gray400,
                                contentDescription = null
                            )
                        }
                    }

                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.zIndex(1f)
                        ) {
                            if (showPlaceP1List) {
                                // Place P1 List
                                LazyColumn(
                                    modifier = Modifier
                                        .widthForScreenPercentage(placeItemWidth - screenWidthDp(12.dp))
                                        .heightIn(max = screenWidthDp(34.dp * 6))
                                        .dropShadow(
                                            color = Color.Black.copy(alpha = 0.05f),
                                            shape = RoundedCornerShape(4.dp),
                                            blur = 20.dp
                                        )
                                        .background(CertiTheme.colors.white)
                                ) {
                                    itemsIndexed(cityList) { index, placeName ->
                                        PlaceItem(
                                            placeName = placeName,
                                            index = index,
                                            listSize = cityList.size
                                        ) {
                                            cityText = placeName
                                            showPlaceP1List = false
                                        }
                                    }
                                }
                            }

                            if (showPlaceP2List) {
                                // Place P2 List
                                Spacer(Modifier.widthForScreenPercentage(placeItemWidth))

                                LazyColumn(
                                    modifier = Modifier
                                        .widthForScreenPercentage(placeItemWidth - screenWidthDp(12.dp))
                                        .heightIn(max = screenWidthDp(34.dp * 6))
                                        .dropShadow(
                                            color = Color.Black.copy(alpha = 0.05f),
                                            shape = RoundedCornerShape(4.dp),
                                            blur = 20.dp
                                        )
                                        .background(CertiTheme.colors.white)
                                ) {
                                    itemsIndexed(districtList) { index, placeName ->
                                        PlaceItem(
                                            placeName = placeName,
                                            index = index,
                                            listSize = districtList.size
                                        ) {
                                            districtText = placeName
                                            showPlaceP2List = false
                                        }
                                    }
                                }
                            }
                        }

                        Column {
                            Spacer(Modifier.heightForScreenPercentage(24.dp))

                            // Time Title
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.ic_check_24),
                                    contentDescription = null,
                                    tint = CertiTheme.colors.black
                                )

                                Spacer(Modifier.widthForScreenPercentage(4.dp))

                                Text(
                                    text = stringResource(R.string.test_info_bottomsheet_time_title),
                                    style = CertiTheme.typography.body.semibold_16,
                                    color = CertiTheme.colors.gray600
                                )
                            }

                            Spacer(Modifier.heightForScreenPercentage(12.dp))

                            CustomTimePicker { hour, minute ->
                                Log.d("Logd", "Selected time: $hour:$minute")
                                timeData = Pair(hour, minute)
                            }

                            if (forModify) {
                                Spacer(modifier = Modifier.height(screenHeightDp(86.dp)))
                            } else {
                                Spacer(modifier = Modifier.heightIn(min = screenHeightDp(48.dp)))

                                Text(
                                    text = stringResource(R.string.test_info_bottomsheet_confirm_later),
                                    style = CertiTheme.typography.caption.semibold_12,
                                    color = CertiTheme.colors.gray300,
                                    modifier = Modifier
                                        .align(Alignment.CenterHorizontally)
                                        .noRippleClickable {
                                            onConfirmWithNoData()
                                        }
                                )

                                Spacer(Modifier.heightForScreenPercentage(4.dp))

                                HorizontalDivider(
                                    thickness = 1.dp,
                                    color = CertiTheme.colors.gray200,
                                    modifier = Modifier
                                        .widthForScreenPercentage(100.dp)
                                        .align(Alignment.CenterHorizontally)
                                )

                                Spacer(Modifier.heightForScreenPercentage(12.dp))
                            }

                            CertiBasicButton(
                                buttonText = stringResource(R.string.test_info_bottomsheet_confirm),
                                enabled = buttonEnable,
                                onClick = {
                                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                                        if (!sheetState.isVisible) {
                                            onConfirm(
                                                cityText,
                                                districtText,
                                                "$dateText ${timeData.first.dateString()}:${timeData.second.dateString()}:00"
                                            )
                                        }
                                    }
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .heightForScreenPercentage(56.dp)
                            )

                            Spacer(Modifier.heightForScreenPercentage(24.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun PlaceItem(
    placeName: String,
    index: Int,
    listSize: Int,
    itemOnClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .heightForScreenPercentage(34.dp)
            .background(CertiTheme.colors.white)
            .noRippleClickable {
                itemOnClick(placeName)
            },
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(Modifier.weight(1f))

        Text(
            text = placeName,
            style = CertiTheme.typography.caption.semibold_12,
            color = CertiTheme.colors.gray600,
            modifier = Modifier.padding(start = screenWidthDp(12.dp))
        )

        Spacer(Modifier.weight(1f))

        if (index != listSize) {
            HorizontalDivider(thickness = 1.dp, color = CertiTheme.colors.gray100)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun RegisterTestInfoBottomSheetPreview() {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    RegisterTestInfoBottomSheet(
        sheetState = sheetState,
        certTitle = "자격증 이름",
        forModify = false,
        certificationData = null,
        onDismiss = {},
        onConfirm = { _, _, _ -> },
        onConfirmWithNoData = {}
    )
}
