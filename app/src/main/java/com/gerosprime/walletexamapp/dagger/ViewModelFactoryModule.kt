package com.gerosprime.walletexamapp.dagger

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface ViewModelFactoryModule {
    @Binds
    @Singleton
    fun bindViewModelFactory(factory: DefaultViewModelFactory):
            ViewModelProvider.Factory
}