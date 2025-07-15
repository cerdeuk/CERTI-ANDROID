package org.sopt.certi.data.remote.dto.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class GetInterestJobListResponseDto (
    @SerializedName("jobList")
    val jobList: List<String>
)