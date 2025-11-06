package com.sargis.khlopuzyan.domain.di

import com.sargis.khlopuzyan.domain.usecase.GetUserUseCase
import com.sargis.khlopuzyan.domain.usecase.RegisterUserUseCase
import com.sargis.khlopuzyan.domain.usecase.SaveUserUseCase
import org.koin.dsl.module

private val useCasesModule = module {
    single<GetUserUseCase> { GetUserUseCase(get()) }
    single<SaveUserUseCase> { SaveUserUseCase(get()) }
    single<RegisterUserUseCase> { RegisterUserUseCase(get()) }
//    single<ImageSearchUseCase> { ImageSearchUseCase(get()) }
}

val domainModule = listOf(useCasesModule)
