package lib.kalu.frame.mvp.util;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public final class FileUtils {

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
            MvpUtil.logE("checkFile => " + e.getMessage());
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
            MvpUtil.logE("saveFile => " + e.getMessage());
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
            MvpUtil.logE("saveFile => " + e.getMessage());
            return false;
        }
    }
}
