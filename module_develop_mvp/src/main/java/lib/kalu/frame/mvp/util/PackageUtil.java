
package lib.kalu.frame.mvp.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.core.content.FileProvider;

public class PackageUtil {

    private static final String TAG = "PackageUtil";

    // 获取应用版本1.0
    public static String getVersionName(Context context, String pkgName) {
        String verName = "";
        try {
            verName = context.getPackageManager().getPackageInfo(pkgName, 0).versionName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return verName;
    }

    // 获取应用版号
    public static int getVersionCode(Context _context, String _package) {
        int verCode = -1;
        try {
            verCode = _context.getPackageManager().getPackageInfo(_package, 0).versionCode;

        } catch (PackageManager.NameNotFoundException e) {
        }
        return verCode;
    }


    private static boolean isUpgrade;

    /**
     * 应用安装/更新
     *
     * @param context
     * @param file
     */
    public static void installApp(final Context context, final File file, final InstallCall installCall) {
        try {
            chmodPath("777", file.getParent());
            Log.i(TAG, "FILE PATH ==> " + file.getPath());
            Log.i(TAG, "installApp file: " + file.exists() + " , " + file.canRead() + " , " + file.canWrite());
            Log.i(TAG, " sdkVersion===> " + Build.VERSION.SDK_INT);
            Log.i(TAG, "PmInstaller install");

            PmInstaller pmInstaller = new PmInstaller(context) {
                @Override
                protected void onPostExecute(int result, String errorMsg) {
                    super.onPostExecute(result, errorMsg);
                    Log.i(TAG, "result ... " + result);
                    if (result != 0) {
                        Log.i(TAG, "安装失败! errorMsg: " + errorMsg);
                        if (!TextUtils.isEmpty(errorMsg)) {
                            installCall.error(errorMsg);
                            //空间不足
                            if (errorMsg.contains("INSTALL_FAILED_INSUFFICIENT_STORAGE")) {
                                Log.i(TAG, "安装失败，空间不足! ");
                                installCall.error("安装失败，空间不足");
                            }
                            if (errorMsg.contains("SecurityException")) {
                                //非静默安装/更新
                                if (getTopApp(context)) {
                                    Log.i(TAG, "应用安装失败，没有安装权限 -->" + file.getName());

                                }

                            } else {

                                if (getTopApp(context)) {

                                    Log.i(TAG, "应用安装失败 -->" + file.getName());
                                }
                            }

                        } else {
                            if (getTopApp(context)) {

                                Log.i(TAG, "应用安装失败 -->" + file.getName());
                            }
                        }
                    } else {
                        Log.i(TAG, "安装成功 -->" + file.getName());
                        installCall.success();
                    }
                }
            };

//            pmInstaller.execute(new String[]{"pm", "install", "-i","com.huan.myapplication", "--user 0",file.getPath()});
            pmInstaller.execute(new String[]{"pm", "install", "-r", file.getPath()});

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public interface InstallCall {
        void success();

        void error(String info);
    }

    /**
     * 应用卸载
     */
    public static void unInstallApp(Context context, final String pkgName) {
        try {
            PmInstaller pmInstaller = new PmInstaller(context) {
                @Override
                protected void onPostExecute(int result, String errorMsg) {
                    super.onPostExecute(result, errorMsg);
                    Log.i(TAG, "uninstallApp pkName==> " + pkgName + "  ,result==> " + result);

                }
            };

            pmInstaller.execute(new String[]{"pm", "uninstall", pkgName});

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 系统原生UI安装
     */
    public static void installBySystem(Context context, File file) {
		/*new Thread(new Runnable() {
			@Override
			public void run() {
				File myInstallDir = new File("/data/data/"+context.getPackageName()+"/files/"+"pkgs");
				if(!myInstallDir.exists()) {
					myInstallDir.mkdirs();
				}
				File toFile = new File(myInstallDir, file.getName());
				FileCopy.copy(file, toFile);
				new PmInstaller(context).execute(new String[] {
						"cd "+myInstallDir.getAbsolutePath(),
						"pm install -r "+file.getName()
				});
			}
		}).start();*/

        try {
            chmodPath("777", file.getParent());
            Log.i(TAG, "安装 " + file);
            Log.i("PackageUtil", file.getAbsolutePath() + "[" + file.isFile() + "&" + file.exists() + "]");
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Log.i("PackageUtil", "执行安装");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // 6.0+以上版本
                Log.i("PackageUtil", "版本>=6.0");
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Uri apkUri = FileProvider.getUriForFile(context, context.getPackageName() + ".FileProvider", file);
                intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
                context.startActivity(intent);
                Log.i("PackageUtil", "打开系统安装界面！");
                return;
            } else {
                Log.i("PackageUtil", "版本<6.0");
                intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            }
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            context.startActivity(intent);
            Log.i("PackageUtil", "打开系统安装界面！");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("PackageUtil", "安装发生错误！");
        }
    }

    /**
     * android 9.0静默安装
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void android9Install(Context context, String path) throws Exception {
        Log.i(TAG, "android9Install " + path);
        File file = new File(path);
        String apkName = path.substring(path.lastIndexOf(File.separator) + 1, path.lastIndexOf(".apk"));
        //获取PackageInstaller
        PackageInstaller packageInstaller = context.getPackageManager()
                .getPackageInstaller();
        PackageInstaller.SessionParams params = new PackageInstaller
                .SessionParams(PackageInstaller.SessionParams.MODE_FULL_INSTALL);
        PackageInstaller.Session session = null;
        OutputStream outputStream = null;
        FileInputStream inputStream = null;
        try {
            packageInstaller.registerSessionCallback(new PackageInstaller.SessionCallback() {
                @Override
                public void onCreated(int sessionId) {
                    Log.i(TAG, "onCreated " + sessionId);
                }

                @Override
                public void onBadgingChanged(int sessionId) {
                    Log.i(TAG, "onBadgingChanged " + sessionId);
                }

                @Override
                public void onActiveChanged(int sessionId, boolean active) {
                    Log.i(TAG, "onActiveChanged " + sessionId + " , " + active);
                }

                @Override
                public void onProgressChanged(int sessionId, float progress) {
                    Log.i(TAG, "onProgressChanged " + sessionId + " , progress: " + progress);
                }

                @Override
                public void onFinished(int sessionId, boolean success) {
                    Log.i(TAG, "onFinished " + sessionId + " , success: " + success);
                }
            });
            //创建Session
            int sessionId = packageInstaller.createSession(params);
            //开启Session
            session = packageInstaller.openSession(sessionId);
            //获取输出流，用于将apk写入session
            outputStream = session.openWrite(apkName, 0, -1);
            inputStream = new FileInputStream(file);
            byte[] buffer = new byte[4096];
            int n;
            //读取apk文件写入session
            while ((n = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, n);
            }
            //写完需要关闭流，否则会抛异常“files still open”
            inputStream.close();
            inputStream = null;
            outputStream.flush();
            outputStream.close();
            outputStream = null;
            //配置安装完成后发起的intent，通常是打开activity
//            Intent intent = new Intent(context, AppReceiver.class);
//            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
//            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//            IntentSender intentSender = pendingIntent.getIntentSender();
            //提交启动安装
//            session.commit(intentSender);
            Log.i(TAG, "========commit==============");
        } catch (Exception e) {

            if (session != null) {
                session.abandon();
            }
            e.printStackTrace();
            throw e;
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * 判断是否安装了指定的应用,检查版本
     */
    public static boolean isInstalledAppCheckVercode(Context context, String app) {
        String pkgname = app;
        try {
            if (pkgname == null || "".equalsIgnoreCase(pkgname))
                return false;

            PackageInfo pi = context.getPackageManager().getPackageInfo(
                    pkgname, PackageManager.GET_UNINSTALLED_PACKAGES);
            if (pi != null && pi.versionCode >= Integer.parseInt(app)) {
                return true;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }

        return false;
    }

    /**
     * 判断是否安装了指定的应用
     */
    public static boolean isInstalledApp(Context context, String pkgname) {
        try {
            if (pkgname == null || "".equalsIgnoreCase(pkgname))
                return false;

            PackageInfo pi = context.getPackageManager().getPackageInfo(
                    pkgname, PackageManager.GET_UNINSTALLED_PACKAGES);
            if (pi != null && pi.packageName.equals(pkgname)) {
                return true;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }

        return false;
    }


    /**
     * 卸载应用
     * 根据商店的安装方式，选择不同的卸载方式
     */
    @Deprecated
    public static void uninstallApp(Context context, final String packagename) {
        try {
            if (isSystemApp(context.getPackageManager().getPackageInfo(context.getPackageName(), 0))) {
                Log.i(TAG, "appStore is system app " + packagename);
                PmInstaller pmInstaller = new PmInstaller(context) {
                    @Override
                    protected void onPostExecute(int result, String errorMsg) {
                        super.onPostExecute(result, errorMsg);
                        Log.i(TAG, "uninstallApp packagename==> " + packagename + "  ,result==> " + result);
                    }
                };
                pmInstaller.execute(new String[]{"pm", "uninstall", packagename});
            } else {
                Log.i(TAG, "appStore not is system app " + packagename);
                uninstallBySystem(context, packagename);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * 系统原生卸载应用
     */
    public static void uninstallBySystem(Context context, final String packagename) {
//        if (Constants.Setting.PERMIT_INSTALL) {
        try {
            Log.i(TAG, "context is " + context + "packagename is " + packagename);
            if (context == null || packagename == null) {
                return;
            }
            Uri packageURI = Uri.parse("package:" + packagename);
            // 创建Intent意图
            Intent intent = new Intent(Intent.ACTION_DELETE, packageURI);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            // 执行卸载程序
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        }
    }


    public static List<String> getAllAppPackages(Context context, boolean hasSys) {
        List<String> appInfos = new ArrayList<>();
        PackageManager pm = context.getPackageManager();
        List<PackageInfo> packages = pm.getInstalledPackages(0);
        for (PackageInfo packageInfo : packages) {
            if (!packageInfo.packageName.equals(context.getPackageName())) {
                // 判断系统/非系统应用
                if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0 || hasSys) {
                    appInfos.add(packageInfo.packageName);
                }
            }
        }
        return appInfos;
    }

    /**
     * 获取已安装应用的大小
     */
    public static long getPackageSize(Context context, PackageInfo packageInfo) {
        String datadir = packageInfo.applicationInfo.dataDir;
        String publicSourceDir = packageInfo.applicationInfo.publicSourceDir;

        long datasize = new File(datadir).length();
        long codesize = new File(publicSourceDir).length();
        long totalsize = datasize + codesize;

        return totalsize > 0 ? totalsize / 1024 : 0;
    }

    public static long getPackageSize(Context context, ApplicationInfo applicationInfo) {
        String datadir = applicationInfo.dataDir;
        String publicSourceDir = applicationInfo.publicSourceDir;

        long datasize = new File(datadir).length();
        long codesize = new File(publicSourceDir).length();
        long totalsize = datasize + codesize;

        return totalsize > 0 ? totalsize / 1024 : 0;
    }

    /**
     * 运行已安装的应用
     */
    public static boolean runApp(Context context, String packagename) {
        Log.i(TAG, "runApp start... , App: " + packagename);
        if (context == null || packagename == null) {
            Log.e(TAG, "conntext is --" + context + "packagename is --" + packagename);
            return false;
        }
        Intent intent;
        PackageManager pm = context.getPackageManager();// 获得已安装的应用程序信息
        intent = pm.getLaunchIntentForPackage(packagename);
        if (intent != null) {
            List<ResolveInfo> list = pm.queryIntentActivities(intent, 0);
            if (list != null && list.size() > 0) {
                try {
                    context.startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 判断是否是系统应用
     */
    public static boolean isSystemApp(PackageInfo pkgInfo) {
        // 非系统应用
        return (pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
    }

    public static boolean isSystemApp(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            // 非系统应用
            return (packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getLabel(Context context, String pkgName) {
        if (context == null || pkgName == null) {
            Log.w(TAG, "getLabel(), pkgName: " + pkgName);
            return "";
        }
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pkgInfo = pm.getPackageInfo(pkgName, 0);
            ApplicationInfo info = pkgInfo.applicationInfo;
            return info.loadLabel(pm).toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean isInstalled(String packageName) {
        try {
            Process process = Runtime.getRuntime().exec("pm list package -3");
            BufferedReader bis = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            while ((line = bis.readLine()) != null) {
                if (packageName.equals(line.substring(8))) {
                    return true;
                }
            }
            throw new Exception("not find");
        } catch (Exception e) {
            MvpUtil.logE("PMUtil => isInstalled => " + e.getMessage());
            return false;
        }
    }

    public static boolean isApkInstall(Context context, String packageName) {
        try {
            PackageManager pm = context.getPackageManager();
            Intent intent = new Intent();
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setPackage(packageName);
            List<ResolveInfo> activities = pm.queryIntentActivities(intent, 0);
            return activities.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public static void chmodPath(String permission, String path) {
        try {
            Runtime.getRuntime().exec("chmod -R " + permission + " " + path);
        } catch (Exception e) {
            MvpUtil.logE("PMUtil => chmodPath => " + e.getMessage());
        }
    }

    public static boolean getTopApp(Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);

        if (runningTaskInfos != null) {
            String name = (runningTaskInfos.get(0).topActivity).getPackageName();
            Log.i(TAG, "getTopApp packagename:" + name);
            if (context.getApplicationInfo().packageName.equals(name)) {
                Log.i(TAG, "return ...");
                return true;
            }
        } else {
            Log.i(TAG, "RunningTaskInfo is null. ");
        }

        return false;
    }

    /*************/

    static class PmInstaller implements Runnable {

        private boolean running;
        private final Handler mHandler;
        private final StringBuilder errorMsg = new StringBuilder();

        private final List<String[]> params = new ArrayList<String[]>();

        public PmInstaller(Context context) {
            mHandler = new Handler(context.getMainLooper());
        }


        @Override
        public void run() {
            running = true;
            while (params.size() != 0) {
                String[] exception = params.remove(0);
                final int result = goInstall(exception);
                mHandler.post(new Runnable() {
                    public void run() {
                        mHandler.removeCallbacks(this);
                        PmInstaller.this.onPostExecute(result, errorMsg == null ? "" : errorMsg.toString());
                    }
                });
            }
            running = false;
        }


        private int goInstall(String[] arg) {
            ProcessBuilder processBuilder = new ProcessBuilder(arg);
            Process process = null;
            BufferedReader successResult = null;
            BufferedReader errorResult = null;
            StringBuilder successMsg = new StringBuilder();
            int result;
            try {
                process = processBuilder.start();
                successResult = new BufferedReader(new InputStreamReader(process.getInputStream()));
                errorResult = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                String s;

                while ((s = successResult.readLine()) != null) {
                    successMsg.append(s);
                }

                while ((s = errorResult.readLine()) != null) {
                    errorMsg.append(s);
                }
            } catch (IOException e) {
                e.printStackTrace();
                result = 1;
            } catch (Exception e) {
                e.printStackTrace();
                result = 1;
            } finally {
                try {
                    if (successResult != null) {
                        successResult.close();
                    }
                    if (errorResult != null) {
                        errorResult.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (process != null) {
                    process.destroy();
                }
            }

            if (successMsg.toString().contains("Success") ||
                    successMsg.toString().contains("success")) {
                result = 0;
            } else {
                result = 1;
            }
            MvpUtil.logE("pm install successMsg:" + successMsg + ", Msg:" + errorMsg);
            return result;
        }

        protected void onPostExecute(int result, String errorMsg) {
        }

        public void execute(String[] params) {
            this.params.add(params);
            if (!running) {
                new Thread(this).start();
            }
        }
    }
}
