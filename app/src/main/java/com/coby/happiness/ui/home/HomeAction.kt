package com.coby.happiness.ui.home

import com.coby.happiness.domain.model.MemoryModel

sealed class HomeAction {
    data object ShowAddMemory : HomeAction()
    data class ShowDetailMemory(val memory: MemoryModel) : HomeAction()
    data object GetMemories : HomeAction()
    data class GetMemoriesResponse(val memories: List<MemoryModel>) : HomeAction()
    data class GetMemoriesFailure(val error: Throwable) : HomeAction()
}