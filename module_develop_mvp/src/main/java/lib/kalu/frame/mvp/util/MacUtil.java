package lib.kalu.frame.mvp.util;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import androidx.core.app.ActivityCompat;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

public final class MacUtil {

    public static String getMacWIFI() {
        BufferedReader mReader = null;
        InputStreamReader mInputStreamReader = null;
        InputStream mInputStream = null;
        try {
            Runtime mRuntime = Runtime.getRuntime();
            Process mProcess = mRuntime.exec("cat /sys/class/net/wlan0/address");
            mInputStream = mProcess.getInputStream();
            mInputStreamReader = new InputStreamReader(mInputStream);
            mReader = new BufferedReader(mInputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            char[] buff = new char[1024];
            int ch = 0;
            while ((ch = mReader.read(buff)) != -1) {
                stringBuilder.append(buff, 0, ch);
            }
            mReader.close();
            String result = stringBuilder.toString();
            if (null == result || result.length() == 0)
                throw new Exception("error: result null");
            return result;
        } catch (Exception e) {
            MvpUtil.logE("MacUtil => getMacWIFI => " + e.getMessage());
            return null;
        } finally {
            try {
                if (null != mReader) {
                    mReader.close();
                    mReader = null;
                }
            } catch (Exception ex) {
            }
            try {
                if (null != mInputStreamReader) {
                    mInputStreamReader.close();
                    mInputStreamReader = null;
                }
            } catch (Exception ex) {
            }
            try {
                if (null != mInputStream) {
                    mInputStream.close();
                    mInputStream = null;
                }
            } catch (Exception ex) {
            }
        }
    }

    public static String getMacETH() {
        BufferedReader mReader = null;
        InputStreamReader mInputStreamReader = null;
        InputStream mInputStream = null;
        try {
            Runtime mRuntime = Runtime.getRuntime();
            Process mProcess = mRuntime.exec("cat /sys/class/net/eth0/address");
            mInputStream = mProcess.getInputStream();
            mInputStreamReader = new InputStreamReader(mInputStream);
            mReader = new BufferedReader(mInputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            char[] buff = new char[1024];
            int ch = 0;
            while ((ch = mReader.read(buff)) != -1) {
                stringBuilder.append(buff, 0, ch);
            }
            mReader.close();
            String result = stringBuilder.toString();
            if (null == result || result.length() == 0)
                throw new Exception("error: result null");
            return result;
        } catch (Exception e) {
            MvpUtil.logE("MacUtil => getMacETH => " + e.getMessage());
            return null;
        } finally {
            try {
                if (null != mReader) {
                    mReader.close();
                    mReader = null;
                }
            } catch (Exception ex) {
            }
            try {
                if (null != mInputStreamReader) {
                    mInputStreamReader.close();
                    mInputStreamReader = null;
                }
            } catch (Exception ex) {
            }
            try {
                if (null != mInputStream) {
                    mInputStream.close();
                    mInputStream = null;
                }
            } catch (Exception ex) {
            }
        }
    }

    public static String getMacWIFIAndroid(Context context) {
        try {
            WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wifi.getConnectionInfo();
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                throw new Exception("error: checkSelfPermission fail");
            String macAddress = info.getMacAddress();
            if (null == macAddress || macAddress.length() == 0)
                throw new Exception("error: macAddress null");
            return macAddress;
        } catch (Exception e) {
            MvpUtil.logE("MacUtil => getMacWIFIAndroid => " + e.getMessage());
            return "00:00:00:00:00:00";
        }
    }

    public static String getMacETHAndroid() {
        try {
            List<NetworkInterface> allNetworkInterface = Collections.list(NetworkInterface.getNetworkInterfaces());
            if (null == allNetworkInterface || allNetworkInterface.size() == 0)
                throw new Exception("error: allNetworkInterface null");
            for (NetworkInterface nif : allNetworkInterface) {
                if (null == nif)
                    continue;
                if (!"eth0".equalsIgnoreCase(nif.getName()))
                    continue;
                byte[] macBytes = nif.getHardwareAddress();
                if (null == macBytes)
                    throw new Exception("error: macBytes null");
                StringBuilder stringBuilder = new StringBuilder();
                for (byte b : macBytes) {
                    stringBuilder.append(String.format("%02X:", b));
                }
                if (stringBuilder.length() > 0) {
                    stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                }
                return stringBuilder.toString();
            }
            throw new Exception("error: not find");
        } catch (Exception e) {
            MvpUtil.logE("MacUtil => getMacETHAndroid => " + e.getMessage());
            return "00:00:00:00:00:00";
        }
    }
}
