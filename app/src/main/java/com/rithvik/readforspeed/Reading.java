package com.rithvik.readforspeed;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Reading extends AppCompatActivity {

    Button b2;
    EditText e2;
    private int interval;
    private Handler h;
    String[] gArr;
    Spinner speedChoice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);
        h = new Handler();
        b2 = (Button) findViewById(R.id.button2);

        e2 = (EditText) findViewById(R.id.editText2);
        speedChoice = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter =  new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.speed));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        speedChoice.setAdapter(adapter);


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String given = e2.getText().toString();
                gArr = given.split(" ");
                int speedPos = speedChoice.getSelectedItemPosition();
                double speed = giveSpeed(speedPos);
                interval = (int) ((60 / speed) * 1000);
                startRepeatingTask();
            }
        });
    }
    int index = 0;

    Runnable sc = new Runnable() {
        @Override
        public void run() {
            try {
                if (index < gArr.length) {
                    e2.setText(gArr[index]);
                    index++;
                }
                else {
                    e2.clearComposingText();
                }
            }
            finally {
                h.postDelayed(sc, interval);
            }
        }
    };

    @Override
    public void onDestroy() {

        super.onDestroy();
        stopRepeatingTask();
    }

    void startRepeatingTask() {
        sc.run();
    }

    void stopRepeatingTask() {
        h.removeCallbacks(sc);
    }

    double giveSpeed (int index) {
        switch (index) {
            case 1: return 400.0;
            case 2: return 500.0;
            case 3: return 600.0;
            case 4: return 700.0;
            case 5: return 800.0;
            case 6: return 900.0;
            case 7: return 1000.0;
        }
        return index * 1.0;
    }
}
