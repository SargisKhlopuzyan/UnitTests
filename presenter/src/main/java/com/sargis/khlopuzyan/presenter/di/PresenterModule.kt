package com.sargis.khlopuzyan.presenter.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

private val viewModelModule = module {
//    viewModel {
//        TODOViewModel(get())
//    }
}

val presenterModule = listOf(viewModelModule)