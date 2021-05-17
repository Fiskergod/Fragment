package com.example.fragment.domain;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class FirestoreNotesRepository implements NotesRepository {

    private static final String NOTES = "notes";
    private static final String TITLE = "title";
    private static final String IMAGE = "imageUrl";
    private static final String CREATED = "createdAt";

    private final FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    @Override
    public void getNotes(Callback<List<Note>> callback) {

        firestore.collection(NOTES)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {

                            ArrayList<Note> tmp = new ArrayList<>();

                            List<DocumentSnapshot> docs = task.getResult().getDocuments();

                            for (DocumentSnapshot doc : docs) {

                                String id = doc.getId();

                                String title = doc.getString(TITLE);
                                String image = doc.getString(IMAGE);
                                Date createdAt = doc.getDate(CREATED);

                                tmp.add(new Note(id, title, image, createdAt));
                            }

                            callback.onSuccess(tmp);

                        } else {

                            callback.onError(task.getException());

                        }
                    }
                });
    }

    @Override
    public void addNote(String title, String imageUrl, Callback<Note> callback) {

        HashMap<String, Object> data = new HashMap<>();

        Date date = new Date();

        data.put(TITLE, title);
        data.put(IMAGE, imageUrl);
        data.put(CREATED, new Date());

        firestore.collection(NOTES)
                .add(data)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {

                        if (task.isSuccessful()) {
                            callback.onSuccess(new Note(task.getResult().getId(), title, imageUrl, date));

                        } else {
                            callback.onError(task.getException());
                        }
                    }
                });
    }

    @Override
    public void remove(Note item, Callback<Object> callback) {

        firestore.collection(NOTES)
                .document(item.getId())
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            callback.onSuccess(new Object());

                        } else {
                            callback.onError(task.getException());
                        }

                    }
                });

    }
}