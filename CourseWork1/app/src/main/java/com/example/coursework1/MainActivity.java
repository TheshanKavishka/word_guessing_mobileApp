package com.example.coursework1;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    String[] Months = new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    String month;
    Random random;

    TextView txtCorrectAnswer, txtRightAnswer, txtQuestionContainer;
    EditText etUserInput;
    Button btnCheck, btnShow, btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCorrectAnswer = findViewById(R.id.txtCorrectAnswer);
        txtRightAnswer = findViewById(R.id.txtRightAnswer);
        txtQuestionContainer = findViewById(R.id.txtQuestionContainer);

        etUserInput = findViewById(R.id.etUserInput);

        btnShow = findViewById(R.id.btnShow);
        btnCheck = findViewById(R.id.btnCheck);
        btnNext = findViewById(R.id.btnNext);

        random = new Random();
        month = Months[random.nextInt(Months.length)];
        txtQuestionContainer.setText(mixWords(month));


        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etUserInput.getText().toString().equals(month)){

                    Dialog dialog = new Dialog(MainActivity.this);
                    dialog.setContentView(R.layout.activity_dialogbox);
                    Button btnhide = dialog.findViewById(R.id.btnhide);
                    dialog.show();

                    btnhide.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    etUserInput.setText("");
                    month = Months[random.nextInt(Months.length)];
                    txtQuestionContainer.setText(mixWords(month));
                }
                else{
                    Toast.makeText(MainActivity.this,"You are Wrong :(",Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                month = Months[random.nextInt(Months.length)];
                txtQuestionContainer.setText(mixWords(month));

                etUserInput.setText("");
                txtCorrectAnswer.setVisibility(View.INVISIBLE);
                txtRightAnswer.setVisibility(View.INVISIBLE);
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtCorrectAnswer.setVisibility(View.VISIBLE);
                txtRightAnswer.setVisibility(View.VISIBLE);

                txtRightAnswer.setText(month);

            }
        });
    }

    private String mixWords(String word){
        List<String> words = Arrays.asList(word.split(""));

        Collections.shuffle(words);
        String mixed = "";
        for (String i : words){
            mixed += i;
        }
        return mixed;
    }
}