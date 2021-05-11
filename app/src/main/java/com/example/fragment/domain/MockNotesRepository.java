package com.example.fragment.domain;

import java.util.ArrayList;
import java.util.List;

public class MockNotesRepository implements NotesRepository {
    @Override
    public List<Note> getNotes() {
        ArrayList<Note> notes = new ArrayList<>();

        notes.add(new Note("id1", "Note One", "https://cdn.pixabay.com/photo/2020/08/18/06/06/extreme-5497194_960_720.jpg"));
        notes.add(new Note("id2", "Note Two", "https://cdn.pixabay.com/photo/2017/02/27/08/50/cyclone-2102397_960_720.jpg"));
        notes.add(new Note("id3", "Note Three", "https://cdn.pixabay.com/photo/2016/11/14/03/26/cliff-1822484_960_720.jpg"));
        notes.add(new Note("id4", "Note Four", "https://cdn.pixabay.com/photo/2014/08/15/11/29/beach-418742_960_720.jpg"));
        notes.add(new Note("id5", "Note Five", "https://cdn.pixabay.com/photo/2014/12/15/17/16/boardwalk-569314_960_720.jpg"));
        notes.add(new Note("id6", "Note Six", "https://cdn.pixabay.com/photo/2014/05/03/00/34/pelicans-336583_960_720.jpg"));
        notes.add(new Note("id7", "Note Seven", "https://cdn.pixabay.com/photo/2013/12/17/20/10/bubbles-230014_960_720.jpg"));
        notes.add(new Note("id8", "Note Eight", "https://cdn.pixabay.com/photo/2015/06/28/14/10/soap-bubble-824558_960_720.jpg"));
        notes.add(new Note("id9", "Note Nine", "https://cdn.pixabay.com/photo/2017/01/06/20/43/soap-bubble-1958841_960_720.jpg"));
        notes.add(new Note("id10", "Note Ten", "https://cdn.pixabay.com/photo/2016/02/19/11/36/microphone-1209816_960_720.jpg"));

        return notes;
    }
}
