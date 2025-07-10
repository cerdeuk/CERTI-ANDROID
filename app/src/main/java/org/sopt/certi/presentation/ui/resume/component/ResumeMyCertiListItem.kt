package org.sopt.certi.presentation.ui.resume.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.component.dialog.CertiDeleteDialog
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.domain.model.ResumeCertificationListData
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun ResumeMyCertiListItem(
    certification: ResumeCertificationListData,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ResumeCertificationSmallCard(
            certification = certification
        )

        Spacer(modifier = Modifier.width(screenWidthDp(12.dp)))

        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_close_36),
            contentDescription = null,
            tint = CertiTheme.colors.gray500,
            modifier = Modifier
                .size(screenWidthDp(36.dp))
                .noRippleClickable {
                    onDeleteClick()
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewResumeMyCertiListItem() {
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        CertiDeleteDialog(
            onConfirmClick = {
                showDialog = false
            },
            onDismissClick = {
                showDialog = false
            }
        )
    }

    CERTITheme {
        ResumeMyCertiListItem(
            certification = ResumeCertificationListData(
                name = "GTQ 1급 (그래픽기술자격)",
                year = 2025,
                month = 7,
                day = 3,
                cardImageUrl = "https://mblogthumb-phinf.pstatic.net/MjAyMDAxMTBfMTgx/MDAxNTc4NjM1MTAxNjk1.m2q2MOZR3vArhqg1nC4-i2CEaVPlcPNcbic3KyTGj-cg.BBprGk0SqCmOMngKaT1CaaR_IBTJ8t-4LrOu_Nn2prAg.JPEG.p197273/88aad6.jpg?type=w800",
                tags = listOf("태그", "태그", "태그")
            ),
            onDeleteClick = { showDialog = true }
        )
    }
}
