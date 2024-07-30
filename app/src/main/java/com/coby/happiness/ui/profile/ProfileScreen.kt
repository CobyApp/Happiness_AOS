package com.coby.happiness.ui.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.coby.cobylibrary.ui.element.basic.ContentType
import com.coby.cobylibrary.ui.element.basic.TopBarView

@Composable
fun ProfileScreen() {
    Column {
        TopBarView(
            leftSide = ContentType.Title,
            leftTitle = "정보",
            rightSide = ContentType.Text,
            rightTitle = "설정",
            rightAction = { }
        )

        Spacer(modifier = Modifier.fillMaxSize())
    }
}