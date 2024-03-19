package lib.kalu.frame.mvp.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.SystemClock;

import java.net.InetAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * @author：tqzhang on 18/7/23 11:33
 */
public class NetworkUtil {

    public static boolean pingAndroid(String ipv4) {
        try {
            if (null == ipv4)
                throw new Exception("error: ipv4 null");
//            String[] split = ipv4.split(".");
//            if (null == split)
//                throw new Exception("error: split null");
//            if (split.length != 4)
//                throw new Exception("error: split.length != 4");
            Boolean[] result = new Boolean[]{null};
            long startMillis = System.currentTimeMillis();
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        InetAddress inetAddress = InetAddress.getByName(ipv4);
                        if (!inetAddress.isReachable(1000))
                            throw new Exception("error: isReachable false");
                        result[0] = true;
                    } catch (Exception e) {
                        result[0] = false;
                    }
                }
            });
            while (System.currentTimeMillis() - startMillis <= 1000) {
                if (null != result[0]) {
                    break;
                } else {
                    SystemClock.sleep(10);
                }
            }
            if (null != executor) {
                executor.shutdown();
                executor = null;
            }
            if (null == result[0] || !result[0])
                throw new Exception("error: result false");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean pingJava(String ipv4) {
        try {
            if (null == ipv4)
                throw new Exception("error: ipv4 null");
//            String[] split = ipv4.split(".");
//            if (null == split)
//                throw new Exception("error: split null");
//            if (split.length != 4)
//                throw new Exception("error: split.length != 4");
            Boolean[] result = new Boolean[]{null};
            long startMillis = System.currentTimeMillis();

            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        StringBuilder builder = new StringBuilder();
                        builder.append("ping ");
                        builder.append("-c "); // ping 次数
                        builder.append("1 ");
                        builder.append("-i "); // ping 每次发送数据包中间的间隔时间[单位秒]
                        builder.append("0.2 ");
                        builder.append("-w "); // ping 超时[单位秒]
                        builder.append("0.2 ");
                        builder.append(ipv4);
                        String command = builder.toString();
                        Runtime runtime = Runtime.getRuntime();
                        Process proc = runtime.exec(command);
                        // 一定要调用waitFor,因为ping的返回不是即时的。
                        int wF = proc.waitFor();
                        if (wF != 0)
                            throw new Exception("error: wF != 0");
//                        InputStreamReader inputStreamReader = new InputStreamReader(proc.getInputStream());
//                        BufferedReader bufferedreader = new BufferedReader(inputStreamReader);
//                        InputStreamReader inputStreamReader1 = new InputStreamReader(proc.getErrorStream());
//                        BufferedReader errorbuffer = new BufferedReader(inputStreamReader1);
//                        String line = "";
//                        StringBuilder sb = new StringBuilder(line);
//                        while ((line = bufferedreader.readLine()) != null) {
//                            sb.append(line);
//                            sb.append(';');
//                        }
//                        while ((line = errorbuffer.readLine()) != null) {
//                            sb.append(line);
//                            sb.append(';');
//                        }
//                        LogUtil.logE("NetworkUtil => pingJava => Thread => sb = " + sb);
                        result[0] = true;
                    } catch (Exception e) {
                        result[0] = false;
                    }
                }
            });
            while (System.currentTimeMillis() - startMillis <= 1000) {
                if (null != result[0]) {
                    break;
                } else {
                    SystemClock.sleep(10);
                }
            }
            if (null != executor) {
                executor.shutdown();
                executor = null;
            }
            if (null == result[0] || !result[0])
                throw new Exception("error: result false");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isNetworkAvailable(Context context) {
        try {
            ConnectivityManager mgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo[] info = mgr.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
            throw new Exception();
        } catch (Exception e) {
            return false;
        }
    }
}