package com.git.amarradi.daycounter;


import static androidx.core.util.ObjectsCompat.requireNonNull;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textview.MaterialTextView;

import java.text.SimpleDateFormat;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    MaterialButton dateone, datetwo, calculate;
    MaterialTextView tvStartDate = null, tvEndDate = null, tvResult;
    Boolean start, end;

    Snackbar snackbar;

    Long days, startlong, endlong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setLogo(R.mipmap.logo_round);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //MaterialButtons
        dateone = findViewById(R.id.date_one);
        datetwo = findViewById(R.id.date_two);
        calculate = findViewById(R.id.result);
        //MaterialTextView
        tvStartDate = findViewById(R.id.tv_Startdate);
        tvEndDate = findViewById(R.id.tv_Enddate);
        tvResult = findViewById(R.id.tvrRsult);
        start = false;
        end = false;

        //calculate



        dateone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showsStartDatePickerDialog();

            }
        });

        datetwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showsEndDatePickerDialog();
            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if (!start || !end) {
                    snackbar = Snackbar.make(findViewById(R.id.mainActivity),
                            R.string.no_date_set,
                            BaseTransientBottomBar.LENGTH_SHORT);
                    snackbar.show();
                } else {
                    if (endlong>startlong) {
                    days = endlong-startlong;
                    @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "d");
                    tvResult.setText(simpleDateFormat.format(days)+" "+getString(R.string.daysuntil));
                    } else {
                        days = startlong-endlong;
                        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "d");
                        tvResult.setText(simpleDateFormat.format(days)+" "+getString(R.string.daysuntil));

                    }
                }
            }
        });

    }

    private void showsEndDatePickerDialog() {
        @SuppressLint("ResourceType") MaterialDatePicker<Long> materialDatePicker =
                MaterialDatePicker.Builder.datePicker()
                        .setTitleText(getResources().getString(R.string.end_date))
                        .build();

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                tvEndDate.setText(materialDatePicker.getHeaderText());
                endlong = materialDatePicker.getSelection();

                end = true;


            }
        });
        materialDatePicker.show(getSupportFragmentManager(),"TAG");
    }



    private void showsStartDatePickerDialog(){
     @SuppressLint("ResourceType") MaterialDatePicker<Long> materialDatePicker =
                MaterialDatePicker.Builder.datePicker()
                        .setTitleText(getResources().getString(R.string.start_date))
                                .build();

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                tvStartDate.setText(materialDatePicker.getHeaderText());
                startlong = materialDatePicker.getSelection();
                start = true;
            }
        });
        materialDatePicker.show(getSupportFragmentManager(),"TAG");
    }


/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_setting) {
            Intent intentSetting = new Intent(this, SettingActivity.class);
            startActivity(intentSetting);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
*/

}