package ru.kpfa.dictionary.shared

import com.russhwolf.settings.MapSettings
import ru.kpfa.dictionary.datalayer.Repository
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import mylocal.db.LocalDb


actual fun getTestRepository() : Repository {
    val sqlDriver = NativeSqliteDriver(LocalDb.Schema, "test.db")
    return Repository(sqlDriver, MapSettings(), true)
}