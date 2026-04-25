package com.example.myapplication.ui

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.myapplication.navigation.CreateEntry
import com.example.myapplication.navigation.Destination
import com.example.myapplication.navigation.Home
import com.example.myapplication.ui.screens.CreateEntryScreen
import com.example.myapplication.ui.screens.HomeScreen

@Composable
fun SerenityJournalApp() {
    val backStack = rememberNavBackStack(Home)

    NavDisplay(
        backStack = backStack,
        onBack = { if (backStack.size > 1) backStack.removeAt(backStack.lastIndex) },
        entryProvider = { key ->
            when (key) {
                is Home -> NavEntry(key) {
                    HomeScreen(onCreateEntry = { backStack.add(CreateEntry) })
                }
                is CreateEntry -> NavEntry(key) {
                    CreateEntryScreen(onBack = { backStack.removeAt(backStack.lastIndex) })
                }
                else -> error("Unknown route: $key")
            }
        }
    )
}
