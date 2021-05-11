package com.example.fragment.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragment.DetailsFragment;
import com.example.fragment.ListFragment;
import com.example.fragment.R;
import com.example.fragment.domain.Note;
import com.example.fragment.domain.Task;

import java.util.Calendar;
import java.util.List;

public class NotesListActivity extends AppCompatActivity implements ListFragment.OnTaskClicked {

    private NotesListViewModel viewModel;

    private boolean isLandscape = false;

    private DatePickerDialog datePickerDialog;
    private Button dateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NotesAdapter adapter = new NotesAdapter();

        adapter.setClickListener(new NotesAdapter.onNoteClicked() {
            @Override
            public void onNoteClicked(Note note) {
                Toast.makeText(NotesListActivity.this, note.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

        viewModel = new ViewModelProvider(this).get(NotesListViewModel.class);

        if (savedInstanceState == null) {
            viewModel.requestNotes();
        }

        viewModel.getNotesLiveData().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                adapter.addNList(notes);
                adapter.notifyDataSetChanged();
            }
        });

        /*initDatePicker();
        dateButton = findViewById(R.id.datePickerButton);*/

        RecyclerView noteList = findViewById(R.id.note_list);

        noteList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        noteList.setAdapter(adapter);

        //adapter.addNList();



        /*isLandscape = getResources().getBoolean(R.bool.isLand);

        if (!isLandscape) {
            FragmentManager fragmentManager = getSupportFragmentManager();

            Fragment fragment = fragmentManager.findFragmentById(R.id.container);

            if (fragment == null) {
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new ListFragment())
                        .commit();
            }
        }*/
    }

    private void initDatePicker() {

        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, day) -> {
            month += 1;
            String date = makeDateString(day, month, year);
            dateButton.setText(date);
        };

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }

    private String makeDateString(int day, int month, int year) {
        return day + " " + month + " " + year;
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }

    @Override
    public void onTaskClicked(Task task) {

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (isLandscape) {

            fragmentManager.beginTransaction()
                    .replace(R.id.details_fragment, DetailsFragment.newInstance(task))
                    .commit();
        } else {

            fragmentManager.beginTransaction()
                    .replace(R.id.container, DetailsFragment.newInstance(task))
                    .addToBackStack(null)
                    .commit();
        }
    }
}