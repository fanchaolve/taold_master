package com.bb.taold.fragment;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bb.taold.R;
import com.bb.taold.activitiy.addBankCard.AddBankCardFinalActivity;
import com.bb.taold.api.PostCallback;
import com.bb.taold.api.Result_Api;
import com.bb.taold.base.BaseActivity;
import com.bb.taold.base.BaseFragment;
import com.bb.taold.bean.Contact;
import com.bb.taold.bean.DataUtils;
import com.bb.taold.bean.MapInfo;
import com.bb.taold.bean.MemberContactInfo;
import com.bb.taold.bean.UserInfo;
import com.bb.taold.bean.UserParam;
import com.bb.taold.listener.Callexts;
import com.bb.taold.utils.AppManager;
import com.bb.taold.utils.Constants;
import com.bb.taold.utils.PermissionUtil;
import com.bb.taold.widget.Line_ItemView;
import com.bb.taold.widget.NumberPickViewDialog;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import retrofit2.Call;

/**
 * 认证个人信息界面
 */
public class PersonInfoFragment extends BaseFragment implements View.OnClickListener {

    private PermissionUtil.onPermissionGentedListener listener;   //权限获取
    protected PermissionUtil permissionUtil;

    private final static int Emergency_Contact_REQUEST = 100;

    private final static int Second_Contact_REQUEST = 101;

    private Contact contact1;//联系人1

    private Contact contact2;//联系人2

    private String ship1 = "";//关系1

    private String ship2 = "";//关系2


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


    private int btnTag;//0是联系人1 ，1是联系人2

    private int dialog_Type = 0;//0学历,1关系，2关系

    private UserParam param = new UserParam();


    @Override
    public int getLayoutId() {
        return R.layout.fragment_baseinfo;
    }

    @Override
    public void initView() {

        item_edu.select_View();
        item_edu.setOnClickListener(this);

        li_tel.select_View();
        li_tel.setOnClickListener(this);
        li_relship.select_View();
        li_relship.setOnClickListener(this);

        li_tel2.select_View();
        li_tel2.setOnClickListener(this);
        li_relship2.select_View();
        li_relship2.setOnClickListener(this);

        tv_confirm.setOnClickListener(this);

    }

    @Override
    protected void initdate(Bundle savedInstanceState) {
        permissionUtil = PermissionUtil.getInstance();
        listener = new PermissionUtil.onPermissionGentedListener() {
            @Override
            public void onGented() {
                //魅族之类的手机为获取权限做的处理
                ContentResolver cr = getActivity().getContentResolver();
                Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

                if (cursor != null)
                    cursor.close();

                if (btnTag == 0) {
                    startActivityForResult(new Intent(
                            Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI), Emergency_Contact_REQUEST);
                } else {
                    startActivityForResult(new Intent(
                            Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI), Second_Contact_REQUEST);
                }
            }

            @Override
            public void onFalied() {
                ((BaseActivity) getActivity()).showTip("您关闭了“信趣贷”的通讯录访问权限，开启后才能添加联系人");
            }
        };
        permissionUtil.setListener(listener);
        getUserInfo();
    }

