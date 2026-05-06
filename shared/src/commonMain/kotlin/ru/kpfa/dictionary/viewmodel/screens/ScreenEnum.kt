package ru.kpfa.dictionary.viewmodel.screens

import ru.kpfa.dictionary.viewmodel.ScreenIdentifier
import ru.kpfa.dictionary.viewmodel.StateManager
import ru.kpfa.dictionary.viewmodel.screens.countrieslist.initCountriesList
import ru.kpfa.dictionary.viewmodel.screens.countrydetail.initCountryDetail


// DEFINITION OF ALL SCREENS IN THE APP

enum class Screen(
    val asString: String,
    val navigationLevel : Int = 1,
    val initSettings: StateManager.(ScreenIdentifier) -> ScreenInitSettings,
) {
    CountriesList("countrieslist", 1, { initCountriesList(it.params()) }),
    CountryDetail("country", 2, { initCountryDetail(it.params()) })
}