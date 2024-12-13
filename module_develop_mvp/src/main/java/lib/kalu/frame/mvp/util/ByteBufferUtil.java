package lib.kalu.frame.mvp.util;

import java.nio.ByteBuffer;

/**
 * public static final int RGBA_8888    = 1;
 * public static final int RGBX_8888    = 2;
 * public static final int RGB_888      = 3;
 * public static final int RGB_565      = 4;
 * public static final int RGBA_5551    = 6;
 * public static final int RGBA_4444    = 7;
 */
public final class ByteBufferUtil {

    // 1个像素4个字节
    private static int RGBA_8888 = 4;
    private static int RGBX_8888 = 4;
    private static int RGB_888 = 3;
    private static int RGB_565 = 2;
    private static int RGBA_5551 = 2;
    private static int RGBA_4444 = 2;

    public static ByteBuffer resizeQualityRGBA_8888(ByteBuffer originalBuffer,
                                                    int originalWidth, int originalHeight,
                                                    int newWidth,
                                                    int newHeight) {
        return resizeQuality(originalBuffer, originalWidth, originalHeight, newWidth, newHeight, RGBA_8888);
    }

    // 辅助方法，从ByteBuffer中获取指定位置的像素（RGBA_8888格式）
    private static int[] getPixel(ByteBuffer buffer, int offset) {
        buffer.position(offset);
        int[] pixel = new int[4];
        pixel[0] = buffer.get() & 0xff;  // 红色通道，确保是无符号字节值
        pixel[1] = buffer.get() & 0xff;  // 绿色通道
        pixel[2] = buffer.get() & 0xff;  // 蓝色通道
        pixel[3] = buffer.get() & 0xff;  // 透明度通道
        return pixel;
    }

    // 辅助方法，将像素（RGBA_8888格式）设置到ByteBuffer的指定位置
    private static void setPixel(ByteBuffer buffer, int offset, int[] pixel) {
        buffer.position(offset);
        buffer.put((byte) pixel[0]);
        buffer.put((byte) pixel[1]);
        buffer.put((byte) pixel[2]);
        buffer.put((byte) pixel[3]);
    }

    private static ByteBuffer resizeQuality(ByteBuffer originalBuffer,
                                            int originalWidth, int originalHeight,
                                            int newWidth,
                                            int newHeight,
                                            int flag) {

        try {
            //        int newWidth = originalWidth / 2;
//        int newHeight = originalHeight / 2;

            // 计算新的ByteBuffer容量（RGBA_8888格式，每个像素4字节）
            int newCapacity = newWidth * newHeight * flag;
            ByteBuffer newBuffer = ByteBuffer.allocate(newCapacity);

            // 采用最近邻插值算法进行图像缩小
            for (int y = 0; y < originalHeight; y += 2) {
                for (int x = 0; x < originalWidth; x += 2) {
                    // 获取原图像中对应位置的像素（这里取左上角的像素代表2x2像素块，也可以取其他规则）
                    int offset = (y * originalWidth + x) * 4;
                    int[] pixel = getPixel(originalBuffer, offset);

                    // 将该像素设置到新图像对应的位置（新图像位置按缩放后的坐标计算）
                    int newOffset = ((y / 2) * newWidth + (x / 2)) * 4;
                    setPixel(newBuffer, newOffset, pixel);
                }
            }

            // 重置新ByteBuffer的指针状态，方便后续读取
            newBuffer.flip();

            return newBuffer;
        } catch (Exception e) {
            return null;
        }
    }
}
