package org.sopt.certi.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.certi.data.repositoryimpl.ActivityRepositoryImpl
import org.sopt.certi.data.repositoryimpl.AcquisitionRepositoryImpl
import org.sopt.certi.data.repositoryimpl.AuthRepositoryImpl
import org.sopt.certi.data.repositoryimpl.CareerRepositoryImpl
import org.sopt.certi.data.repositoryimpl.CertRepositoryImpl
import org.sopt.certi.data.repositoryimpl.DummyRepositoryImpl
import org.sopt.certi.data.repositoryimpl.HomeRepositoryImpl
import org.sopt.certi.data.repositoryimpl.PreCertRepositoryImpl
import org.sopt.certi.data.repositoryimpl.UserRepositoryImpl
import org.sopt.certi.domain.repository.ActivityRepository
import org.sopt.certi.domain.repository.AcquisitionRepository
import org.sopt.certi.domain.repository.AuthRepository
import org.sopt.certi.domain.repository.CareerRepository
import org.sopt.certi.domain.repository.CertRepository
import org.sopt.certi.domain.repository.DummyRepository
import org.sopt.certi.domain.repository.HomeRepository
import org.sopt.certi.domain.repository.PreCertRepository
import org.sopt.certi.domain.repository.UserRepository
import javax.inject.Singleton

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
    abstract fun bindHomeRepository(homeRepositoryImpl: HomeRepositoryImpl): HomeRepository

    @Binds
    @Singleton
    abstract fun bindCertRepository(certRepositoryImpl: CertRepositoryImpl): CertRepository

    @Binds
    @Singleton
    abstract fun bindActivityRepository(activityRepositoryImpl: ActivityRepositoryImpl): ActivityRepository

    @Binds
    @Singleton
    abstract fun bindCareerRepository(careerRepositoryImpl: CareerRepositoryImpl): CareerRepository

    @Binds
    @Singleton
    abstract fun bindAcquisitionRepository(acquisitionRepositoryImpl: AcquisitionRepositoryImpl): AcquisitionRepository

    @Binds
    @Singleton
    abstract fun bindPreCertRepository(certRepositoryImpl: PreCertRepositoryImpl): PreCertRepository
}
