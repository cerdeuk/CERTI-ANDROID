package org.sopt.certi.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.certi.data.remote.service.AcquisitionService
import org.sopt.certi.data.remote.service.AuthService
import javax.inject.Singleton
import org.sopt.certi.data.remote.service.DummyService
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun providesDummyService(retrofit: Retrofit): DummyService =
        retrofit.create(DummyService::class.java)

    @Provides
    @Singleton
    fun providesAuthService(retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    @Provides
    @Singleton
    fun providesAcquisitionService(retrofit: Retrofit): AcquisitionService =
        retrofit.create(AcquisitionService::class.java)
}
