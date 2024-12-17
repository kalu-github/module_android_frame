package lib.kalu.frame.mvp.util;

public final class ShaUtil {

    public static String getSha1(String strs) {
        try {
            MessageDigest md = MessageDigest.getInstance("sha1");
            byte[] digest = md.digest(strs.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b :
                    digest) {
                sb.append(chars[(b >> 4) & 15]);
                sb.append(chars[b & 15]);
            }
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }
}
