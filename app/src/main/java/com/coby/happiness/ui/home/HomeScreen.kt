package com.coby.happiness.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.coby.cobylibrary.ui.element.basic.BarType
import com.coby.cobylibrary.ui.element.basic.BaseSize
import com.coby.cobylibrary.ui.element.basic.ContentType
import com.coby.cobylibrary.ui.element.basic.TopBarView
import com.coby.cobylibrary.ui.theme.BackgroundNormalNormal
import com.coby.happiness.R

@Composable
fun HomeScreen() {
    Column {
        Box {
            TopBarView(
                rightSide = ContentType.Text,
                rightTitle = "추억 기록하기",
                rightAction = { }
            )

            Icon(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = BaseSize.horizontalPadding, top = 12.dp)
                    .size(width = 52.dp, height = 40.dp),
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "logo"
            )
        }

        Spacer(modifier = Modifier.fillMaxSize())
    }
}