package ru.kpfa.dictionary.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.saveable.SaveableStateHolder
import ru.kpfa.dictionary.viewmodel.Navigation
import ru.kpfa.dictionary.viewmodel.NavigationState

@Composable
expect fun Navigation.HandleBackButton(
    saveableStateHolder: SaveableStateHolder,
    localNavigationState: MutableState<NavigationState>,
)