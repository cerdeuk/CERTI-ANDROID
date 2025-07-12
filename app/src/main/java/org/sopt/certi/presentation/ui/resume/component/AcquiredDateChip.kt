package org.sopt.certi.presentation.ui.resume.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.util.roundedBackgroundWithBorder
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.domain.model.CertificationData
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme
import java.time.LocalDate

@Composable
fun AcquiredDateChip(
    certificationData: CertificationData,
    modifier: Modifier = Modifier

) {
    Box(
        modifier = modifier
            .roundedBackgroundWithBorder(
                cornerRadius = 12.dp,
                backgroundColor = CertiTheme.colors.white
            )
            .padding(horizontal = screenWidthDp(12.dp), vertical = screenHeightDp(6.dp))
    ) {
        Text(
            text = stringResource(
                R.string.resume_acquired_date,
                certificationData.createdAt.year,
                certificationData.createdAt.month,
                certificationData.createdAt.dayOfWeek
            ),
            style = CertiTheme.typography.caption.semibold_14,
            color = CertiTheme.colors.mainBlue
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AcquiredDateChipPreview() {
    CERTITheme {
        AcquiredDateChip(
            certificationData = CertificationData(
                certificationId = 1,
                certificationName = "GTQ 1급",
                createdAt = LocalDate.of(2025, 5, 12)
            )
        )
    }
}
