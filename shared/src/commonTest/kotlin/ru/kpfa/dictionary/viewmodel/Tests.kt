package ru.kpfa.dictionary.viewmodel

import ru.kpfa.dictionary.datalayer.objects.CountryExtraData
import ru.kpfa.dictionary.datalayer.objects.CountryListData
import ru.kpfa.dictionary.datalayer.sources.localdb.countries.setCountriesList
import ru.kpfa.dictionary.getTestRepository
import ru.kpfa.dictionary.viewmodel.screens.Screen.*
import ru.kpfa.dictionary.viewmodel.screens.countrydetail.CountryDetailState
import ru.kpfa.dictionary.viewmodel.screens.countrydetail.CountryDetailParams
import ru.kpfa.dictionary.viewmodel.screens.countrydetail.CountryInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import ru.kpfa.dictionary.viewmodel.screens.countrieslist.CountriesListParams
import ru.kpfa.dictionary.viewmodel.screens.countrieslist.CountriesListState
import ru.kpfa.dictionary.viewmodel.screens.countrieslist.CountriesListType
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class ViewModelTests {

    private val testDispatcher: TestDispatcher = StandardTestDispatcher()
    val vm: DictionaryViewModel = DictionaryViewModel(getTestRepository())
    val navigation: Navigation
        get() = vm.navigation
    val stateProvider: StateProvider
        get() = navigation.stateProvider
    val stateManager: StateManager
        get() = navigation.stateManager



    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testCountriesListStateUpdate() = runTest {
        val screenIdentifier = ScreenIdentifier.Factory.get(CountriesList,
            CountriesListParams(CountriesListType.ALL)
        )
        navigation.addScreenToBackstack(screenIdentifier)
        stateManager.updateScreen(CountriesListState::class) {
            it.copy(favoriteCountries = mapOf("Italy" to true))
        }
        val screenState = stateProvider.getScreenStateFlow(screenIdentifier).value as CountriesListState
        assertTrue(screenState.favoriteCountries.containsKey("Italy"))
    }

    @Test
    fun testCountryDetailStateUpdate() = runTest {
        stateManager.dataRepository.localDb.setCountriesList(
            listOf(CountryListData(name = "Germany"))
        )
        val screenIdentifier = ScreenIdentifier.Factory.get(CountryDetail, CountryDetailParams("Germany"))
        navigation.addScreenToBackstack(screenIdentifier)
        stateManager.updateScreen(CountryDetailState::class) {
            it.copy(countryInfo = CountryInfo(_extraData = CountryExtraData(vaccines = "Pfizer, Moderna, AstraZeneca")))
        }
        val screenState = stateProvider.getScreenStateFlow(screenIdentifier).value as CountryDetailState
        assertTrue(screenState.countryInfo.vaccinesList!!.contains("Pfizer"))
    }

}
