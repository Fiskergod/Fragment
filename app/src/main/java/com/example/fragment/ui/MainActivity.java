package com.example.fragment.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.fragment.R;
import com.example.fragment.router.AppRouter;
import com.example.fragment.router.RouterHolder;

public class MainActivity extends AppCompatActivity implements RouterHolder {

    private AppRouter router;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.alert_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });

        router = new AppRouter(getSupportFragmentManager());

        if (savedInstanceState == null) {
            router.showNotesList();
        }
    }

    private void showAlertDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(R.string.alert_dialog)
                .setMessage(R.string.alert_dialog_message)
                .setIcon(R.drawable.ic_baseline_3p_24)
                .setCancelable(false)
                .setPositiveButton(R.string.positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Yes", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(R.string.negative, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "No", Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }

    @Override
    public AppRouter getRouter() {
        return router;
    }
}