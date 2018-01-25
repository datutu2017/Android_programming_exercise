package com.eg.android.geoquiz;

/**
 * @author 大土土
 * @date on 2018/1/3.
 * @email yiyun0331@163.com
 */

public class Question {
    private int mTextResId;
    private boolean mAnswer;
    private boolean mAnswerend;

    public Question(int textResId, boolean answer) {
        mTextResId = textResId;
        mAnswer = answer;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswer() {
        return mAnswer;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswer = answerTrue;
    }

    public boolean isAnswerend() {
        return mAnswerend;
    }

    public void setAnswerend(boolean answerend) {
        mAnswerend = answerend;
    }
}
