package com.sargis.khlopuzyan.presenter.di

import com.sargis.khlopuzyan.presenter.ui.auth.login.LoginViewModel
import com.sargis.khlopuzyan.presenter.ui.auth.register.RegisterViewModel
import com.sargis.khlopuzyan.presenter.ui.main.MainViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

private val viewModelModule = module {
    viewModel {
        LoginViewModel(get(), get())
    }
    viewModel {
        RegisterViewModel(get())
    }
    viewModel {
        MainViewModel()
    }
}

val presenterModule = listOf(viewModelModule)