    /**
     * 获取通讯录联系人
     *
     * @param uri
     * @return
     */
    private Contact getContact(Uri uri) {
        ContentResolver reContentResolverol = getActivity().getContentResolver();
        Cursor cursor = getActivity().managedQuery(uri, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            Contact contact = new Contact();
            String username = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            contact.setName(username);
            String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            Cursor phone = reContentResolverol.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId,
                    null,
                    null);

            while (phone.moveToNext()) {
                String usernumber = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                usernumber = usernumber.replace("-", "");
                usernumber = usernumber.replace(" ", "");
                usernumber = usernumber.replace("+86", "");
                if (usernumber.length() < 11)
                    break;
                else
                    contact.setTelphone(usernumber);
            }
            return contact;
        } else {
            ((BaseActivity) getActivity()).showTip("请在设置中打开本App通讯录权限");
        }
        return null;

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_edu:
                dialog_Type = 0;
                showDialog(DataUtils.getMap().keySet());
                break;
            case R.id.li_tel:
                btnTag = 0;
                permissionUtil.ReadPhoneContactsTask2();
                break;
            case R.id.li_tel2:
                btnTag = 1;
                permissionUtil.ReadPhoneContactsTask2();
                break;
            case R.id.li_relship:
                dialog_Type = 1;
                showDialog(DataUtils.fristShipsMap().keySet());
                break;
            case R.id.li_relship2:
                dialog_Type = 2;
                showDialog(DataUtils.getShipsMap().keySet());
                break;
            case R.id.tv_confirm:
                if (!confirm()) {
                    return;
                }
                Call<Result_Api<String>> call = service.member_commitMemberContactInfo(param.getEducation(),
                        param.getAddress(), param.getCompany(), param.getCompanyAddress(), param.getMemberContactInfo());
                Callexts.need_sessionPost(call, new PostCallback<PersonInfoFragment>(this) {
                    @Override
                    public void successCallback(Result_Api api) {
                        Bundle bundle = new Bundle();
                        bundle.putString(Constants.ADD_CARD_FROM, Constants.FROM_AUTU);
                        AppManager.getInstance().showActivity(AddBankCardFinalActivity.class, bundle);
                        getActivity().finish();
                    }

                    @Override
                    public void failCallback() {

                    }
                });
                break;
        }
    }


    private void showDialog(Set<String> set) {

        showDialog(set.toArray(new String[]{}));

    }

    /**
     * 弹出框 选项
     *
     * @param contents
     */
    private void showDialog(String[] contents) {

        final NumberPickViewDialog dialog = new NumberPickViewDialog(getActivity());
        dialog.show();
        dialog.initData(contents);
        dialog.setOnNegativeListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.setOnPositiveListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                String key = dialog.getCurrentContent();
                if (dialog_Type == 0) {
                    item_edu.setText(key);
                    param.setEducation(DataUtils.getMap().get(key));
                } else if (dialog_Type == 1) {
                    li_relship.setText(key);
                    ship1 = DataUtils.getShipsMap().get(key);

                } else if (dialog_Type == 2) {
                    li_relship2.setText(key);
                    ship2 = DataUtils.getShipsMap().get(key);
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null)
            return;
        if (requestCode == Emergency_Contact_REQUEST && resultCode == getActivity().RESULT_OK) {
            Uri contactData = data.getData();
            contact1 = getContact(contactData);
            if (contact1 != null) {
                if (contact2 != null) {
                    if (contact1.getTelphone().equals(contact2.getTelphone())) {
                        ((BaseActivity) getActivity()).showMsg("两个联系人的号码不能相同");
                        return;
                    }
                }
                li_tel.setText(contact1.getTelphone());
                li_realname.setText(contact1.getName());


            }
        }
        if (requestCode == Second_Contact_REQUEST && resultCode == getActivity().RESULT_OK) {
            Uri contactData = data.getData();
            contact2 = getContact(contactData);
            if (contact2 != null) {
                if (contact1 != null) {
                    if (contact1.getTelphone().equals(contact2.getTelphone())) {
                        ((BaseActivity) getActivity()).showMsg("两个联系人的号码不能相同");
                        return;
                    }
                }
                li_tel2.setText(contact2.getTelphone());
                li_realname2.setText(contact2.getName());
            }
        }


    }

    /**
     * 判断必选项的非空条件
     */
    private boolean confirm() {
        if (param.getEducation().length() == 0) {
            ((BaseActivity) getActivity()).showMsg("请选择最高学历");
            return false;
        }
        if (TextUtils.isEmpty(item_address.getValue())) {
            ((BaseActivity) getActivity()).showMsg(getString(R.string.address_hint));
            return false;
        }
        param.setAddress(item_address.getValue().toString());

        if (TextUtils.isEmpty(li_company_name.getValue())) {
            ((BaseActivity) getActivity()).showMsg(getString(R.string.company_name_hint));
            return false;
        }
        param.setCompany(li_company_name.getValue().toString());
        if (TextUtils.isEmpty(li_company_address.getValue())) {
            ((BaseActivity) getActivity()).showMsg(getString(R.string.company_address_hint));
            return false;
        }
        param.setCompanyAddress(li_company_address.getValue());

        if (contact1.getTelphone().isEmpty()) {
            ((BaseActivity) getActivity()).showMsg("请选择联系人电话");
            return false;
        }

        if (li_realname.getValue().isEmpty()) {
            ((BaseActivity) getActivity()).showMsg("请填写联系人真实姓名");
            return false;
        }

        if (ship1.isEmpty()) {
            ((BaseActivity) getActivity()).showMsg("请选择联系人关系");
            return false;
        }
        contact1.setName(li_realname.getValue().toString());
        contact1.setRelativeType(ship1);


        if (contact2.getTelphone().isEmpty()) {
            ((BaseActivity) getActivity()).showMsg("请选择联系人电话");
            return false;
        }

        if (li_realname2.getValue().isEmpty()) {
            ((BaseActivity) getActivity()).showMsg("请填写联系人真实姓名");
            return false;
        }

        if (ship2.isEmpty()) {
            ((BaseActivity) getActivity()).showMsg("请选择联系人关系");
            return false;
        }
        contact2.setName(li_realname2.getValue().toString());
        contact2.setRelativeType(ship2);
        if (contact1.getTelphone().equals(contact2.getTelphone())) {
            ((BaseActivity) getActivity()).showMsg("两个联系人的号码不能相同");
            return false;
        }

        List<Contact> contacts = new ArrayList<>();
        contacts.add(contact1);
        contacts.add(contact2);
        Gson gson = new Gson();
        String json_list = gson.toJson(contacts);
        param.setMemberContactInfo(json_list);
        return true;
    }

    private void getUserInfo() {
        Call<Result_Api<UserInfo>> call = service.user_info();
        Callexts.need_sessionPost(call, new PostCallback<PersonInfoFragment>(this) {
            @Override
            public void successCallback(Result_Api api) {
                if (api.getT() instanceof UserInfo) {
                    UserInfo info = (UserInfo) api.getT();
                    if (info != null) {
                        if (info.getMap() != null) {
                            MapInfo mapInfo = info.getMap();
                            item_address.setText(mapInfo.getAddress());
                            li_company_name.setText(mapInfo.getCompany());
                            li_company_address.setText(mapInfo.getCompanyAddress());
                            param.setEducation(mapInfo.getEducation() + "");
                            item_edu.setText(DataUtils.getEduText(mapInfo.getEducation()));
                            if (mapInfo.getMemberContactInfo() != null) {
                                for (int i = 0; i < mapInfo.getMemberContactInfo().size(); i++) {
                                    MemberContactInfo mberContactInfo = mapInfo.getMemberContactInfo().get(i);
                                    if (i == 0) {
                                        contact1 = new Contact();
                                        contact1.setName(mberContactInfo.getName());
                                        contact1.setTelphone(mberContactInfo.getTelphone());
                                        ship1 = mberContactInfo.getRelativeType() + "";
                                        li_realname.setText(mberContactInfo.getName());
                                        li_tel.setText(mberContactInfo.getTelphone());
                                        li_relship.setText(DataUtils.getShipText(mberContactInfo.getRelativeType()));
                                    } else {
                                        contact2 = new Contact();
                                        contact2.setName(mberContactInfo.getName());
                                        contact2.setTelphone(mberContactInfo.getTelphone());
                                        ship2 = mberContactInfo.getRelativeType() + "";
                                        li_realname2.setText(mberContactInfo.getName());
                                        li_tel2.setText(mberContactInfo.getTelphone());
                                        li_relship2.setText(DataUtils.getShipText(mberContactInfo.getRelativeType()));

                                    }

                                }

                            }
                        }
                    }
                }
            }

            @Override
            public void failCallback() {

            }
        });
    }
}
