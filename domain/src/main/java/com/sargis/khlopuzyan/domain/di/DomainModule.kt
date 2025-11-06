package com.sargis.khlopuzyan.domain.di

import com.sargis.khlopuzyan.domain.usecase.GetLastSignedInUserUseCase
import com.sargis.khlopuzyan.domain.usecase.LoginUserUseCase
import com.sargis.khlopuzyan.domain.usecase.RegisterUserUseCase
import org.koin.dsl.module

private val useCasesModule = module {
    single<GetLastSignedInUserUseCase> { GetLastSignedInUserUseCase(get()) }
    single<LoginUserUseCase> { LoginUserUseCase(get()) }
    single<RegisterUserUseCase> { RegisterUserUseCase(get()) }
//    single<ImageSearchUseCase> { ImageSearchUseCase(get()) }
}

val domainModule = listOf(useCasesModule)
