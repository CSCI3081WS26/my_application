package com.example.myapplication.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.data.JournalEntry
import com.example.myapplication.data.Mood
import com.example.myapplication.ui.components.NatureHeader
import com.example.myapplication.ui.viewmodel.JournalViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HomeScreen(
    onCreateEntry: () -> Unit,
    viewModel: JournalViewModel = viewModel()
) {
    val entries by viewModel.getAllEntries().collectAsStateWithLifecycle(initialValue = emptyList())
    HomeScreenContent(entries = entries, onCreateEntry = onCreateEntry)
}

@Composable
fun HomeScreenContent(
    entries: List<JournalEntry>,
    onCreateEntry: () -> Unit,
    useInsets: Boolean = true
) {
    Scaffold(
        floatingActionButton = {
            LargeFloatingActionButton(
                onClick = onCreateEntry,
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ) {
                Icon(Icons.Default.Add, contentDescription = "Create Entry")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            NatureHeader(title = "Serenity Journal", useInsets = useInsets)
            
            Box(modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding())) {
                if (entries.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Your journey begins here...",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.outline
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(
                            start = 16.dp,
                            top = 16.dp,
                            end = 16.dp,
                            bottom = 16.dp
                        ),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(entries) { entry ->
                            JournalEntryItem(entry)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun JournalEntryItem(entry: JournalEntry) {
    val locale = LocalConfiguration.current.locales[0]
    val dateFormat = remember(locale) { SimpleDateFormat("MMM dd, yyyy", locale) }
    
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = entry.title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold
                )
                MoodBadge(entry.mood)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = dateFormat.format(entry.date),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.outline
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = entry.content,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 3
            )
        }
    }
}

@Composable
fun MoodBadge(mood: Mood) {
    Surface(
        color = MaterialTheme.colorScheme.secondaryContainer,
        shape = MaterialTheme.shapes.small
    ) {
        Text(
            text = mood.name,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
}
