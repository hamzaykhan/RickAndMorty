package com.hamza.rickandmorty.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.hamza.rickandmorty.R
import com.hamza.rickandmorty.navigation.BottomNavItem
import com.hamza.rickandmorty.navigation.BottomNavigationBar
import com.hamza.rickandmorty.navigation.Navigation
import com.hamza.rickandmorty.ui.theme.RickAndMortyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(
                            items = listOf(
                                BottomNavItem(
                                    name = getString(R.string.character_nav_title_str),
                                    route = "character",
                                    icon = Icons.Default.Person
                                ),
                                BottomNavItem(
                                    name = getString(R.string.episode_nav_title_str),
                                    route = "episode",
                                    icon = Icons.Default.Book
                                ),
                                BottomNavItem(
                                    name = getString(R.string.location_nav_title_str),
                                    route = "location",
                                    icon = Icons.Default.LocationOn
                                ),
                            ),
                            navController = navController,
                            onItemClick = {
                                navController.navigate(it.route)
                            }
                        )
                    }
                ) { innerPadding ->
                    // Apply the padding globally to the whole BottomNavScreensController
                    Box(modifier = Modifier.padding(innerPadding)) {
                        Navigation(navController = navController)
                    }
                }
            }
        }
    }
}