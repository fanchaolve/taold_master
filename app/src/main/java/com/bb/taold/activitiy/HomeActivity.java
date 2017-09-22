package com.bb.taold.activitiy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;

import com.bb.taold.R;
import com.bb.taold.activitiy.login.LoginActivity;
import com.bb.taold.base.BaseActivity;
import com.bb.taold.bean.UserInfo;
import com.bb.taold.fragment.HomeFragment;
import com.bb.taold.fragment.LoanFragment;
import com.bb.taold.fragment.MyFragment;
import com.bb.taold.fragment.RepayFragment;
import com.bb.taold.pushservice.IntentService;
import com.bb.taold.pushservice.PushService;
import com.bb.taold.utils.AppManager;
import com.bb.taold.utils.CacheUtils;
import com.bb.taold.utils.Constants;
import com.bb.taold.view.UtilDialog;
import com.bb.taold.widget.BottomBar;
import com.igexin.sdk.PushManager;

import butterknife.BindView;

//import com.bb.taold.bean.EventType;


/**
 * Created by Administrator on 2016/12/20.
 */

public class HomeActivity extends BaseActivity {


    @BindView(R.id.bb)
    BottomBar bb;

    //借款页
    private LoanFragment loanFragment;
    //账单页
    private RepayFragment repayFragment;
    //我的页面
    private MyFragment myFragment;
    private UtilDialog utilDialog;

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void initView() {
        utilDialog = new UtilDialog(this).setMessage("").setDialogLister(new UtilDialog.DialogLister() {
            @Override public void onCancel() {

            }

            @Override public void onConfirm() {

            }
        });

    }


    @Override
    public void initListener() {
        bb.setListener(new BottomBar.ChangeListener() {
            @Override
            public void changeTab2(int tab) {
                final FragmentTransaction transaction = obtainFragmentTransaction();
                hideFragments(transaction);
                switch (tab) {
                    case 0:
                        if (loanFragment == null) {
                            loanFragment = new LoanFragment();
                            transaction.add(R.id.tab_content, loanFragment, HomeFragment.class.getName());
                        } else {
                            transaction.show(loanFragment);
                        }
                        break;
                    case 1:
                        if (!AppManager.getInstance().isLogin()) {
                            AppManager.getInstance().showActivity(LoginActivity.class, null);
                        }
                        if (repayFragment == null) {
                            repayFragment = new RepayFragment();
                            transaction.add(R.id.tab_content, repayFragment, RepayFragment.class.getName());
                        } else {
                            transaction.show(repayFragment);
                        }
                        break;
                    case 2:
                        if (myFragment == null) {
                            myFragment = new MyFragment();
                            transaction.add(R.id.tab_content, myFragment, MyFragment.class.getName());
                        } else {
                            transaction.show(myFragment);
                        }
                }
                transaction.commit();
            }
        });


    }

    @Override
    public void initdata() {
        bb.changeTab(0);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int give_tab = 0;
        Bundle bundle = intent.getExtras();

        if (bundle != null && bundle.containsKey("card")) {
            give_tab = bundle.getInt("card", 0);
        }
        bb.changeTab(give_tab);

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
        if (loanFragment != null) {
            transaction.hide(loanFragment);
        }
        if (repayFragment != null) {
            transaction.hide(repayFragment);
        }
        if (myFragment != null) {
            transaction.hide(myFragment);
        }
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
                return true;
            } else {
                AppManager.getInstance().AppExit(this);
            }


        }
        return super.onKeyDown(keyCode, event);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}


