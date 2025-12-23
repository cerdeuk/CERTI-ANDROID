package org.sopt.certi.presentation.type

import androidx.annotation.StringRes
import org.sopt.certi.R

enum class MyCertType(
    @StringRes val label: Int
) {
    PLANNED(R.string.cert_detail_acquire_expected_button_text),
    ACQUIRED(R.string.cert_detail_acquired_button_text),
    FAVORITE(R.string.cert_list_favorite_btn)
}
