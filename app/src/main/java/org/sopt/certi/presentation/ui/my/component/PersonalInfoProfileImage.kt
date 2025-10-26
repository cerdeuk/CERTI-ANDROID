package org.sopt.certi.presentation.ui.my.component

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.sopt.certi.R
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun PersonalInfoProfileImage(
    selectedImageUri: Uri?,
    onImageUriChange: (Uri?) -> Unit,
    modifier: Modifier = Modifier
) {
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri: Uri? ->
            onImageUriChange(uri)
        }
    )

    if (selectedImageUri == null) {
        Box(
            modifier = modifier
        ) {
            MyPageEmptyProfileImage(
                modifier = Modifier
                    .padding(screenWidthDp(2.dp))
                    .size(screenWidthDp(100.dp))
            )

            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_pencil_24),
                contentDescription = null,
                tint = CertiTheme.colors.white,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(CertiTheme.colors.mainBlue)
                    .noRippleClickable {
                        imagePickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    }
                    .padding(screenWidthDp(4.dp))
                    .align(Alignment.BottomEnd)
            )
        }
    } else {
        AsyncImage(
            model = selectedImageUri,
            contentDescription = null,
            modifier = Modifier
                .padding(screenWidthDp(2.dp))
                .size(screenWidthDp(100.dp))
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PersonalInfoProfileImagePreview() {
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    CERTITheme {
        PersonalInfoProfileImage(
            selectedImageUri = selectedImageUri,
            onImageUriChange = { selectedImageUri = it }
        )
    }
}
