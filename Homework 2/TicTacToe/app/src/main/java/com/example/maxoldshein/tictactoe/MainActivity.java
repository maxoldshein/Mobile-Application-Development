//Maxwell Oldshein
//Mobile Application Development
//Professor Signorile
//Homework 2

package com.example.maxoldshein.tictactoe;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int wins;
    int losses;
    int ties;

    Button topLeftButton; Button topCenterButton; Button topRightButton;
    Button middleLeftButton; Button middleCenterButton; Button middleRightButton;
    Button bottomLeftButton; Button bottomCenterButton; Button bottomRightButton;

    Button playAgain;

    TextView turnView; TextView winLossView;

    Button[][] buttonArray = new Button[3][3];

    int[][] scoreArray = new int[3][3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topLeftButton = (Button) findViewById(R.id.topLeftButton);
        topCenterButton = (Button) findViewById(R.id.topCenterButton);
        topRightButton = (Button) findViewById(R.id.topRightButton);
        middleLeftButton = (Button) findViewById(R.id.middleLeftButton);
        middleCenterButton = (Button) findViewById(R.id.middleCenterButton);
        middleRightButton = (Button) findViewById(R.id.middleRightButton);
        bottomLeftButton = (Button) findViewById(R.id.bottomLeftButton);
        bottomCenterButton = (Button) findViewById(R.id.bottomCenterButton);
        bottomRightButton = (Button) findViewById(R.id.bottomRightButton);

        playAgain = (Button) findViewById(R.id.playAgain);

        winLossView = (TextView) findViewById(R.id.winLossView);
        turnView = (TextView) findViewById(R.id.turnView);

        buttonArray[0][0] = topLeftButton;
        buttonArray[0][1] = topCenterButton;
        buttonArray[0][2] = topRightButton;
        buttonArray[1][0] = middleLeftButton;
        buttonArray[1][1] = middleCenterButton;
        buttonArray[1][2] = middleRightButton;
        buttonArray[2][0] = bottomLeftButton;
        buttonArray[2][1] = bottomCenterButton;
        buttonArray[2][2] = bottomRightButton;

        wins = 0;
        losses = 0;
        ties = 0;

        initialize();
    }

    public void onBoardClick(View clickedButton) {
        turnView.setText("");
        Button currentButton = (Button) clickedButton;
        currentButton.setText("O");
        currentButton.setTextColor(Color.rgb(0, 250, 0));
        currentButton.setClickable(false);

        int row;
        int col;

        switch (currentButton.getId()) {
            case (R.id.topLeftButton):
                row = 0;
                col = 0;
                scoreArray[row][col] = 0;
                break;
            case (R.id.topCenterButton):
                row = 0;
                col = 1;
                scoreArray[row][col] = 0;
                break;
            case (R.id.topRightButton):
                row = 0;
                col = 2;
                scoreArray[row][col] = 0;
                break;
            case (R.id.middleLeftButton):
                row = 1;
                col = 0;
                scoreArray[row][col] = 0;
                break;
            case (R.id.middleCenterButton):
                row = 1;
                col = 1;
                scoreArray[row][col] = 0;
                break;
            case (R.id.middleRightButton):
                row = 1;
                col = 2;
                scoreArray[row][col] = 0;
                break;
            case (R.id.bottomLeftButton):
                row = 2;
                col = 0;
                scoreArray[row][col] = 0;
                break;
            case (R.id.bottomCenterButton):
                row = 2;
                col = 1;
                scoreArray[row][col] = 0;
                break;
            case(R.id.bottomRightButton):
                row = 2;
                col = 2;
                scoreArray[row][col] = 0;
                break;
        }

        if (checkForWin()) {
            playerWin();
        } else {
            computerPlayerMove();
        }
    }

    public void onPlayAgainClick(View clickedButton) {
        initialize();
    }

    private void initialize() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button tempButton = buttonArray[i][j];
                tempButton.setClickable(true);
                tempButton.setText("");
                tempButton.setTextColor(Color.rgb(0, 0, 0));
            }
        }

        scoreArray[0][0] = -1;
        scoreArray[0][1] = -1;
        scoreArray[0][2] = -1;
        scoreArray[1][0] = -1;
        scoreArray[1][1] = -1;
        scoreArray[1][2] = -1;
        scoreArray[2][0] = -1;
        scoreArray[2][1] = -1;
        scoreArray[2][2] = -1;
    }

    private void computerPlayerMove() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (scoreArray[x][y] == -1) {
                    scoreArray[x][y] = 1;
                    buttonArray[x][y].setText("X");
                    buttonArray[x][y].setTextColor(Color.rgb(250, 0, 0));
                    buttonArray[x][y].setClickable(false);

                    if (checkForWin()) {
                        computerWin();
                    }

                    return;
                }
            }
        }
        tieGame();
    }

    private boolean checkForWin() {
        return (checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin());
    }

    private boolean checkRowsForWin() {
        for (int i = 0; i < 3; i++){
            if (checkRowColumn(scoreArray[i][0], scoreArray[i][1], scoreArray[i][2]) == true) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumnsForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkRowColumn(scoreArray[0][i], scoreArray[1][i], scoreArray[2][i]) == true) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonalsForWin() {
        return ((checkRowColumn(scoreArray[0][0], scoreArray[1][1], scoreArray[2][2]) == true) || (checkRowColumn(scoreArray[0][2], scoreArray[1][1], scoreArray[2][0]) == true));
    }

    private boolean checkRowColumn(int i1, int i2, int i3) {
        return ((i1 != -1) && (i1 == i2) && (i2 == i3));
    }

    private void computerWin() {
        turnView.setText("You have lost!");
        losses++;
        updateScores();
        disableAllButtons();
    }

    public void playerWin() {
        turnView.setText("You have won!");
        wins++;
        updateScores();
        disableAllButtons();
    }

    private void tieGame() {
        turnView.setText("Its a tie!");
        ties++;
        updateScores();
        disableAllButtons();
    }

    private void updateScores() {
        winLossView.setText("Wins: " + wins + ", Losses: " + losses + ", Ties: " + ties + ".");
    }

    private void disableAllButtons() {
        topLeftButton.setClickable(false);
        topCenterButton.setClickable(false);
        topRightButton.setClickable(false);
        middleLeftButton.setClickable(false);
        middleCenterButton.setClickable(false);
        middleRightButton.setClickable(false);
        bottomLeftButton.setClickable(false);
        bottomCenterButton.setClickable(false);
        bottomRightButton.setClickable(false);
    }
}
