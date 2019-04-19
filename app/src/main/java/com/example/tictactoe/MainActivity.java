package com.example.tictactoe;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {
private Button[][] Buttons= new Button[3][3];
private boolean player1turn=true;
private int round;
    private int player1point;
    private int player2point;
private TextView t_Player1;
private TextView t_Player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t_Player1 = findViewById(R.id.text_player1);
        t_Player2 = findViewById(R.id.text_player2);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonid = "action_button_" + i + j;
                int resid = getResources().getIdentifier(buttonid, "id", getPackageName());
                Buttons[i][j] = findViewById(resid);
                Buttons[i][j].setOnClickListener(this);
            }
        }
        Button buttonReset=findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
resetgame();
            }
        });
    }

    @Override
    public void onClick(View v) {
if(!((Button) v).getText().toString().equals("")){
    return;
}
if(player1turn){
    ((Button) v).setText("X");
}
else
{
    ((Button) v).setText("O");
}

round++;

if(checkForWin())
{

    if(player1turn)
    {

        player1win();
    }
    else
    {
        player2win();
    }
}
else if(round==9)
{
    draw();
}else
{
    player1turn=!player1turn;
}
    }



    private boolean checkForWin(){
String[][] field=new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
     field[i][j]=Buttons[i][j].getText().toString();

            }
        }
for(int i=0 ; i<3; i++){
    if(field[i][0].equals(field[i][1])&& field[i][0].equals(field[i][2])&& !field[i][0].equals("")) {

        return true;
    }
}
        for(int i=0 ; i<3; i++){
            if(field[0][i].equals(field[1][i])&& field[0][i].equals(field[2][i])&& !field[0][i].equals(""))
            {  return true;
            }
        }
            if(field[0][0].equals(field[1][1])&& field[0][0].equals(field[2][2])&& !field[0][0].equals(""))
            {  return true;
            }
        if(field[0][2].equals(field[1][1])&& field[0][2].equals(field[2][0])&& !field[0][2].equals(""))
        {  return true;
        }
        return false;
    }

    private void player1win() {

        player1point++;
        Toast.makeText(this,"1.Player wins!",Toast.LENGTH_SHORT).show();
        updateText();
        resetBoard();
    }


    private void player2win() {
        player2point++;
        Toast.makeText(this,"2.Player wins!",Toast.LENGTH_SHORT).show();
        updateText();
        resetBoard();
    }
    private void draw() {
        Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();
        resetBoard();
    }
    private void updateText() {
      t_Player1.setText("1.Player: "+player1point);
      t_Player2.setText("2.Player: "+player2point);
    }
    private void resetBoard() {
for(int i=0; i<3; i++)
{
    for(int j=0; j<3; j++)
    {
Buttons[i][j].setText("");
    }
}
round=0;
player1turn=true;
    }





    private  void resetgame()
    {
        player1point=0;
        player2point=0;
        updateText();
        resetBoard();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("round",round);
        outState.putInt("player1point",player1point);
        outState.putInt("player2point",player2point);
        outState.putBoolean("player1turn",player1turn);
    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        round=savedInstanceState.getInt("round");
        player1point=savedInstanceState.getInt("player1point");
        player2point=savedInstanceState.getInt("player2point");
        player1turn=savedInstanceState.getBoolean("player1turn");
    }
}
