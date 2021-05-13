package com.example.fragment.router;

import androidx.fragment.app.FragmentManager;

import com.example.fragment.R;
import com.example.fragment.domain.Note;
import com.example.fragment.ui.edit.EditNote;
import com.example.fragment.ui.list.NotesFragment;

public class AppRouter {

    private FragmentManager fragmentManager;

    public AppRouter(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void showNotesList() {
        fragmentManager.beginTransaction()
                .replace(R.id.container, new NotesFragment())
                .commit();
    }

    public void editNote(Note note) {
        fragmentManager.beginTransaction()
                .replace(R.id.container, EditNote.newInstance(note))
                .addToBackStack(null)
                .commit();
    }
}