package org.sopt.certi.presentation.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.ui.theme.CertiTheme
import androidx.compose.material3.Text


@Composable
fun PreCertificationEditRoute(
    padding: PaddingValues,
    viewModel: HomeViewModel = hiltViewModel()
) {
    PreCertificationEditScreen()
}

@Composable
fun PreCertificationEditScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(top = screenHeightDp(60.dp), start = screenWidthDp(20.dp))
    ) {
        Text(
            text = "취득 예정 자격증",
            style = CertiTheme.typography.subtitle.semibold_20,
            color = CertiTheme.colors.gray600
        )
        LazyColumn(
            modifier = Modifier
                .padding(),
            contentPadding = PaddingValues(top = 36.dp),
            verticalArrangement = Arrangement.spacedBy(36.dp)
        ) {
            items()


        }

    }

}