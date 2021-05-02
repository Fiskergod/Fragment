package com.example.fragment.domain;

import com.example.fragment.R;

import java.util.ArrayList;
import java.util.List;

public class TasksRepository {

    public List<Task> getTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task(R.drawable.ic_account_box, R.string.contacts));
        tasks.add(new Task(R.drawable.ic_add_call, R.string.calls));
        tasks.add(new Task(R.drawable.ic_announcement, R.string.announcements));
        tasks.add(new Task(R.drawable.ic_check_circle_outline, R.string.done));
        tasks.add(new Task(R.drawable.ic_done, R.string.ok));
        return tasks;
    }
}
