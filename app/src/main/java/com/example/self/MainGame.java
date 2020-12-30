package com.example.self;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainGame extends AppCompatActivity {

    //take all buttons into an Array
    String[] buttonColours = {"red", "blue", "green", "yellow"};

    //creating new Array for game pattern and user clicked patterns
    List<String> gamePattern = new ArrayList<String>();
    List<String> userClickedPattern = new ArrayList<String>();

    //Assign of image and levels
    private ImageView img;
    private TextView level;

    //Animation FadeIn And FadeOut
    private Animation fadein;
    private Animation fadeout;

    //Assign buttons
    Button red;
    Button yellow;
    Button blue;
    Button green;

    //starting stage level as zero
    boolean started = false;
    int levelcount = 0;

    //variables for sounds
    private SoundPool mSoundPool;
    private int red_sound;
    private int yellow_sound;
    private int green_sound;
    private int blue_sound;
    private int wrong_sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        img = (ImageView) findViewById(R.id.maingameimgview);
        level = (TextView) findViewById(R.id.level);

        //set buttons
        red = (Button) findViewById(R.id.red);
        blue = (Button) findViewById(R.id.blue);
        yellow = (Button) findViewById(R.id.yellow);
        green = (Button) findViewById(R.id.green);

        //Animations
        fadein = (Animation) AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        fadeout = (Animation) AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);


        //img listener to change image and levels
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!started) {
                    img.setImageResource(R.drawable.changedimg);
                    level.setText("Level: " + levelcount);
                    nextSequence();
                    started = true;
                }
            }
        });

        //create new sound pool
        mSoundPool = new SoundPool(5 ,AudioManager.STREAM_MUSIC, 0);
        red_sound = mSoundPool.load(getApplicationContext(), R.raw.red,1);
        green_sound = mSoundPool.load(getApplicationContext(), R.raw.green, 1);
        yellow_sound = mSoundPool.load(getApplicationContext(), R.raw.yellow, 1);
        blue_sound = mSoundPool.load(getApplicationContext(), R.raw.blue, 1);
        wrong_sound = mSoundPool.load(getApplicationContext(), R.raw.wrong, 1);
    }


        //play sound
        public void playSound (String playcolor){
            if (playcolor == "red") {
                mSoundPool.play(red_sound, 1.0f, 1.0f, 0, 0, 1.0f);
            } else if (playcolor == "blue") {
                mSoundPool.play(blue_sound, 1.0f, 1.0f, 0, 0, 1.0f);
            } else if (playcolor == "green") {
                mSoundPool.play(green_sound, 1.0f, 1.0f, 0, 0, 1.0f);
            } else if (playcolor == "yellow") {
                mSoundPool.play(yellow_sound, 1.0f, 1.0f, 0, 0, 1.0f);
            } else {
                mSoundPool.play(wrong_sound, 1.0f, 1.0f, 0, 0, 1.0f);
            }
        }

    //Starting code to start game
    public void nextSequence(){

        userClickedPattern = new ArrayList<String>();
        levelcount++;
        level.setText("Level: "+levelcount);

        Random random = new Random();
        int randomnum = random.nextInt(4);
        String randomChosenColour = buttonColours[randomnum];
        gamePattern.add(randomChosenColour);
        animations(randomChosenColour);
        playSound(randomChosenColour);
    }


    //Animating buttons
    public void animations (String color){
        if (color == "red") {
             red.startAnimation(fadein);
             red.startAnimation(fadeout);
        }
        else if (color == "blue") {
               blue.startAnimation(fadein);
                blue.startAnimation(fadeout);
        }
        else if (color == "green") {
              green.startAnimation(fadein);
              green.startAnimation(fadeout);
        }
        else{
            yellow.startAnimation(fadein);
            yellow.startAnimation(fadeout);
       }
    }

        //On click play sounds

    public void playRed (View V){
        if(started) {
            String userChosenColor = "red";
            playSound(userChosenColor);
            animations(userChosenColor);
            userClickedPattern.add(userChosenColor);
            checkAnswer(userClickedPattern.size() - 1);
        }
    }
    public void playBlue (View V){
        if(started) {
            String userChosenColor = "blue";
            playSound(userChosenColor);
            animations(userChosenColor);
            userClickedPattern.add(userChosenColor);
            checkAnswer(userClickedPattern.size() - 1);
        }
    }
    public void playGreen (View V){
        if(started) {
            String userChosenColor = "green";
            playSound(userChosenColor);
            animations(userChosenColor);
            userClickedPattern.add(userChosenColor);
            checkAnswer(userClickedPattern.size() - 1);
        }
    }
    public void playYellow (View V){
        if(started) {
            String userChosenColor = "yellow";
            playSound(userChosenColor);
            animations(userChosenColor);
            userClickedPattern.add(userChosenColor);
            checkAnswer(userClickedPattern.size() - 1);
        }
    }

    //Checking answers
    public void checkAnswer(int currentLevel) {
        if (gamePattern.get(currentLevel) == userClickedPattern.get(currentLevel)) {

           System.out.println("success");

            if (userClickedPattern.size() == gamePattern.size()){
                new android.os.Handler().postDelayed(
                        new Runnable() {
                            @Override
                            public void run() {
                                nextSequence();
                            }
                        },1000);
            }

        }
        else {

            System.out.println("Wrong");

            playSound("wrong");

            endgame();
        }
    }

    public void endgame() {
        Intent intent = new Intent(this, endGame.class);
        intent.putExtra("Level",levelcount);
        startActivity(intent);
    }

}