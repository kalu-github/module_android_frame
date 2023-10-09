package lib.kalu.frame.mvvm.util;

import android.content.Context;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public final class AssetsUtil {

    public static boolean copyFile(@NonNull Context context, @NonNull String assetsName, @NonNull String savePath) {
        try {
            // a
            File file = new File(savePath);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            // b
            InputStream is = context.getAssets().open(assetsName);
            FileOutputStream fos = new FileOutputStream(savePath);
            byte[] buffer = new byte[1024];
            int byteCount = 0;
            while ((byteCount = is.read(buffer)) != -1) {// 循环从输入流读取
                // buffer字节
                fos.write(buffer, 0, byteCount);// 将读取的输入流写入到输出流
            }
            fos.flush();// 刷新缓冲区
            is.close();
            fos.close();
            return true;
        } catch (Exception e) {
            MvvmUtil.logE("AssetsUtil => copyFile => " + e.getMessage());
            return false;
        }
    }

    public static boolean unZip(@NonNull Context context, @NonNull String assetsName, @NonNull String outputPath) {
        try {
            String copyPath = context.getFilesDir().getAbsolutePath() + File.separator + System.currentTimeMillis();
            boolean copyFile = copyFile(context, assetsName, copyPath);
            if (!copyFile)
                throw new Exception("copyFile error: false");
            boolean unzip = ZipUtil.unzip(copyPath, outputPath);
            File file = new File(copyPath);
            if (file.exists()) {
                file.delete();
            }
            if (!unzip)
                throw new Exception("unzip error: false");
            return true;
        } catch (Exception e) {
            MvvmUtil.logE("AssetsUtil => copyFile => " + e.getMessage());
            return false;
        }
    }
}
