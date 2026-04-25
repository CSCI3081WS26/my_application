package com.example.myapplication.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
import kotlinx.coroutines.flow.Flow;

@Dao
public interface JournalDao {
    @Query("SELECT * FROM journal_entries ORDER BY date DESC")
    Flow<List<JournalEntry>> getAllEntries();

    @Query("SELECT * FROM journal_entries WHERE id = :id")
    Flow<JournalEntry> getEntryById(long id);

    @Insert
    void insert(JournalEntry entry);

    @Update
    void update(JournalEntry entry);

    @Delete
    void delete(JournalEntry entry);
}
