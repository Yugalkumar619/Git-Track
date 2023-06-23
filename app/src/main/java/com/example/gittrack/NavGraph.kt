package com.example.gittrack

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gittrack.ui.screens.HomeScreen
import com.example.gittrack.viewmodel.MainVModel

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
            LoginPage(navController = navController, mainViewModel)
        }
    }
}