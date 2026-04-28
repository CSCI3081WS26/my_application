package com.example.myapplication.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.data.JournalEntry
import com.example.myapplication.data.Mood
import com.example.myapplication.ui.components.NatureHeader
import com.example.myapplication.ui.viewmodel.JournalViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateEntryScreen(
    onBack: () -> Unit,
    viewModel: JournalViewModel = viewModel()
) {
    CreateEntryScreenContent(
        onBack = onBack,
        onSave = { entry ->
            viewModel.insert(entry)
            onBack()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateEntryScreenContent(
    onBack: () -> Unit,
    onSave: (JournalEntry) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var mood by remember { mutableStateOf(Mood.CALM) }
    var date by remember { mutableLongStateOf(System.currentTimeMillis()) }
    
    var showDatePicker by remember { mutableStateOf(false) }
    var expandedMood by remember { mutableStateOf(false) }

    val locale = LocalConfiguration.current.locales[0]
    val dateFormat = remember(locale) { SimpleDateFormat("MMM dd, yyyy", locale) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("New Entry") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            if (title.isNotBlank()) {
                                onSave(JournalEntry(title, content, Date(date), mood))
                            }
                        },
                        enabled = title.isNotBlank()
                    ) {
                        Icon(Icons.Default.Check, contentDescription = "Save")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            NatureHeader(
                title = "Reflection",
                imageUrl = "https://images.unsplash.com/photo-1441974231531-c6227db76b6e?q=80&w=1200&h=450&auto=format&fit=crop",
                useInsets = false
            )

            Column(modifier = Modifier.padding(16.dp)) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = dateFormat.format(Date(date)),
                        onValueChange = {},
                        label = { Text("Date") },
                        modifier = Modifier.weight(1f),
                        readOnly = true,
                        trailingIcon = {
                            IconButton(onClick = { showDatePicker = true }) {
                                Icon(Icons.Default.DateRange, contentDescription = "Select Date")
                            }
                        }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                ExposedDropdownMenuBox(
                    expanded = expandedMood,
                    onExpandedChange = { expandedMood = !expandedMood },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = mood.name,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Mood") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedMood) },
                        modifier = Modifier
                            .menuAnchor(MenuAnchorType.PrimaryNotEditable, true)
                            .fillMaxWidth()
                    )
                    ExposedDropdownMenu(
                        expanded = expandedMood,
                        onDismissRequest = { expandedMood = false }
                    ) {
                        Mood.values().forEach { selectionOption ->
                            DropdownMenuItem(
                                text = { Text(selectionOption.name) },
                                onClick = {
                                    mood = selectionOption
                                    expandedMood = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = content,
                    onValueChange = { content = it },
                    label = { Text("How was your day?") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 150.dp),
                    minLines = 5
                )
            }
        }
    }

    if (showDatePicker) {
        val datePickerState = rememberDatePickerState(initialSelectedDateMillis = date)
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    date = datePickerState.selectedDateMillis ?: date
                    showDatePicker = false
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}
