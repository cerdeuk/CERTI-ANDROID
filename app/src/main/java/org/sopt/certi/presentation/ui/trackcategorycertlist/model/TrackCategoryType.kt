package org.sopt.certi.presentation.ui.trackcategorycertlist.model

import androidx.annotation.StringRes
import org.sopt.certi.R
import org.sopt.certi.domain.type.CategoryType
import org.sopt.certi.domain.type.TrackType

enum class TrackCategoryType(
    @StringRes val titleResId: Int,
    val type: List<String>
) {
    TRACK(
        titleResId = R.string.track_cert_list_top_bar,
        type = TrackType.entries.map { it.description }
    ),
    CATEGORY(
        titleResId = R.string.category_cert_list_top_bar,
        type = CategoryType.entries.map { it.description }
    );

    companion object {
        fun fromRouteArg(arg: String?): TrackCategoryType =
            when (arg?.lowercase()) {
                "track" -> TRACK
                "category" -> CATEGORY
                else -> CATEGORY
            }
    }
}
