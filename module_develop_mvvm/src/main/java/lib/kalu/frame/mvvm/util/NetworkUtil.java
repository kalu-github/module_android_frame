package lib.kalu.frame.mvvm.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


/**
 * @author：tqzhang on 18/7/23 11:33
 */
public class NetworkUtil {

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
