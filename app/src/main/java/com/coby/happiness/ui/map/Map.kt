package com.coby.happiness.ui.map

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.coby.cobylibrary.ui.element.basic.ContentType
import com.coby.cobylibrary.ui.element.basic.TopBarView

@Composable
fun Map() {
    Column {
        TopBarView(
            leftSide = ContentType.Title,
            leftTitle = "지도",
            rightSide = ContentType.Text,
            rightTitle = "추억 기록하기",
            rightAction = { }
        )

        Spacer(modifier = Modifier.fillMaxSize())
    }
}