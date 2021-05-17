package com.example.fragment.ui.list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fragment.domain.Callback;
import com.example.fragment.domain.FirestoreNotesRepository;
import com.example.fragment.domain.MockNotesRepository;
import com.example.fragment.domain.Note;
import com.example.fragment.domain.NotesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NotesListViewModel extends ViewModel {

    private final MutableLiveData<List<Note>> notesLiveData = new MutableLiveData<>();

    /*private final MutableLiveData<Note> noteAddedLiveData = new MutableLiveData<>();

    private final MutableLiveData<Integer> noteDeletedLiveData = new MutableLiveData<>();*/

    private final NotesRepository repository = new FirestoreNotesRepository();

    public LiveData<List<Note>> getNotesLiveData() {
        return notesLiveData;
    }

    public void requestNotes() {
        repository.getNotes(new Callback<List<Note>>() {
            @Override
            public void onSuccess(List<Note> value) {
                notesLiveData.setValue(value);

            }

            @Override
            public void onError(Throwable error) {

            }
        });
    }

    public void addClicked() {
        repository.addNote(UUID.randomUUID().toString(), "https://cdn.pixabay.com/photo/2016/11/14/03/26/cliff-1822484_960_720.jpg", new Callback<Note>() {
            @Override
            public void onSuccess(Note value) {
                if (notesLiveData.getValue() != null) {

                    ArrayList<Note> notes = new ArrayList<>(notesLiveData.getValue());

                    notes.add(value);

                    notesLiveData.setValue(notes);

                } else {
                    ArrayList<Note> notes = new ArrayList<>();
                    notes.add(value);

                    notesLiveData.setValue(notes);
                }
            }

            @Override
            public void onError(Throwable error) {

            }
        });

        //noteAddedLiveData.setValue(note);
    }

    /*public LiveData<Note> getNoteAddedLiveData() {
        return noteAddedLiveData;
    }*/

    public void deleteClicked(Note note) {

        repository.remove(note, new Callback<Object>() {
            @Override
            public void onSuccess(Object value) {
                if (notesLiveData.getValue() != null) {

                    ArrayList<Note> notes = new ArrayList<>(notesLiveData.getValue());

                    notes.remove(note);

                    notesLiveData.setValue(notes);
                }
            }

            @Override
            public void onError(Throwable error) {

            }
        });
        //repository.removeAtPosition(longClickedPosition);

        //noteDeletedLiveData.setValue(longClickedPosition);

    }

    /*public LiveData<Integer> getNoteDeletedLiveData() {
        return noteDeletedLiveData;
    }*/
}
