package lib.kalu.frame.mvvm;

import android.app.Application;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;

/**
 * @author zhanghang
 * @description:
 * @date :2022-01-17
 */
public abstract class BaseActivity<K extends BaseViewModel, M extends BasePresenter> extends AppCompatActivity implements BaseView {

    private M mP = null;
    private K mVM = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindow();
        setContentView(initLayout());

        mVM = initViewModel();
        mP = initPresenter();

        initData();
    }

    @Override
    public K getViewModel() {
        if (null != mVM) {
            return mVM;
        } else {
            throw new IllegalArgumentException("mBaseViewModel is null");
        }
    }

    @Override
    public <K extends BaseViewModel> K initViewModel() {
        try {
            Class<K> clazz = (Class<K>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            Constructor constructor = clazz.getDeclaredConstructor(new Class[]{Application.class});
            constructor.setAccessible(true);
            return (K) constructor.newInstance(getApplication());
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public M getPresenter() {
        if (null != mP) {
            return mP;
        } else {
            throw new IllegalArgumentException("mBasePresenter is null");
        }
    }

    @Override
    public <M extends BasePresenter> M initPresenter() {
        try {
            Class<M> clazz = (Class<M>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
            Constructor constructor = clazz.getDeclaredConstructor(BaseView.class, BaseViewModel.class);
            constructor.setAccessible(true);
            return (M) constructor.newInstance(this, getViewModel());
        } catch (Exception e) {
//            Log.d("BaseActivity", "initViewModel => " + getClass().getName());
//            Log.d("BaseActivity", "initPresenter => " + e.getMessage(), e);
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