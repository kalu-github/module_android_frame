package lib.kalu.frame.mvvm.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public final class ApkUtil {

    private static String PACKAGE_ARCHIVE = "application/vnd.android.package-archive";
    private static String PACKAGE = "package:";
    private static String AUTHORITY = ".frame-upgrade";

    public static boolean installApk(@NonNull Context context, @NonNull String apkPath) {
        try {
            if (null == context)
                throw new Exception("context error: null");
            String applicationId = context.getPackageName();
            if (null == applicationId || applicationId.length() == 0)
                throw new Exception("applicationId error: " + applicationId);
            if (null == apkPath || apkPath.length() == 0)
                throw new Exception("apkPath error: " + apkPath);
            File file = new File(apkPath);
            if (!file.exists())
                throw new Exception("apkFile error: not exists");
            Intent intent = new Intent(Intent.ACTION_VIEW);
            // 8.0
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                MvpUtil.logE("ApkUtil => installApk => android8.0");
                boolean canRequestPackageInstalls = context.getPackageManager().canRequestPackageInstalls();
                MvpUtil.logE("ApkUtil => installApk => android8.0 => canRequestPackageInstalls = " + canRequestPackageInstalls);
                if (canRequestPackageInstalls) {
                    intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setDataAndType(FileProvider.getUriForFile(context, applicationId + AUTHORITY, file), PACKAGE_ARCHIVE);
                } else {
                    intent.setAction(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
                    intent.setData(Uri.parse(PACKAGE + context.getPackageName()));
                }
            }
            // 7.0
            else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                MvpUtil.logE("ApkUtil => installApk => android7.0");
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setDataAndType(FileProvider.getUriForFile(context, applicationId + AUTHORITY, file), PACKAGE_ARCHIVE);
            } else {
                MvpUtil.logE("ApkUtil => installApk => android6.0");
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                intent.setDataAndType(Uri.fromFile(file), PACKAGE_ARCHIVE);
            }
            context.startActivity(intent);
            return true;
        } catch (Exception e) {
            MvpUtil.logE("ApkUtil => installApk => " + e.getMessage());
            return false;
        }
    }

    public static String downloadApk(Context context, String apkUrl) {
        try {
            if (null == context)
                throw new Exception("error: context null");
            if (null == apkUrl || apkUrl.length() == 0)
                throw new Exception("error: apkUrl = " + apkUrl);
            //统一资源
            URL url = new URL(apkUrl);
            //打开链接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //设置链接超时
            connection.setConnectTimeout(4000);
            //设置允许得到服务器的输入流,默认为true可以不用设置
            connection.setDoInput(true);
            //设置允许向服务器写入数据，一般get方法不会设置，大多用在post方法，默认为false
            connection.setDoOutput(true);//此处只是为了方法说明
            //设置请求方法
            connection.setRequestMethod("GET");
            //设置请求的字符编码
            connection.setRequestProperty("Charset", "utf-8");
            //设置connection打开链接资源
            connection.connect();
            //得到链接的响应码 200为成功
            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK)
                throw new Exception("error: responseCode = " + responseCode);
            File externalFilesDir = context.getExternalFilesDir(null);
            if (null == externalFilesDir) {
                externalFilesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                if (null == externalFilesDir) {
                    throw new Exception("externalFilesDir error: null");
                } else if (!externalFilesDir.exists()) {
                    externalFilesDir.mkdirs();
                }
            }
            String filesDirAbsolutePath = externalFilesDir.getAbsolutePath();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(filesDirAbsolutePath);
            stringBuilder.append("/Android/data/");
            stringBuilder.append("update.apk");
            String apkPath = stringBuilder.toString();
            File apkFile = new File(apkPath);
            if (apkFile.exists()) {
                apkFile.delete();
            }
            //创建一个文件输出流
            FileOutputStream outputStream = new FileOutputStream(apkFile);
            //得到服务器响应的输入流
            InputStream inputStream = connection.getInputStream();
            //获取请求的内容总长度
            int contentLength = connection.getContentLength();
            //创建缓冲输入流对象，相对于inputStream效率要高一些
            BufferedInputStream bfi = new BufferedInputStream(inputStream);
            //此处的len表示每次循环读取的内容长度
            int len;
            //已经读取的总长度
            int totle = 0;
            //bytes是用于存储每次读取出来的内容
            byte[] bytes = new byte[1024];
            while ((len = bfi.read(bytes)) != -1) {
                //每次读取完了都将len累加在totle里
                totle += len;
                //通过文件输出流写入从服务器中读取的数据
                outputStream.write(bytes, 0, len);
            }
            //关闭打开的流对象
            outputStream.close();
            inputStream.close();
            bfi.close();
            return apkFile.getAbsolutePath();
        } catch (Exception e) {
            return null;
        }
    }
}
