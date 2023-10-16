package lib.kalu.frame.mvp.util;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public final class ProguardUtil {

    /**
     * @param dicts  字典样本
     * @param length 字典行数
     * @param output 输出混淆文件路径
     */
    public static void generateProguard(@NonNull List<String> dicts, @NonNull long length, @NonNull String output) {
        List<String> unicodeList = new ArrayList(dicts);
        List<String> outputList = new ArrayList<String>();
        Collections.sort(unicodeList);
        File file = new File(output);
        if (file.exists()) {
            System.out.println("文件已存在，删除");
            file.delete();
        } else {
            System.out.println("文件不存在");
        }

        String encoding = "UTF-8";
        int repeatCount = 0;

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            int i = 0;
            while (i < length) {
                String tmp = "";
                int width = new Random().nextInt(7) + 4;
                for (int j = 0; j < width; j++) {
                    tmp = tmp + getRandomString(unicodeList);
                }
                if (!outputList.contains(tmp)) {
                    i++;
                    outputList.add(tmp);
                    fileOutputStream.write(tmp.getBytes(encoding));
                    if (i < length) {
                        //最后一行不输入回车
                        fileOutputStream.write('\n');
                    }
                    repeatCount = 0;
                } else {
                    repeatCount++;
                    System.out.println("重复生成的字符串当前行数--->" + i + " 内容---> " + tmp);
                    if (repeatCount == 10000) {
                        System.out.println("连续重复次数超过10000次 已达到最大行数 无法继续生成");
                        break;
                    }
                }
            }
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            MvpUtil.logE("ProguardUtil => generateProguard => " + e.getMessage());
        }
    }

    private static String getRandomString(List<String> list) {
        int s = new Random().nextInt(list.size());
        return list.get(s);
    }
}
