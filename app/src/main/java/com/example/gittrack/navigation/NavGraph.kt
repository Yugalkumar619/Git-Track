package com.example.gittrack.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gittrack.ui.screens.AddRepoScreen
import com.example.gittrack.ui.screens.HomeScreen
import com.example.gittrack.ui.screens.Screen
import com.example.gittrack.viewmodel.MainVModel

// Here is the NavGraph Class for controlling the different screens
@Composable
fun SetupNavGraph(
    navController: NavHostController,
    mainViewModel: MainVModel
){
    NavHost(navController = navController,
    startDestination = Screen.Home.route
    ){
        composable(
            route = Screen.Home.route
        ){
            HomeScreen(navController, mainViewModel)
        }

        composable(
            route = Screen.AddRepo.route
        ){
            AddRepoScreen(navController = navController, mainViewModel)
        }
    }
}