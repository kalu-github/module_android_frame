package lib.kalu.frame.mvp;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
public abstract class BaseFragment<V extends BaseView, P extends BasePresenter> extends Fragment implements BaseView {

    private P mP = null;

    @Override
    public void onDestroyView() {
        try {
            mP.cleanDisposable();
        } catch (Exception e) {
            MvpUtil.logE("BaseFragment => onDestroyView => " + e.getMessage());
        }
        hideLoading();
        super.onDestroyView();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            getWindow().setFormat(PixelFormat.TRANSLUCENT);
        } catch (Exception e) {
        }
        try {
            initWindow();
            mP = initPresenter();
        } catch (Exception e) {
            MvpUtil.logE("BaseFragment => onCreate => " + e.getMessage());
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        ViewGroup viewGroup = (ViewGroup) container.getParent();
//        if (null != viewGroup) {
//            viewGroup.removeAllViews();
//        }
        View inflate = inflater.inflate(initLayout(), container, false);
        return inflate;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    @Override
    public final P getPresenter() {
        try {
            if (null == mP)
                throw new IllegalArgumentException("mP error: null");
            return mP;
        } catch (Exception e) {
            MvpUtil.logE("BaseFragment => getPresenter => " + e.getMessage());
            return null;
        }
    }

    private P initPresenter() throws Exception {
        try {
            Class<V> clazzV = (Class<V>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            Class<P> clazzP = (Class<P>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
            Constructor constructorP = clazzP.getDeclaredConstructor(new Class[]{clazzV});
            constructorP.setAccessible(true);
            return (P) constructorP.newInstance(this);
        } catch (Exception e) {
            MvpUtil.logE("BaseFragment => getPresenter => " + e.getMessage());
            try {
                throw e;
            } catch (NoSuchMethodException ex) {
                throw new RuntimeException(ex);
            } catch (IllegalAccessException ex) {
                throw new RuntimeException(ex);
            } catch (java.lang.InstantiationException ex) {
                throw new RuntimeException(ex);
            } catch (InvocationTargetException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        try {
            hideLoading();
        } catch (Exception e) {
        }
    }

    @NonNull
    @Override
    public CreationExtras getDefaultViewModelCreationExtras() {
        try {
            return super.getDefaultViewModelCreationExtras();
        } catch (Exception e) {
            MvpUtil.logE("BaseFragment => getDefaultViewModelCreationExtras => " + e.getMessage());
            return new MutableCreationExtras();
        }
    }
}
