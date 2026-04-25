package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.data.JournalEntry
import com.example.myapplication.data.Mood
import com.example.myapplication.ui.SerenityJournalApp
import com.example.myapplication.ui.screens.HomeScreenContent
import com.example.myapplication.ui.screens.CreateEntryScreenContent
import com.example.myapplication.ui.theme.MyApplicationTheme
import java.util.Date

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                SerenityJournalApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MyApplicationTheme {
        HomeScreenContent(
            entries = listOf(
                JournalEntry("Morning Walk", "The forest was beautiful today.", Date(), Mood.PEACEFUL),
                JournalEntry("Productive Day", "Finished all my tasks early.", Date(), Mood.HAPPY)
            ),
            onCreateEntry = {},
            useInsets = false
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CreateEntryPreview() {
    MyApplicationTheme {
        CreateEntryScreenContent(onBack = {}, onSave = {})
    }
}
