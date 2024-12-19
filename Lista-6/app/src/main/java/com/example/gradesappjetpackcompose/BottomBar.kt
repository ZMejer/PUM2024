package com.example.gradesappjetpackcompose

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
    data object E1 : BottomBar(Screens.E1.route, "Listy zadań", Icons.Default.Home)
    data object E2 : BottomBar(Screens.E2.route, "Oceny", Icons.Default.Email)
}