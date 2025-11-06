package com.sargis.khlopuzyan.data.di

import com.sargis.khlopuzyan.data.local.dao.UserDao
import com.sargis.khlopuzyan.data.local.db.UserDatabase
import com.sargis.khlopuzyan.data.remote.PixabayApiRetrofitBuilder
import com.sargis.khlopuzyan.data.repository.ImageSearchRepositoryImpl
import com.sargis.khlopuzyan.data.repository.UserRepositoryImpl
import com.sargis.khlopuzyan.domain.repository.ImageSearchRepository
import com.sargis.khlopuzyan.domain.repository.UserRepository
import org.koin.dsl.module

private val repositoryModule = module {
    single<ImageSearchRepository> {
        ImageSearchRepositoryImpl(get())
    }

    single<UserRepository> {
        UserRepositoryImpl()
    }

    single { PixabayApiRetrofitBuilder.build() }
}

private val databaseModule = module {
    single<UserDatabase> {
        UserDatabase.getInstance(get())
    }
    single<UserDao> {
        get<UserDatabase>().userDao()
    }
//    single<UserDataSource> {
//        UserDataSourceImpl(get())
//    }
}

val dataModule = listOf(databaseModule, repositoryModule)
