package com.example.fragment.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fragment.domain.MockNotesRepository;
import com.example.fragment.domain.Note;
import com.example.fragment.domain.NotesRepository;

import java.util.List;

public class NotesListViewModel extends ViewModel {

    private final MutableLiveData<List<Note>> notesLiveData = new MutableLiveData<>();

    private final NotesRepository repository = new MockNotesRepository();

    public LiveData<List<Note>> getNotesLiveData() {
        return notesLiveData;
    }

    public void requestNotes() {
        List<Note> notes = repository.getNotes();

        notesLiveData.setValue(notes);
    }
}
