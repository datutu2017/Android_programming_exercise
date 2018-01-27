package com.eg.android.criminalintent;

import android.support.v4.app.Fragment;

public class CrimeActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeFragment();
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_fragment);
//
//        FragmentManager fm = getSupportFragmentManager();
//        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
//        /**
//         * 为什么要获取的fragment可能有了呢？前面说过，设备旋转或回收内存时，Android系统会销
//         毁CrimeActivity，而后重建时，会调用CrimeActivity.onCreate(Bundle)方法。activity被
//         销毁时，它的FragmentManager会将fragment队列保存下来。这样，activity重建时，新的
//         FragmentManager会首先获取保存的队列，然后重建fragment队列，从而恢复到原来的状态。
//         */
//        if (fragment == null) {
//            fragment = new CrimeFragment();
//            fm.beginTransaction().add(R.id.fragment_container, fragment)
//                    .commit();
//        }
//}
}
