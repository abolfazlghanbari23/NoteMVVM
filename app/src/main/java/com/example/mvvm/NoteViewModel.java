package com.example.mvvm;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;


import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private NoteRepository repository;
    private LiveData<List<Note>> allNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
        allNotes = repository.getAllNotes();
    }

    public void insert(Note note, NoteAdapter adapter) {
        repository.insert(note, adapter);
    }

    public void update(Note note, NoteAdapter adapter) {
        repository.update(note, adapter);
    }

    public void delete(Note note, NoteAdapter adapter) {
        repository.delete(note, adapter);
    }

    public void deleteAllNotes(NoteAdapter adapter) {
        repository.deleteAllNotes(adapter);
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
}