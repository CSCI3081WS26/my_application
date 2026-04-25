package com.example.myapplication.data;

import android.app.Application;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlinx.coroutines.flow.Flow;

public class JournalRepository {
    private final JournalDao journalDao;
    private final ExecutorService executorService;

    public JournalRepository(Application application) {
        JournalDatabase db = JournalDatabase.getDatabase(application);
        journalDao = db.journalDao();
        executorService = Executors.newFixedThreadPool(4);
    }

    public Flow<List<JournalEntry>> getAllEntries() {
        return journalDao.getAllEntries();
    }

    public Flow<JournalEntry> getEntryById(long id) {
        return journalDao.getEntryById(id);
    }

    public void insert(JournalEntry entry) {
        executorService.execute(() -> journalDao.insert(entry));
    }

    public void update(JournalEntry entry) {
        executorService.execute(() -> journalDao.update(entry));
    }

    public void delete(JournalEntry entry) {
        executorService.execute(() -> journalDao.delete(entry));
    }
}
