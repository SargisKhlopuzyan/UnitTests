package com.sargis.khlopuzyan.domain.di

import com.sargis.khlopuzyan.domain.usecase.GetUserNameUseCase
import com.sargis.khlopuzyan.domain.usecase.SaveUserNameUseCase
import org.koin.dsl.module

private val useCasesModule = module {
    single<GetUserNameUseCase> { GetUserNameUseCase(get()) }
    single<SaveUserNameUseCase> { SaveUserNameUseCase(get()) }
//    single<ImageSearchUseCase> { ImageSearchUseCase(get()) }
}

val domainModule = listOf(useCasesModule)
