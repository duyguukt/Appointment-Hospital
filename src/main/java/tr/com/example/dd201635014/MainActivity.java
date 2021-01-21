package tr.com.example.dd201635014;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    EditText editTextDate;
    EditText editTextTime;
    Spinner doctor;

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth+"."+(monthOfYear+1)+"."+year;
        editTextDate.setText(date);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        Intent myIntent = getIntent(); // gets the previously created intent  for selected information .
        TextView textView = findViewById(R.id.textViewAdi);
        textView.setText(myIntent.getStringExtra("KullaniciAdi"));
        Button buttonAppointment = findViewById(R.id.buttonAppointment);
        Button buttonDateSec = findViewById(R.id.buttonDateSec);
        Button buttonTimeSec = findViewById(R.id.buttonTimeSec);

        doctor = findViewById(R.id.doctor);
        editTextDate = findViewById(R.id.editTextDate);
        editTextTime = findViewById(R.id.editTextTime);

        buttonAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(editTextTime.getText()) && !TextUtils.isEmpty(editTextDate.getText()) ) {
                    Intent myIntent = new Intent(MainActivity.this, ShowActivity.class);
                    myIntent.putExtra("Date",editTextDate.getText() + " " + editTextTime.getText());
                    myIntent.putExtra("doctor" ,doctor.getSelectedItem().toString());
                    MainActivity.this.startActivity(myIntent);
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Please Enter your select.");
                    builder.show();
                }
            }
        });

        buttonDateSec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        MainActivity.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)

                );
                dpd.show(getSupportFragmentManager(), "Datepickerdialog");
            }
        });

        buttonTimeSec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog dia = TimePickerDialog.newInstance(MainActivity.this, true);
                dia.show(getSupportFragmentManager(), "Timepickerdialog");
            }
        });


    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        editTextTime.setText(hourOfDay + ":"+minute);
    }
}
