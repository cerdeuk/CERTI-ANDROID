package org.sopt.certi.core.component.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import org.sopt.certi.R
import org.sopt.certi.core.component.calendar.DatePickerCalendar
import org.sopt.certi.core.util.dropShadow
import org.sopt.certi.core.util.heightForScreenPercentage
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.roundedBackgroundWithBorder
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.toLocalDateOrMin
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.ui.theme.CertiTheme
import java.time.YearMonth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterTestInfoBottomSheet(
    sheetState: SheetState,
    forModify: Boolean,
    certTitle: String,
    certificationData: CertificationData? = null,
    onDismissClick: () -> Unit = {},
    changeBottomSheetVisibility: (Boolean) -> Unit = {}
) {
    val density = LocalDensity.current

    var showCalendar by remember { mutableStateOf(false) }
    var dateText by remember { mutableStateOf("") }
    var currentMonth by remember { mutableStateOf(YearMonth.now()) }

    var showPlaceP1List by remember { mutableStateOf(false) }
    var showPlaceP2List by remember { mutableStateOf(false) }
    var placeTextP1 by remember { mutableStateOf("") }
    var placeTextP2 by remember { mutableStateOf("") }

    //FIXME Sample 서버데이터
    val place1List = listOf("서울", "경기", "부산", "인천", "충남", "충북", "강원", "경북")
    val place2List = listOf("서울", "경기", "부산", "인천", "충남", "충북", "강원", "경북")

    var placeItemWidth by remember { mutableStateOf(0.dp) }



    ModalBottomSheet(
        onDismissRequest = {
            changeBottomSheetVisibility(false)
            onDismissClick()
        },
        shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
        containerColor = CertiTheme.colors.white,
        sheetState = sheetState,
        dragHandle = {
            Box(
                modifier = Modifier
                    .padding(top = screenHeightDp(24.dp))
                    .widthForScreenPercentage(80.dp)
                    .heightForScreenPercentage(5.dp)
                    .roundedBackgroundWithBorder(12.dp, CertiTheme.colors.gray200)
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ){
            Spacer(Modifier.heightForScreenPercentage(35.dp))

            Text(
                text = stringResource(R.string.test_info_bottomsheet_title),
                style = CertiTheme.typography.body.bold_18,
                color = CertiTheme.colors.black,
                modifier = Modifier.padding(horizontal = 20.dp)
            )

            Text(
                text = certTitle,
                style = CertiTheme.typography.caption.semibold_14,
                color = CertiTheme.colors.gray400,
                modifier = Modifier.padding(horizontal = 20.dp)
            )

            Spacer(Modifier.heightForScreenPercentage(32.dp))

            // Date Title
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_check_24),
                    contentDescription = null,
                    tint = CertiTheme.colors.black
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
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
                    .heightForScreenPercentage(40.dp)
                    .border(width = 1.dp, color = CertiTheme.colors.gray200, shape = RoundedCornerShape(4.dp))
                    .clip(RoundedCornerShape(4.dp))
                    .padding(horizontal = 12.dp)
                    .noRippleClickable {
                        showCalendar = true
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = dateText.ifEmpty { stringResource(R.string.test_info_bottomsheet_date_hint) },
                    style = CertiTheme.typography.caption.semibold_12,
                    color = if(dateText.isEmpty()) CertiTheme.colors.gray300 else CertiTheme.colors.black
                )

                Spacer(Modifier.weight(1f))

                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_arrowdown_24),
                    tint = CertiTheme.colors.gray400,
                    contentDescription = null,
                )
            }

            Spacer(Modifier.heightForScreenPercentage(6.dp))

            Box(
                modifier = Modifier.fillMaxSize()
            ) {

                if(showCalendar) {
                    // Calendar
                    DatePickerCalendar(
                        selectedDate = dateText.toLocalDateOrMin(),
                        currentMonth = currentMonth,
                        onDateSelected = { date ->
                            dateText = date.toString().replace("-", ".")
                            showCalendar = false
                        },
                        onMonthChanged = {},
                        modifier = Modifier
                            .zIndex(1f)
                            .padding(horizontal = 6.dp)
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp)
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
                                text = placeTextP1.ifEmpty { stringResource(R.string.test_info_bottomsheet_place_p1_hint) },
                                style = CertiTheme.typography.caption.semibold_12,
                                color = if(placeTextP1.isEmpty()) CertiTheme.colors.gray300 else CertiTheme.colors.black
                            )

                            Spacer(Modifier.weight(1f))

                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.ic_arrowdown_24),
                                tint = CertiTheme.colors.gray400,
                                contentDescription = null,
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
                                    if(placeTextP1.isNotEmpty()) {
                                        showPlaceP2List = true
                                    }
                                },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = placeTextP2.ifEmpty { stringResource(R.string.test_info_bottomsheet_place_p2_hint) },
                                style = CertiTheme.typography.caption.semibold_12,
                                color = if(placeTextP2.isEmpty()) CertiTheme.colors.gray300 else CertiTheme.colors.black
                            )

                            Spacer(Modifier.weight(1f))

                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.ic_arrowdown_24),
                                tint = CertiTheme.colors.gray400,
                                contentDescription = null,
                            )
                        }
                    }

                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Row {
                            if(showPlaceP1List) {
                                // Place P1 List
                                LazyColumn (
                                    modifier = Modifier
                                        .widthForScreenPercentage(placeItemWidth  - screenWidthDp(12.dp))
                                        .heightIn(max = screenWidthDp(34.dp * 6))
                                        .dropShadow(
                                            color = Color.Black.copy(alpha = 0.05f),
                                            shape = RoundedCornerShape(4.dp),
                                            blur = 20.dp
                                        )
                                        .background(CertiTheme.colors.white)
                                ) {
                                    itemsIndexed(place1List) { index, placeName ->
                                        PlaceItem(
                                            placeName = placeName,
                                            index = index,
                                            listSize = place1List.size,
                                        ) {
                                            placeTextP1 = placeName
                                            showPlaceP1List = false
                                        }
                                    }
                                }
                            }

                            if(showPlaceP2List) {
                                // Place P2 List
                                Spacer(Modifier.widthForScreenPercentage(placeItemWidth))

                                LazyColumn (
                                    modifier = Modifier
                                        .widthForScreenPercentage(placeItemWidth  - screenWidthDp(12.dp))
                                        .heightIn(max = screenWidthDp(34.dp * 6))
                                        .dropShadow(
                                            color = Color.Black.copy(alpha = 0.05f),
                                            shape = RoundedCornerShape(4.dp),
                                            blur = 20.dp
                                        )
                                        .background(CertiTheme.colors.white)
                                ) {
                                    itemsIndexed(place2List) { index, placeName ->
                                        PlaceItem(
                                            placeName = placeName,
                                            index = index,
                                            listSize = place2List.size,
                                        ) {
                                            placeTextP2 = placeName
                                            showPlaceP2List = false
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
                            }
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
            .fillMaxSize()
            .heightForScreenPercentage(34.dp)
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

        if(index != listSize) {
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
        certificationData = null
    )
}