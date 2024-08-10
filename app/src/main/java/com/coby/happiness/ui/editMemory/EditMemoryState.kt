package com.coby.happiness.ui.editMemory

import com.coby.happiness.domain.model.MemoryModel
import com.coby.happiness.domain.type.PageType

data class EditMemoryState(
    var selection: PageType = PageType.FIRST,
    val memory: MemoryModel = MemoryModel()
)
