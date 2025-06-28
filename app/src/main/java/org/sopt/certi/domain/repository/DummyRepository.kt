package org.sopt.certi.domain.repository

import org.sopt.certi.domain.model.DummyData

interface DummyRepository{
    suspend fun dummy():Result<DummyData>
}