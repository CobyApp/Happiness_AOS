package com.coby.happiness.ui.editMemory.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.coby.happiness.domain.model.MemoryModel
import com.coby.happiness.ui.editMemory.component.SetMemoryContentView

@Composable
fun EditMemorySecondPageView(
    modifier: Modifier = Modifier,
    memory: MutableState<MemoryModel>
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .verticalScroll(scrollState)
            .padding(bottom = 16.dp)
    ) {
        SetMemoryContentView(
            title = memory.value.title,
            note = memory.value.note,
            onTitleChanged = { newTitle ->
                memory.value = memory.value.copy(title = newTitle)
            },
            onNoteChanged = { newNote ->
                memory.value = memory.value.copy(note = newNote)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EditMemorySecondPageViewPreview() {
    val memory = remember { mutableStateOf(MemoryModel(title = "Sample Title", note = "Sample note about the happy memory.")) }

    EditMemorySecondPageView(memory = memory)
}