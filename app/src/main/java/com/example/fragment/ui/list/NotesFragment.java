package com.example.fragment.ui.list;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragment.R;
import com.example.fragment.domain.Note;
import com.example.fragment.router.AppRouter;
import com.example.fragment.router.RouterHolder;

import java.util.List;

public class NotesFragment extends Fragment {

    private NotesListViewModel viewModel;

    private NotesAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(NotesListViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = view.findViewById(R.id.toolbar);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_add) {
                    viewModel.addClicked();
                    return true;
                }
                return false;
            }
        });

        adapter = new NotesAdapter(this);

        adapter.setClickListener(note -> Toast.makeText(requireContext(), note.getTitle(), Toast.LENGTH_SHORT).show());

        if (savedInstanceState == null) {
            viewModel.requestNotes();
        }

        viewModel.getNotesLiveData().observe(getViewLifecycleOwner(), new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                adapter.addNList(notes);
            }
        });

        RecyclerView noteList = view.findViewById(R.id.note_list);

        viewModel.getNoteAddedLiveData().observe(getViewLifecycleOwner(), new Observer<Note>() {
            @Override
            public void onChanged(Note note) {
                int position = adapter.addNList(note);
                noteList.smoothScrollToPosition(position);
            }
        });

        viewModel.getNoteDeletedLiveData().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer position) {
               adapter.delete(position);
            }
        });

        noteList.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));

        noteList.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        requireActivity().getMenuInflater().inflate(R.menu.menu_context, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.action_open) {
            Toast.makeText(requireContext(), "Open", Toast.LENGTH_SHORT).show();

            return true;

        } else if (item.getItemId() == R.id.action_update) {

            if (getActivity() instanceof RouterHolder) {
                AppRouter router = ((RouterHolder)getActivity()).getRouter();

                router.editNote(adapter.getItemAt(adapter.getLongClickedPosition()));
            }

            Toast.makeText(requireContext(), "Update", Toast.LENGTH_SHORT).show();

            return true;

        } else if (item.getItemId() == R.id.action_delete) {
            viewModel.deleteClicked(adapter.getLongClickedPosition());
            Toast.makeText(requireContext(), "Delete", Toast.LENGTH_SHORT).show();

            return true;
        }

        return super.onContextItemSelected(item);
    }
}
