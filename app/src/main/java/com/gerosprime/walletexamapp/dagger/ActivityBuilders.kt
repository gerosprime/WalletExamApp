package com.gerosprime.walletexamapp.dagger

import com.gerosprime.walletexamapp.presentation.WalletMainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilders {

    @ContributesAndroidInjector
    abstract fun contributeWalletMainActivity() : WalletMainActivity

}