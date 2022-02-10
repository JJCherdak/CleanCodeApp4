package com.geekbrains.cleancodeapp.di

import androidx.room.Room
import com.geekbrains.model.data.DataModel
import com.geekbrains.model.room.HistoryDataBase
import com.geekbrains.history.view.history.HistoryViewModel
import com.geekbrains.history.view.history.HystoryInteractor
import com.geekbrains.cleancodeapp.view.main.MainInteractor
import com.geekbrains.cleancodeapp.view.main.MainViewModel
import com.geekbrains.repository.*
import org.koin.dsl.module

val application = module {
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<Repository<List<DataModel>>> { RepositoryImplementation(RetrofitImplementation()) }
    single<RepositoryLocal<List<DataModel>>> { RepositoryImplementationLocal(
        RoomDataBaseImplementation(get())
    )
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