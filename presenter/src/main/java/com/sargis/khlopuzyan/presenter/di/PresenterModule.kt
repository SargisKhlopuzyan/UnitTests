package com.sargis.khlopuzyan.presenter.di

import com.sargis.khlopuzyan.presenter.ui.auth.login.LoginViewModel
import com.sargis.khlopuzyan.presenter.ui.auth.register.RegisterViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

private val viewModelModule = module {
    viewModel {
        LoginViewModel(get(), get())
    }
    viewModel {
        RegisterViewModel(get(), get(), get())
    }
}

val presenterModule = listOf(viewModelModule)