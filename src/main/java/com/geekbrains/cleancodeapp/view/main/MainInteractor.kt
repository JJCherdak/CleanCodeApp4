package com.geekbrains.cleancodeapp.view.main

import com.geekbrains.cleancodeapp.utils.mapSearchResultToResult
import com.geekbrains.model.data.AppState
import com.geekbrains.model.data.dto.SearchResultDto
import com.geekbrains.repository.Repository
import com.geekbrains.repository.RepositoryLocal
import com.geekbrains.core.viewmodel.Interactor


class MainInteractor(
    private val remoteRepository: Repository<List<SearchResultDto>>,
    private val localRepository: RepositoryLocal<List<SearchResultDto>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        val appState: AppState
        if (fromRemoteSource) {
            appState = AppState.Success(mapSearchResultToResult(remoteRepository.getData(word)))
            localRepository.saveToDB(appState)
        } else {
            appState = AppState.Success(mapSearchResultToResult(localRepository.getData(word)))
        }
        return appState
    }
}