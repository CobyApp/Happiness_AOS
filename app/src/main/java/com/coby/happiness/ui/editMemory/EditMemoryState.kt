package com.coby.happiness.ui.editMemory

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.coby.happiness.domain.model.MemoryModel
import com.coby.happiness.domain.type.PageType
import com.coby.happiness.ui.common.AlertState
import com.coby.happiness.ui.common.CloseAlertAction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class EditMemoryState(
    val closeAlert: AlertState<CloseAlertAction>? = null,
    var selection: PageType = PageType.FIRST,
    val memory: MemoryModel = MemoryModel()
)
