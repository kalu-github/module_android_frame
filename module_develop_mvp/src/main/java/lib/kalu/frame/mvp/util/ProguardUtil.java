package lib.kalu.frame.mvp.util;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public final class ProguardUtil {

    public static boolean genProguard(@NonNull List<String> data, @NonNull String filePath, @NonNull long maxLength) {
        try {
            // step1
            if (maxLength <= 0)
                throw new Exception("maxLength error: " + maxLength);
            if (null == filePath || filePath.length() == 0)
                throw new Exception("filePath error: " + filePath);
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            // step2
            List<String> unicodeList = new ArrayList(data);
            Collections.sort(unicodeList);
            List<String> outputList = new ArrayList<>();
            String encoding = "UTF-8";
            int repeatCount = 0;
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            int i = 0;
            while (i <= maxLength) {
                String tmp = "";
                int width = new Random().nextInt(7) + 4;
                for (int j = 0; j < width; j++) {
                    tmp = tmp + getRandomString(unicodeList);
                }
                if (!outputList.contains(tmp)) {
                    i++;
                    outputList.add(tmp);
                    fileOutputStream.write(tmp.getBytes(encoding));
                    if (i <= maxLength) {
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
            return true;
        } catch (Exception e) {
            MvpUtil.logE("ProguardUtil => genProguard => " + e.getMessage());
            return false;
        }
    }

    private static String getRandomString(List<String> list) {
        int s = new Random().nextInt(list.size());
        return list.get(s);
    }
}
