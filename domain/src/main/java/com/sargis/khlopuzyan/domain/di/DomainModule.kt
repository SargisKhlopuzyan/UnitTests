package com.sargis.khlopuzyan.domain.di

import com.sargis.khlopuzyan.domain.usecase.DeleteUserUseCase
import com.sargis.khlopuzyan.domain.usecase.GetLastSignedInUserUseCase
import com.sargis.khlopuzyan.domain.usecase.LoginUserUseCase
import com.sargis.khlopuzyan.domain.usecase.ObserveAllUsersUseCase
import com.sargis.khlopuzyan.domain.usecase.RegisterUserUseCase
import com.sargis.khlopuzyan.domain.util.NameValidator
import com.sargis.khlopuzyan.domain.util.PasswordValidator
import com.sargis.khlopuzyan.domain.util.UsernameValidator
import org.koin.dsl.module

private val useCasesModule = module {
    single<GetLastSignedInUserUseCase> { GetLastSignedInUserUseCase(get()) }
    single<LoginUserUseCase> { LoginUserUseCase(get(), get(), get()) }
    single<RegisterUserUseCase> { RegisterUserUseCase(get(), get(), get(), get()) }
    single<ObserveAllUsersUseCase> { ObserveAllUsersUseCase(get()) }
    single<DeleteUserUseCase> { DeleteUserUseCase(get()) }
//    single<ImageSearchUseCase> { ImageSearchUseCase(get()) }
}

private val validatorsModule = module {
    single<UsernameValidator> {
        UsernameValidator
    }
    single<NameValidator> {
        NameValidator
    }
    single<PasswordValidator> {
        PasswordValidator
    }
}

val domainModule = listOf(useCasesModule, validatorsModule)
