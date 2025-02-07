package com.coby.happiness.ui.bunch

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.coby.cobylibrary.ui.element.basic.ContentType
import com.coby.cobylibrary.ui.element.basic.TopBarView

@Composable
fun Bunch() {
    Column {
        TopBarView(
            leftSide = ContentType.Title,
            leftTitle = "뭉치",
            rightSide = ContentType.Text,
            rightTitle = "추억 뭉치기",
            rightAction = { }
        )

        Spacer(modifier = Modifier.fillMaxSize())
    }
}