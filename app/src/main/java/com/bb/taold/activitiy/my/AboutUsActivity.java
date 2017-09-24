package com.bb.taold.activitiy.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bb.taold.R;
import com.bb.taold.api.PostCallback;
import com.bb.taold.api.Result_Api;
import com.bb.taold.base.BaseActivity;
import com.bb.taold.bean.UpdateInfo;
import com.bb.taold.listener.Callexts;
import com.bb.taold.update.UpdateService;
import com.bb.taold.utils.Constants;
import com.bb.taold.utils.DeviceUtils;
import com.bb.taold.widget.UpdateDialog;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;

public class AboutUsActivity extends BaseActivity {

    @BindView(R.id.btn_back)
    ImageButton mBtnBack;
    @BindView(R.id.btn_info)
    ImageButton mBtnInfo;
    @BindView(R.id.tv_right)
    TextView mTvRight;
    @BindView(R.id.iv_right)
    ImageView mIvRight;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.title)
    RelativeLayout mTitle;
    @BindView(R.id.tv_version_name)
    TextView mTvVersionName;
    @BindView(R.id.lay_check_update)
    LinearLayout mLayCheckUpdate;
    @BindView(R.id.iv_can_update)
    ImageView mIvCanUpdate;
    private PostCallback postCallback;
    private UpdateInfo mUpdateInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_about_us;
    }

    @Override
    public void initView() {
        mTvTitle.setText(R.string.about_us);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initdata() {
        postCallback = new PostCallback<BaseActivity>(this) {

            @Override public void successCallback(Result_Api api) {
                if (api.getT() instanceof UpdateInfo) {
                    mUpdateInfo = (UpdateInfo) api.getT();
                    setViewData(mUpdateInfo);
                }
            }

            @Override public void failCallback() {

            }
        };
        checkUpdateVersion(Constants.PRODUCT_CODE, Constants.DEVICE_TYPE, DeviceUtils.getCurrVersionCode(mContext) + "");
    }

    private void setViewData(UpdateInfo updateInfo) {
        if(canUpdate(updateInfo.getBuildversion())){
            mIvCanUpdate.setVisibility(View.VISIBLE);
        }else{
            mIvCanUpdate.setVisibility(View.GONE);
        }
    }

    private void checkUpdateVersion(String productCode, String devicetype, String buildversion) {
        Call<Result_Api<UpdateInfo>> call = service.queryAppRelease(productCode, devicetype, buildversion);
        Callexts.need_sessionPost(call,postCallback);
    }

    @OnClick({R.id.btn_back, R.id.lay_check_update})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.lay_check_update:
                if (mUpdateInfo!=null&&canUpdate(mUpdateInfo.getBuildversion())) {
                    showUpdateAlert();
                }
//                EBJPayUtil ebjPayUtil = new EBJPayUtil(mContext, "10001", "100100102", "8098902", "0.01", LojaDateUtils.getNow()-60000+"");
//                ebjPayUtil.startPay();

                break;
        }
    }

    private void showUpdateAlert() {
        final UpdateDialog dialog = new UpdateDialog(mContext);
        dialog.setOnPositiveListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(mContext, UpdateService.class);
                intent.putExtra("updateUrl", "http://imtt.dd.qq.com/16891/51F06FB002018975678634859A0EC654.apk");
                startService(intent);
            }
        }).setOnNegativeListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                dialog.dismiss();
            }
        }).setMemo(mUpdateInfo.getDescriptionText());
        dialog.show();
    }

    public boolean canUpdate(int romoteVersion){
        return romoteVersion>DeviceUtils.getCurrVersionCode(mContext);
    }
}
