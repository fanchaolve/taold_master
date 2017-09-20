package com.bb.taold.utils;
import android.os.Environment;
import com.bb.taold.MyApplication;
import com.jakewharton.disklrucache.DiskLruCache;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class CacheUtils {
    private static final int _maxSize = 20971520;

    public CacheUtils() {
    }

    private static DiskLruCache openCache(String dataType) {
        try {
            File e = getDiskLruCacheDir(dataType);
            if(!e.exists()) {
                e.mkdirs();
            }

            int versionCode = DeviceUtils.getCurrVersionCode(MyApplication.getAppContext());
            DiskLruCache mDiskLruCache = DiskLruCache.open(e, versionCode, 1, 20971520L);
            return mDiskLruCache;
        } catch (IOException var4) {
            var4.printStackTrace();
            return null;
        }
    }

    public static void closeCache(DiskLruCache cache) {
        try {
            if(null != cache) {
                cache.close();
            }
        } catch (IOException var2) {
            ;
        }

    }

    public static File getDiskLruCacheDir(String dataType) {
        File cacheFile = null;
        Boolean isSDCard = Boolean.valueOf(false);
        String dirPath;
        if("mounted".equals(Environment.getExternalStorageState()) && null != MyApplication.getAppContext().getExternalCacheDir()) {
            dirPath = MyApplication.getAppContext().getExternalCacheDir().getPath().replace("cache", "medtap");
            isSDCard = Boolean.valueOf(true);
        } else {
            dirPath = MyApplication.getAppContext().getCacheDir().getPath().replace("cache", "medtap");
        }

        cacheFile = new File(dirPath + File.separator + dataType);
        cacheFile.mkdirs();
        boolean isCanWrite = cacheFile.canWrite();
        if(isSDCard.booleanValue() && !isCanWrite) {
            dirPath = MyApplication.getAppContext().getCacheDir().getPath().replace("cache", "medtap");
            cacheFile = new File(dirPath + File.separator + dataType);
        }

        return cacheFile;
    }

    public static File getDiskLruCacheDirToCache(String dataType) {
        File cacheFile = null;
        String dirPath;
        if("mounted".equals(Environment.getExternalStorageState()) && null != MyApplication.getAppContext().getExternalCacheDir()) {
            dirPath = MyApplication.getAppContext().getExternalCacheDir().getPath();
        } else {
            dirPath = MyApplication.getAppContext().getCacheDir().getPath();
        }

        cacheFile = new File(dirPath + File.separator + dataType);
        return cacheFile;
    }

    public static boolean detectSDCardAvailability() {
        boolean result = false;

        try {
            Date e = new Date();
            long times = e.getTime();
            String fileName = "/sdcard/" + times + ".test";
            File file = new File(fileName);
            result = file.createNewFile();
            file.delete();
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        return result;
    }

    public static Object getDataCache(String key) {
        DiskLruCache cache = openCache("Object");
        Object obj = null;

        try {
            String e = hashKeyForDisk(key);
            DiskLruCache.Snapshot snapShot = cache.get(e);
            if(snapShot != null) {
                InputStream is = snapShot.getInputStream(0);
                obj = toObject(toByteArray(is));
            }
        } catch (IOException var6) {
            var6.printStackTrace();
        }

        closeCache(cache);
        return obj;
    }

    public static void saveDataToDiskLruCache(String key, Object obj) {
        try {
            DiskLruCache e = openCache("Object");
            String md5Key = hashKeyForDisk(key);
            DiskLruCache.Editor editor = e.edit(md5Key);
            if(editor != null) {
                OutputStream outputStream = editor.newOutputStream(0);
                outputStream.write(toByteArray(obj));
                editor.commit();
                e.flush();
            }

            closeCache(e);
        } catch (IOException var6) {
            var6.printStackTrace();
        }

    }

    private static String hashKeyForDisk(String key) {
        String cacheKey;
        try {
            MessageDigest e = MessageDigest.getInstance("MD5");
            e.update(key.getBytes());
            cacheKey = bytesToHexString(e.digest());
        } catch (NoSuchAlgorithmException var3) {
            cacheKey = String.valueOf(key.hashCode());
        }

        return cacheKey;
    }

    private static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < bytes.length; ++i) {
            String hex = Integer.toHexString(255 & bytes[i]);
            if(hex.length() == 1) {
                sb.append('0');
            }

            sb.append(hex);
        }

        return sb.toString();
    }

    public static byte[] toByteArray(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        boolean n = false;

        int n1;
        while(-1 != (n1 = input.read(buffer))) {
            output.write(buffer, 0, n1);
        }

        return output.toByteArray();
    }

    private static byte[] toByteArray(Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ObjectOutputStream ex = new ObjectOutputStream(bos);
            ex.writeObject(obj);
            ex.flush();
            bytes = bos.toByteArray();
            ex.close();
            bos.close();
        } catch (IOException var4) {
            var4.printStackTrace();
        }

        return bytes;
    }

    public static Object toObject(byte[] bytes) {
        Object obj = null;

        try {
            ByteArrayInputStream ex = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(ex);
            obj = ois.readObject();
            ois.close();
            ex.close();
        } catch (IOException var4) {
            var4.printStackTrace();
        } catch (ClassNotFoundException var5) {
            var5.printStackTrace();
        }

        return obj;
    }
}

