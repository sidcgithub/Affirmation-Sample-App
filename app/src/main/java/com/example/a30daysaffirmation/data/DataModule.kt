package com.example.a30daysaffirmation.data

import com.example.a30daysaffirmation.network.AffirmationService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Provides
    @Singleton
    fun provideRepo(service: AffirmationService): AffirmationRepository = AffirmationRepositoryImpl(service)
}