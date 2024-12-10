package lib.kalu.frame.mvp.util;

import android.app.Service;
import android.content.Context;
import android.graphics.PixelFormat;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.media.ImageReader;
import android.media.projection.MediaProjection;
import android.util.DisplayMetrics;

/**
 * 静默截屏功能，支持android12系统，需要系统签名，根据需要在AndroidManifest中配置以下权限和声明：
 * <p>
 * android:sharedUserId="android.uid.system"
 * <uses-permission android:name="android.permission.READ_FRAME_BUFFER" />
 * <uses-permission android:name="android.permission.CAPTURE_VIDEO_OUTPUT" />
 * <uses-permission android:name="android.permission.CAPTURE_SECURE_VIDEO_OUTPUT" />
 */
public final class VirtualDisplayUtil {

    /**
     * 表示创建的虚拟显示器是公共可用的。其他应用（满足相应权限要求等条件下）可以发现并使用这个虚拟显示器来输出内容等。例如在一些投屏场景或者多显示器协作场景中，如果希望外部应用能够知晓并利用该虚拟显示设备来推送画面，就可以设置这个标志。
     */
    private static int VIRTUAL_DISPLAY_FLAG_PUBLIC = DisplayManager.VIRTUAL_DISPLAY_FLAG_PUBLIC;
    /**
     * 将虚拟显示器标记为安全的。当设置这个标志后，内容显示在该虚拟显示器上会受到安全方面的限制，比如禁止截屏等操作，常用于显示一些涉及隐私敏感信息（如银行密码输入界面等）的场景，保障信息不会轻易被非法获取。
     */
    private static int VIRTUAL_DISPLAY_FLAG_SECURE = DisplayManager.VIRTUAL_DISPLAY_FLAG_SECURE;
    /**
     * 指定只有创建该虚拟显示器的所有者（通常是对应的应用）能够向其推送内容，其他应用无法将内容渲染到这个虚拟显示器上，可用于限制显示内容来源，保障显示内容的可控性。
     */
    private static int VIRTUAL_DISPLAY_FLAG_OWN_CONTENT_ONLY = DisplayManager.VIRTUAL_DISPLAY_FLAG_OWN_CONTENT_ONLY;
    /**
     * 当设置了这个标志后，系统会自动将内容镜像到其他合适的显示设备上（比如在支持无线投屏且开启相关功能的场景下，会自动把该虚拟显示器呈现的内容也投送到外部显示设备）。它可以简化多屏显示场景中内容同步展示的操作，让应用不需要额外去处理复杂的多屏同步逻辑，方便实现快速的内容共享和展示扩展，常用于多媒体播放、演示文稿展示等应用场景，使得内容能便捷地同时出现在多个相关显示终端上。
     */
    private static int VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR = DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR;
    /**
     * 主要用于标识该虚拟显示器是用于展示相关内容的，比如在进行会议演示、课堂教学展示等场景中创建的专门用来展示 PPT、教学资料等的虚拟显示设备。它可以让系统或者其他相关组件知晓这个虚拟显示器的用途偏向于对外的展示，有可能会在一些系统层面的显示优化策略、权限管理等方面与普通用途的虚拟显示器有所区分，使得应用在展示特定内容时更加契合用户的实际使用情境和期望。
     */
    private static int VIRTUAL_DISPLAY_FLAG_PRESENTATION = DisplayManager.VIRTUAL_DISPLAY_FLAG_PRESENTATION;


    public static int getId(VirtualDisplay virtualDisplay, int errorId) {
        try {
            return virtualDisplay.getDisplay().getDisplayId();
        } catch (Exception e) {
            return errorId;
        }
    }

    public static VirtualDisplay createPrivateVirtualDisplay(Context context, String displayName, int width, int height, int dpi) {
        return createVirtualDisplay(context, displayName, width, height, dpi, VIRTUAL_DISPLAY_FLAG_OWN_CONTENT_ONLY);
    }

    public static VirtualDisplay createPublicVirtualDisplay(Context context, String displayName, int width, int height, int dpi) {
        return createVirtualDisplay(context, displayName, width, height, dpi, VIRTUAL_DISPLAY_FLAG_PUBLIC);
    }

    /**
     * @param context
     * @param displayName 虚拟屏名称
     * @param width       虚拟屏宽度
     * @param height      虚拟屏高度
     * @param dpi         屏幕密度
     * @return
     */
    private static VirtualDisplay createVirtualDisplay(Context context, String displayName, int width, int height, int dpi, int flag) {
        try {

            DisplayMetrics metrics = new DisplayMetrics();
            metrics.densityDpi = dpi;
            metrics.widthPixels = width;
            metrics.heightPixels = height;

            return ((DisplayManager) context.getSystemService(Service.DISPLAY_SERVICE)).createVirtualDisplay(displayName,
                    metrics.widthPixels,
                    metrics.heightPixels,
                    metrics.densityDpi,
                    ImageReader.newInstance(metrics.widthPixels, metrics.heightPixels, PixelFormat.RGBA_8888, 2).getSurface(),
                    flag);
        } catch (Exception e) {
            return null;
        }
    }

    private static VirtualDisplay createPrivateVirtualDisplay(MediaProjection mediaProjection, VirtualDisplay.Callback callback, String displayName, int width, int height, int dpi, int flag) {
        return createVirtualDisplay(mediaProjection, callback, displayName, width, height, dpi, VIRTUAL_DISPLAY_FLAG_OWN_CONTENT_ONLY);
    }

    private static VirtualDisplay createPublicVirtualDisplay(MediaProjection mediaProjection, VirtualDisplay.Callback callback, String displayName, int width, int height, int dpi, int flag) {
        return createVirtualDisplay(mediaProjection, callback, displayName, width, height, dpi, VIRTUAL_DISPLAY_FLAG_PUBLIC);
    }

    private static VirtualDisplay createVirtualDisplay(MediaProjection mediaProjection, VirtualDisplay.Callback callback, String displayName, int width, int height, int dpi, int flag) {
        try {
            DisplayMetrics metrics = new DisplayMetrics();
            metrics.densityDpi = dpi;
            metrics.widthPixels = width;
            metrics.heightPixels = height;

            return mediaProjection.createVirtualDisplay(displayName,
                    metrics.widthPixels,
                    metrics.heightPixels,
                    metrics.densityDpi,
                    flag,
                    ImageReader.newInstance(metrics.widthPixels, metrics.heightPixels, PixelFormat.RGBA_8888, 2).getSurface(),
                    callback,
                    null);
        } catch (Exception e) {
            return null;
        }
    }
}
