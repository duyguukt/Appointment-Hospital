package tr.com.example.dd201635014;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import android.view.WindowManager;
import android.widget.TextView;



public class ShowActivity extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show );
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        Intent myIntent = getIntent();
        TextView textViewAppointment = findViewById(R.id.textViewAppointment);
        textViewAppointment.setText("Doctor : \n" + myIntent.getStringExtra("doctor") +"\nDate : \n" +
                myIntent.getStringExtra("Date"));



    }
    public void bList(View view) {
        Intent intent = new Intent( ShowActivity.this, UserDetail.class);
        startActivity(intent);
        finish();
    }
}