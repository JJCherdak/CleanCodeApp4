package com.geekbrains.cleancodeapp.model.datasource

import com.geekbrains.cleancodeapp.model.data.AppState
import com.geekbrains.cleancodeapp.model.data.DataModel
import com.geekbrains.cleancodeapp.room.HistoryDao
import com.geekbrains.cleancodeapp.utils.convertDataModelSuccessToEntity
import com.geekbrains.cleancodeapp.utils.mapHistoryEntityToSearchResult

class RoomDataBaseImplementation(private val historyDao: HistoryDao) : DataSourceLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }

    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }
}