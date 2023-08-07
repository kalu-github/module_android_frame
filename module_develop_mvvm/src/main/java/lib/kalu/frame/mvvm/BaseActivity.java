package lib.kalu.frame.mvvm;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.MutableCreationExtras;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;

import lib.kalu.frame.mvvm.util.MvpUtil;

/**
 * @author zhanghang
 * @description:
 * @date :2022-01-17
 */
public abstract class BaseActivity<M extends BaseModel, V extends BaseView, VM extends BaseViewModel> extends FragmentActivity implements BaseView {

    private VM mVM = null;

    @Override
    public void onBackPressed() {
        try {
            mVM.onCleared();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onBackPressed();
    }

    @Override
    public void finish() {
        try {
            mVM.onCleared();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            initWindow();
            setContentView(initLayout());
            mVM = initViewModel();
            initData();
        } catch (Exception e) {
            MvpUtil.logE("BaseActivity => onCreate => " + e.getMessage());
        }
    }

    @Override
    public final VM getViewModel() {
        if (null != mVM) {
            return mVM;
        } else {
            throw new IllegalArgumentException("view-model is null");
        }
    }

    private final <VM extends BaseViewModel> VM initViewModel() {
        try {
            Class<M> clazzM = (Class<M>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            M m = clazzM.newInstance();
            Class<V> clazzV = (Class<V>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
            Class<VM> clazzVM = (Class<VM>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[2];
            Constructor constructorVM = clazzVM.getDeclaredConstructor(new Class[]{Application.class, clazzV, clazzM});
            constructorVM.setAccessible(true);
            return (VM) constructorVM.newInstance(getApplication(), this, m);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
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