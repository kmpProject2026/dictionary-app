package ru.kpfa.dictionary

import android.app.Application
import androidx.lifecycle.*
import ru.kpfa.dictionary.viewmodel.DictionaryViewModel
import ru.kpfa.dictionary.viewmodel.getAndroidInstance

class DictionaryApp : Application() {

    lateinit var model: DictionaryViewModel

    override fun onCreate() {
        super.onCreate()
        model = DictionaryViewModel.Factory.getAndroidInstance(this)
        
        val appLifecycleObserver = AppLifecycleObserver(model)
        ProcessLifecycleOwner.get().lifecycle.addObserver(appLifecycleObserver)
    }

}

class AppLifecycleObserver (private val model: DictionaryViewModel) : LifecycleEventObserver {

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_START ->
                model.navigation.onEnterForeground()
            Lifecycle.Event.ON_STOP ->
                model.navigation.onEnterBackground()
            else ->
                return
        }
    }

}