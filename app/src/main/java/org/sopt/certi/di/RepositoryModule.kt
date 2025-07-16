package org.sopt.certi.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.certi.data.repositoryimpl.AuthRepositoryImpl
import org.sopt.certi.data.repositoryimpl.CertRepositoryImpl
import javax.inject.Singleton
import org.sopt.certi.data.repositoryimpl.DummyRepositoryImpl
import org.sopt.certi.data.repositoryimpl.UserRepositoryImpl
import org.sopt.certi.domain.repository.AuthRepository
import org.sopt.certi.domain.repository.CertRepository
import org.sopt.certi.domain.repository.DummyRepository
import org.sopt.certi.domain.repository.UserRepository

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
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    abstract fun bindCertRepository(certRepositoryImpl: CertRepositoryImpl): CertRepository
}
