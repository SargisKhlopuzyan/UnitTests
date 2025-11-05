package com.sargis.khlopuzyan.data.di

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

//private val databaseModule = module {
//    single<NoteDatabase> {
//        NoteDatabase.getInstance(get())
//    }
//    single<NoteDao> {
//        get<NoteDatabase>().noteDao()
//    }
//    single<NoteDataSource> {
//        NoteDataSourceImpl(get())
//    }
//}

val dataModule = listOf(/*databaseModule,*/ repositoryModule)
