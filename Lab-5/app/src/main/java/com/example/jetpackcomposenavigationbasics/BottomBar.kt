package com.example.jetpackcomposenavigationbasics

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBar(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object Home : BottomBar(Screens.HomeScreen.route, "Home", Icons.Default.Home)
    data object First : BottomBar(Screens.FirstScreen.route, "First", Icons.Default.Info)
    data object Second : BottomBar(Screens.SecondScreen.route, "Second", Icons.Default.Email)
}