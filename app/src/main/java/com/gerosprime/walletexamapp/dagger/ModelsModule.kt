package com.gerosprime.walletexamapp.dagger

import dagger.Module

@Module(includes = [RepositoryModule::class, HttpModule::class])
interface ModelsModule