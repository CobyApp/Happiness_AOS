package com.coby.happiness.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TitleView(
    title: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, start = 16.dp, end = 16.dp), // BaseSize.horizantalPadding equivalent
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 21.sp, // .pretendard(size: 21)
            fontWeight = FontWeight.Bold, // .bold weight
            color = Color.Gray // Color.labelNormal equivalent
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}
