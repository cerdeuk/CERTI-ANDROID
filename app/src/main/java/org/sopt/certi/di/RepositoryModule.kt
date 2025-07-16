package org.sopt.certi.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.certi.data.repositoryimpl.AcquisitionRepositoryImpl
import org.sopt.certi.data.repositoryimpl.AuthRepositoryImpl
import javax.inject.Singleton
import org.sopt.certi.data.repositoryimpl.DummyRepositoryImpl
import org.sopt.certi.domain.repository.AcquisitionRepository
import org.sopt.certi.domain.repository.AuthRepository
import org.sopt.certi.domain.repository.DummyRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindDummyRepository(dummyRepositoryImpl: DummyRepositoryImpl): DummyRepository

    @Binds
    @Singleton
    abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun bindAcquisitionRepository(acquisitionRepositoryImpl: AcquisitionRepositoryImpl): AcquisitionRepository
}
