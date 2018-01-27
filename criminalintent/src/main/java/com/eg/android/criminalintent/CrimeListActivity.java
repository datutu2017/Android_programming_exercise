package com.eg.android.criminalintent;

import android.support.v4.app.Fragment;

/**
 * @author Datuu
 * @date on 2018/1/27.
 * @email yiyun0331@163.com
 */

public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }

}
