package com.coby.happiness.ui.editMemory.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.coby.happiness.domain.type.MemoryType

@Composable
fun SetMemoryTypeView(
    selectedType: MemoryType,
    onTypeSelected: (MemoryType) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "추억 태그를 선택해주세요.",
            fontSize = 16.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(4.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            MemoryType.values().forEach { memoryType ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (selectedType == memoryType) Color.Blue else Color.LightGray)
                        .clickable { onTypeSelected(memoryType) }
                        .padding(16.dp)
                ) {
                    Text(
                        text = memoryType.title,
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@Composable
fun TagView(
    isSelected: Boolean,
    title: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .clickable { onClick() }
    ) {
        Text(
            text = title,
            color = if (isSelected) Color.Blue else Color.Gray, // 선택 여부에 따라 색상 변경
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun FlexibleStack(
    spacing: Dp,
    content: @Composable () -> Unit
) {
    // 여기에 FlexibleStack의 동작을 재현하는 구현을 추가할 수 있습니다.
    // 이 예제에서는 Column을 사용하여 간단히 구현
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(spacing)
    ) {
        content()
    }
}
