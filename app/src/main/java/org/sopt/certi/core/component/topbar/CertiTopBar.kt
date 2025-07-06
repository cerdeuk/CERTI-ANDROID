package org.sopt.certi.core.component.topbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.ui.theme.CERTITheme

@Composable
fun CertiTopBar(){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.img_logo_black),
            contentDescription = null,
            modifier = Modifier.width(75.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CertiTopBarPreview(){
    CERTITheme {
        CertiTopBar()
    }
}