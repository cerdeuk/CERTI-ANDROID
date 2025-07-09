package org.sopt.certi.presentation.ui.resume.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun ResumeDateInput(
    value: String,
    onValueChange:(String) ->Unit,
    modifier: Modifier = Modifier,
    placeholder: String = stringResource(R.string.resume_textfield_date)
){
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(CertiTheme.colors.white)
            .border(
                width = 1.dp,
                color = CertiTheme.colors.gray100,
                shape = RoundedCornerShape(12.dp)
            ),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp),
                contentAlignment = Alignment.CenterStart
            ){
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = CertiTheme.typography.caption.semibold_12,
                        color = CertiTheme.colors.gray200
                    )
                }
                innerTextField()
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun ResumeDateInputPreview(){
    val text = remember { mutableStateOf("") }

    CERTITheme {
        ResumeDateInput(
            value = text.value,
            onValueChange = {text.value = it}
        )
    }
}