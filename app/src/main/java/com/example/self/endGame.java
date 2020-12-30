package com.example.self;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class endGame extends AppCompatActivity {

    Button restart;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        restart = (Button) findViewById(R.id.restart);
        text = (TextView) findViewById(R.id.textView3);

        //Initialize Adds
        Bundle levelCount = getIntent().getExtras();

        text.setText("Sad! your jerry was in Danger :( at Level "+levelCount.get("Level"));

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewGame();
            }
        });
    }

    public void openNewGame() {
        Intent intent = new Intent(this, MainGame.class);
        startActivity(intent);
    }
}