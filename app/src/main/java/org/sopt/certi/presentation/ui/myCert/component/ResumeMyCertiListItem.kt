package org.sopt.certi.presentation.ui.myCert.component

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
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.presentation.ui.resume.component.ResumeCertificationSmallCard
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme
import java.time.LocalDate

@Composable
fun ResumeMyCertiListItem(
    certification: CertificationData,
    onDeleteClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ResumeCertificationSmallCard(
            certification = certification,
            onClick = { }
        )

        Spacer(modifier = Modifier.width(screenWidthDp(12.dp)))

        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_close_36),
            contentDescription = null,
            tint = CertiTheme.colors.gray300,
            modifier = Modifier
                .size(screenWidthDp(36.dp))
                .noRippleClickable {
                    onDeleteClick(certification.certificationId)
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
            certification = CertificationData(
                certificationId = 1,
                certificationName = "GTQ 1급 (그래픽기술자격)",
                createdAt = LocalDate.now(),
                cardFrontImageUrl = "https://sopt-certi-bucket.s3.ap-northeast-2.amazonaws.com/certi/color%3Dblue.png",
                tags = listOf("태그", "태그", "태그")
            ),
            onDeleteClick = { showDialog = true }
        )
    }
}
