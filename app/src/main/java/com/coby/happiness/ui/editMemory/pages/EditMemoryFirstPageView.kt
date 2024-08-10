package com.coby.happiness.ui.editMemory.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.coby.happiness.domain.model.MemoryModel
import com.coby.happiness.ui.editMemory.component.SetMemoryPhotosView
import com.coby.happiness.ui.editMemory.component.SetMemoryTypeView
import com.coby.happiness.ui.common.TitleView

@Composable
fun EditMemoryFirstPageView(
    modifier: Modifier = Modifier,
    memory: MutableState<MemoryModel>
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .verticalScroll(scrollState)
            .padding(bottom = 16.dp)
    ) {
        TitleView(title = "추억 기록하기")

        Spacer(modifier = Modifier.height(24.dp))

        SetMemoryTypeView(
            selectedType = memory.value.type,
            onTypeSelected = { newType ->
                memory.value = memory.value.copy(type = newType)
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        SetMemoryPhotosView(
            photosData = memory.value.photos,
            onPhotosChanged = { newPhotos ->
                memory.value = memory.value.copy(photos = newPhotos)
            },
            date = memory.value.date,
            onDateChanged = { newDate ->
                memory.value = memory.value.copy(date = newDate)
            },
            location = memory.value.location,
            onLocationChanged = { newLocation ->
                memory.value = memory.value.copy(location = newLocation)
            },
            title = memory.value.type.description
        )
    }
}