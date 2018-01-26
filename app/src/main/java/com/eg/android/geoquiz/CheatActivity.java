package com.eg.android.geoquiz;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CheatActivity extends AppCompatActivity {
    private boolean mAnswer;
    private TextView mAnswerTextView;
    private Button mShowAnswer;
    private boolean mIsCheater = false;
    private TextView mCountapi;
    private TextView mCount;
    private int mInt;

    //这里封装里访问此活动的方式 在需要访问的活动中调用，表面里访问这个活动需要做的准备
    public static Intent newIntent(Context packageContext, boolean answer, int count) {
        Intent intent = new Intent(packageContext, CheatActivity.class);
        intent.putExtra("answer", answer);
        intent.putExtra("count", count);
        return intent;
    }

    public static boolean wasAnswerShown(Intent result) {
        return result.getBooleanExtra("SHOWN", false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        if (savedInstanceState != null) {

            mIsCheater = savedInstanceState.getBoolean("Cheater", false);
            mInt = savedInstanceState.getInt("mInt",0);
            setAnswerShownResult(mIsCheater);
        }

        mAnswer = getIntent().getBooleanExtra("answer", false);
        mInt = getIntent().getIntExtra("count", 0);

        mAnswerTextView = findViewById(R.id.answer_text_view);
        mShowAnswer = findViewById(R.id.show_answer);
        mCountapi = findViewById(R.id.countAPI_text_view);
        mCountapi.setText("API Level" + Build.VERSION.SDK_INT);
        mCount = findViewById(R.id.count_text_view);


        if (mInt <= 2) {
            mCount.setText("剩余查看次数" + (3 - mInt));
        } else {
            mCount.setText("机会用完了");
            mShowAnswer.setVisibility(View.INVISIBLE);
        }


        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //记录是否点击了
                mIsCheater = true;
//                展示答案
                if (mAnswer) {
                    mAnswerTextView.setText(mAnswer + "");
                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }
                setAnswerShownResult(mIsCheater);
//                Build.VERSION.SDK_INT代表了设备的版本号
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//动画
                    int cx = mShowAnswer.getWidth() / 2;
                    int cy = mShowAnswer.getHeight() / 2;
                    float radius = mShowAnswer.getWidth();
                    Animator anim = ViewAnimationUtils
                            .createCircularReveal(mShowAnswer, cx, cy, radius, 0);
                    anim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            mShowAnswer.setVisibility(View.INVISIBLE);
                        }
                    });
                    anim.start();
                } else {
                    mShowAnswer.setVisibility(View.INVISIBLE);
                }
            }
        });


    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean("Cheater", mIsCheater);
        savedInstanceState.putInt("mInt", mInt);

    }

    private void setAnswerShownResult(boolean mCheater) {
        if (mCheater) {
            Intent data = new Intent();
            data.putExtra("SHOWN", true);
//       2   调用setResult(int, Intent)方法将结果代码以及intent打包
            setResult(RESULT_OK, data);
        }

    }

}


//在用户按后退键回到QuizActivity时，ActivityManager调用父activity的以下方法：
//        protected void onActivityResult(int requestCode, int resultCode, Intent data)
//        该方法的参数来自QuizActivity的原始请求代码以及传入setResult(int，Intent)方法
//        的结果代码和intent。
//    MyRunnable r2 = () -> {
//    };

//    @FunctionalInterface
//    public interface MyRunnable {
//        void run();
//    }