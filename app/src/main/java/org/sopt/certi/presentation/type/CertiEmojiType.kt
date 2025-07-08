package org.sopt.certi.presentation.type

import androidx.annotation.DrawableRes
import org.sopt.certi.R

enum class CertiEmojiType(@DrawableRes val resId: Int) {
    EMOJI_1(resId = R.drawable.ic_certi_emogi_50_1),
    EMOJI_2(resId = R.drawable.ic_certi_emoji_50_2),
    EMOJI_3(resId = R.drawable.ic_certi_emoji_50_3);

    companion object {
        fun fromId(certificationId: Int): CertiEmojiType {
            return values()[certificationId % values().size]
        }
    }
}
