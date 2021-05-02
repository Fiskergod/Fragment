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
import android.widget.TextView;

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

        LinearLayout tasksList = view.findViewById(R.id.tasks_list);

        for (Task task : tasks) {

            View taskView = LayoutInflater.from(requireContext()).inflate(R.layout.item_deal, tasksList, false);

            taskView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openTaskDetail(task);
                }
            });

            ImageView image = taskView.findViewById(R.id.icon);
            image.setImageResource(task.getDrawRes());

            TextView title = taskView.findViewById(R.id.task_name);
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