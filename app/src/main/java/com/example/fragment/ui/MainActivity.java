package com.example.fragment.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.fragment.ListFragment;
import com.example.fragment.R;
import com.example.fragment.domain.Task;
import com.example.fragment.router.AppRouter;
import com.example.fragment.router.RouterHolder;

public class MainActivity extends AppCompatActivity implements RouterHolder {

    private AppRouter router;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        router = new AppRouter(getSupportFragmentManager());

        if (savedInstanceState == null) {
            router.showNotesList();
        }
    }

    @Override
    public AppRouter getRouter() {
        return router;
    }
}