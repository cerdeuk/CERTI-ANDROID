package org.sopt.certi.presentation.ui.mypage.component

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.core.component.chip.CertiChipList
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun MyPageProfile(
    name: String,
    email: String,
    jobList: List<String>,
    profileImageUri: Uri?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(CertiTheme.colors.white)
            .padding(top = screenHeightDp(52.dp), bottom = screenHeightDp(20.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MyPageProfileImage(
            imageUri = profileImageUri,
            modifier = Modifier.size(screenWidthDp(80.dp))
        )
        Text(
            text = name,
            style = CertiTheme.typography.subtitle.bold_20,
            color = CertiTheme.colors.mainBlue,
            modifier = Modifier.padding(top = screenHeightDp(16.dp))
        )
        Text(
            text = email,
            style = CertiTheme.typography.caption.regular_14,
            color = CertiTheme.colors.gray500,
            modifier = Modifier.padding(top = screenHeightDp(4.dp))
        )
        CertiChipList(
            categories = jobList,
            modifier = Modifier.padding(top = screenHeightDp(16.dp)),
            spacing = screenWidthDp(8.dp),
            backgroundColor = CertiTheme.colors.purpleWhite
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MyPageProfilePreview() {
    CERTITheme {
        MyPageProfile(
            name = "김서티",
            email = "certification@gmail.com",
            jobList = listOf("경영/사무", "무역/유통", "마케팅/광고/홍보"),
            profileImageUri = null
        )
    }
}
