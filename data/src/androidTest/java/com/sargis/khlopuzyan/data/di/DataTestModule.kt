package com.sargis.khlopuzyan.data.di

import com.sargis.khlopuzyan.data.local.dao.UserDao
import com.sargis.khlopuzyan.data.local.db.UserDatabase
import com.sargis.khlopuzyan.data.local.sharedPreferences.UserSharedPref
import com.sargis.khlopuzyan.data.local.source.UserDataSource
import com.sargis.khlopuzyan.data.local.source.UserDataSourceImpl
import com.sargis.khlopuzyan.data.remote.PixabayApiRetrofitBuilder
import com.sargis.khlopuzyan.data.repository.ImageSearchRepositoryImpl
import com.sargis.khlopuzyan.data.repository.UserRepositoryImpl
import com.sargis.khlopuzyan.domain.repository.ImageSearchRepository
import com.sargis.khlopuzyan.domain.repository.UserRepository
import org.koin.dsl.module

private val repositoryModule = module {

//    single<NoteRepository> { FakeNoteRepository() }
    single<UserRepository> { UserRepositoryImpl(get()) }

    single<ImageSearchRepository> {
        ImageSearchRepositoryImpl(get())
    }

    single { PixabayApiRetrofitBuilder.build() }
}

private val sharedPrefModule = module {
    single<UserSharedPref> {
        UserSharedPref(get())
    }
}

private val databaseTestModule = module {
    single<UserDatabase> {
        UserDatabase.getTestInstance(get())
    }
    single<UserDao> {
        get<UserDatabase>().userDao()
    }
    single<UserDataSource> {
//        FakeAndroidNoteDataSource()
        UserDataSourceImpl(get(), get())
    }
}

val dataTestModule = listOf(sharedPrefModule, databaseTestModule, repositoryModule)