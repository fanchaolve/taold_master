package com.bb.taold.activitiy;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;


import com.bb.taold.R;
import com.bb.taold.base.BaseActivity;
import com.bb.taold.fragment.HomeFragment;
import com.bb.taold.utils.AppManager;
import com.bb.taold.widget.BottomBar;

import butterknife.BindView;


/**
 * Created by Administrator on 2016/12/20.
 */

public class HomeActivity extends BaseActivity{


    @BindView(R.id.bb)
    BottomBar   bb;

    private HomeFragment homeFragment;
    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void initView() {

    }


    @Override
    public void initListener() {
        bb.setListener(new BottomBar.ChangeListener() {
            @Override
            public void changeTab2(int tab) {

            }
        });


    }

    @Override
    public void initdata() {
        bb.changeTab(0);
    }

    private FragmentTransaction obtainFragmentTransaction() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // 设置切换动画
//        if (index > currentTab) {
//            ft.setCustomAnimations(R.anim.slide_left_in, R.anim.slide_left_out2);
//        } else {
//            ft.setCustomAnimations(R.anim.slide_right_in2, R.anim.slide_right_out);
//        }
        return ft;
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {
//        if (homeFragment != null) {
//            transaction.hide(homeFragment);
//        }
//        if (classFragment != null) {
//            transaction.hide(classFragment);
//        }
//        if (mallFragment != null) {
//            transaction.hide(mallFragment);
//        }
//
//        if (findFragment != null) {
//            transaction.hide(findFragment);
//        }
//
//        if (mineFragment != null) {
//            transaction.hide(mineFragment);
//        }


    }

    /**
     * 实现再按一次退出提醒
     */
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 3000) {
                showTip(getString(R.string.snack_exit_once_more));
                exitTime = System.currentTimeMillis();
            } else {
                AppManager.getInstance().AppExit(this);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}


