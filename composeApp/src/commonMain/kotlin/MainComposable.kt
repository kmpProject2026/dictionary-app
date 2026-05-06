package ru.kpfa.dictionary

import androidx.compose.runtime.Composable
import ru.kpfa.dictionary.navigation.Router
import ru.kpfa.dictionary.viewmodel.DictionaryViewModel

@Composable
fun MainComposable(model: DictionaryViewModel) {
    val dkmpNav = model.navigation
    dkmpNav.Router()
}

