package com.example.gittrack

sealed class Screen(val route: String){
    object Home: Screen(route = "home_screen")
    object AddRepo: Screen(route = "addrepo_screen")
}