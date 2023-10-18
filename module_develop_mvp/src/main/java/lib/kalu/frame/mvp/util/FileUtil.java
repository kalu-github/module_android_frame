package lib.kalu.frame.mvp.util;

import androidx.annotation.NonNull;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public final class FileUtil {

//    public static boolean saveFile(String url, String toPath) {
//        try {
//            InputStream is = new URL(url).openStream();
//            return saveFile(is, toPath);
//        } catch (Exception e) {
//            OkhttpUtil.logE("saveFile => " + e.getMessage());
//            return false;
//        }
//    }

    private static File checkFile(@NonNull String filePath) {
        try {
            if (null == filePath || filePath.length() == 0)
                throw new Exception("filePath error: " + filePath);
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
            }
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            file.createNewFile();
            return file;
        } catch (Exception e) {
            MvpUtil.logE("FileUtils => checkFile => " + e.getMessage());
            return null;
        }
    }

    public static boolean saveFile(@NonNull byte[] bytes, @NonNull String filePath) {
        try {
            File checkFile = checkFile(filePath);
            if (null == checkFile)
                throw new Exception("checkFile error: null");
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            fileOutputStream.write(bytes, 0, bytes.length);
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        } catch (Exception e) {
            MvpUtil.logE("FileUtils => saveFile => " + e.getMessage());
            return false;
        }
    }

    public static boolean saveFile(@NonNull InputStream inputStream, @NonNull String filePath) {
        try {
            if (null == inputStream)
                throw new Exception("inputStream error: null");
            File checkFile = checkFile(filePath);
            if (null == checkFile)
                throw new Exception("checkFile error: null");
            FileOutputStream outputStream = new FileOutputStream(checkFile);
            byte[] buf = new byte[1024];
            int n;
            while ((n = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, n);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
            return true;
        } catch (Exception e) {
            MvpUtil.logE("FileUtils => saveFile => " + e.getMessage());
            return false;
        }
    }

    public static boolean download(@NonNull String savePath, @NonNull String fileUrl) {

        try {
            MvpUtil.logE("FileUtil => download => savePath = " + savePath + ", fileUrl = " + fileUrl);
            File file = new File(savePath);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            URL url = new URL(fileUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            // 获取文件的长度
            int responseCode = connection.getResponseCode();
            if (responseCode != 200)
                throw new Exception("responseCode error: " + responseCode);
            // 创建输入流
            InputStream input = new BufferedInputStream(url.openStream());
            // 创建输出流
            OutputStream output = new FileOutputStream(savePath);
            // 缓存大小
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
            // 关闭流
            output.flush();
            output.close();
            input.close();
            MvpUtil.logE("FileUtil => download => succ");
            return true;
        } catch (Exception e) {
            MvpUtil.logE("FileUtil => download => " + e.getMessage());
            return false;
        }
    }
}
