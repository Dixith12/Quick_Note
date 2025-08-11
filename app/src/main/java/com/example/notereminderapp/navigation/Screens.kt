package com.example.notereminderapp.navigation

sealed class Screens(val route:String) {
    object HomeScreen:Screens("HomeScreen")
    object CreateScreen:Screens("CreateScreen")
}