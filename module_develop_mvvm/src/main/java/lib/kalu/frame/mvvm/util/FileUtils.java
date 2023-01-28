package lib.kalu.frame.mvvm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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

    public static boolean saveFile(byte[] bytes, String toPath) {
        try {
//            ByteArrayInputStream is = new ByteArrayInputStream(bytes);
            ByteBuffer byteBuffer = readFile(bytes);
            return saveFile(byteBuffer, toPath);
        } catch (Exception e) {
            MvpUtil.logE("saveFile => " + e.getMessage());
            return false;
        }
    }

    public static boolean saveFile(ByteBuffer src, String toPath) {

        boolean status = false;
        FileChannel fc = null;
        try {
            File f = new File(toPath);
            if (f.exists()) {
                f.delete();
            }
            f.createNewFile();
            fc = new FileOutputStream(toPath).getChannel();
            fc.write(src);
            status = true;
        } catch (Exception e) {
            MvpUtil.logE("saveFile => " + e.getMessage());
        } finally {
            if (null != fc) {
                try {
                    fc.close();
                } catch (Exception e) {
                } finally {
                    fc = null;
                }
            }
        }
        return status;
    }

//    public static boolean saveFile(InputStream is, String toPath) {
//        try {
////            FileChannel fc = new FileOutputStream(toPath).getChannel();
////            fc.write(src);
////            fc.close();
////            fc = null;
//            ReadableByteChannel rbc = Channels.newChannel(is);
//            FileOutputStream fileOutputStream = new FileOutputStream(toPath);
//            FileChannel fc = fileOutputStream.getChannel();
//            fc.transferFrom(rbc, 0, Long.MAX_VALUE);
////            fc.close();
////            fc = null;
////            is.close();
////            is = null;
////            rbc.close();
////            rbc = null;
//            return true;
//        } catch (Exception e) {
//            OkhttpUtil.logE("saveFile => " + e.getMessage());
//            return false;
//        }
//    }

    public static ByteBuffer readFile(String filename) {
        try {
            FileChannel fc = new FileInputStream(filename).getChannel();
            MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
//            fc.close();
//            fc = null;
            return mbb;
        } catch (IOException e) {
            MvpUtil.logE("saveFile => " + e.getMessage());
            return null;
        }
    }

    public static ByteBuffer readFile(byte[] bytes) {
        try {
            return ByteBuffer.wrap(bytes);
        } catch (Exception e) {
            MvpUtil.logE("saveFile => " + e.getMessage());
            return null;
        }
    }
}
