package com.coby.happiness.ui.common

enum class CloseAlertAction {
    CLOSE
}

data class AlertState<T>(
    val title: String,
    val message: String?,
    val buttons: List<AlertButton<T>>
)

data class AlertButton<T>(
    val text: String,
    val action: T?
)