package com.geekbrains.cleancodeapp.model.datasource



interface DataSource <T> {
    suspend fun getData(word: String): T
}