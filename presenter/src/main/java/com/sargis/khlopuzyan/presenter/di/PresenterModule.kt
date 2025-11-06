package com.sargis.khlopuzyan.presenter.di

import com.sargis.khlopuzyan.presenter.ui.auth.login.LoginViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

private val viewModelModule = module {
    viewModel {
        LoginViewModel(get(), get())
    }
}

val presenterModule = listOf(viewModelModule)