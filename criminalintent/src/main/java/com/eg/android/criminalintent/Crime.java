package com.eg.android.criminalintent;

import java.util.Date;
import java.util.UUID;

/**
 * @author Datuu
 * @date on 2018/1/27.
 * @email yiyun0331@163.com
 */

public class Crime {
    private UUID mId;
    private String mTitle;
    private boolean mSolved;
    private Date mDate;

    public Crime() {
        mId = UUID.randomUUID();
        mDate = new Date();

    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }
}
