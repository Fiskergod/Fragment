package com.example.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.fragment.domain.Task;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements ListFragment.OnTaskClicked {

    private boolean isLandscape = false;
    private DatePickerDialog datePickerDialog;
    private Button dateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDatePicker();
        dateButton = findViewById(R.id.datePickerButton);

        // Выделенный код работает, но в эмуляторе скрывает значки главного меню. Не могу понять почему,
        // укажите на ошибку пожалуйста
        // Выдает вот такое дело:
        // E/: [ZeroHung]zrhung_get_config: Get config failed for wp[0x0102]
        // E/MemoryLeakMonitorManager: MemoryLeakMonitor.jar is not exist!
        // E/AwareLog: AtomicFileUtils: readFileLines file not exist: android.util.AtomicFile@772d1b

        /*Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_sort) {
                    Toast.makeText(MainActivity.this, "Action Sort", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if (item.getItemId() == R.id.action_info) {
                    Toast.makeText(MainActivity.this, "Action Info", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });*/

        isLandscape = getResources().getBoolean(R.bool.isLand);

        if (!isLandscape) {
            FragmentManager fragmentManager = getSupportFragmentManager();

            Fragment fragment = fragmentManager.findFragmentById(R.id.container);

            if (fragment == null) {
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new ListFragment())
                        .commit();
            }
        }
    }

    private void initDatePicker() {

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month += 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
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