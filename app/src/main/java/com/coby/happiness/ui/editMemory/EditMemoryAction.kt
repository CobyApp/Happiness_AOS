package com.coby.happiness.ui.editMemory

import com.coby.happiness.domain.model.MemoryModel

sealed class EditMemoryAction {
    data class Binding(val state: EditMemoryState) : EditMemoryAction()
    data class CloseAlert(val action: CloseAlertAction) : EditMemoryAction()
    object ShowCloseAlert : EditMemoryAction()
    object CompleteButtonTapped : EditMemoryAction()
    data class SaveMemory(val memory: MemoryModel) : EditMemoryAction()
    object SaveMemoryResponse : EditMemoryAction()
    object Dismiss : EditMemoryAction()
}

enum class CloseAlertAction {
    CLOSE
}
