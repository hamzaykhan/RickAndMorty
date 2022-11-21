package com.hamza.rickandmorty.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hamza.rickandmorty.presentation.character_listing.CharacterListingScreen
import com.hamza.rickandmorty.presentation.episode_listing.EpisodeListingScreen
import com.hamza.rickandmorty.presentation.location_listing.LocationListingScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "character") {
        composable("character") {
            CharacterListingScreen()
        }
        composable("episode") {
            EpisodeListingScreen()
        }
        composable("location") {
            LocationListingScreen()
        }
    }
}