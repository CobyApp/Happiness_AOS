package com.coby.happiness.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coby.happiness.data.repository.MemoryRepository
import com.coby.happiness.domain.model.MemoryModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

sealed class HomeAction {
    object ShowAddMemory : HomeAction()
    data class ShowDetailMemory(val memory: MemoryModel) : HomeAction()
    object GetMemories : HomeAction()
    data class GetMemoriesResponse(val memories: List<MemoryModel>) : HomeAction()
    data class GetMemoriesFailure(val error: Throwable) : HomeAction()
}

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
            is HomeAction.GetMemoriesFailure -> {
                // Handle error appropriately
                println(action.error.localizedMessage)
            }
        }
    }

    private fun showAddMemory() {
//        _state.value = _state.value.copy(addMemory = EditMemoryState())
    }

    private fun showDetailMemory(memory: MemoryModel) {
//        _state.value = _state.value.copy(detailMemory = DetailMemoryState(memory))
    }

    private fun getMemories() {
        viewModelScope.launch {
            try {
                val memories = memoryRepository.getAllMemories()
                handleAction(HomeAction.GetMemoriesResponse(memories))
            } catch (e: Exception) {
                handleAction(HomeAction.GetMemoriesFailure(e))
            }
        }
    }
}

data class HomeState(
//    val addMemory: EditMemoryState? = null,
//    val detailMemory: DetailMemoryState? = null,
    val memories: List<MemoryModel> = emptyList()
)
