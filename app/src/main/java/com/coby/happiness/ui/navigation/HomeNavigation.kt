package com.coby.happiness.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import com.coby.happiness.ui.editMemory.EditMemoryScreen
import com.coby.happiness.ui.home.HomeScreen

@Composable
fun HomeNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController = navController) }
        composable("editMemory") { EditMemoryScreen(navController = navController) }
        composable("detailMemory/{memoryId}") { backStackEntry ->
//            val memoryId = backStackEntry.arguments?.getString("memoryId")
//            // 메모리 ID를 사용하여 상세 뷰를 표시
//            val homeViewModel: HomeViewModel = hiltViewModel()
//            val state by homeViewModel.state.collectAsStateWithLifecycle()
//            val memory = state.memories.find { it.id.toString() == memoryId }
//            if (memory != null) {
//                DetailMemoryView(memory = memory, navController = navController)
//            }
        }
    }
}