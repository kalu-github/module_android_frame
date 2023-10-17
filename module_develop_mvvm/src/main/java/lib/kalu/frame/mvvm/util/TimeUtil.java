package lib.kalu.frame.mvvm.util;

import androidx.annotation.NonNull;

public class TimeUtil {

    public static String format(@NonNull String duration) {
        return format(false, duration);
    }

    public static String format(@NonNull boolean millisecond, @NonNull String duration) {
        try {
            long parseLong = Long.parseLong(duration);
            return format(millisecond, parseLong);
        } catch (Exception e) {
            return "00:00";
        }
    }

    public static String format(@NonNull boolean millisecond, @NonNull long duration) {
        try {

            // 1
            // ms => s
            long c = millisecond ? duration / 1000 : duration;
            long c1 = c / 60;
            long c2 = c % 60;
            StringBuilder stringBuilder = new StringBuilder();
            if (c1 < 10) {
                stringBuilder.append("0");
            }
            stringBuilder.append(c1);
            stringBuilder.append(":");
            if (c2 < 10) {
                stringBuilder.append("0");
            }
            stringBuilder.append(c2);
            return stringBuilder.toString();
        } catch (Exception e) {
            return "00:00";
        }
    }

    public static int formatStr(String str) {

        if ("00:00:00,000".equals(str))
            return 0;

        int time = -1;

        // HH
        try {
            String s1 = str.split(":")[0];
            if (!"00".equals(s1)) {
                int i0 = Integer.parseInt(String.valueOf(s1.charAt(0))) * 10;
                int i1 = Integer.parseInt(String.valueOf(s1.charAt(1)));
                time += (60 * 60 * 1000) * (i0 + i1);
            }
        } catch (Exception e) {
        }

        // MM
        try {
            String s2 = str.split(":")[1];
            if (!"00".equals(s2)) {
                int i0 = Integer.parseInt(String.valueOf(s2.charAt(0))) * 10;
                int i1 = Integer.parseInt(String.valueOf(s2.charAt(1)));
                time += (60 * 1000) * (i0 + i1);
            }
        } catch (Exception e) {
        }

        // SS
        try {
            String s3 = str.split(":")[2].split(",")[0];
            if (!"00".equals(s3)) {
                int i0 = Integer.parseInt(String.valueOf(s3.charAt(0))) * 10;
                int i1 = Integer.parseInt(String.valueOf(s3.charAt(1)));
                time += 1000 * (i0 + i1);
            }
        } catch (Exception e) {
        }

        // MS
        try {
            String s4 = str.split(":")[2].split(",")[1];
            if (!"000".equals(s4)) {
                int i0 = Integer.parseInt(String.valueOf(s4.charAt(0))) * 100;
                int i1 = Integer.parseInt(String.valueOf(s4.charAt(1))) * 10;
                int i2 = Integer.parseInt(String.valueOf(s4.charAt(2)));
                time += (i0 + i1 + i2);
            }
        } catch (Exception e) {
        }

        return time;
    }
}
