package com.muedsa.bltv

import com.muedsa.bltv.repository.VideosRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
internal object ViewModelModule {
    @Provides
    @ViewModelScoped
    fun provideRepo(foo: Foo) = VideosRepo(foo)
}