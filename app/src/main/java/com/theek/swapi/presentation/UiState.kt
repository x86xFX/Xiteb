package com.theek.swapi.presentation

interface UiState<out T> {
    data object Loading : UiState<Nothing>
    data class Success<T>(val data: T) : UiState<T>
    data class Failure<T>(val message: String?) : UiState<T>
}