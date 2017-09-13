package com.bb.taold.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bb.taold.R;
import com.bb.taold.base.BaseFragment;
import com.bb.taold.widget.Line_ItemView;

import butterknife.BindView;

/**
 * ==============================================
 * <p>
 * 包名：com.bb.taold.fragment
 * <p>
 * 说明：TODO
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2017/9/10
 * <p>
 * ==============================================
 */

public class BaseInfoFragment extends BaseFragment implements View.OnClickListener{



    @BindView(R.id.item_edu)
    Line_ItemView item_edu;

    @BindView(R.id.item_address)
    Line_ItemView item_address;

    @BindView(R.id.li_company_name)
    Line_ItemView li_company_name;

    @BindView(R.id.li_company_address)
    Line_ItemView li_company_address;

    @BindView(R.id.li_tel)
    Line_ItemView li_tel;

    @BindView(R.id.li_realname)
    Line_ItemView li_realname;

    @BindView(R.id.li_relship)
    Line_ItemView li_relship;


    @BindView(R.id.li_tel2)
    Line_ItemView li_tel2;

    @BindView(R.id.li_realname2)
    Line_ItemView li_realname2;

    @BindView(R.id.li_relship2)
    Line_ItemView li_relship2;


    @BindView(R.id.tv_confirm)
    TextView tv_confirm;



    @Override
    public int getLayoutId() {
        return R.layout.fragment_baseinfo;
    }

    @Override
    public void initView() {
        li_tel.select_View();
        li_tel.setOnClickListener(this);
        li_relship.select_View();
        li_relship.setOnClickListener(this);

        li_tel2.select_View();
        li_tel2.setOnClickListener(this);
        li_relship2.select_View();
        li_relship2.setOnClickListener(this);

    }

    @Override
    protected void initdate(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.li_tel:
                break;
            case R.id.li_tel2:
                break;

            case R.id.li_relship:
                break;
            case R.id.li_relship2:
                break;
        }
    }
}
