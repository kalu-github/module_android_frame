package lib.kalu.frame.mvvm.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

public final class FileUtils {

    public static boolean saveFile(ByteBuffer src, String toPath) {
        try {
            FileChannel fc = new FileOutputStream(toPath).getChannel();
            fc.write(src);
            fc.close();
            fc = null;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean saveFile(String url, String toPath) {
        try {
            InputStream is = new URL(url).openStream();
            return saveFile(is, toPath);
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean saveFile(InputStream is, String toPath) {
        try {
            ReadableByteChannel rbc = Channels.newChannel(is);
            FileOutputStream fileOutputStream = new FileOutputStream(toPath);
            FileChannel fc = fileOutputStream.getChannel();
            fc.transferFrom(rbc, 0, Long.MAX_VALUE);
            fc.close();
            fc = null;
            rbc.close();
            rbc = null;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static ByteBuffer readFile(String fromPath) {
        try {
            FileChannel fc = new FileInputStream(fromPath).getChannel();
            MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
            fc.close();
            fc = null;
            return mbb;
        } catch (IOException e) {
            return null;
        }
    }
}
