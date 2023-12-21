package com.dicoding.definderapps.ui.navigation

import androidx.compose.ui.graphics.painter.Painter

data class NavigationItem(
    val icon: Painter,
    val iconFilled:Painter,
    val screen: Screen
)