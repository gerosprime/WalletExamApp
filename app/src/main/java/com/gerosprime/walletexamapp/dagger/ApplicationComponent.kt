package com.gerosprime.walletexamapp.dagger

import android.app.Application
import com.gerosprime.walletexamapp.WalletApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelFactoryModule::class,
    ActivityBuilders::class,
    ViewModelModule::class, ModelsModule::class, RxModule::class, UseCaseModule::class,
    AndroidInjectionModule::class, AndroidSupportInjectionModule::class])
interface ApplicationComponent {

    fun inject(application: WalletApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application) : Builder

        fun build(): ApplicationComponent
    }

}