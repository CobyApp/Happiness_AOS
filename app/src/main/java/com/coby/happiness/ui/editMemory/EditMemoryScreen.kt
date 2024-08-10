package com.coby.happiness.ui.editMemory

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.coby.cobylibrary.ui.element.basic.TopBarView
import com.coby.happiness.domain.type.PageType
import com.coby.happiness.ui.common.PageBottomButton
import com.coby.happiness.ui.editMemory.pages.EditMemoryFirstPageView
import com.coby.happiness.ui.editMemory.pages.EditMemorySecondPageView

@Composable
fun EditMemoryScreen(
    navController: NavHostController,
    viewModel: EditMemoryViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val memoryState = remember { mutableStateOf(state.memory) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopBarView(
            leftAction = {
                navController.popBackStack()
            }
        )

        when (state.selection) {
            PageType.FIRST -> EditMemoryFirstPageView(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
                memory = memoryState
            )
            PageType.SECOND -> EditMemorySecondPageView(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
                memory = memoryState
            )
        }

        PageBottomButton(
            selection = state.selection,
            isDisabled = isPageDisabled(state),
            buttonAction = {
                viewModel.handleAction(EditMemoryAction.CompleteButtonTapped)
            },
            onSelectionChange = { newSelection -> state.selection = newSelection }
        )
    }
}

fun isPageDisabled(state: EditMemoryState): Boolean {
    return when (state.selection) {
        PageType.FIRST -> state.memory.isFirstPageDisabled
        PageType.SECOND -> state.memory.isSecondPageDisabled
    }
}