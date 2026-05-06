package ru.kpfa.dictionary.shared.viewmodel

import ru.kpfa.dictionary.datalayer.Repository
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import mylocal.db.LocalDb
import ru.kpfa.dictionary.viewmodel.DictionaryViewModel


fun DictionaryViewModel.Factory.getIosInstance() : DictionaryViewModel {
    val sqlDriver = NativeSqliteDriver(LocalDb.Schema, "Local.db")
    val repository = Repository(sqlDriver)
    return DictionaryViewModel(repository)
}