package com.example.notereminderapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notereminderapp.screen.CreateScreen
import com.example.notereminderapp.screen.HomeScreen
import com.example.notereminderapp.screen.NoteViewModel

@Composable
fun Navigation()
{
    val navController = rememberNavController()
    val viewModel: NoteViewModel = hiltViewModel()
    var notelist = viewModel.notelist.collectAsState().value
    NavHost(navController = navController, startDestination = Screens.HomeScreen.route)
    {
        composable(Screens.HomeScreen.route)
        {
            HomeScreen(
                navController,
                note = notelist,
                onRemoveNote = {
                    viewModel.removeNote(it)
                }
            )
        }
        composable(Screens.CreateScreen.route)
        {
           CreateScreen(
               navController,
               onAddNewNote = {
                   viewModel.addNote(it)
               }
           )
        }
    }
}
