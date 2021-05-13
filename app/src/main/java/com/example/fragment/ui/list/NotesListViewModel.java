package com.example.fragment.ui.list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fragment.domain.MockNotesRepository;
import com.example.fragment.domain.Note;
import com.example.fragment.domain.NotesRepository;

import java.util.List;

public class NotesListViewModel extends ViewModel {

    private final MutableLiveData<List<Note>> notesLiveData = new MutableLiveData<>();

    private final MutableLiveData<Note> noteAddedLiveData = new MutableLiveData<>();

    private final MutableLiveData<Integer> noteDeletedLiveData = new MutableLiveData<>();

    private final NotesRepository repository = new MockNotesRepository();

    public LiveData<List<Note>> getNotesLiveData() {
        return notesLiveData;
    }

    public void requestNotes() {
        List<Note> notes = repository.getNotes();

        notesLiveData.setValue(notes);
    }

    public void addClicked() {
        Note note = repository.addNote();

        noteAddedLiveData.setValue(note);
    }

    public LiveData<Note> getNoteAddedLiveData() {
        return noteAddedLiveData;
    }

    public void deleteClicked(int longClickedPosition) {
        repository.removeAtPosition(longClickedPosition);

        noteDeletedLiveData.setValue(longClickedPosition);

    }

    public LiveData<Integer> getNoteDeletedLiveData() {
        return noteDeletedLiveData;
    }
}
