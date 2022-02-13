package com.geekbrains.cleancodeapp.di

import androidx.room.Room
import com.geekbrains.cleancodeapp.view.main.MainActivity
import com.geekbrains.model.data.dto.SearchResultDto
import com.geekbrains.model.room.HistoryDataBase
import com.geekbrains.history.view.history.HistoryViewModel
import com.geekbrains.history.view.history.HystoryInteractor
import com.geekbrains.cleancodeapp.view.main.MainInteractor
import com.geekbrains.cleancodeapp.view.main.MainViewModel
import com.geekbrains.history.view.history.HistoryActivity
import com.geekbrains.repository.*
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val application = module {
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<Repository<List<SearchResultDto>>> { RepositoryImplementation(RetrofitImplementation()) }
    single<RepositoryLocal<List<SearchResultDto>>> { RepositoryImplementationLocal(
        RoomDataBaseImplementation(get())
    )
    }
}

val mainScreen = module {
scope(named<MainActivity>()){
    scoped{MainInteractor(get(), get())}
    viewModel{MainViewModel(get())}
}
}

val historyScreen = module {
  scope(named<HistoryActivity>()){
      scoped{HystoryInteractor(get(),get())}
        viewModel{HistoryViewModel(get())}
}
}