package com.example.myapplication.ui

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.screens.HomeScreenContent
import com.example.myapplication.ui.screens.CreateEntryScreenContent
import org.junit.Rule
import org.junit.Test

/**
 * UI tests for the Jetpack Compose screens.
 * These tests verify visual components, layout, and user interaction logic.
 */
class JournalUiTest {

    /**
     * Rule required to test Compose UI components.
     */
    @get:Rule
    val composeTestRule = createComposeRule()

    /**
     * Verifies that the Home screen displays a welcoming message when there are no entries.
     */
    @Test
    fun homeScreen_emptyState_showsMessage() {
        composeTestRule.setContent {
            MyApplicationTheme {
                HomeScreenContent(entries = emptyList(), onCreateEntry = {})
            }
        }

        composeTestRule.onNodeWithText("Your journey begins here...").assertIsDisplayed()
    }

    /**
     * Verifies the validation logic on the Create Entry screen.
     * The "Save" button should be disabled if the title is empty.
     */
    @Test
    fun createEntryScreen_saveButtonDisabled_whenTitleEmpty() {
        composeTestRule.setContent {
            MyApplicationTheme {
                CreateEntryScreenContent(onBack = {}, onSave = {})
            }
        }

        // Initially empty, save icon should be disabled (content description is used for the icon)
        composeTestRule.onNodeWithContentDescription("Save").assertIsNotEnabled()

        // Type a title into the text field
        composeTestRule.onNodeWithText("Title").performTextInput("Valid Title")

        // Once a title is provided, the Save button should become enabled
        composeTestRule.onNodeWithContentDescription("Save").assertIsEnabled()
    }

    /**
     * Verifies that all required input fields are present and visible on the entry composer.
     */
    @Test
    fun createEntryScreen_hasAllFields() {
        composeTestRule.setContent {
            MyApplicationTheme {
                CreateEntryScreenContent(onBack = {}, onSave = {})
            }
        }

        // Check for the presence of all core form elements
        composeTestRule.onNodeWithText("Title").assertIsDisplayed()
        composeTestRule.onNodeWithText("Date").assertIsDisplayed()
        composeTestRule.onNodeWithText("Mood").assertIsDisplayed()
        composeTestRule.onNodeWithText("How was your day?").assertIsDisplayed()
    }
}
