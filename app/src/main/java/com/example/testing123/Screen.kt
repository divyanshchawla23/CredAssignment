package com.example.testing123

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Sheet1 : Screen("sheet1")
    object Sheet2 : Screen("sheet2")
    object Sheet3 : Screen("sheet3")
}