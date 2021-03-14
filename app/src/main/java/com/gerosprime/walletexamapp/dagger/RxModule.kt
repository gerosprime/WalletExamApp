package com.gerosprime.walletexamapp.dagger

import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.Executors
import javax.inject.Named
import javax.inject.Singleton

@Module
class RxModule {

    @Provides
    @Named("ioScheduler")
    @Singleton
    fun provideIOScheduler() : Scheduler = Schedulers.io()

    @Provides
    @Named("uiScheduler")
    fun provideUIScheduler() : Scheduler = AndroidSchedulers.mainThread()


}