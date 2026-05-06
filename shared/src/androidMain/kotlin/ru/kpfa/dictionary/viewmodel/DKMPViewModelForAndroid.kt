package ru.kpfa.dictionary.viewmodel

import android.content.Context
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import ru.kpfa.dictionary.datalayer.Repository
import mylocal.db.LocalDb


fun DictionaryViewModel.Factory.getAndroidInstance(context : Context) : DictionaryViewModel {
    val sqlDriver = AndroidSqliteDriver(LocalDb.Schema, context, "Local.db")
    val repository = Repository(sqlDriver)
    return DictionaryViewModel(repository)
}