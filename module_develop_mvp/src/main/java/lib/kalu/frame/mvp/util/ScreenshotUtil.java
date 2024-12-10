package lib.kalu.frame.mvp.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Build;
import android.os.IBinder;
import android.view.Surface;
import android.view.WindowManager;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * <p>
 * 反射教程
 * https://blog.csdn.net/weixin_45395059/article/details/126765905
 */
public class ScreenshotUtil {


    /**
     * 静默截屏，需要权限android.permission.READ_FRAME_BUFFER
     *
     * @return
     */
    public static Bitmap getReflectScreenshot(Context context) {
        try {
            Class<?> mClassType = Class.forName("android.view.SurfaceControl");
            Method nativeScreenshotMethod;
            nativeScreenshotMethod = mClassType.getDeclaredMethod("nativeScreenshot", IBinder.class, Rect.class, int.class, int.class, int.class, int.class, boolean.class, boolean.class, int.class);
            nativeScreenshotMethod.setAccessible(true);
            Method getBuiltInDisplayMethod = mClassType.getMethod("getBuiltInDisplay", int.class);
            IBinder displayToken = (IBinder) getBuiltInDisplayMethod.invoke(mClassType, 3);
//            Log.d("MainActivity", "zly --> nativeScreenshotMethod before");
            Bitmap invoke = (Bitmap) nativeScreenshotMethod.invoke(mClassType, displayToken, new Rect(), 800, 480, 0, 0, true, false, Surface.ROTATION_0);
            return invoke;
        } catch (Exception e) {
            return null;
        }
    }

    public static Bitmap getReflectScreenshot(Context context, Rect rect) {


        try {
            // android12以上
            if (Build.VERSION.SDK_INT >= 31) {

                // 1
                Class SurfaceControl = Class.forName("android.view.SurfaceControl");


                // 2. 指定displayId
                IBinder displayToken = (IBinder) SurfaceControl.getMethod("getInternalDisplayToken").invoke(null);

//                long[] physicalDisplayIds = (long[]) SurfaceControl.getMethod("getPhysicalDisplayIds").invoke(null);
//                LogUtils.i(TAG, "getScreenshot => physicalDisplayIds = " + Arrays.toString(physicalDisplayIds));
//                if (null == physicalDisplayIds || physicalDisplayIds.length == 0)
//                    throw new Exception();
//                IBinder displayToken = (IBinder) SurfaceControl.getMethod("getPhysicalDisplayToken", long.class).invoke((Object) Long.parseLong("0"));


//                long[] displayIds = (long[]) SurfaceControl.getMethod("getPhysicalDisplayIds").invoke(null);
//                LogUtils.i(TAG, "getScreenshot => displayIds = " + Arrays.toString(displayIds));

//                IBinder displayToken = (IBinder) SurfaceControl.getMethod("getBuiltInDisplay", int.class).invoke(0);
                // 2：创建SurfaceControl.DisplayCaptureArgs.Builder
                Class Builder = Class.forName("android.view.SurfaceControl$DisplayCaptureArgs$Builder");
                Constructor CBuilder = Builder.getConstructor(IBinder.class);


                Object mBuilder = CBuilder.newInstance(displayToken);

                // setSourceCrop
                Method m_setSourceCrop = mBuilder.getClass().getDeclaredMethod("setSourceCrop", Rect.class);
                m_setSourceCrop.setAccessible(true);
                m_setSourceCrop.invoke(mBuilder, rect);

                // setSize
                Method m_setSize = mBuilder.getClass().getDeclaredMethod("setSize", int.class, int.class);
                m_setSize.setAccessible(true);
                m_setSize.invoke(mBuilder, rect.width(), rect.height());

                Object mDisplayCaptureArgs = Builder.getMethod("build").invoke(mBuilder);

                Class DisplayCaptureArgs = Class.forName("android.view.SurfaceControl$DisplayCaptureArgs");
                Method captureDisplay = SurfaceControl.getMethod("captureDisplay", DisplayCaptureArgs);
                captureDisplay.setAccessible(true);
                Object mScreenshotHardwareBuffer = captureDisplay.invoke(null, mDisplayCaptureArgs);
                return (Bitmap) mScreenshotHardwareBuffer.getClass().getMethod("asBitmap").invoke(mScreenshotHardwareBuffer);
            } else {
                WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
                Class c = Class.forName("android.view.SurfaceControl");
                Method method = c.getMethod("screenshot", Rect.class, int.class, int.class, int.class);
                return (Bitmap) method.invoke(c, rect, rect.width(), rect.height(), wm.getDefaultDisplay().getRotation());
//                return (Bitmap) method.invoke(c, new Rect(), 0, 0, wm.getDefaultDisplay().getRotation());
            }

        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 静默截屏，需要权限android.permission.READ_FRAME_BUFFER
     *
     * @return
     */
    public static Bitmap getScreenshot1(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (Build.VERSION.SDK_INT >= 31) {
            //android12以上
            try {
                Class SurfaceControl = Class.forName("android.view.SurfaceControl");
                Class DisplayCaptureArgs = Class.forName("android.view.SurfaceControl$DisplayCaptureArgs");
                Class Builder = Class.forName("android.view.SurfaceControl$DisplayCaptureArgs$Builder");
                IBinder displayToken = (IBinder) SurfaceControl.getMethod("getInternalDisplayToken").invoke(null);
                Constructor CBuilder = Builder.getConstructor(IBinder.class);
                Object mBuilder = CBuilder.newInstance(displayToken);
                Object mDisplayCaptureArgs = Builder.getMethod("build").invoke(mBuilder);
                Method captureDisplay = SurfaceControl.getMethod("captureDisplay", DisplayCaptureArgs);
                captureDisplay.setAccessible(true);
                Object mScreenshotHardwareBuffer = captureDisplay.invoke(null, mDisplayCaptureArgs);
                return (Bitmap) mScreenshotHardwareBuffer.getClass().getMethod("asBitmap").invoke(mScreenshotHardwareBuffer);
            } catch (Throwable e) {
            }
        } else {
            try {
                Class c = Class.forName("android.view.SurfaceControl");
                Method method = c.getMethod("screenshot", Rect.class, int.class, int.class, int.class);
                return (Bitmap) method.invoke(c, new Rect(), 0, 0, wm.getDefaultDisplay().getRotation());
            } catch (Throwable e) {
            }
        }
        return null;
    }
}