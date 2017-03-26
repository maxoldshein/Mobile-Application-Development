package com.example.maxoldshein.gamesbymaxwelloldshein;

/**
 * Created by maxoldshein on 2/6/17.
 */

//Maxwell Oldshein
//Professor Signorile
//Mobile Application Development
//Homework 1 - Hangman

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class HangmanGame extends AppCompatActivity {
    TextView theWordSoFar;

    TextView wordDisplay;

    Intent starterIntent;

    String theWord;
    String dashesString;
    int numberOfGuesses;
    int userGuesses;

    Button aButton; Button bButton; Button cButton; Button dButton;
    Button eButton; Button fButton; Button gButton; Button hButton;
    Button iButton; Button jButton; Button kButton; Button lButton;
    Button mButton; Button nButton; Button oButton; Button pButton;
    Button qButton; Button rButton; Button sButton; Button tButton;
    Button uButton; Button vButton; Button wButton; Button xButton;
    Button yButton; Button zButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman_game);
        starterIntent = getIntent();

        //Find the elements.
        theWordSoFar = (TextView) findViewById(R.id.theWordSoFar);

        wordDisplay = (TextView) findViewById(R.id.wordDisplay);

        initialize();
    }

    public void onLetterClick(View clickedButton) {
        int buttonId = clickedButton.getId();
        Button currentButton = (Button) findViewById(buttonId);
        currentButton.setVisibility(View.INVISIBLE);
        String letter = currentButton.getText().toString().toLowerCase();

        String tempString = checkLetter(letter);

        updateDisplay(tempString);
        dashesString = tempString;
        checkWinLose();
    }

    public String checkLetter(String letter) {
        char theLetter = letter.charAt(0);

        char [] theWordArray = dashesString.toCharArray();
        int temp = 0;

        while(theWord.indexOf(theLetter, temp) >= 0) {
            int index = theWord.indexOf(theLetter, temp);
            theWordArray[index] = theLetter;
            temp = index + 1;
        }

        userGuesses++;

        String charToString = new String(theWordArray);

        return charToString;
    }

    public void checkWinLose() {
        if(dashesString.equals(theWord) && userGuesses <= numberOfGuesses) {
            wordDisplay.setText("YOU WIN, the word was " + theWord + " and you used " + userGuesses + " guesses out of " + numberOfGuesses + "!");
        } else if(userGuesses >= numberOfGuesses) {
            wordDisplay.setText("YOU LOST, the word was: " + theWord + ", you used " + userGuesses + " guesses.");
        }
    }

    public void updateDisplay(String tempString) {
        wordDisplay.setText(tempString + " | You have used " + userGuesses + " out of " + numberOfGuesses + ".");
    }

    public void onResetClick(View clickButton) {
        initialize();
    }

    private String generateWord() {
        String[] words = {"handler", "against", "horizon", "chops", "junkyard",
                "amoeba", "academy", "roast", "countryside", "children",
                "strange", "best", "drumbeat", "amnesiac", "chant", "amphibian",
                "smuggler", "fetish"};
        Random r = new Random();
        int index = r.nextInt(words.length);
        return words[index];
    }

    public String numberOfDashes(String theWord) {
        int length = theWord.length();
        String dashes = "";

        for(int i = 0; i < length; i++) {
            dashes += "-";
        }

        return dashes;
    }

    private void initialize() {
        aButton = (Button) findViewById(R.id.aButton);
        aButton.setVisibility(View.VISIBLE);
        bButton = (Button) findViewById(R.id.bButton);
        bButton.setVisibility(View.VISIBLE);
        cButton = (Button) findViewById(R.id.cButton);
        cButton.setVisibility(View.VISIBLE);
        dButton = (Button) findViewById(R.id.dButton);
        dButton.setVisibility(View.VISIBLE);
        eButton = (Button) findViewById(R.id.eButton);
        eButton.setVisibility(View.VISIBLE);
        fButton = (Button) findViewById(R.id.fButton);
        fButton.setVisibility(View.VISIBLE);
        gButton = (Button) findViewById(R.id.gButton);
        gButton.setVisibility(View.VISIBLE);
        hButton = (Button) findViewById(R.id.hButton);
        hButton.setVisibility(View.VISIBLE);
        iButton = (Button) findViewById(R.id.iButton);
        iButton.setVisibility(View.VISIBLE);
        jButton = (Button) findViewById(R.id.jButton);
        jButton.setVisibility(View.VISIBLE);
        kButton = (Button) findViewById(R.id.kButton);
        kButton.setVisibility(View.VISIBLE);
        lButton = (Button) findViewById(R.id.lButton);
        lButton.setVisibility(View.VISIBLE);
        mButton = (Button) findViewById(R.id.mButton);
        mButton.setVisibility(View.VISIBLE);
        nButton = (Button) findViewById(R.id.nButton);
        nButton.setVisibility(View.VISIBLE);
        oButton = (Button) findViewById(R.id.oButton);
        oButton.setVisibility(View.VISIBLE);
        pButton = (Button) findViewById(R.id.pButton);
        pButton.setVisibility(View.VISIBLE);
        qButton = (Button) findViewById(R.id.qButton);
        qButton.setVisibility(View.VISIBLE);
        rButton = (Button) findViewById(R.id.rButton);
        rButton.setVisibility(View.VISIBLE);
        sButton = (Button) findViewById(R.id.sButton);
        sButton.setVisibility(View.VISIBLE);
        tButton = (Button) findViewById(R.id.tButton);
        tButton.setVisibility(View.VISIBLE);
        uButton = (Button) findViewById(R.id.uButton);
        uButton.setVisibility(View.VISIBLE);
        vButton = (Button) findViewById(R.id.vButton);
        vButton.setVisibility(View.VISIBLE);
        wButton = (Button) findViewById(R.id.wButton);
        wButton.setVisibility(View.VISIBLE);
        xButton = (Button) findViewById(R.id.xButton);
        xButton.setVisibility(View.VISIBLE);
        yButton = (Button) findViewById(R.id.yButton);
        yButton.setVisibility(View.VISIBLE);
        zButton = (Button) findViewById(R.id.zButton);
        zButton.setVisibility(View.VISIBLE);

        theWord = generateWord();
        dashesString = numberOfDashes(theWord);
        numberOfGuesses = theWord.length() + 5;
        userGuesses = 0;
        wordDisplay.setText(dashesString);
    }
}
