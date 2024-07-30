package com.coby.happiness.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coby.happiness.data.repository.MemoryRepository
import com.coby.happiness.domain.model.MemoryModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val memoryRepository: MemoryRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state

    init {
        handleAction(HomeAction.GetMemories)
    }

    fun handleAction(action: HomeAction) {
        when (action) {
            is HomeAction.ShowAddMemory -> showAddMemory()
            is HomeAction.ShowDetailMemory -> showDetailMemory(action.memory)
            is HomeAction.GetMemories -> getMemories()
            is HomeAction.GetMemoriesResponse -> _state.value = _state.value.copy(memories = action.memories)
            is HomeAction.GetMemoriesFailure -> handleFailure(action.error)
        }
    }

    private fun showAddMemory() {
        // _state.value = _state.value.copy(addMemory = EditMemoryState())
    }

    private fun showDetailMemory(memory: MemoryModel) {
        // _state.value = _state.value.copy(detailMemory = DetailMemoryState(memory))
    }

    private fun getMemories() {
        viewModelScope.launch {
            try {
                val memories = memoryRepository.getAllMemories()
                _state.value = _state.value.copy(memories = memories)
            } catch (e: Exception) {
                handleAction(HomeAction.GetMemoriesFailure(e))
            }
        }
    }

    private fun handleFailure(error: Throwable) {
        // 적절한 예외 처리
        println(error.localizedMessage)
    }
}