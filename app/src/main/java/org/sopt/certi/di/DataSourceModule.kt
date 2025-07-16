package org.sopt.certi.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.certi.data.remote.datasource.AcquisitionRemoteDataSource
import org.sopt.certi.data.remote.datasource.AuthRemoteDataSource
import org.sopt.certi.data.remote.datasource.CareerRemoteDataSource
import org.sopt.certi.data.remote.datasource.CertRemoteDataSource
import javax.inject.Singleton
import org.sopt.certi.data.remote.datasource.DummyRemoteDataSource
import org.sopt.certi.data.remote.datasourceimpl.AcquisitionRemoteDataSourceImpl
import org.sopt.certi.data.remote.datasource.UserRemoteDataSource
import org.sopt.certi.data.remote.datasourceimpl.AuthRemoteDataSourceImpl
import org.sopt.certi.data.remote.datasourceimpl.CareerRemoteDataSourceImpl
import org.sopt.certi.data.remote.datasourceimpl.CertRemoteDataSourceImpl
import org.sopt.certi.data.remote.datasourceimpl.DummyRemoteDataSourceImpl
import org.sopt.certi.data.remote.datasourceimpl.UserRemoteDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindsDummyDataSource(dummyRemoteDataSourceImpl: DummyRemoteDataSourceImpl): DummyRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindsAuthDataSource(authRemoteDataSourceImpl: AuthRemoteDataSourceImpl): AuthRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindsUserDataSource(userRemoteDataSourceImpl: UserRemoteDataSourceImpl): UserRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindsCertDataSource(certRemoteDataSourceImpl: CertRemoteDataSourceImpl): CertRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindsAcquisitionDataSource(acquisitionDataSourceImpl: AcquisitionRemoteDataSourceImpl): AcquisitionRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindsCareerDataSource(careerDataSourceImpl: CareerRemoteDataSourceImpl): CareerRemoteDataSource
}
