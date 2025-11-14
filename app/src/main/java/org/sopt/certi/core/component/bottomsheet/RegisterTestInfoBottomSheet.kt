package org.sopt.certi.core.component.bottomsheet

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.util.heightForScreenPercentage
import org.sopt.certi.core.util.roundedBackgroundWithBorder
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.ui.theme.CertiTheme

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
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = screenWidthDp(20.dp))
        ){
            Spacer(Modifier.heightForScreenPercentage(35.dp))

            Text(
                text = stringResource(R.string.test_info_bottomsheet_title),
                style = CertiTheme.typography.body.bold_18,
                color = CertiTheme.colors.black
            )

            Text(
                text = certTitle,
                style = CertiTheme.typography.caption.semibold_14,
                color = CertiTheme.colors.gray400
            )

            Spacer(Modifier.heightForScreenPercentage(32.dp))

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
                    text = stringResource(R.string.test_info_bottomsheet_date_title),
                    style = CertiTheme.typography.body.semibold_16,
                    color = CertiTheme.colors.gray600
                )
            }
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