package com.coby.happiness.ui.editMemory.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SetMemoryContentView(
    title: String,
    note: String,
    onTitleChanged: (String) -> Unit,
    onNoteChanged: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp) // BaseSize.horizantalPadding equivalent
            .padding(vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        CBTextFieldView(
            text = title,
            onTextChange = { newText ->
                if (newText.length <= 10) { // 최대 10자까지 입력 가능
                    onTitleChanged(newText)
                }
            },
            title = "추억에 이름을 만들어주세요.",
            placeholder = "10자 이내로 적어주세요.",
            maxLength = 10
        )

        CBTextAreaView(
            text = note,
            onTextChange = { newText -> onNoteChanged(newText) },
            title = "어떤 행복한 추억이었나요?",
            placeholder = "내용을 기록해주세요."
        )
    }
}

@Composable
fun CBTextFieldView(
    text: String,
    onTextChange: (String) -> Unit,
    title: String,
    placeholder: String,
    maxLength: Int
) {
    Column {
        Text(
            text = title,
            fontSize = 16.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(8.dp))
        BasicTextField(
            value = text,
            onValueChange = onTextChange,
            textStyle = TextStyle(fontSize = 18.sp, color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .background(Color.LightGray)
                .padding(8.dp),
            maxLines = 1
        )
        Text(
            text = "${text.length}/$maxLength",
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.align(Alignment.End)
        )
    }
}

@Composable
fun CBTextAreaView(
    text: String,
    onTextChange: (String) -> Unit,
    title: String,
    placeholder: String
) {
    Column {
        Text(
            text = title,
            fontSize = 16.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(8.dp))
        BasicTextField(
            value = text,
            onValueChange = onTextChange,
            textStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(vertical = 8.dp)
                .background(Color.LightGray)
                .padding(8.dp),
            maxLines = 5
        )
    }
}