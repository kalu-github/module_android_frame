package lib.kalu.frame.mvvm;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

/**
 * @author zhanghang
 * @description:
 * @date :2022-01-17
 */
public abstract class BaseActivity<M extends BaseModel, VM extends BaseViewModel> extends AppCompatActivity implements BaseView {

    private VM mVM = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindow();
        setContentView(initLayout());
        mVM = initViewModel();
        initData();
    }

    @Override
    public VM getViewModel() {
        if (null != mVM) {
            return mVM;
        } else {
            throw new IllegalArgumentException("view-model is null");
        }
    }

    @Override
    public <VM extends BaseViewModel> VM initViewModel() {
        try {

            // model
            Class<M> clazzM = (Class<M>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            M m = clazzM.newInstance();

            // view-model
            Class<VM> clazzVM = (Class<VM>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
            Constructor constructorVM = clazzVM.getDeclaredConstructor(new Class[]{Application.class, BaseView.class, clazzM});
            constructorVM.setAccessible(true);
            return (VM) constructorVM.newInstance(getApplication(), this, m);
//
//            // init model setViewModel
//            Method setViewModel = m.getClass().getDeclaredMethod("setViewModel", BaseViewModel.class);
//            setViewModel.setAccessible(true);
//            setViewModel.invoke(m, vm);

//            // init view-model setModel
//            Method setModel = vm.getClass().getDeclaredMethod("setModel", BaseModel.class);
//            setModel.setAccessible(true);
//            setModel.invoke(vm, m);
//
//            // init view-model setView
//            Method setView = vm.getClass().getDeclaredMethod("setView", BaseView.class);
//            setView.setAccessible(true);
//            setView.invoke(vm, this);

        } catch (Exception e) {
            Log.d("BaseActivity", "initViewModel => " + e.getMessage(), e);
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
}