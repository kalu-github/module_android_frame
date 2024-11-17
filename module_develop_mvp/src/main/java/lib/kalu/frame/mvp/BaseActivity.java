package lib.kalu.frame.mvp;

import android.graphics.PixelFormat;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.MutableCreationExtras;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;

import lib.kalu.frame.mvp.util.MvpUtil;

/**
 * @author zhanghang
 * @description:
 * @date :2022-01-17
 */
public abstract class BaseActivity<V extends BaseView, P extends BasePresenter> extends FragmentActivity implements BaseView {

    private P mP = null;

    @Override
    public void onBackPressed() {
        try {
            mP.cleanDisposable();
        } catch (Exception e) {
            MvpUtil.logE("BaseActivity => onBackPressed => " + e.getMessage());
        }
        super.onBackPressed();
    }

    @Override
    public void finish() {
        try {
            mP.cleanDisposable();
        } catch (Exception e) {
            MvpUtil.logE("BaseActivity => finish => " + e.getMessage());
        }
        super.finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            getWindow().setFormat(PixelFormat.TRANSLUCENT);
        } catch (Exception e) {
        }
        try {
            initWindow();
            setContentView(initLayout());
            mP = initPresenter();
            initData();
        } catch (Exception e) {
            MvpUtil.logE("BaseActivity => onCreate => " + e.getMessage());
        }
    }

    @Override
    public final P getPresenter() {
        try {
            if (null == mP)
                throw new IllegalArgumentException("mP error: null");
            return mP;
        } catch (Exception e) {
            MvpUtil.logE("BaseActivity => getPresenter => " + e.getMessage());
            return null;
        }
    }

    private P initPresenter() {
        try {
            Class<V> clazzV = (Class<V>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            Class<P> clazzP = (Class<P>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
            Constructor constructorP = clazzP.getDeclaredConstructor(new Class[]{clazzV});
            constructorP.setAccessible(true);
            return (P) constructorP.newInstance(this);
        } catch (Exception e) {
            MvpUtil.logE("BaseActivity => initPresenter => " + e.getMessage());
            return null;
        }
    }

    @Override
    public void initWindow() {
//        Window window = getWindow();
//        WindowManager.LayoutParams params = window.getAttributes();
//        params.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE;
//        window.setAttributes(params);
//        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
//        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    }

    @NonNull
    @Override
    public CreationExtras getDefaultViewModelCreationExtras() {
        try {
            return super.getDefaultViewModelCreationExtras();
        } catch (Exception e) {
            MvpUtil.logE("BaseActivity => getDefaultViewModelCreationExtras => " + e.getMessage());
            return new MutableCreationExtras();
        }
    }
}