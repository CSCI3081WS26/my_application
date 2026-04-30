package com.example.myapplication.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.*

/**
 * Instrumented tests for the Room DAO.
 * These tests run on an emulator or a physical device and use an in-memory 
 * database to verify that the core data access logic is correct.
 */
@RunWith(AndroidJUnit4::class)
class JournalDaoTest {
    private lateinit var journalDao: JournalDao
    private lateinit var db: JournalDatabase

    /**
     * Set up an in-memory version of the database before each test.
     * In-memory databases are fast and ensure that tests don't 
     * interfere with the actual app data.
     */
    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, JournalDatabase::class.java).build()
        journalDao = db.journalDao()
    }

    /**
     * Clean up by closing the database after every test.
     */
    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    /**
     * Verifies that a basic journal entry can be saved and retrieved.
     * This confirms the primary 'Insert' and 'Query' functionality.
     */
    @Test
    @Throws(Exception::class)
    fun writeEntryAndReadInList() = runBlocking {
        val entry = JournalEntry("Today", "I am testing", Date(), Mood.HAPPY)
        journalDao.insert(entry)
        val allEntries = journalDao.getAllEntries().first()
        assertEquals(allEntries[0].title, entry.title)
        assertEquals(allEntries[0].mood, entry.mood)
    }

    /**
     * Verifies that the Room query correctly sorts entries by date (newest first).
     * This is essential for the chronological feed on the Home screen.
     */
    @Test
    @Throws(Exception::class)
    fun testSortingByDate() = runBlocking {
        val now = System.currentTimeMillis()
        val entry1 = JournalEntry("Old", "Old content", Date(now - 10000), Mood.CALM)
        val entry2 = JournalEntry("New", "New content", Date(now), Mood.HAPPY)
        
        journalDao.insert(entry1)
        journalDao.insert(entry2)
        
        val allEntries = journalDao.getAllEntries().first()
        assertEquals(2, allEntries.size)
        // Verify reverse chronological order (newest first)
        assertEquals("New", allEntries[0].title)
        assertEquals("Old", allEntries[1].title)
    }

    /**
     * Verifies that a journal entry can be deleted and is successfully 
     * removed from the list.
     */
    @Test
    @Throws(Exception::class)
    fun testDeleteEntry() = runBlocking {
        val entry = JournalEntry("Delete Me", "Bye", Date(), Mood.SAD)
        journalDao.insert(entry)
        
        var allEntries = journalDao.getAllEntries().first()
        assertEquals(1, allEntries.size)
        
        journalDao.delete(allEntries[0])
        
        allEntries = journalDao.getAllEntries().first()
        assertEquals(0, allEntries.size)
    }
}
