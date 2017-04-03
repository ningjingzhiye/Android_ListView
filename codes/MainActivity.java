package com.example.yy.test_listview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button)findViewById(R.id.btn);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast toast=Toast.makeText(getApplicationContext(), "_t6ftd67yur6", Toast.LENGTH_SHORT);
                //toast.show();

                Intent intent = new Intent(MainActivity.this, MyListView.class);

                startActivity(intent);
            }
        });

    }

}
