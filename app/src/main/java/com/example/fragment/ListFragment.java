package com.example.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fragment.domain.Task;
import com.example.fragment.domain.TasksRepository;

import java.util.List;

public class ListFragment extends Fragment {

    public interface OnTaskClicked {
        void onTaskClicked(Task task);
    }

    private OnTaskClicked onTaskClicked;

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof OnTaskClicked) {
            onTaskClicked = (OnTaskClicked) context;
        }
    }

    @Override
    public void onDetach() {
        onTaskClicked = null;
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Task> tasks = new TasksRepository().getTasks();

        LinearLayout tasksList = view.findViewById(R.id.task);

        for (Task task : tasks) {

            View taskView = LayoutInflater.from(requireContext()).inflate(R.layout.item_note, tasksList, false);

            taskView.setOnClickListener(v -> openTaskDetail(task));

            view.findViewById(R.id.popUp_menu).setOnClickListener(v -> {
                PopupMenu menu = new PopupMenu(requireContext(), v);

                requireActivity().getMenuInflater().inflate(R.menu.activity_menu, menu.getMenu());

                menu.setOnMenuItemClickListener(item -> {
                    if (item.getItemId() == R.id.popUp_menu) {
                        Toast.makeText(requireContext(), "PopUpMenu", Toast.LENGTH_LONG).show();
                        return true;
                    }
                    return false;
                });

                menu.show();
            });

            ImageView image = taskView.findViewById(R.id.icon);
            image.setImageResource(task.getDrawRes());

            TextView title = taskView.findViewById(R.id.task);
            title.setText(task.getTitleRes());

            tasksList.addView(taskView);
        }
    }

    private void openTaskDetail(Task task) {

        if (onTaskClicked != null) {
            onTaskClicked.onTaskClicked(task);
        }
    }
}