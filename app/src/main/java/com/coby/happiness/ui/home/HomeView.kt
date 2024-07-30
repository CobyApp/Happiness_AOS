package com.coby.happiness.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.coby.cobylibrary.ui.element.basic.BaseSize
import com.coby.cobylibrary.ui.element.basic.ContentType
import com.coby.cobylibrary.ui.element.basic.TopBarView
import com.coby.cobylibrary.ui.element.thumbnail.ThumbnailCardView
import com.coby.cobylibrary.ui.theme.BackgroundNormalNormal
import com.coby.happiness.R
import com.coby.happiness.domain.model.MemoryModel
import com.coby.happiness.domain.model.formatLong

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val state by homeViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        homeViewModel.handleAction(HomeAction.GetMemories)
    }

    Surface(color = Color.BackgroundNormalNormal()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray)
        ) {
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

            MemoryListView(
                memories = state.memories,
                onMemoryClick = { memory ->
                    homeViewModel.handleAction(HomeAction.ShowDetailMemory(memory))
                }
            )
        }
    }

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController = navController) }
//        composable("addMemory") { EditMemoryView(navController = navController) }
        composable("detailMemory/{memoryId}") { backStackEntry ->
            val memoryId = backStackEntry.arguments?.getString("memoryId")
            // 메모리 ID를 사용하여 상세 뷰를 표시
            val memory = state.memories.find { it.id.toString() == memoryId }
            if (memory != null) {
//                DetailMemoryView(memory = memory, navController = navController)
            }
        }
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
            // Implement your empty view here
        }
    }
}