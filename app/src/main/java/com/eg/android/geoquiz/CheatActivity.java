package com.eg.android.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {
    private boolean mAnswer;
    private TextView mAnswerTextView;
    private Button mShowAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        mAnswer = getIntent().getBooleanExtra("answer", false);
        mAnswerTextView = findViewById(R.id.answer_text_view);
        mShowAnswer = findViewById(R.id.show_answer);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAnswer) {
                    mAnswerTextView.setText(mAnswer + "");
                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }
                setAnswerShownResult();
            }
MyRunnable r2=()->{

};

        });
    }

    public static Intent newIntent(Context packageContext, boolean answer) {
        Intent intent = new Intent(packageContext, CheatActivity.class);
        intent.putExtra("answer", answer);
        return intent;
    }
    public static boolean wasAnswerShown(Intent result) {
        return result.getBooleanExtra("answer", false);
    }

    private void setAnswerShownResult() {
        Intent data = new Intent();
        data.putExtra("SHOWN", true);
        setResult(RESULT_OK, data);
    }
    @FunctionalInterface
    public interface MyRunnable {
        public void run();
    }

}
