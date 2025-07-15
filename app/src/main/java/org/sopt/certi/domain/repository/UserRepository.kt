package org.sopt.certi.domain.repository

import org.sopt.certi.domain.model.InterestedJobListData


interface UserRepository {
    suspend fun getInterestedJobList() : Result<InterestedJobListData>
}