package com.geekbrains.cleancodeapp.model.repository

import com.geekbrains.cleancodeapp.model.data.AppState
import com.geekbrains.cleancodeapp.model.data.DataModel
import com.geekbrains.cleancodeapp.model.datasource.DataSourceLocal

class RepositoryImplementationLocal(private val dataSource: DataSourceLocal<List<DataModel>>) :
    RepositoryLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }

    override suspend fun saveToDB(appState: AppState) {
        dataSource.saveToDB(appState)
    }
}