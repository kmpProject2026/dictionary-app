package ru.kpfa.dictionary.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import ru.kpfa.dictionary.screens.countrydetail.CountryDetailScreen
import ru.kpfa.dictionary.screens.countrieslist.CountriesListScreen
import ru.kpfa.dictionary.screens.countrieslist.CountriesListTwoPaneDefaultDetail
import ru.kpfa.dictionary.viewmodel.Navigation
import ru.kpfa.dictionary.viewmodel.ScreenIdentifier
import ru.kpfa.dictionary.viewmodel.ScreenParams
import ru.kpfa.dictionary.viewmodel.screens.Screen
import ru.kpfa.dictionary.viewmodel.screens.Screen.*
import ru.kpfa.dictionary.viewmodel.screens.countrieslist.CountriesListState
import ru.kpfa.dictionary.viewmodel.screens.countrieslist.selectFavorite
import ru.kpfa.dictionary.viewmodel.screens.countrydetail.CountryDetailParams
import ru.kpfa.dictionary.viewmodel.screens.countrydetail.CountryDetailState


@Composable
fun Navigation.ScreenPicker(
    screenIdentifier: ScreenIdentifier,
    navigate: (Screen, ScreenParams?) -> Unit
) {

    val state by stateProvider.getScreenStateFlow(screenIdentifier).collectAsState()

    when (screenIdentifier.screen) {

        CountriesList ->
            CountriesListScreen(
                countriesListState = state as CountriesListState,
                onListItemClick = { navigate(CountryDetail, CountryDetailParams(countryName = it)) },
                onFavoriteIconClick = { events.selectFavorite(countryName = it) },
            )

        CountryDetail ->
            CountryDetailScreen(
                countryDetailState = state as CountryDetailState
            )

    }

}



@Composable
fun Navigation.TwoPaneDefaultDetail(
    screenIdentifier: ScreenIdentifier
) {

    when (screenIdentifier.screen) {

        CountriesList ->
            CountriesListTwoPaneDefaultDetail()

        else -> Box{}
    }

}