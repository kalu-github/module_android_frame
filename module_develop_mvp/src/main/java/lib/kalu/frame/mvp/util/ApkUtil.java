package lib.kalu.frame.mvp.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;

import java.io.BufferedReader;
import java.io.File;

import lib.kalu.frame.mvp.context.FrameContext;

public final class ApkUtil {

    private static String PACKAGE_ARCHIVE = "application/vnd.android.package-archive";
    private static String PACKAGE = "package:";
    private static String AUTHORITY = "lib.kalu.frame.mvp.common.upgrade";

    public static boolean installApk(@NonNull Context context, @NonNull String apkPath) {
        try {
            if (null == context)
                throw new Exception("context error: null");
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
                    intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setDataAndType(FileProvider.getUriForFile(context, AUTHORITY, file), PACKAGE_ARCHIVE);
                } else {
                    intent.setAction(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
                    intent.setData(Uri.parse(PACKAGE + context.getPackageName()));
                }
            }
            // 7.0
            else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                MvpUtil.logE("ApkUtil => installApk => android7.0");
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setDataAndType(FileProvider.getUriForFile(context, AUTHORITY, file), PACKAGE_ARCHIVE);
            } else {
                MvpUtil.logE("ApkUtil => installApk => android6.0");
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.setDataAndType(Uri.fromFile(file), PACKAGE_ARCHIVE);
            }
            context.startActivity(intent);
            return true;
        } catch (Exception e) {
            MvpUtil.logE("ApkUtil => installApk => " + e.getMessage());
            return false;
        }
    }
}
