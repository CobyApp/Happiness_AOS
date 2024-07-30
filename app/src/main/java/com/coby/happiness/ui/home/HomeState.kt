package com.coby.happiness.ui.home

import com.coby.happiness.domain.model.MemoryModel

data class HomeState(
//    val addMemory: EditMemoryState? = null,
//    val detailMemory: DetailMemoryState? = null,
    val memories: List<MemoryModel> = emptyList()
)