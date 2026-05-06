package ru.kpfa.dictionary.viewmodel

import ru.kpfa.dictionary.DebugLogger
import ru.kpfa.dictionary.datalayer.Repository

val debugLogger by lazy { DebugLogger("D-KMP SAMPLE") }


class DictionaryViewModel (repo: Repository) {

    companion object Factory {
        // factory methods are defined in the platform-specific shared code (androidMain and iosMain)
    }


    private val stateManager by lazy { StateManager(repo) }
    val navigation by lazy { Navigation(stateManager) }

}