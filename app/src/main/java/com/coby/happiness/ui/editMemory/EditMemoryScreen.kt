package com.coby.happiness.ui.editMemory

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.coby.cobylibrary.ui.element.basic.TopBarView
import com.coby.happiness.domain.model.MemoryModel
import com.coby.happiness.domain.type.PageType
import com.coby.happiness.ui.common.CloseAlertAction
import kotlinx.coroutines.flow.collect

@Composable
fun EditMemoryScreen(
    navController: NavHostController,
    viewModel: EditMemoryViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    var showAlert by remember { mutableStateOf(false) }

    LaunchedEffect(viewModel) {
        viewModel.state.collect { state ->
            if (state.closeAlert != null) {
                showAlert = true
            }
        }
    }

    if (showAlert) {
        AlertDialog(
            onDismissRequest = { showAlert = false },
            title = { Text(text = "작성하지 않고 나가시겠습니까?") },
            confirmButton = {
                Button(onClick = {
                    showAlert = false
                    viewModel.handleAction(EditMemoryAction.CloseAlert(CloseAlertAction.CLOSE))
                }) {
                    Text("나가기")
                }
            },
            dismissButton = {
                Button(onClick = { showAlert = false }) {
                    Text("취소")
                }
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE0E0E0))
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    // Close keyboard functionality
                })
            }
    ) {
        TopBarView(
            leftAction = {
                viewModel.handleAction(EditMemoryAction.ShowCloseAlert)
            }
        )

        when (state.selection) {
            PageType.FIRST -> EditMemoryFirstPage(
                memory = state.memory
            )
            PageType.SECOND -> EditMemorySecondPage(
                memory = state.memory
            )
        }

        PageBottomButton(
            selection = state.selection,
            isDisabled = isPageDisabled(state),
            onButtonClick = {
                // Close keyboard functionality
                viewModel.handleAction(EditMemoryAction.CompleteButtonTapped)
            }
        )
    }
}

@Composable
fun EditMemoryFirstPage(memory: MemoryModel) {
    // First page UI implementation
    Text(text = memory.title)
}

@Composable
fun EditMemorySecondPage(memory: MemoryModel) {
    // Second page UI implementation
    Text(text = memory.note)
}

@Composable
fun PageBottomButton(
    selection: PageType,
    isDisabled: Boolean,
    onButtonClick: () -> Unit
) {
    // Page bottom button UI implementation
    Button(onClick = onButtonClick, enabled = !isDisabled) {
        Text("Next")
    }
}

fun isPageDisabled(state: EditMemoryState): Boolean {
    return when (state.selection) {
        PageType.FIRST -> state.memory.isFirstPageDisabled
        PageType.SECOND -> state.memory.isSecondPageDisabled
    }
}