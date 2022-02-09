package com.geekbrains.cleancodeapp.model.datasource

import com.geekbrains.cleancodeapp.model.data.AppState


interface DataSourceLocal<T> : DataSource<T> {

    suspend fun saveToDB(appState: AppState)
}