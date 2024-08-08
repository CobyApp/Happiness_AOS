package com.coby.happiness.ui.editMemory

import com.coby.happiness.domain.model.MemoryModel
import com.coby.happiness.ui.common.CloseAlertAction

sealed class EditMemoryAction {
    data class Binding(val state: EditMemoryState) : EditMemoryAction()
    data class CloseAlert(val action: CloseAlertAction) : EditMemoryAction()
    data object ShowCloseAlert : EditMemoryAction()
    data object CompleteButtonTapped : EditMemoryAction()
    data class SaveMemory(val memory: MemoryModel) : EditMemoryAction()
    data object SaveMemoryResponse : EditMemoryAction()
    data object Dismiss : EditMemoryAction()
}

sealed class EditMemoryUiEvent {
//    data object ShowCloseAlert : EditMemoryUiEvent()
    data object Dismiss : EditMemoryUiEvent()
}