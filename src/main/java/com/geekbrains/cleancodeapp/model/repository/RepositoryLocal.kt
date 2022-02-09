package com.geekbrains.cleancodeapp.model.repository

import com.geekbrains.cleancodeapp.model.data.AppState

interface RepositoryLocal<T> : Repository<T> {
    suspend fun saveToDB(appState: AppState)
}