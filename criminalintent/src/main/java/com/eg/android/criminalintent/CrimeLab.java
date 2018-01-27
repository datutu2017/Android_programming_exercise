package com.eg.android.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Datuu
 * @date on 2018/1/27.
 * @email yiyun0331@163.com
 */

public class CrimeLab {
    /**
     * 要创建单例，需创建一个带有私有构造方法及get()方法的类。如果实例已存在，
     * get()方法就直接返回它；如果实例还不存在，get()方法就会调用构造方法创建它。
     * 在设备旋转或是在fragment和activity间跳转的场景下，单例不会受到影响，
     * 而旧的fragment或activity已经不复存在了。
     * 深思熟虑：数据究竟用在哪里？用在哪里能真正解决问题？
     */
    private static CrimeLab sCrimeLab;

    private List<Crime> mCrimes;

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context) {
        mCrimes = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Crime crime = new Crime();
            crime.setTitle("Crime#" + i);
            crime.setSolved(i % 2 == 0);
            mCrimes.add(crime);
        }
    }

    public List<Crime> getCrimes() {
        return mCrimes;
    }

    public Crime getCrime(UUID id) {
        for (Crime crime : mCrimes) {
            if (crime.getId().equals(id)) {
                return crime;
            }
        }
        return null;
    }

}
