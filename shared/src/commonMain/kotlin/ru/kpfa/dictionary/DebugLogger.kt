package ru.kpfa.dictionary

expect class DebugLogger (tagString : String) {
    val tag : String
    fun log(message: String)
}