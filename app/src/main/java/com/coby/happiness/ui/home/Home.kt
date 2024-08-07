package com.coby.happiness.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.coby.cobylibrary.ui.element.basic.BaseSize
import com.coby.cobylibrary.ui.element.basic.ContentType
import com.coby.cobylibrary.ui.element.basic.TopBarView
import com.coby.cobylibrary.ui.element.thumbnail.ThumbnailCardView
import com.coby.cobylibrary.ui.theme.LabelNormal
import com.coby.cobylibrary.ui.theme.Typography
import com.coby.happiness.R
import com.coby.happiness.domain.model.MemoryModel
import com.coby.happiness.domain.model.formatLong

@Composable
fun Home(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.handleAction(HomeAction.GetMemories)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box {
            TopBarView(
                rightSide = ContentType.Text,
                rightTitle = "추억 기록하기",
                rightAction = {
                    viewModel.handleAction(HomeAction.ShowAddMemory)
                    navController.navigate("addMemory")
                }
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

        MemoryListView(
            memories = state.memories,
            onMemoryClick = { memory ->
                viewModel.handleAction(HomeAction.ShowDetailMemory(memory))
                navController.navigate("detailMemory/${memory.id}")
            }
        )
    }
}

@Composable
fun MemoryListView(memories: List<MemoryModel>, onMemoryClick: (MemoryModel) -> Unit) {
    if (memories.isEmpty()) {
        EmptyMemoryView {
            // Implement action for empty memory view
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(memories) { memory ->
                ThumbnailCardView(
                    image = BitmapPainter(memory.photos.first().asImageBitmap()),
                    title = memory.title,
                    description = memory.date.formatLong(),
                    onClick = { onMemoryClick(memory) }
                )
            }
        }
    }
}

@Composable
fun EmptyMemoryView(onAddMemoryClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "데이터가 없습니다.",
                style = Typography.titleMedium,
                color = Color.LabelNormal()
            )
        }
    }
}