package lib.kalu.frame.mvp.util;

import java.security.MessageDigest;

public final class ShaUtil {

    public static String getSha1(String input) {
        try {
            // 步骤 1: 导入必要的类
            MessageDigest md = MessageDigest.getInstance("SHA-1"); // 步骤 2: 创建 SHA1 加密对象
            byte[] hashBytes = md.digest(input.getBytes()); // 步骤 4: 执行 SHA1 计算
            // 将字节数组转换为十六进制字符串
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b); // 转换为十六进制
                if (hex.length() == 1) hexString.append('0'); // 添加前导零
                hexString.append(hex); // 添加十六进制字符
            }

            return hexString.toString();
        } catch (Exception e) {
            return null;
        }


    }

}
