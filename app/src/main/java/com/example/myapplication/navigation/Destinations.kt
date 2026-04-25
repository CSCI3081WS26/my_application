package com.example.myapplication.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface Destination : NavKey

@Serializable
data object Home : Destination

@Serializable
data object CreateEntry : Destination
