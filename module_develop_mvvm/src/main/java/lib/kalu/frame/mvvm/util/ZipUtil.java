
package lib.kalu.frame.mvvm.util;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipUtil {

    public static final boolean zip(@NonNull String src, @NonNull String output) {
        try {
            //创建ZIP
            ZipOutputStream outZip = new ZipOutputStream(new FileOutputStream(output));
            //创建文件
            File file = new File(src);
            loop(file.getParent() + File.separator, file.getName(), outZip);
            //完成和关闭
            outZip.finish();
            outZip.close();
            return true;
        } catch (Exception e) {
            logD(e.getMessage());
            return false;
        }
    }

    public static boolean unzip(@NonNull String src, @NonNull String output) {
        return unzip(false, src, output);
    }

    public static boolean unzip(@NonNull boolean deleteZip, @NonNull String src, @NonNull String output) {
        try {
            logD("开始解压的文件：" + src + "," + "解压的目标路径：" + output);
            // 创建解压目标目录
            File file = new File(output);
            // 如果目标目录不存在，则创建
            if (!file.exists()) {
                file.mkdirs();
            }
            // 打开压缩文件
            InputStream inputStream = new FileInputStream(src);

            ZipInputStream zipInputStream = new ZipInputStream(inputStream);

            // 读取一个进入点
            ZipEntry zipEntry = zipInputStream.getNextEntry();
            // 使用1Mbuffer
            byte[] buffer = new byte[1024 * 1024];
            // 解压时字节计数
            int count = 0;
            // 如果进入点为空说明已经遍历完所有压缩包中文件和目录
            while (zipEntry != null) {
//                Log.e("whh0927", "解压文件 入口 1： " + zipEntry);
                if (!zipEntry.isDirectory()) {  //如果是一个文件
                    // 如果是文件
                    String fileName = zipEntry.getName();
//                    Log.e("whh0927", "解压文件 原来 文件的位置： " + fileName);
                    fileName = fileName.substring(fileName.lastIndexOf("/") + 1);  //截取文件的名字 去掉原文件夹名字
//                    Log.e("whh0927", "解压文件 的名字： " + fileName);
                    file = new File(output + File.separator + fileName);  //放到新的解压的文件路径

                    file.createNewFile();
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    while ((count = zipInputStream.read(buffer)) > 0) {
                        fileOutputStream.write(buffer, 0, count);
                    }
                    fileOutputStream.close();

                }

                // 定位到下一个文件入口
                zipEntry = zipInputStream.getNextEntry();
//                Log.e("whh0927", "解压文件 入口 2： " + zipEntry);
            }
            zipInputStream.close();

            // delete
            if (deleteZip) {
                File zip = new File(src);
                zip.delete();
            }

            return true;
        } catch (Exception e) {
            logD(e.getMessage());
            return false;
        }
    }

    /**************************/

    private static void loop(String folderString, String fileString, ZipOutputStream zipOutputSteam) throws Exception {
        if (zipOutputSteam == null)
            return;
        File file = new File(folderString + fileString);
        logD(" " + folderString + fileString);
        if (file.isFile()) {
            ZipEntry zipEntry = new ZipEntry(fileString);
            FileInputStream inputStream = new FileInputStream(file);
            zipOutputSteam.putNextEntry(zipEntry);
            int len;
            byte[] buffer = new byte[4096];
            while ((len = inputStream.read(buffer)) != -1) {
                zipOutputSteam.write(buffer, 0, len);
            }
            zipOutputSteam.closeEntry();
        } else {
            //文件夹
            String[] fileList = file.list();
            //没有子文件和压缩
            if (null == fileList) {
                logD("fileList == null");
                return;
            }
            if (fileList.length <= 0) {
                ZipEntry zipEntry = new ZipEntry(fileString + File.separator);
                zipOutputSteam.putNextEntry(zipEntry);
                zipOutputSteam.closeEntry();
            }
            //子文件和递归
            for (int i = 0; i < fileList.length; i++) {
                loop(folderString + fileString + "/", fileList[i], zipOutputSteam);
            }
        }
    }

    private static final void logD(@NonNull String msg) {

        if (null == msg || msg.length() == 0)
            return;

        Log.e("ZipFiles:", msg);
    }
}
