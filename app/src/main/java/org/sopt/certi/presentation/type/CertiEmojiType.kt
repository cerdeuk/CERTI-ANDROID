package org.sopt.certi.presentation.type

import androidx.annotation.DrawableRes
import org.sopt.certi.R

enum class CertiEmojiType(@DrawableRes val resId: Int) {
    EMOJI_1(resId = R.drawable.ic_certi_emogi_50_1),
    EMOJI_2(resId = R.drawable.ic_certi_emoji_50_2),
    EMOJI_3(resId = R.drawable.ic_certi_emoji_50_3);

    companion object {
        fun fromIndex(index: Int): CertiEmojiType {
            return when (index) {
                0 -> EMOJI_1
                1 -> EMOJI_2
                2 -> EMOJI_3
                else -> EMOJI_1
            }
        }
    }
}
