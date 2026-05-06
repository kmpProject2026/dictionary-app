package ru.kpfa.dictionary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.kpfa.dictionary.styling.MyMaterialTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val model = (application as DictionaryApp).model
        setContent {
            MyMaterialTheme {
                MainComposable(model)
            }
        }
    }
}