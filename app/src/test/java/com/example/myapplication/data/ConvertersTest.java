package com.example.myapplication.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import java.util.Date;

/**
 * Unit tests for the Room TypeConverters.
 * These tests ensure that custom data types (Date and Mood) are correctly 
 * transformed into database-compatible formats (Long and String) and back.
 */
public class ConvertersTest {

    /**
     * Verifies that a Date object is correctly converted into a Long timestamp.
     * Also checks that null inputs are handled gracefully.
     */
    @Test
    public void testDateToTimestamp() {
        Long timestamp = 123456789L;
        Date date = new Date(timestamp);
        assertEquals(timestamp, Converters.dateToTimestamp(date));
        assertNull(Converters.dateToTimestamp(null));
    }

    /**
     * Verifies that a Long timestamp is correctly converted back into a Date object.
     * Ensures data integrity when reading from the database.
     */
    @Test
    public void testFromTimestamp() {
        Long timestamp = 123456789L;
        Date date = Converters.fromTimestamp(timestamp);
        assertEquals(timestamp.longValue(), date.getTime());
        assertNull(Converters.fromTimestamp(null));
    }

    /**
     * Verifies that the Mood enum is correctly converted into its String representation.
     * This is necessary for persisting enums in SQLite.
     */
    @Test
    public void testMoodToString() {
        assertEquals("HAPPY", Converters.moodToString(Mood.HAPPY));
        assertEquals("CALM", Converters.moodToString(Mood.CALM));
        assertNull(Converters.moodToString(null));
    }

    /**
     * Verifies that a String value is correctly converted back into the corresponding Mood enum.
     * Ensures the correct mood is restored when loading a journal entry.
     */
    @Test
    public void testFromString() {
        assertEquals(Mood.HAPPY, Converters.fromString("HAPPY"));
        assertEquals(Mood.PEACEFUL, Converters.fromString("PEACEFUL"));
        assertNull(Converters.fromString(null));
    }
}
