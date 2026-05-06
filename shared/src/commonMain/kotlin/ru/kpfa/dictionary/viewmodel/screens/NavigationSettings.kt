package ru.kpfa.dictionary.viewmodel.screens

import ru.kpfa.dictionary.viewmodel.ScreenIdentifier
import ru.kpfa.dictionary.viewmodel.screens.countrieslist.CountriesListParams
import ru.kpfa.dictionary.viewmodel.screens.countrieslist.CountriesListType


// CONFIGURATION SETTINGS

object navigationSettings {
    val homeScreen = Level1Navigation.AllCountries // the start screen should be specified here
    val saveLastLevel1Screen = true
    val alwaysQuitOnHomeScreen = true
}


// LEVEL 1 NAVIGATION OF THE APP

enum class Level1Navigation(val screenIdentifier: ScreenIdentifier, val rememberVerticalStack: Boolean = false) {
    AllCountries( ScreenIdentifier.Factory.get(
        Screen.CountriesList,
        CountriesListParams(listType = CountriesListType.ALL)
    ), true),
    FavoriteCountries( ScreenIdentifier.Factory.get(
        Screen.CountriesList,
        CountriesListParams(listType = CountriesListType.FAVORITES)
    ), true),
}