package com.coby.happiness.ui.editMemory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coby.happiness.data.repository.MemoryRepository
import com.coby.happiness.domain.model.MemoryModel
import com.coby.happiness.domain.type.PageType
import com.coby.happiness.ui.common.AlertButton
import com.coby.happiness.ui.common.AlertState
import com.coby.happiness.ui.common.CloseAlertAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditMemoryViewModel @Inject constructor(
    private val memoryRepository: MemoryRepository
) : ViewModel() {

    private val _state = MutableStateFlow(EditMemoryState())
    val state: StateFlow<EditMemoryState> = _state

    private val _uiEvent = MutableSharedFlow<EditMemoryUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    fun handleAction(action: EditMemoryAction) {
        when (action) {
            is EditMemoryAction.Binding -> _state.update { action.state }
            is EditMemoryAction.CloseAlert -> handleCloseAlert(action.action)
            is EditMemoryAction.ShowCloseAlert -> showCloseAlert()
            is EditMemoryAction.CompleteButtonTapped -> completeButtonTapped()
            is EditMemoryAction.SaveMemory -> saveMemory(action.memory)
            is EditMemoryAction.SaveMemoryResponse -> dismiss()
            is EditMemoryAction.Dismiss -> dismiss()
        }
    }

    private fun handleCloseAlert(action: CloseAlertAction) {
        when (action) {
            CloseAlertAction.CLOSE -> handleAction(EditMemoryAction.Dismiss)
        }
    }

    private fun showCloseAlert() {
        _state.update {
            it.copy(closeAlert = AlertState(
                title = "작성하지 않고 나가시겠습니까?",
                message = null,
                buttons = listOf(
                    AlertButton(
                        text = "나가기",
                        action = CloseAlertAction.CLOSE
                    ),
                    AlertButton(
                        text = "취소",
                        action = null
                    )
                )
            )
            )
        }
    }

    private fun completeButtonTapped() {
        _state.update { state ->
            when (state.selection) {
                PageType.FIRST -> state.copy(selection = PageType.SECOND)
                PageType.SECOND -> {
                    if (!state.memory.isDisabled) {
                        handleAction(EditMemoryAction.SaveMemory(state.memory))
                    }
                    state
                }
            }
        }
    }

    private fun saveMemory(memory: MemoryModel) {
        viewModelScope.launch {
            val isFirst = _state.value.memory.isFirst
            try {
                if (isFirst) {
                    memoryRepository.insertMemory(memory)
                } else {
                    memoryRepository.updateMemory(memory)
                }
                handleAction(EditMemoryAction.SaveMemoryResponse)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    private fun dismiss() {
        viewModelScope.launch {
            _uiEvent.emit(EditMemoryUiEvent.Dismiss)
        }
    }
}