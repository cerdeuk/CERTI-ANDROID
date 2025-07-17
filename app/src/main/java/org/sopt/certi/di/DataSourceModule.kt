package org.sopt.certi.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.certi.data.remote.datasource.AcquisitionRemoteDataSource
import org.sopt.certi.data.remote.datasource.ActivityRemoteDataSource
import org.sopt.certi.data.remote.datasource.AuthRemoteDataSource
import org.sopt.certi.data.remote.datasource.CareerRemoteDataSource
import org.sopt.certi.data.remote.datasource.CertRemoteDataSource
import javax.inject.Singleton
import org.sopt.certi.data.remote.datasource.DummyRemoteDataSource
import org.sopt.certi.data.remote.datasource.HomeRemoteDataSource
import org.sopt.certi.data.remote.datasource.PreCertEditRemoteDataSource
import org.sopt.certi.data.remote.datasource.PreCertRemoteDataSource
import org.sopt.certi.data.remote.datasourceimpl.AcquisitionRemoteDataSourceImpl
import org.sopt.certi.data.remote.datasource.UserRemoteDataSource
import org.sopt.certi.data.remote.datasourceimpl.ActivityRemoteDataSourceImpl
import org.sopt.certi.data.remote.datasourceimpl.AuthRemoteDataSourceImpl
import org.sopt.certi.data.remote.datasourceimpl.CareerRemoteDataSourceImpl
import org.sopt.certi.data.remote.datasourceimpl.CertRemoteDataSourceImpl
import org.sopt.certi.data.remote.datasourceimpl.DummyRemoteDataSourceImpl
import org.sopt.certi.data.remote.datasourceimpl.HomeRemoteDataSourceImpl
import org.sopt.certi.data.remote.datasourceimpl.PreCertEditRemoteDataSourceImpl
import org.sopt.certi.data.remote.datasourceimpl.PreCertRemoteDataSourceImpl
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
    abstract fun bindsHomeDataSource(homeRemoteDataSourceImpl: HomeRemoteDataSourceImpl): HomeRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindsActivityDataSource(activityRemoteDataSourceImpl: ActivityRemoteDataSourceImpl): ActivityRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindsCareerDataSource(careerRemoteDataSourceImpl: CareerRemoteDataSourceImpl): CareerRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindsAcquisitionDataSource(acquisitionRemoteDataSourceImpl: AcquisitionRemoteDataSourceImpl): AcquisitionRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindsPreCertDataSource(preCertRemoteDataSourceImpl: PreCertRemoteDataSourceImpl): PreCertRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindsPreCertEditDataSource(preCertEditRemoteDataSourceImpl: PreCertEditRemoteDataSourceImpl): PreCertEditRemoteDataSource
}
