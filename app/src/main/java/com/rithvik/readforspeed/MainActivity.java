package com.rithvik.readforspeed;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    Button b1;
    EditText e1;
    private int interval = 5000;
    private Handler h;
    String[] gArr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button) findViewById(R.id.button);
        e1 = (EditText) findViewById(R.id.editText);
        h = new Handler();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String given = e1.getText().toString();
                gArr = given.split(" ");
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
                    e1.setText(gArr[index]);
                    index++;
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
}
