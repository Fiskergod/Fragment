package com.example.fragment.domain;

import java.util.List;

public interface NotesRepository {

    List<Note> getNotes();

    Note addNote();

    void removeAtPosition(int longClickedPosition);
}
