package com.example.myapplication.ui.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import com.example.myapplication.data.JournalEntry;
import com.example.myapplication.data.JournalRepository;
import java.util.List;
import kotlinx.coroutines.flow.Flow;

public class JournalViewModel extends AndroidViewModel {
    private final JournalRepository repository;
    private final Flow<List<JournalEntry>> allEntries;

    public JournalViewModel(@NonNull Application application) {
        super(application);
        repository = new JournalRepository(application);
        allEntries = repository.getAllEntries();
    }

    public Flow<List<JournalEntry>> getAllEntries() {
        return allEntries;
    }

    public void insert(JournalEntry entry) {
        repository.insert(entry);
    }
    
    public void delete(JournalEntry entry) {
        repository.delete(entry);
    }
}
