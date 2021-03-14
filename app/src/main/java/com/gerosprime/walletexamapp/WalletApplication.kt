package com.gerosprime.walletexamapp

import android.app.Application
import com.gerosprime.walletexamapp.dagger.ApplicationComponent
import com.gerosprime.walletexamapp.dagger.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class WalletApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var injector : DispatchingAndroidInjector<Any>

    lateinit var component : ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerApplicationComponent.builder().application(this)
            .build()
        component.inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = injector

}