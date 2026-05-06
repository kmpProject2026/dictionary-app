package ru.kpfa.dictionary.datalayer.functions

import ru.kpfa.dictionary.datalayer.Repository
import ru.kpfa.dictionary.datalayer.sources.localdb.countries.getFavoriteCountriesMap
import ru.kpfa.dictionary.datalayer.sources.localdb.countries.toggleFavoriteCountry

suspend fun Repository.getFavoriteCountriesMap(alsoToggleCountry : String? = null): Map<String,Boolean> = withRepoContext {

    // LOCAL DB operation, to toggle a country as favorite
    if (alsoToggleCountry != null) {
        localDb.toggleFavoriteCountry(alsoToggleCountry)
    }

    // RETURN a "trueMap" (i.e. a map whose values are always TRUE boolean):
    // where the keys are the names of the favorite countries
    localDb.getFavoriteCountriesMap()
}