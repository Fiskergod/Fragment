package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fragment.domain.Task;

public class DetailsFragment extends Fragment {

    private static final String ARG_TASK = "ARG_TASK";

    public static DetailsFragment newInstance(Task task) {
        DetailsFragment fragment = new DetailsFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_TASK, task);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView imageView = view.findViewById(R.id.icon);
        EditText title = view.findViewById(R.id.task_description);

        Task task = getArguments().getParcelable(ARG_TASK);

        imageView.setImageResource(task.getDrawRes());
        title.setText(task.getTitleRes());
    }
}
