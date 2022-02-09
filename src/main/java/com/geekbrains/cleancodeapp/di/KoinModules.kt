package com.geekbrains.cleancodeapp.di

import androidx.room.Room
import com.geekbrains.cleancodeapp.model.data.DataModel
import com.geekbrains.cleancodeapp.model.datasource.RetrofitImplementation
import com.geekbrains.cleancodeapp.model.datasource.RoomDataBaseImplementation
import com.geekbrains.cleancodeapp.model.repository.Repository
import com.geekbrains.cleancodeapp.model.repository.RepositoryImplementation
import com.geekbrains.cleancodeapp.model.repository.RepositoryImplementationLocal
import com.geekbrains.cleancodeapp.model.repository.RepositoryLocal
import com.geekbrains.cleancodeapp.room.HistoryDataBase
import com.geekbrains.cleancodeapp.view.history.HistoryViewModel
import com.geekbrains.cleancodeapp.view.history.HystoryInteractor
import com.geekbrains.cleancodeapp.view.main.MainInteractor
import com.geekbrains.cleancodeapp.view.main.MainViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val application = module {
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<Repository<List<DataModel>>> { RepositoryImplementation(RetrofitImplementation()) }
    single<RepositoryLocal<List<DataModel>>> { RepositoryImplementationLocal(RoomDataBaseImplementation(get()))
    }
}

val mainScreen = module {
    factory { MainViewModel(get()) }
    factory { MainInteractor(get(), get()) }
}

val historyScreen = module {
    factory { HistoryViewModel(get()) }
    factory { HystoryInteractor(get(), get()) }
}