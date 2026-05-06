package ru.kpfa.dictionary.navigation

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.ui.unit.dp
import ru.kpfa.dictionary.navigation.templates.OnePane
import ru.kpfa.dictionary.navigation.templates.TwoPane
import ru.kpfa.dictionary.viewmodel.Navigation
import ru.kpfa.dictionary.viewmodel.NavigationState
import ru.kpfa.dictionary.viewmodel.ScreenIdentifier
import ru.kpfa.dictionary.viewmodel.ScreenParams
import ru.kpfa.dictionary.viewmodel.screens.Level1Navigation
import ru.kpfa.dictionary.viewmodel.screens.Screen



@Composable
fun Navigation.Router() {

    val screenUIsStateHolder = rememberSaveableStateHolder()
    val localNavigationState = remember { mutableStateOf( navigationState ) }

    val twopaneWidthThreshold = 1000.dp
    BoxWithConstraints {
        if (maxWidth < maxHeight || maxWidth<twopaneWidthThreshold) {
            OnePane(screenUIsStateHolder, localNavigationState)
        } else {
            TwoPane(screenUIsStateHolder, localNavigationState)
        }
    }

    HandleBackButton(screenUIsStateHolder, localNavigationState)

}

fun Navigation.navigationProcessor(localNavigationState: MutableState<NavigationState>) : (Screen, ScreenParams?) -> Unit {
    return { screen, screenParams ->
        val screenIdentifier = ScreenIdentifier.get(screen, screenParams)
        navigateToScreen(screenIdentifier) // shared navigationState is updated
        localNavigationState.value = navigationState // update localNavigationState
    }
}

fun Navigation.level1NavigationProcessor(localNavigationState: MutableState<NavigationState>) : (Level1Navigation) -> Unit {
    return {
        selectLevel1Navigation(it.screenIdentifier) // shared navigationState is updated
        localNavigationState.value = navigationState // update localNavigationState
    }
}