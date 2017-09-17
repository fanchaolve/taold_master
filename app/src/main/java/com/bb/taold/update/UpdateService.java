package com.bb.taold.update;

import android.app.DownloadManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.webkit.MimeTypeMap;

import java.io.File;

public class UpdateService extends Service {
    private DownloadManager downloadManager;
    private long mTaskId;
    private String downloadUrl = "";

    public UpdateService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        this.downloadUrl = intent.getStringExtra("updateUrl");
        initDownload();
        Log.i("updateservice","onStartCommand");
        initDownload();
        return START_STICKY;
    }

    private void initDownload() {
        //创建下载任务,downloadUrl就是下载链接
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(downloadUrl));
        String extension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(downloadUrl));
        Log.i("getMimeTypeExtension",extension);
        //指定下载路径和下载文件名

        request.setDestinationInExternalPublicDir("/download/", "chaochao.apk").
                setTitle("夜间模式").
                setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)/*.
                setMimeType(extension)*/;
        //获取下载管理器
        downloadManager= (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        //将下载任务加入下载队列，否则不会进行下载
        mTaskId = downloadManager.enqueue(request);
        //注册广播接收者，监听下载状态
        registerReceiver(receiver,new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i("updateservice","onBind");
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            checkDownloadStatus();//检查下载状态
        }
    };
    //检查下载状态
    private void checkDownloadStatus() {
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(mTaskId);//筛选下载任务，传入任务ID，可变参数
        Cursor c = downloadManager.query(query);
        if (c.moveToFirst()) {
            int status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
            switch (status) {
                case DownloadManager.STATUS_PAUSED:
                    Log.i("w",">>>下载暂停");
                case DownloadManager.STATUS_PENDING:
                    Log.i("w",">>>下载延迟");
                case DownloadManager.STATUS_RUNNING:
                    Log.i("w",">>>正在下载");
                    break;
                case DownloadManager.STATUS_SUCCESSFUL:
                    Log.i("w",">>>下载完成");
                    //下载完成安装APK
                    String downloadPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + File.separator + "chaochao.apk";
                    installAPK(new File(downloadPath));                    break;
                case DownloadManager.STATUS_FAILED:
                    Log.i("w",">>>下载失败");
                    break;
            }
        }
    }

    //下载到本地后执行安装
    protected void installAPK(File file) {
        if (!file.exists()) return;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.parse("file://" + file.toString());
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        //在服务中开启activity必须设置flag,后面解释
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
