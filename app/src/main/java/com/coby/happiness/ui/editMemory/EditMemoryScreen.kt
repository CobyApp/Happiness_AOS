package com.coby.happiness.ui.editMemory

import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.coby.cobylibrary.ui.element.basic.TopBarView
import com.coby.happiness.domain.model.MemoryModel
import com.coby.happiness.domain.type.PageType
import com.coby.happiness.ui.common.CloseAlertAction
import com.coby.happiness.ui.common.PageBottomButton

@Composable
fun EditMemoryScreen(
    navController: NavHostController,
    viewModel: EditMemoryViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    var showAlert by remember { mutableStateOf(false) }

    LaunchedEffect(viewModel) {
        viewModel.state.collect { state ->
            if (state.closeAlert != null) {
                showAlert = true
            }
        }

        viewModel.uiEvent.collect { event ->
            when (event) {
                is EditMemoryUiEvent.Dismiss -> navController.popBackStack()
            }
        }
    }

    if (showAlert) {
        AlertDialog(
            onDismissRequest = { showAlert = false },
            title = { Text(text = state.closeAlert?.title ?: "") },
            confirmButton = {
                Button(onClick = {
                    showAlert = false
                    state.closeAlert?.buttons?.find { it.action == CloseAlertAction.CLOSE }?.let {
                        viewModel.handleAction(EditMemoryAction.CloseAlert(it.action!!))
                    }
                }) {
                    Text(state.closeAlert?.buttons?.find { it.action == CloseAlertAction.CLOSE }?.text ?: "")
                }
            },
            dismissButton = {
                Button(onClick = { showAlert = false }) {
                    Text(state.closeAlert?.buttons?.find { it.action == null }?.text ?: "")
                }
            }
        )
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopBarView(
            leftAction = {
                viewModel.handleAction(EditMemoryAction.ShowCloseAlert)
            }
        )

        when (state.selection) {
            PageType.FIRST -> EditMemoryFirstPage()
            PageType.SECOND -> EditMemorySecondPage()
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

@Composable
fun EditMemoryFirstPage() {
    // First page UI implementation
    Text(text = "첫째")
}

@Composable
fun EditMemorySecondPage() {
    // Second page UI implementation
    Text(text = "둘째")
}

fun isPageDisabled(state: EditMemoryState): Boolean {
    return when (state.selection) {
        PageType.FIRST -> state.memory.isFirstPageDisabled
        PageType.SECOND -> state.memory.isSecondPageDisabled
    }
}